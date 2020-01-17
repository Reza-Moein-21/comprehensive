package ir.comprehensive.repository;

import ir.comprehensive.domain.MyNoteTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MyNoteTempRepository extends JpaRepository<MyNoteTemp, Long>, JpaSpecificationExecutor<MyNoteTemp> {
}
