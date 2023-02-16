package ir.comprehensive.database.service;

import ir.comprehensive.database.service.base.DescribableDomainDao;
import ir.comprehensive.domain.enums.ProductStatusEnum;
import ir.comprehensive.domain.model.ProductDeliveryModel;

public interface ProductDeliveryDao extends DescribableDomainDao<ProductDeliveryModel, Long> {
    int countByStatus(ProductStatusEnum status);

    int countOfNoneReceivedByProductId(Long warehouseId);

    int countByStatusAndPersonId(ProductStatusEnum status, Long personId);

}
