package ir.comprehensive.entity.service.impl;

import ir.comprehensive.entity.ProductDeliveryEntity;
import ir.comprehensive.entity.base.BaseServiceImpl;
import ir.comprehensive.entity.mapper.ProductDeliveryMapper;
import ir.comprehensive.entity.model.ProductDeliveryModel;
import ir.comprehensive.entity.repository.ProductDeliveryRepository;
import ir.comprehensive.entity.service.ProductDeliveryService;
import org.springframework.stereotype.Service;

@Service
public class ProductDeliveryServiceImpl extends BaseServiceImpl<ProductDeliveryEntity, ProductDeliveryModel, ProductDeliveryMapper, ProductDeliveryRepository, Long> implements ProductDeliveryService {

    public ProductDeliveryServiceImpl(ProductDeliveryMapper mapper, ProductDeliveryRepository repository) {
        super(mapper, repository);
    }

}
