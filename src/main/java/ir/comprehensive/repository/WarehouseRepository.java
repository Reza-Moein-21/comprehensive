package ir.comprehensive.repository;

import ir.comprehensive.entity.WarehouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long>, JpaSpecificationExecutor<WarehouseEntity> {
    @Query("select w from WarehouseEntity w where concat(lower(w.title),' ',lower(w.code) ) like concat('%',lower(trim(?1)),'%') order by w.title")
    Page<WarehouseEntity> findByName(String name, Pageable pageable);


    @Query("select count(w) from WarehouseEntity w")
    long totalCount();

    @Query(value = "select wt.WAREHOUSE_ID from WAREHOUSE_WAREHOUSE_TAG wt where wt.TAG_ID = ?1", nativeQuery = true)
    List<BigInteger> warehouseByTag(Long tagIds);

}
