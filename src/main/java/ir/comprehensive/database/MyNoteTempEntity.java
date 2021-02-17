package ir.comprehensive.database;

import ir.comprehensive.database.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "MY_NOTE_TEMP")
public class MyNoteTempEntity extends BaseEntity<Long> {

    @OneToOne
    @JoinColumn(name = "MY_NOTE_ID", foreignKey = @ForeignKey(name = "FK_MY_NOTE_MY_NOTE_TEMP"))
    private MyNoteEntity myNote;

    public MyNoteTempEntity() {
    }

    public MyNoteTempEntity(MyNoteEntity myNote) {
        this.myNote = myNote;
    }
}
