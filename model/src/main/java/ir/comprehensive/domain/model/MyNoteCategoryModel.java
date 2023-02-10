package ir.comprehensive.domain.model;

import ir.comprehensive.domain.enums.MyNoteCategoryStatusEnum;
import ir.comprehensive.domain.model.base.DescribableDomainModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class MyNoteCategoryModel extends DescribableDomainModel<Long> {

    private Long countOfActive;

    private Long countOfInActive;

    private Set<MyNoteModel> myNotes;

    private MyNoteCategoryStatusEnum status;
}
