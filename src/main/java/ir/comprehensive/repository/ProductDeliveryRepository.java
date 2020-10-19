package ir.comprehensive.repository;

import ir.comprehensive.domain.ProductDelivery;
import ir.comprehensive.domain.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDeliveryRepository extends JpaRepository<ProductDelivery, Long>, JpaSpecificationExecutor<ProductDelivery> {
    @Query("select case when count(pd) > 0 then true else false end from ProductDelivery pd  where pd.person.id = ?1")
    Boolean isPersonExist(Long personId);

    @Query("select case when count(pd) > 0 then true else false end from ProductDelivery pd  where pd.product.id = ?1")
    boolean isWarehouseExist(Long productId);

    long countByStatus(ProductStatus status);

    @Query("select sum(pd.count) from ProductDelivery pd where pd.product.id = ?1 and pd.status <> :#{T(ir.comprehensive.domain.ProductStatus).RECEIVED} ")
    Long consumptionCountForPrint(Long WarehouseId);

    Long countByStatusAndPersonId(ProductStatus status, Long personId);
}
