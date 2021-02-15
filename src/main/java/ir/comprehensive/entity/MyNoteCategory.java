package ir.comprehensive.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "MY_NOTE_CATEGORY")
public class MyNoteCategory implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;

    @Column(name = "TITLE", nullable = false)
    String title;

    @Column(name = "DESCRIPTION")
    String description;

    @Formula("select count(*) from MY_NOTE note where note.MY_NOTE_CATEGORY_ID = ID and note.IS_ACTIVE = 1")
    Long countOfActive;

    @Formula("select count(*) from MY_NOTE note where note.MY_NOTE_CATEGORY_ID = ID and  note.IS_ACTIVE = 0")
    Long countOfInActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "myNoteCategory", orphanRemoval = true)
    Set<MyNote> myNotes = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 20)
    MyNoteCategoryStatus status;

    public MyNoteCategory() {
    }

    public MyNoteCategory(Long id) {
        this.id = id;
    }
}
