package ir.comprehensive.service;

import ir.comprehensive.domain.Product;
import ir.comprehensive.domain.ProductDelivery;
import ir.comprehensive.mapper.ProductDeliveryMapper;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.repository.PersonRepository;
import ir.comprehensive.repository.ProductDeliveryRepository;
import ir.comprehensive.service.response.RequestCallback;
import ir.comprehensive.service.response.ResponseStatus;
import ir.comprehensive.utils.MessageUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductDeliveryService {
    private PersonRepository personRepository;
    private ProductDeliveryRepository repository;
    private ProductDeliveryMapper mapper;


    public ProductDeliveryService(ProductDeliveryRepository repository, ProductDeliveryMapper mapper, PersonRepository personRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.personRepository = personRepository;
    }

    public ObservableList<ProductDeliveryModel> getAllModel() {

        List<ProductDelivery> all = repository.findAll();
        List<ProductDeliveryModel> collect = all.stream().map(mapper::entityToModel).collect(Collectors.toList());
        return FXCollections.observableList(collect);
    }

    public void save(ProductDeliveryModel model, RequestCallback<ProductDelivery> callback) {
        ProductDelivery productDelivery = new ProductDelivery();
        Product product = new Product();
        productDelivery.setPerson(personRepository.getOne(model.getPerson().getId()));
        product.setName(model.getProductName());
        productDelivery.setProduct(product);
        productDelivery.setDeliveryDate(model.getDeliveryDate());
        productDelivery.setDesiredDate(model.getDesiredDate());
        productDelivery.setDescription(model.getDescription());

        String callbackMessage = MessageUtils.Message.PRODUCT + " " + MessageUtils.Message.SUCCESS_SAVE;
        callback.accept(repository.save(productDelivery), callbackMessage, ResponseStatus.SUCCESS);
    }
}
