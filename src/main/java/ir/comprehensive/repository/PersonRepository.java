package ir.comprehensive.repository;

import ir.comprehensive.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long>, JpaSpecificationExecutor<PersonEntity> {
    @Query("select p from PersonEntity p where concat(lower(p.firstName),' ',lower(p.lastName) ) like concat('%',lower(trim(?1)),'%') order by p.firstName")
    Page<PersonEntity> findByName(String name, Pageable pageable);

    @Query("select case when count(p) > 0 then true else false end from PersonEntity p inner join p.categories c where c.id = ?1")
    Boolean isCategoryExist(Long categoryId);

    @Query("select case when count(p) > 0 then true else false end from PersonEntity p where p.firstName = :firstName and p.lastName = :lastName")
    Boolean isNotUnique(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("select case when count(p) > 0 then true else false end from PersonEntity p where p.firstName = :firstName and p.lastName = :lastName and p.id <> :id")
    Boolean isNotUnique(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("select count(p) from PersonEntity p")
    long totalCount();
}
