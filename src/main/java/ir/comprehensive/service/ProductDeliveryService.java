package ir.comprehensive.service;

import ir.comprehensive.domain.ProductDelivery;
import ir.comprehensive.domain.ProductStatus;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.repository.ProductDeliveryRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.service.extra.Swappable;
import ir.comprehensive.utils.MessageUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductDeliveryService implements Swappable<ProductDelivery> {
    private ProductDeliveryRepository repository;
    private WarehouseService warehouseService;

    public ProductDeliveryService(ProductDeliveryRepository repository, WarehouseService warehouseService) {
        this.repository = repository;
        this.warehouseService = warehouseService;
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
        Specification<ProductDelivery> productDeliverySpecification = (root, query, criteriaBuilder) -> {
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

        return Optional.of(repository.findAll(productDeliverySpecification));
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
}
