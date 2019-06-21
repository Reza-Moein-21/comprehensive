package ir.comprehensive.service;

import ir.comprehensive.domain.ProductDelivery;
import ir.comprehensive.mapper.ProductDeliveryMapper;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.repository.ProductDeliveryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        List<ProductDelivery> all = repository.findAll();
        List<ProductDeliveryModel> collect = all.stream().map(mapper::entityToModel).collect(Collectors.toList());
        return FXCollections.observableList(collect);
    }

    public void save(ProductDeliveryModel model) {
        repository.save(mapper.modelToEntity(model));
    }
}
