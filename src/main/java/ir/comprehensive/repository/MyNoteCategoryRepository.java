package ir.comprehensive.repository;

import ir.comprehensive.entity.MyNoteCategory;
import ir.comprehensive.entity.MyNoteCategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MyNoteCategoryRepository extends JpaRepository<MyNoteCategory, Long>, JpaSpecificationExecutor<MyNoteCategory> {
    @Query("select case when count(c) > 0 then true else false end from MyNoteCategory c where c.title = :title")
    Boolean isNotUnique(@Param("title") String title);

    @Query("select case when count(c) > 0 then true else false end from MyNoteCategory c where c.title = :title and c.id <> :id")
    Boolean isNotUnique(@Param("id") Long id, @Param("title") String title);


    @Query("select count(c) from MyNoteCategory c")
    long totalCount();

    long countByStatus(MyNoteCategoryStatus status);
}
