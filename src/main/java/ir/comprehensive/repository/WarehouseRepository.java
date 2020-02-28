package ir.comprehensive.repository;

import ir.comprehensive.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>, JpaSpecificationExecutor<Warehouse> {
    @Query("select w from Warehouse w where concat(lower(w.title),' ',lower(w.code) ) like concat('%',lower(trim(?1)),'%') order by w.title")
    Page<Warehouse> findByName(String name, Pageable pageable);


    @Query("select count(w) from Warehouse w")
    long totalCount();

    @Query(value = "select wt.WAREHOUSE_ID from WAREHOUSE_WAREHOUSE_TAG wt " +
            "GROUP by wt.WAREHOUSE_ID " +
            "having count(wt.TAG_ID) >= ?1 and wt.WAREHOUSE_ID in (select wt2.WAREHOUSE_ID from WAREHOUSE_WAREHOUSE_TAG wt2 where wt2.TAG_ID in ?2)", nativeQuery = true)
    List<Long> warehouseByTag(int tagListSize, List<Long> tagIds);

}
