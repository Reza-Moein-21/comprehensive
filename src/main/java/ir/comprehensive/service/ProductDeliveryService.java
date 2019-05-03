package ir.comprehensive.service;

import ir.comprehensive.domain.Product;
import ir.comprehensive.domain.ProductDelivery;
import ir.comprehensive.mapper.ProductDeliveryMapper;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.repository.PersonRepository;
import ir.comprehensive.repository.ProductDeliveryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static ir.comprehensive.utils.MessageUtils.getMessage;

@Service
@Transactional
public class ProductDeliveryService extends CallbackMessage<ProductDelivery> {
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
        List<ProductDeliveryModel> collect = all.stream().map(mapper::entityToDto).collect(Collectors.toList());
        return FXCollections.observableList(collect);
    }

    public CallbackMessage save(ProductDeliveryModel model) {
        ProductDelivery productDelivery = new ProductDelivery();
        Product product = new Product();
        productDelivery.setPerson(personRepository.getOne(model.getPerson().getId()));
        product.setName(model.getProductName());
        productDelivery.setProduct(product);
        productDelivery.setDeliveryDate(model.getDeliveryDate());
        productDelivery.setDesiredDate(model.getDesiredDate());
        productDelivery.setDescription(model.getDescription());

        setCallbackMessage(getMessage("product") + " " + getMessage("successSave"));
        setCallbackResult(repository.save(productDelivery));
        return this;
    }
}
