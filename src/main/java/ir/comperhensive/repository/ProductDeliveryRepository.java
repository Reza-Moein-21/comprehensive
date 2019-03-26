package ir.comperhensive.repository;

import ir.comperhensive.domain.ProductDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDeliveryRepository extends JpaRepository<ProductDelivery, Long> {
}
