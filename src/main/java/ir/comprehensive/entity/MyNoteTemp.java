package ir.comprehensive.entity;

import ir.comprehensive.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "MY_NOTE_TEMP")
public class MyNoteTemp extends BaseEntity<Long> {

    @OneToOne
    @JoinColumn(name = "MY_NOTE_ID", foreignKey = @ForeignKey(name = "FK_MY_NOTE_MY_NOTE_TEMP"))
    private MyNote myNote;

    public MyNoteTemp() {
    }

    public MyNoteTemp(MyNote myNote) {
        this.myNote = myNote;
    }
}
