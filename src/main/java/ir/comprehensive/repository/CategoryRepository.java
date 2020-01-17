package ir.comprehensive.repository;

import ir.comprehensive.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    @Query("select c from Category c where lower(c.title) like concat('%',lower(trim(?1)),'%') order by c.title")
    Page<Category> findByTitle(String title, Pageable pageable);

    @Query("select case when count(c) > 0 then true else false end from Category c where c.title = :title")
    Boolean isNotUnique(@Param("title") String title);

    @Query("select case when count(c) > 0 then true else false end from Category c where c.title = :title and c.id <> :id")
    Boolean isNotUnique(@Param("id") Long id, @Param("title") String title);


    @Query("select count(c) from Category c")
    long totalCount();
}
