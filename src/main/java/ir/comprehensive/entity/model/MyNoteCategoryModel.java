package ir.comprehensive.entity.model;

import ir.comprehensive.entity.enums.MyNoteCategoryStatusEnum;
import ir.comprehensive.entity.base.BaseModel;
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
