package ir.comprehensive.repository;

import ir.comprehensive.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where lower(c.title) like concat('%',lower(trim(?1)),'%') order by c.title")
    Page<Category> findByTitle(String title, Pageable pageable);
}
