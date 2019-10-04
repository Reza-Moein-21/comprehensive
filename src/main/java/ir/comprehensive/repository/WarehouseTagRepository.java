package ir.comprehensive.repository;

import ir.comprehensive.domain.WarehouseTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseTagRepository extends JpaRepository<WarehouseTag, Long> {

    @Query("select tag from WarehouseTag tag where lower(tag.title) like concat('%',lower(trim(?1)),'%') order by tag.title")
    Page<WarehouseTag> findByTitle(String title, Pageable pageable);
}
