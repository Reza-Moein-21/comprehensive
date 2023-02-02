package ir.comprehensive.service;

import ir.comprehensive.domain.model.ProductDeliveryModel;

public interface ProductDeliveryService {
    ProductDeliveryModel saveOrUpdateWithEffectOnWarehouse(ProductDeliveryModel productDelivery);
}
