package ir.comprehensive.service;

import ir.comprehensive.domain.ProductDelivery;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.repository.ProductDeliveryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDeliveryService {
    ProductDeliveryRepository repository;

    public ProductDeliveryService(ProductDeliveryRepository repository) {
        this.repository = repository;
    }

    public ObservableList<ProductDeliveryModel> getAllModel() {
        List<ProductDelivery> all = repository.findAll();
        ObservableList<ProductDeliveryModel> productDeliveryModels = FXCollections.observableArrayList();

        all.stream().map(this::convertEntityToModel).forEach(productDeliveryModels::add);

        return productDeliveryModels;
    }

    private ProductDeliveryModel convertEntityToModel(ProductDelivery productDelivery) {
        ProductDeliveryModel model = new ProductDeliveryModel();
        model.setId(productDelivery.getId());
        model.setPersonName(productDelivery.getPerson().getFirstName());
        model.setProductName(productDelivery.getProduct().getName());
        model.setDeliveryDate(productDelivery.getDeliveryDate());
        model.setDesiredDate(productDelivery.getDesiredDate());

        return model;
    }
}
