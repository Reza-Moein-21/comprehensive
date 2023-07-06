package ir.comprehensive.database.provider.service;

import ir.comprehensive.database.provider.mapper.ProductDeliveryMapper;
import ir.comprehensive.database.service.ProductDeliveryDao;
import ir.comprehensive.domain.enums.ProductStatusEnum;
import ir.comprehensive.domain.model.ProductDeliveryModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.ProductDeliveryRecord;

import static org.jooq.generated.tables.ProductDelivery.PRODUCT_DELIVERY;

public class ProductDeliveryDaoImpl extends AbstractDescribableDomainDao<ProductDeliveryModel, ProductDeliveryRecord, Long> implements ProductDeliveryDao {
    private final DSLContext context;

    public ProductDeliveryDaoImpl(DSLContext context, ProductDeliveryMapper mapper) {
        super(context, mapper, PRODUCT_DELIVERY, PRODUCT_DELIVERY.ID);
        this.context = context;
    }

    @Override
    public int countByStatus(ProductStatusEnum status) {
        if (status == null) return 0;
        return context.fetchCount(PRODUCT_DELIVERY,
                PRODUCT_DELIVERY.STATUS.eq(status.toString())
        );
    }

    @Override
    public int countOfNoneReceivedByProductId(Long warehouseId) {
        return context.fetchCount(PRODUCT_DELIVERY,
                PRODUCT_DELIVERY.PRODUCT_ID.eq(warehouseId),
                PRODUCT_DELIVERY.STATUS.notEqual(ProductStatusEnum.RECEIVED.toString())
        );
    }

    @Override
    public int countByStatusAndPersonId(ProductStatusEnum status, Long personId) {
        if (status == null) return 0;
        return context.fetchCount(PRODUCT_DELIVERY,
                PRODUCT_DELIVERY.STATUS.eq(status.toString()),
                PRODUCT_DELIVERY.PERSON_ID.eq(personId)
        );
    }
}
