package ir.comprehensive.repository;

import ir.comprehensive.database.entity.ProductDeliveryEntity;
import ir.comprehensive.database.enums.ProductStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDeliveryFxRepository extends JpaRepository<ProductDeliveryEntity, Long>, JpaSpecificationExecutor<ProductDeliveryEntity> {
    @Query("select case when count(pd) > 0 then true else false end from ProductDeliveryEntity pd  where pd.person.id = ?1")
    Boolean isPersonExist(Long personId);

    @Query("select case when count(pd) > 0 then true else false end from ProductDeliveryEntity pd  where pd.product.id = ?1")
    boolean isWarehouseExist(Long productId);

    long countByStatus(ProductStatusEnum status);

    @Query("select sum(pd.count) from ProductDeliveryEntity pd where pd.product.id = ?1 and pd.status <> :#{T(ir.comprehensive.database.enums.ProductStatusEnum).RECEIVED} ")
    Long consumptionCountForPrint(Long WarehouseId);

    Long countByStatusAndPersonId(ProductStatusEnum status, Long personId);
}
