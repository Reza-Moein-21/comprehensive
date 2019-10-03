package ir.comprehensive.repository;

import ir.comprehensive.domain.WarehouseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseCategoryRepository extends JpaRepository<WarehouseCategory, Long> {
}
