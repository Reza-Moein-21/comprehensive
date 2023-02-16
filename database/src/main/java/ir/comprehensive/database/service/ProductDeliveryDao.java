package ir.comprehensive.database.service;

import ir.comprehensive.database.service.base.DomainDao;
import ir.comprehensive.domain.enums.ProductStatusEnum;
import ir.comprehensive.domain.model.ProductDeliveryModel;

public interface ProductDeliveryDao extends DomainDao<ProductDeliveryModel, Long> {
    int countByStatus(ProductStatusEnum status);

    int countOfNoneReceivedByProductId(Long warehouseId);

    int countByStatusAndPersonId(ProductStatusEnum status, Long personId);

}
