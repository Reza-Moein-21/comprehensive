package ir.comprehensive.service;

import ir.comprehensive.domain.Person;
import ir.comprehensive.domain.Product;
import ir.comprehensive.domain.ProductDelivery;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.repository.ProductDeliveryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductDeliveryService {
    ProductDeliveryRepository repository;
    EntityManagerFactory entityManagerFactory;

    public ProductDeliveryService(ProductDeliveryRepository repository, EntityManagerFactory entityManagerFactory) {
        this.repository = repository;
        this.entityManagerFactory = entityManagerFactory;
    }

    public ObservableList<ProductDeliveryModel> getAllModel() {
        List<ProductDelivery> all = repository.findAll();
        ObservableList<ProductDeliveryModel> productDeliveryModels = FXCollections.observableArrayList();

        all.stream().map(this::convertEntityToModel).forEach(productDeliveryModels::add);

        return productDeliveryModels;
    }

    public ObservableList<ProductDeliveryModel> search(ProductDeliveryModel model) {
        ProductDelivery example = new ProductDelivery();

        if (model.getPersonName() != null && !model.getPersonName().isEmpty()) {
            Person person = new Person();
            person.setFullName(model.getPersonName());
            example.setPerson(person);
        }

        if (model.getProductName() != null && model.getProductName().isEmpty()) {
            Product product = new Product();
            product.setName(model.getProductName());
            example.setProduct(product);
        }

        if (model.getDeliveryDate() != null) {

            example.setDeliveryDate(toDate(model.getDeliveryDate()));
        }

        List<ProductDelivery> all = repository.findAll(Example.of(example));
        ObservableList<ProductDeliveryModel> productDeliveryModels = FXCollections.observableArrayList();

        all.stream().map(this::convertEntityToModel).forEach(productDeliveryModels::add);

        return productDeliveryModels;
    }

    private ProductDeliveryModel convertEntityToModel(ProductDelivery productDelivery) {
        ProductDeliveryModel model = new ProductDeliveryModel();
        model.setId(productDelivery.getId());
        model.setPersonName(productDelivery.getPerson().getFullName());
        model.setProductName(productDelivery.getProduct().getName());
        model.setDeliveryDate(toLocalDate(productDelivery.getDeliveryDate()));
        model.setDesiredDate(toLocalDate(productDelivery.getDesiredDate()));

        return model;
    }

    LocalDate toLocalDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    Date toDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
