package ir.comprehensive.database.repository;

import ir.comprehensive.database.entity.CategoryEntity;
import ir.comprehensive.database.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<CategoryEntity, Long> {
    @Query("select c from CategoryEntity c where lower(c.title) like concat('%',lower(trim(?1)),'%') order by c.title")
    Page<CategoryEntity> findByTitle(String title, Pageable pageable);

    @Query("select case when count(c) > 0 then true else false end from CategoryEntity c where c.title = :title")
    Boolean isNotUnique(@Param("title") String title);

    @Query("select case when count(c) > 0 then true else false end from CategoryEntity c where c.title = :title and c.id <> :id")
    Boolean isNotUnique(@Param("id") Long id, @Param("title") String title);


    @Query("select count(c) from CategoryEntity c")
    long totalCount();
}
