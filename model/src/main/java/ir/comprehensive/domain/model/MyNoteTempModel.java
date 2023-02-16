package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DomainModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MyNoteTempModel extends DomainModel<Long> {

    private MyNoteModel myNote;

}
