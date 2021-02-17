package ir.comprehensive.repository;

import ir.comprehensive.entity.HadisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HadisFxRepository extends JpaRepository<HadisEntity, Long>, JpaSpecificationExecutor<HadisEntity> {
    @Query("select count(h) from HadisEntity h")
    Long countAll();
}
