package ir.comprehensive.repository;

import ir.comprehensive.domain.WarehouseCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseCategoryRepository extends JpaRepository<WarehouseCategory, Long>, JpaSpecificationExecutor<WarehouseCategory> {
    @Query("select wCat from WarehouseCategory wCat where lower(wCat.title) like concat('%',lower(trim(?1)),'%') order by wCat.title")
    Page<WarehouseCategory> findByTitle(String title, Pageable pageable);
}
