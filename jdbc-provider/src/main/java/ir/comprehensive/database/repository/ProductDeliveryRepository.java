package ir.comprehensive.database.repository;

import ir.comprehensive.database.entity.ProductDeliveryEntity;
import ir.comprehensive.database.repository.base.BaseRepository;
import ir.comprehensive.domain.enums.ProductStatusEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDeliveryRepository extends BaseRepository<ProductDeliveryEntity, Long> {
    @Query("select case when count(pd) > 0 then true else false end from ProductDeliveryEntity pd  where pd.person.id = ?1")
    Boolean isPersonExist(Long personId);

    @Query("select case when count(pd) > 0 then true else false end from ProductDeliveryEntity pd  where pd.product.id = ?1")
    boolean isWarehouseExist(Long productId);

    long countByStatus(ProductStatusEnum status);

    @Query("select sum(pd.count) from ProductDeliveryEntity pd where pd.product.id = ?1 and pd.status <> :#{T(ir.comprehensive.database.enums.ProductStatusEnum).RECEIVED} ")
    Long consumptionCountForPrint(Long WarehouseId);

    Long countByStatusAndPersonId(ProductStatusEnum status, Long personId);
}
