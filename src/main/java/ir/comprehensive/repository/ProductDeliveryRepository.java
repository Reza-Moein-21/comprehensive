package ir.comprehensive.repository;

import ir.comprehensive.domain.ProductDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDeliveryRepository extends JpaRepository<ProductDelivery, Long>, JpaSpecificationExecutor<ProductDelivery> {
    @Query("select case when count(pd) > 0 then true else false end from ProductDelivery pd  where pd.person.id = ?1")
    Boolean isPersonExist(Long personId);
}
