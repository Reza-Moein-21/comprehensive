package ir.comprehensive.repository;

import ir.comprehensive.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>, JpaSpecificationExecutor<Warehouse> {
    @Query("select w from Warehouse w where concat(lower(w.title),' ',lower(w.code) ) like concat('%',lower(trim(?1)),'%') order by w.title")
    Page<Warehouse> findByName(String name, Pageable pageable);

}
