package ir.comprehensive.service;

import ir.comprehensive.entity.ProductStatus;
import ir.comprehensive.entity.Warehouse;
import ir.comprehensive.entity.WarehouseTag;
import ir.comprehensive.mapper.WarehouseMapper;
import ir.comprehensive.mapper.WarehouseReportMapper;
import ir.comprehensive.fxmodel.WarehouseInfo;
import ir.comprehensive.fxmodel.WarehouseModel;
import ir.comprehensive.fxmodel.WarehouseReportBean;
import ir.comprehensive.fxmodel.WarehouseTagModel;
import ir.comprehensive.fxmodel.basemodel.BaseReportBean;
import ir.comprehensive.repository.ProductDeliveryRepository;
import ir.comprehensive.repository.WarehouseRepository;
import ir.comprehensive.repository.WarehouseTagRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class WarehouseService implements BaseService<Warehouse, WarehouseModel> {
    private WarehouseRepository repository;
    private WarehouseMapper mapper;
    private ProductDeliveryRepository productDeliveryRepository;
    private WarehouseTagRepository warehouseTagRepository;
    private final WarehouseReportMapper warehouseReportMapper;

    public WarehouseService(WarehouseRepository repository, WarehouseMapper mapper, ProductDeliveryRepository productDeliveryRepository, WarehouseTagRepository warehouseTagRepository, WarehouseReportMapper warehouseReportMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.productDeliveryRepository = productDeliveryRepository;
        this.warehouseTagRepository = warehouseTagRepository;
        this.warehouseReportMapper = warehouseReportMapper;
    }

    public Optional<Warehouse> load(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("null id");
        }
        return repository.findById(id);
    }

    public Optional<List<Warehouse>> findByName(String name) {
        Page<Warehouse> warehouses = repository.findByName(name, PageRequest.of(0, 10));
        return Optional.of(warehouses.getContent());
    }

    public Optional<List<Warehouse>> loadAll() {
        return Optional.of(repository.findAll(Sort.by(Sort.Order.asc("category.title"))));
    }


    public Optional<List<Warehouse>> search(WarehouseModel searchExample) {
        Specification<Warehouse> warehouseSpecification = getWarehouseSpecification(searchExample);

        return Optional.of(repository.findAll(warehouseSpecification).stream().distinct().collect(Collectors.toList()));
    }

    private Specification<Warehouse> getWarehouseSpecification(WarehouseModel searchExample) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getTitle() != null && !searchExample.getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), StringUtils.makeAnyMatch(searchExample.getTitle())));
            }
            if (searchExample.getCode() != null && !searchExample.getCode().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), StringUtils.makeAnyMatch(searchExample.getCode())));
            }
            if (searchExample.getCompanyName() != null && !searchExample.getCompanyName().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("companyName")), StringUtils.makeAnyMatch(searchExample.getCompanyName())));
            }
            if (searchExample.getCategory() != null && searchExample.getCategory().getId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("category").get("id"), searchExample.getCategory().getId()));
            }
            if (searchExample.getTagList() != null && !searchExample.getTagList().isEmpty()) {
                List<Long> allWarehouseByTag = new ArrayList<>();

                searchExample
                        .getTagList()
                        .stream()
                        .map(WarehouseTagModel::getId)
                        .map(tagId -> repository.warehouseByTag(tagId))
                        .map(bigIntegers -> bigIntegers
                                .stream()
                                .map(BigInteger::longValue)
                                .collect(Collectors.toList()))
                        .forEach(allWarehouseByTag::addAll);


                // inner join on allWarehouseByTag
                Set<Long> result = new HashSet<>();
                for (Long aLong : allWarehouseByTag) {
                    int countOfMatch = 0;
                    for (Long aLong1 : allWarehouseByTag) {
                        if (aLong.equals(aLong1)) {
                            countOfMatch++;
                        }
                    }
                    if (countOfMatch == searchExample.getTagList().size()) {
                        result.add(aLong);
                    }
                }

                predicateList.add(root.get("id").in(result));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }

    private void validateEntity(Warehouse warehouse) throws GeneralException {
        if (warehouse == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
    }

    public Optional<Warehouse> save(Warehouse warehouse) throws GeneralException {
        validateEntity(warehouse);
        warehouse.setId(null);
        return Optional.of(repository.save(warehouse));
    }

    public Optional<Warehouse> update(Warehouse warehouse) throws GeneralException {
        validateEntity(warehouse);
        if (warehouse.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }

        // TODO must fix message
        Warehouse loadedWarehouse = repository.findById(warehouse.getId()).orElseThrow(() -> new GeneralException("not found"));

        return Optional.of(repository.save(swap(warehouse, loadedWarehouse)));

    }

    public Optional<Warehouse> saveOrUpdate(Warehouse warehouse) throws GeneralException {
        List<WarehouseTag> newTagListForSave = new ArrayList<>();

        if (warehouse.getTagList() != null) {
            for (WarehouseTag warehouseTag : warehouse.getTagList()) {
                Optional<WarehouseTag> byTitleExact = warehouseTagRepository.findByTitleExact(warehouseTag.getTitle());
                if (byTitleExact.isPresent()) {
                    newTagListForSave.add(byTitleExact.get());
                } else if (warehouseTag.getId() != null) {
                    newTagListForSave.add(warehouseTagRepository.findById(warehouseTag.getId()).orElse(null));
                } else {
                    newTagListForSave.add(warehouseTag);
                }
            }
        }

        warehouse.setTagList(newTagListForSave);

        return warehouse.getId() == null ? save(warehouse) : update(warehouse);
    }


    public Optional<Long> delete(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("not null id");
        }
        if (productDeliveryRepository.isWarehouseExist(id)) {
            throw new GeneralException(MessageUtils.Message.PRODUCT + " " + MessageUtils.Message.USE_IN + " " + MessageUtils.Message.STOREROOM);
        }

        repository.deleteById(id);

        return Optional.of(id);
    }

    public void reduceCount(Long id, Long count) {
        Warehouse warehouse = repository.findById(id).orElseThrow(RuntimeException::new);
        Long currentCount = warehouse.getCount();
        warehouse.setCount(currentCount - count);
        repository.save(warehouse);
    }

    public void increaseCount(Long id, Long count) {
        Warehouse warehouse = repository.findById(id).orElseThrow(RuntimeException::new);
        Long currentCount = warehouse.getCount();
        warehouse.setCount(currentCount + count);
        repository.save(warehouse);
    }

    public WarehouseInfo getInfo() {
        WarehouseInfo info = new WarehouseInfo();
        info.setTotalWarehouse(this.getNumberString(repository.totalCount()));
        info.setLostCount(this.getNumberString(productDeliveryRepository.countByStatus(ProductStatus.LOST)));
        info.setReceivedCount(this.getNumberString(productDeliveryRepository.countByStatus(ProductStatus.RECEIVED)));
        info.setRejectedCount(this.getNumberString(productDeliveryRepository.countByStatus(ProductStatus.REJECTED)));
        info.setUnknownCount(this.getNumberString(productDeliveryRepository.countByStatus(ProductStatus.UNKNOWN)));

        return info;
    }

    @Override
    public Page<WarehouseModel> loadItem(WarehouseModel searchModel, PageRequest pageRequest) {
        Page<Warehouse> page;
        if (searchModel == null) {
            page = repository.findAll(pageRequest);
        } else {
            page = repository.findAll(getWarehouseSpecification(searchModel), pageRequest);
        }
        return page.map(mapper::entityToModel);
    }

    public List<WarehouseReportBean> getReportBeanList(WarehouseModel searchModel) throws GeneralException {
        return getReportBeanList(searchModel, null);
    }

    public List<WarehouseReportBean> getReportBeanList(WarehouseModel searchModel, Set<Long> ids) throws GeneralException {
        Function<Warehouse, WarehouseReportBean> customReportMapper = warehouse -> {
            WarehouseReportBean reportBean = warehouseReportMapper.entityToModel(warehouse);
            reportBean.setTagList(warehouse.getTagList() == null ? "" : warehouse.getTagList().stream().map(tagModel -> String.format("[ %s ] ", tagModel.getTitle())).collect(Collectors.joining()));
            return reportBean;
        };
        if (ids != null && !ids.isEmpty()) {
            List<WarehouseReportBean> warehouseReportBeans = repository.findAllById(ids).stream().map(customReportMapper).collect(Collectors.toList());
            return BaseReportBean.fillRowNumber(warehouseReportBeans);
        }

        List<WarehouseReportBean> warehouseReportBeans = repository.findAll(getWarehouseSpecification(searchModel)).stream().map(customReportMapper).collect(Collectors.toList());
        return BaseReportBean.fillRowNumber(warehouseReportBeans);
    }
}
