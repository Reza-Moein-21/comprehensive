package ir.comprehensive.service;

import ir.comprehensive.entity.ProductDelivery;
import ir.comprehensive.entity.ProductStatus;
import ir.comprehensive.entity.Warehouse;
import ir.comprehensive.mapper.ProductDeliveryDetailReportMapper;
import ir.comprehensive.mapper.ProductDeliveryMapper;
import ir.comprehensive.fxmodel.ProductDeliveryModel;
import ir.comprehensive.fxmodel.ProductDeliveryReportBean;
import ir.comprehensive.fxmodel.basemodel.BaseReportBean;
import ir.comprehensive.repository.ProductDeliveryRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.MessageUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductDeliveryService implements BaseService<ProductDelivery, ProductDeliveryModel> {
    private ProductDeliveryRepository repository;
    private WarehouseService warehouseService;
    private PersonService personService;
    private ProductDeliveryMapper mapper;
    private ProductDeliveryDetailReportMapper productDeliveryDetailReportMapper;

    public ProductDeliveryService(ProductDeliveryRepository repository, WarehouseService warehouseService, PersonService personService, ProductDeliveryMapper mapper, ProductDeliveryDetailReportMapper productDeliveryDetailReportMapper) {
        this.repository = repository;
        this.warehouseService = warehouseService;
        this.personService = personService;
        this.mapper = mapper;
        this.productDeliveryDetailReportMapper = productDeliveryDetailReportMapper;
    }

    public Optional<ProductDelivery> load(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("null id");
        }
        return repository.findById(id);
    }

    public Optional<List<ProductDelivery>> loadAll() {
        return Optional.of(repository.findAll());
    }

    public Optional<List<ProductDelivery>> loadByStatus(ProductStatus status) {
        ProductDelivery example = new ProductDelivery();
        example.setStatus(status);
        return Optional.of(repository.findAll(Example.of(example)));
    }

    public Optional<List<ProductDelivery>> search(ProductDeliveryModel searchExample) {
        Specification<ProductDelivery> productDeliverySpecification = getProductDeliverySpecification(searchExample);

        return Optional.of(repository.findAll(productDeliverySpecification));
    }

    private Specification<ProductDelivery> getProductDeliverySpecification(ProductDeliveryModel searchExample) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getPerson() != null && searchExample.getPerson().getId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("person").get("id"), searchExample.getPerson().getId()));
            }
            if (searchExample.getProduct() != null && searchExample.getProduct().getId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("product").get("id"), searchExample.getProduct().getId()));
            }
            if (searchExample.getDeliveryDateFrom() != null) {
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("deliveryDate"), searchExample.getDeliveryDateFrom()));
            }
            if (searchExample.getDeliveryDateTo() != null) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("deliveryDate"), searchExample.getDeliveryDateTo()));
            }
            if (searchExample.getStatus() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("status"), searchExample.getStatus()));
            }
            if (searchExample.getStatus() != null && searchExample.getStatus().equals(ProductStatus.RECEIVED)) {
                if (searchExample.getReceivedDateFrom() != null) {
                    predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("receivedDate"), searchExample.getReceivedDateFrom()));
                }
                if (searchExample.getReceivedDateTo() != null) {
                    predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("receivedDate"), searchExample.getReceivedDateTo()));
                }
            }


            query.orderBy(criteriaBuilder.asc(root.get("deliveryDate")));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }

    private void validateEntity(ProductDelivery productDelivery) throws GeneralException {
        if (productDelivery == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
    }

    public Optional<ProductDelivery> save(ProductDelivery productDelivery) throws GeneralException {
        validateEntity(productDelivery);
        productDelivery.setId(null);
        productDelivery.setStatus(ProductStatus.UNKNOWN);
        productDelivery.setReceivedDate(null);

        // reduce count of warehouse
        warehouseService.reduceCount(productDelivery.getProduct().getId(), productDelivery.getCount());

        return Optional.of(repository.save(productDelivery));
    }

    public Optional<ProductDelivery> update(ProductDelivery productDelivery) throws GeneralException {
        validateEntity(productDelivery);
        if (productDelivery.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }

        productDelivery.setReceivedDate(productDelivery.getStatus().equals(ProductStatus.RECEIVED) ? productDelivery.getReceivedDate() : null);

        // TODO must fix message
        ProductDelivery loadedProductDelivery = repository.findById(productDelivery.getId()).orElseThrow(() -> new GeneralException("not found"));

        ProductStatus currentStatus = loadedProductDelivery.getStatus();
        ProductStatus newStatus = productDelivery.getStatus();

        Long currentProductId = loadedProductDelivery.getProduct().getId();
        Long newProductId = productDelivery.getProduct().getId();

        Long currentCount = loadedProductDelivery.getCount();
        Long newCount = productDelivery.getCount();


        Optional<ProductDelivery> save = Optional.of(repository.save(swap(productDelivery, loadedProductDelivery)));


        if (currentStatus.equals(ProductStatus.UNKNOWN) && newStatus.equals(ProductStatus.UNKNOWN)) {
            state1(currentProductId, newProductId, currentCount, newCount);
        } else if (currentStatus.equals(ProductStatus.UNKNOWN) && newStatus.equals(ProductStatus.RECEIVED)) {
            warehouseService.increaseCount(newProductId, currentCount);
        } else if (currentStatus.equals(ProductStatus.RECEIVED) && newStatus.equals(ProductStatus.RECEIVED)) {
            // nothing
        } else if (currentStatus.equals(ProductStatus.RECEIVED) && newStatus.equals(ProductStatus.UNKNOWN)) {
            warehouseService.reduceCount(currentProductId, currentCount);
            state1(currentProductId, newProductId, currentCount, newCount);
        } else if (currentStatus.equals(ProductStatus.UNKNOWN) && lostOrRejected(newStatus)) {
            // nothing
        } else if (lostOrRejected(currentStatus) && lostOrRejected(newStatus)) {
            // nothing
        } else if (lostOrRejected(currentStatus) && newStatus.equals(ProductStatus.UNKNOWN)) {
            state1(currentProductId, newProductId, currentCount, newCount);
        } else if (lostOrRejected(currentStatus) && newStatus.equals(ProductStatus.RECEIVED)) {
            warehouseService.increaseCount(currentProductId, currentCount);
        } else if (currentStatus.equals(ProductStatus.RECEIVED) && lostOrRejected(newStatus)) {
            warehouseService.reduceCount(currentProductId, currentCount);
        }

        return save;
    }

    private void state1(Long currentProductId, Long newProductId, Long currentCount, Long newCount) {
        // when product change, increase count of current product and reduce new product count
        if (!currentProductId.equals(newProductId)) {
            warehouseService.increaseCount(currentProductId, currentCount);
            warehouseService.reduceCount(newProductId, currentCount);
        }
        // update new product count by resolution of productDelivery current count and new count
        warehouseService.increaseCount(newProductId, currentCount - newCount);
    }

    private boolean lostOrRejected(ProductStatus newStatus) {
        return newStatus.equals(ProductStatus.REJECTED) || newStatus.equals(ProductStatus.LOST);
    }


    private boolean inSameStatus(ProductStatus currentStatus, ProductStatus newStatus) {
        return newStatus.equals(currentStatus);
    }

    private boolean isStatusChange(ProductStatus currentStatus, ProductStatus newStatus) {
        return !newStatus.equals(currentStatus);
    }

    public Optional<ProductDelivery> saveOrUpdate(ProductDelivery productDelivery) throws GeneralException {
        return productDelivery.getId() == null ? save(productDelivery) : update(productDelivery);
    }


    public Optional<Long> delete(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("not null id");
        }
        // back count to warehouse
        ProductDelivery productDelivery = repository.findById(id).orElseThrow(() -> new GeneralException("Id not found"));
        if (!productDelivery.getStatus().equals(ProductStatus.RECEIVED)) {
            warehouseService.increaseCount(productDelivery.getProduct().getId(), productDelivery.getCount());
        }
        repository.deleteById(id);
        return Optional.of(id);
    }

    @Override
    public Page<ProductDeliveryModel> loadItem(ProductDeliveryModel searchModel, PageRequest pageRequest) {
        Page<ProductDelivery> page;
        if (searchModel == null) {
            page = repository.findAll(pageRequest);
        } else {
            page = repository.findAll(getProductDeliverySpecification(searchModel), pageRequest);
        }
        return page.map(mapper::entityToModel);
    }

    public ProductDeliveryReportBean getProductReport(ProductDeliveryModel searchModel) throws GeneralException {
        return getProductReport(searchModel, null);
    }

    public ProductDeliveryReportBean getProductReport(ProductDeliveryModel searchModel, Set<Long> ids) throws GeneralException {
        if (searchModel == null || searchModel.getProduct() == null) {
            throw new GeneralException("Product not found");
        }
        Map<String, Object> params = new HashMap<>();
        ProductDeliveryReportBean bean = new ProductDeliveryReportBean();

        Optional<Warehouse> warehouseOptional = warehouseService.load(searchModel.getProduct().getId());
        warehouseOptional.ifPresent(warehouse -> {
            params.put("productName", warehouse.getTitle());
            params.put("productCode", warehouse.getCode());
            params.put("category", warehouse.getCategory() == null ? "" : warehouse.getCategory().getTitle());
            params.put("productDescription", warehouse.getTagList() == null ? "" : warehouse.getTagList().stream().map(tagModel -> String.format("[ %s ] ", tagModel.getTitle())).collect(Collectors.joining()));
            Long availableCount = warehouse.getCount();
            Long consumptionCount = repository.consumptionCountForPrint(warehouse.getId()) == null ? 0L :  repository.consumptionCountForPrint(warehouse.getId());
            params.put("availableCount", availableCount.toString());
            params.put("consumptionCount", consumptionCount.toString());
            params.put("totalCount", String.valueOf(availableCount + consumptionCount));
        });

        bean.setParams(params);

        if (ids != null && !ids.isEmpty()) {
            List<ProductDeliveryReportBean.ProductDeliveryDetailReport> productDetailReportBeans = repository.findAllById(ids).stream().map(productDeliveryDetailReportMapper::entityToModel).collect(Collectors.toList());
            bean.setTableDetail(BaseReportBean.fillRowNumber(productDetailReportBeans));
            return bean;
        }

        List<ProductDeliveryReportBean.ProductDeliveryDetailReport> productDetailReportBeans = repository.findAll(getProductDeliverySpecification(searchModel)).stream().map(productDeliveryDetailReportMapper::entityToModel).collect(Collectors.toList());
        bean.setTableDetail(BaseReportBean.fillRowNumber(productDetailReportBeans));
        return bean;
    }

    public ProductDeliveryReportBean getPersonReport(ProductDeliveryModel searchModel) throws GeneralException {
        return getPersonReport(searchModel, null);
    }

    public ProductDeliveryReportBean getPersonReport(ProductDeliveryModel searchModel, Set<Long> ids) throws GeneralException {
        if (searchModel == null || searchModel.getPerson() == null) {
            throw new GeneralException("Person not found");
        }

        Map<String, Object> params = new HashMap<>();
        ProductDeliveryReportBean bean = new ProductDeliveryReportBean();
        Long personId = searchModel.getPerson().getId();
        String fullName = personService.load(personId).map(p -> p.getFirstName() + " " + p.getLastName()).orElse("");
        params.put("personName", fullName);
        params.put("unknownCount", String.valueOf(repository.countByStatusAndPersonId(ProductStatus.UNKNOWN, personId)));
        params.put("lostCount", String.valueOf(repository.countByStatusAndPersonId(ProductStatus.LOST, personId)));
        params.put("rejectedCount", String.valueOf(repository.countByStatusAndPersonId(ProductStatus.REJECTED, personId)));
        params.put("receivedCount", String.valueOf(repository.countByStatusAndPersonId(ProductStatus.RECEIVED, personId)));

        bean.setParams(params);

        if (ids != null && !ids.isEmpty()) {
            List<ProductDeliveryReportBean.ProductDeliveryDetailReport> productDetailReportBeans = repository.findAllById(ids).stream().map(productDeliveryDetailReportMapper::entityToModel).collect(Collectors.toList());
            List<ProductDeliveryReportBean.ProductDeliveryDetailReport> productDeliveryDetailReports = BaseReportBean.fillRowNumber(productDetailReportBeans);
            productDeliveryDetailReports.sort(Comparator.comparing(ProductDeliveryReportBean.ProductDeliveryDetailReport::getCategory));
            bean.setTableDetail(productDeliveryDetailReports);
            return bean;
        }

        List<ProductDeliveryReportBean.ProductDeliveryDetailReport> productDetailReportBeans = repository.findAll(getProductDeliverySpecification(searchModel)).stream().map(productDeliveryDetailReportMapper::entityToModel).collect(Collectors.toList());
        List<ProductDeliveryReportBean.ProductDeliveryDetailReport> productDeliveryDetailReports = BaseReportBean.fillRowNumber(productDetailReportBeans);
        productDeliveryDetailReports.sort(Comparator.comparing(ProductDeliveryReportBean.ProductDeliveryDetailReport::getCategory));
        bean.setTableDetail(productDeliveryDetailReports);
        return bean;
    }


}
