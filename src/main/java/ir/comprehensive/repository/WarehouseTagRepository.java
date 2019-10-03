package ir.comprehensive.repository;

import ir.comprehensive.domain.WarehouseTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseTagRepository extends JpaRepository<WarehouseTag, Long> {
}
