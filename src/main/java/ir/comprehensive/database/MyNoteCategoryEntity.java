package ir.comprehensive.database;

import ir.comprehensive.database.base.BaseEntity;
import ir.comprehensive.database.enums.MyNoteCategoryStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "MY_NOTE_CATEGORY")
public class MyNoteCategoryEntity extends BaseEntity<Long> {

    @Column(name = "TITLE", nullable = false)
    String title;

    @Column(name = "DESCRIPTION")
    String description;

    @Formula("select count(*) from MY_NOTE note where note.MY_NOTE_CATEGORY_ID = ID and note.IS_ACTIVE = 1")
    Long countOfActive;

    @Formula("select count(*) from MY_NOTE note where note.MY_NOTE_CATEGORY_ID = ID and  note.IS_ACTIVE = 0")
    Long countOfInActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "myNoteCategory", orphanRemoval = true)
    Set<MyNoteEntity> myNotes = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 20)
    MyNoteCategoryStatusEnum status;

    public MyNoteCategoryEntity() {
    }

    public MyNoteCategoryEntity(Long id) {
        this.id = id;
    }
}
