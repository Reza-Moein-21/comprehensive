package ir.comprehensive.domain;

import lombok.Data;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;

@Data
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

    public MyNoteCategory() {
    }

    public MyNoteCategory(Long id) {
        this.id = id;
    }
}
