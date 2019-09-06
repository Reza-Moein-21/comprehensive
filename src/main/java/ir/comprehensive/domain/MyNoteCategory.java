package ir.comprehensive.domain;

import lombok.Data;

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

    public MyNoteCategory() {
    }

    public MyNoteCategory(Long id) {
        this.id = id;
    }
}
