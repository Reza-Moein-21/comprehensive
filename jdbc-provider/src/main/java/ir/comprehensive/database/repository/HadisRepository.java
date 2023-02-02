package ir.comprehensive.database.repository;

import ir.comprehensive.database.entity.HadisEntity;
import ir.comprehensive.database.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HadisRepository extends BaseRepository<HadisEntity, Long> {
    @Query("select count(h) from HadisEntity h")
    Long countAll();
}
