package ir.comprehensive.repository;

import ir.comprehensive.domain.MyNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MyNoteRepository extends JpaRepository<MyNote, Long>, JpaSpecificationExecutor<MyNote> {
}
