package ir.comprehensive.entity;

import ir.comprehensive.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "MY_NOTE")
public class MyNote extends BaseEntity<Long> {

    @Column(name = "TITLE", nullable = false)
    String title;

    @Column(name = "DESCRIPTION", length = Integer.MAX_VALUE)
    String description;

    @Column(name = "CREATION_DATE", nullable = false)
    LocalDate creationDate;

    @Column(name = "IN_ACTIVATION_DATE")
    LocalDate inActivationDate;

    @Column(name = "PRIORITY", nullable = false)
    Double priority;

    @Column(name = "IS_ACTIVE", nullable = false)
    Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "MY_NOTE_CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_MY_NOTE_MY_NOTE_CATEGORY"), nullable = false)
    MyNoteCategory myNoteCategory;


    @ManyToOne
    @JoinColumn(name = "PERSON_ID", foreignKey = @ForeignKey(name = "FK_MY_NOTE_PERSON"), nullable = false)
    Person person;

    @OneToOne(mappedBy = "myNote", orphanRemoval = true)
    MyNoteTemp myNoteTemp;

    public MyNote() {
    }

    public MyNote(Long id) {
        this.id = id;
    }

}
