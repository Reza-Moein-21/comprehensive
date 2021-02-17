package ir.comprehensive.repository;

import ir.comprehensive.database.entity.WarehouseTagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseTagFxRepository extends JpaRepository<WarehouseTagEntity, Long> {

    @Query("select tag from WarehouseTagEntity tag where lower(tag.title) like concat('%',lower(trim(?1)),'%') order by tag.title")
    Page<WarehouseTagEntity> findByTitle(String title, Pageable pageable);

    @Query("select tag from WarehouseTagEntity tag where tag.title = ?1")
    Optional<WarehouseTagEntity> findByTitleExact(String title);
}
