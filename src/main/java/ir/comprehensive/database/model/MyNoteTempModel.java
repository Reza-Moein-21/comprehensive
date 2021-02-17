package ir.comprehensive.database.model;

import ir.comprehensive.database.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyNoteTempModel extends BaseModel<Long> {

    private MyNoteModel myNote;

}
