package ir.comprehensive.repository;

import ir.comprehensive.database.entity.WarehouseCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseCategoryFxRepository extends JpaRepository<WarehouseCategoryEntity, Long>, JpaSpecificationExecutor<WarehouseCategoryEntity> {
    @Query("select wCat from WarehouseCategoryEntity wCat where lower(wCat.title) like concat('%',lower(trim(?1)),'%') order by wCat.title")
    Page<WarehouseCategoryEntity> findByTitle(String title, Pageable pageable);
}
