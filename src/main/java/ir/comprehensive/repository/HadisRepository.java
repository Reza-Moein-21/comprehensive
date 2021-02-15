package ir.comprehensive.repository;

import ir.comprehensive.entity.Hadis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HadisRepository extends JpaRepository<Hadis, Long>, JpaSpecificationExecutor<Hadis> {
    @Query("select count(h) from Hadis h")
    Long countAll();
}
