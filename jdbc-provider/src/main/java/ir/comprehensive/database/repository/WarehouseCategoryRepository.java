package ir.comprehensive.database.repository;

import ir.comprehensive.database.entity.WarehouseCategoryEntity;
import ir.comprehensive.database.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseCategoryRepository extends BaseRepository<WarehouseCategoryEntity, Long> {
    @Query("select wCat from WarehouseCategoryEntity wCat where lower(wCat.title) like concat('%',lower(trim(?1)),'%') order by wCat.title")
    Page<WarehouseCategoryEntity> findByTitle(String title, Pageable pageable);
}
