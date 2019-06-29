package ir.comprehensive.service;

import ir.comprehensive.domain.ProductDelivery;
import ir.comprehensive.domain.ProductStatus;
import ir.comprehensive.mapper.ProductDeliveryMapper;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.repository.ProductDeliveryRepository;
import ir.comprehensive.service.response.RequestCallback;
import ir.comprehensive.service.response.ResponseStatus;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductDeliveryService {
    private ProductDeliveryRepository repository;
    private ProductDeliveryMapper mapper;


    public ProductDeliveryService(ProductDeliveryRepository repository, ProductDeliveryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ObservableList<ProductDeliveryModel> getAllModel() {
        ProductDelivery example = new ProductDelivery();
        example.setStatus(ProductStatus.UNKNOWN);
        List<ProductDelivery> all = repository.findAll(Example.of(example));
        List<ProductDeliveryModel> collect = all.stream().map(mapper::entityToModel).collect(Collectors.toList());
        return FXCollections.observableList(collect);
    }

    public void search(ProductDeliveryModel searchExample, RequestCallback<ObservableList<ProductDeliveryModel>> callback) {
        Specification<ProductDelivery> productDeliverySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getPerson() != null && searchExample.getPerson().getId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("person").get("id"), searchExample.getPerson().getId()));
            }
            if (searchExample.getProduct() != null && searchExample.getProduct().getTitle() != null && !searchExample.getProduct().getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("product").get("title"), StringUtils.makeAnyMatch(searchExample.getProduct().getTitle())));
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

        List<ProductDeliveryModel> allModel = repository.findAll(productDeliverySpecification).stream().map(mapper::entityToModel).collect(Collectors.toList());
        callback.accept(FXCollections.observableList(allModel), MessageUtils.Message.SUCCESS_LOAD, ResponseStatus.SUCCESS);
    }

    public void saveOrUpdate(ProductDeliveryModel model, RequestCallback<ProductDelivery> callback) {
        // Convert to entity
        ProductDelivery productDelivery = mapper.modelToEntity(model);

        // null point check after convert
        if (productDelivery == null) {
            callback.accept(null, MessageUtils.Message.ERROR_IN_SAVE, ResponseStatus.FAIL);
            return;
        }

        // apply save
        if (null == productDelivery.getId()) {
            String callbackMessage = MessageUtils.Message.PRODUCT + " " + MessageUtils.Message.SUCCESS_SAVE;

            productDelivery.setStatus(ProductStatus.UNKNOWN);
            productDelivery.setReceivedDate(null);

            callback.accept(repository.save(productDelivery), callbackMessage, ResponseStatus.SUCCESS);
            return;
        }

        // apply update

        ProductDelivery loadedProductDelivery = repository.findById(productDelivery.getId()).orElse(null);
        if (loadedProductDelivery == null) {
            callback.accept(null, MessageUtils.Message.ERROR_IN_SAVE, ResponseStatus.FAIL);
        } else {
            loadedProductDelivery.setId(productDelivery.getId());
            loadedProductDelivery.setPerson(productDelivery.getPerson());
            loadedProductDelivery.setProduct(productDelivery.getProduct());
            loadedProductDelivery.setDescription(productDelivery.getDescription());
            loadedProductDelivery.setDeliveryDate(productDelivery.getDeliveryDate());
            loadedProductDelivery.setDesiredDate(productDelivery.getDesiredDate());
            loadedProductDelivery.setStatus(productDelivery.getStatus());
            loadedProductDelivery.setReceivedDate(productDelivery.getStatus().equals(ProductStatus.RECEIVED) ? productDelivery.getReceivedDate() : null);
            String callbackMessage = MessageUtils.Message.PRODUCT + " " + MessageUtils.Message.SUCCESS_UPDATE;
            callback.accept(repository.save(loadedProductDelivery), callbackMessage, ResponseStatus.SUCCESS);

        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ProductDeliveryModel load(Long id) {
        return repository.findById(id).map(mapper::entityToModel).orElse(null);
    }
}
