package ir.comprehensive.domain.model;

import ir.comprehensive.domain.enums.MyNoteCategoryStatusEnum;
import ir.comprehensive.domain.model.base.DescribableDomainModel;
import ir.comprehensive.domain.model.base.DomainModel;
import lombok.*;

import java.util.Set;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class MyNoteCategoryModel extends DescribableDomainModel<Long> {

    private Long countOfActive;

    private Long countOfInActive;

    private Set<MyNoteModel> myNotes;

    private MyNoteCategoryStatusEnum status;
}
