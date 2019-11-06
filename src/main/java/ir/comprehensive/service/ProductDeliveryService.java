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


    public ProductDeliveryService(ProductDeliveryRepository repository) {
        this.repository = repository;
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

        return Optional.of(repository.save(swap(productDelivery, loadedProductDelivery)));

    }

    public Optional<ProductDelivery> saveOrUpdate(ProductDelivery productDelivery) throws GeneralException {
        return productDelivery.getId() == null ? save(productDelivery) : update(productDelivery);
    }


    public Optional<Long> delete(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("not null id");
        }

        repository.deleteById(id);
        return Optional.of(id);
    }
}
