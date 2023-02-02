package ir.comprehensive.database.service;

import ir.comprehensive.database.service.base.DescribableDomainDao;
import ir.comprehensive.domain.enums.ProductStatusEnum;
import ir.comprehensive.domain.model.ProductDeliveryModel;

import java.util.List;

public interface ProductDeliveryDao extends DescribableDomainDao<ProductDeliveryModel, Long> {
    List<ProductDeliveryModel> findAllByStatus(ProductStatusEnum status);
}
