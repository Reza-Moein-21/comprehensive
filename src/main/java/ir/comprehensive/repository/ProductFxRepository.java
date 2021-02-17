package ir.comprehensive.repository;

import ir.comprehensive.database.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFxRepository extends JpaRepository<ProductEntity, Long> {
}
