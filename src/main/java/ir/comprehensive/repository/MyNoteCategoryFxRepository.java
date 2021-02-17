package ir.comprehensive.repository;

import ir.comprehensive.database.MyNoteCategoryEntity;
import ir.comprehensive.database.enums.MyNoteCategoryStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MyNoteCategoryFxRepository extends JpaRepository<MyNoteCategoryEntity, Long>, JpaSpecificationExecutor<MyNoteCategoryEntity> {
    @Query("select case when count(c) > 0 then true else false end from MyNoteCategoryEntity c where c.title = :title")
    Boolean isNotUnique(@Param("title") String title);

    @Query("select case when count(c) > 0 then true else false end from MyNoteCategoryEntity c where c.title = :title and c.id <> :id")
    Boolean isNotUnique(@Param("id") Long id, @Param("title") String title);


    @Query("select count(c) from MyNoteCategoryEntity c")
    long totalCount();

    long countByStatus(MyNoteCategoryStatusEnum status);
}
