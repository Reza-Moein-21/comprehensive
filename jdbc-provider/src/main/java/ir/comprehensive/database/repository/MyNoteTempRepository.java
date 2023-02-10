package ir.comprehensive.database.repository;

import ir.comprehensive.database.entity.MyNoteTempEntity;
import ir.comprehensive.database.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MyNoteTempRepository extends BaseRepository<MyNoteTempEntity, Long> {
    @Modifying
    @Query("delete from MyNoteTempEntity t where t.id in ?1")
    void deleteAllById(Set<Long> ids);

    @Query("select case when count(t) > 0 then true else false end from MyNoteTempEntity t where t.myNote.id = :myNoteId")
    boolean isMyNoteExist(@Param("myNoteId") Long id);
}