package ir.comprehensive.database.service.impl;

import ir.comprehensive.database.ProductDeliveryEntity;
import ir.comprehensive.database.base.BaseServiceImpl;
import ir.comprehensive.database.mapper.ProductDeliveryMapper;
import ir.comprehensive.database.model.ProductDeliveryModel;
import ir.comprehensive.database.repository.ProductDeliveryRepository;
import ir.comprehensive.database.service.ProductDeliveryService;
import org.springframework.stereotype.Service;

@Service
public class ProductDeliveryServiceImpl extends BaseServiceImpl<ProductDeliveryEntity, ProductDeliveryModel, ProductDeliveryMapper, ProductDeliveryRepository, Long> implements ProductDeliveryService {

    public ProductDeliveryServiceImpl(ProductDeliveryMapper mapper, ProductDeliveryRepository repository) {
        super(mapper, repository);
    }

}
