package ir.comprehensive.database.service;

import ir.comprehensive.database.service.base.DomainDao;
import ir.comprehensive.domain.enums.ProductStatusEnum;
import ir.comprehensive.domain.model.ProductDeliveryModel;

import java.util.List;

public interface ProductDeliveryDao extends DomainDao<ProductDeliveryModel, Long> {
    List<ProductDeliveryModel> findAllByStatus(ProductStatusEnum status);
}
