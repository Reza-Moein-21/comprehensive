package ir.comprehensive.service.warehousing;

import ir.comprehensive.domain.model.ProductDeliveryModel;

public interface ProductDeliveryService {
    ProductDeliveryModel saveOrUpdateWithEffectOnWarehouse(ProductDeliveryModel productDelivery);
}
