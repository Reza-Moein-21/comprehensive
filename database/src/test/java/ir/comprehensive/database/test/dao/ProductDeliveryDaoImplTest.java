package ir.comprehensive.database.test.dao;

import ir.comprehensive.database.internal.mapper.ProductDeliveryMapper;
import ir.comprehensive.database.internal.dao.impl.ProductDeliveryDaoImpl;
import ir.comprehensive.database.dao.ProductDeliveryDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static ir.comprehensive.domain.enums.ProductStatusEnum.*;
import static org.assertj.core.api.Assertions.assertThat;

@Sql("/product-delivery-init.sql")
@ExtendWith(DBExtension.class)
public class ProductDeliveryDaoImplTest {

    private final ProductDeliveryDao productDeliveryDao;

    public ProductDeliveryDaoImplTest(DSLContext context, ProductDeliveryMapper mapper) {
        this.productDeliveryDao = new ProductDeliveryDaoImpl(context, mapper);
    }

    @Test
    void givingNull_countByStatus_shouldReturnZero() {
        assertThat(productDeliveryDao.countByStatus(null)).isZero();
    }

    @Test
    void givingNonExistStatus_countByStatus_shouldReturnZero() {
        assertThat(productDeliveryDao.countByStatus(LOST)).isZero();
    }

    @Test
    void givingValidStatus_countByStatus_shouldReturnOne() {
        assertThat(productDeliveryDao.countByStatus(UNKNOWN)).isOne();
        assertThat(productDeliveryDao.countByStatus(RECEIVED)).isOne();
    }

    @Test
    void givingNullWarehouseId_countOfNoneReceivedByProductId_shouldReturnZero() {
        assertThat(productDeliveryDao.countOfNoneReceivedByProductId(null)).isZero();
    }

    @Test
    void givingNoneExistWarehouseId_countOfNoneReceivedByProductId_shouldReturnZero() {
        assertThat(productDeliveryDao.countOfNoneReceivedByProductId(-1L)).isZero();
    }

    @Test
    void givingReceivedWarehouseId_countOfNoneReceivedByProductId_shouldReturnZero() {
        assertThat(productDeliveryDao.countOfNoneReceivedByProductId(113L)).isZero();
    }

    @Test
    void givingNoneReceivedWarehouseId_countOfNoneReceivedByProductId_shouldReturnOne() {
        assertThat(productDeliveryDao.countOfNoneReceivedByProductId(112L)).isOne();
    }

    @Test
    void givingNullStatus_countByStatusAndPersonId_shouldReturnZero() {
        assertThat(productDeliveryDao.countByStatusAndPersonId(null, 987654321L)).isZero();
    }

    @Test
    void givingNullPersonId_countByStatusAndPersonId_shouldReturnZero() {
        assertThat(productDeliveryDao.countByStatusAndPersonId(RECEIVED, null)).isZero();
    }

    @Test
    void givingNoneExistPersonId_countByStatusAndPersonId_shouldReturnZero() {
        assertThat(productDeliveryDao.countByStatusAndPersonId(RECEIVED, -1L)).isZero();
    }

    @Test
    void givingNoneExistStatusValidPersonId_countByStatusAndPersonId_shouldReturnZero() {
        assertThat(productDeliveryDao.countByStatusAndPersonId(REJECTED, 987654321L)).isZero();
    }

    @Test
    void givingExistStatusValidPersonId_countByStatusAndPersonId_shouldReturnOne() {
        assertThat(productDeliveryDao.countByStatusAndPersonId(UNKNOWN, 987654321L)).isOne();
        assertThat(productDeliveryDao.countByStatusAndPersonId(RECEIVED, 987654321L)).isOne();
    }
}
