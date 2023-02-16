package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.ProductDeliveryMapperImpl;
import ir.comprehensive.database.provider.service.ProductDeliveryDaoImpl;
import ir.comprehensive.database.service.ProductDeliveryDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static ir.comprehensive.domain.enums.ProductStatusEnum.*;
import static org.assertj.core.api.Assertions.assertThat;

@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:product-delivery-init.sql"})
@SpringJUnitConfig({ProductDeliveryDaoImpl.class, ProductDeliveryMapperImpl.class, JooqConfig.class})
public class ProductDeliveryDaoImplTest {
    @Autowired
    ProductDeliveryDao productDeliveryDao;

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
