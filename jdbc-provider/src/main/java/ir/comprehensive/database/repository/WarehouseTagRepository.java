package ir.comprehensive.database.repository;

import ir.comprehensive.database.entity.WarehouseTagEntity;
import ir.comprehensive.database.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseTagRepository extends BaseRepository<WarehouseTagEntity, Long> {
    @Query("select tag from WarehouseTagEntity tag where lower(tag.title) like concat('%',lower(trim(?1)),'%') order by tag.title")
    Page<WarehouseTagEntity> findByTitle(String title, Pageable pageable);

    @Query("select tag from WarehouseTagEntity tag where tag.title = ?1")
    Optional<WarehouseTagEntity> findByTitleExact(String title);
}
