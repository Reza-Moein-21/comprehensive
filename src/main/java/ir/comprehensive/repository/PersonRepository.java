package ir.comprehensive.repository;

import ir.comprehensive.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    @Query("select p from Person p where concat(lower(p.firstName),' ',lower(p.lastName) ) like concat('%',lower(trim(?1)),'%') order by p.firstName")
    Page<Person> findByName(String name, Pageable pageable);

    @Query("select case when count(p) > 0 then true else false end from Person p inner join p.categories c where c.id = ?1")
    Boolean isCategoryExist(Long categoryId);

    @Query("select case when count(p) > 0 then true else false end from Person p where p.firstName = :firstName and p.lastName = :lastName")
    Boolean isNotUnique(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("select case when count(p) > 0 then true else false end from Person p where p.firstName = :firstName and p.lastName = :lastName and p.id <> :id")
    Boolean isNotUnique(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName);

}
