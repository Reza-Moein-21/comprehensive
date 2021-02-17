package ir.comprehensive.database.model;

import ir.comprehensive.database.enums.MyNoteCategoryStatusEnum;
import ir.comprehensive.database.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyNoteCategoryModel extends BaseModel<Long> {

    private String title;

    private String description;

    private Long countOfActive;

    private Long countOfInActive;

    private Set<MyNoteModel> myNotes;

    private MyNoteCategoryStatusEnum status;
}
