package ir.comprehensive.repository;

import ir.comprehensive.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    @Query("select p from Person p where lower(p.firstName) like concat('%',lower(trim(?1)),'%') or lower(p.lastName) like concat('%',lower(trim(?1)),'%')  order by p.firstName")
    Page<Person> findByName(String name, Pageable pageable);
}
