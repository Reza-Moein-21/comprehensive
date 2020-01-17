package ir.comprehensive.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MY_NOTE_TEMP")
public class MyNoteTemp {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "MY_NOTE_ID", foreignKey = @ForeignKey(name = "FK_MY_NOTE_MY_NOTE_TEMP"))
    private MyNote myNote;

    public MyNoteTemp() {
    }

    public MyNoteTemp(MyNote myNote) {
        this.myNote = myNote;
    }
}
