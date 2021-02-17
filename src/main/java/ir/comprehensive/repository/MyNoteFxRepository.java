package ir.comprehensive.repository;

import ir.comprehensive.entity.MyNoteEntity;
import ir.comprehensive.service.CalenderNoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MyNoteFxRepository extends JpaRepository<MyNoteEntity, Long>, JpaSpecificationExecutor<MyNoteEntity> {

    @Query(value = "select new ir.comprehensive.service.CalenderNoteStatus(note.creationDate,(select count(note1) from MyNoteEntity note1 where note1.creationDate = note.creationDate and note1.isActive = true and note1.myNoteCategory.id = :myNoteCategoryId),(select count(note1) from MyNoteEntity note1 where note1.creationDate = note.creationDate and note1.isActive = false and note1.myNoteCategory.id = :myNoteCategoryId)) from MyNoteEntity  note where (note.creationDate between :startDate and :endDate ) and note.myNoteCategory.id = :myNoteCategoryId  group by note.creationDate")
    List<CalenderNoteStatus> findNumberOfByDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("myNoteCategoryId") Long myNoteCategoryId);

    @Query("select case when count(p) > 0 then true else false end from MyNoteEntity note inner join note.person p where p.id = ?1")
    Boolean isPersonExist(Long personId);

}
