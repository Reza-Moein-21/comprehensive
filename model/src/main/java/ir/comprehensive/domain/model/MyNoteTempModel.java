package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DomainModel;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class MyNoteTempModel extends DomainModel<Long> {

    private MyNoteModel myNote;

}
