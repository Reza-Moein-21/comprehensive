package ir.comprehensive.service;

import ir.comprehensive.domain.ProductDelivery;
import ir.comprehensive.domain.ProductStatus;
import ir.comprehensive.mapper.ProductDeliveryMapper;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.repository.ProductDeliveryRepository;
import ir.comprehensive.service.response.RequestCallback;
import ir.comprehensive.service.response.ResponseStatus;
import ir.comprehensive.utils.MessageUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        example.setStatus(ProductStatus.NEW_PRODUCT);
        List<ProductDelivery> all = repository.findAll(Example.of(example));
        List<ProductDeliveryModel> collect = all.stream().map(mapper::entityToModel).collect(Collectors.toList());
        return FXCollections.observableList(collect);
    }

    public void search(ProductDeliveryModel searchExample, RequestCallback<ObservableList<ProductDeliveryModel>> callback) {
        ProductDelivery productDelivery = mapper.modelToEntity(searchExample);
        List<ProductDeliveryModel> allModel = repository.findAll(Example.of(productDelivery)).stream().map(mapper::entityToModel).collect(Collectors.toList());
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

            productDelivery.setStatus(ProductStatus.NEW_PRODUCT);
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
            if (productDelivery.getStatus() == ProductStatus.RECEIVED) {
                loadedProductDelivery.setReceivedDate(LocalDate.now());
            }
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
