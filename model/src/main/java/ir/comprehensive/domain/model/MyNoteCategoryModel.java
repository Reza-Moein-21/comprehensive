package ir.comprehensive.domain.model;

import ir.comprehensive.domain.enums.MyNoteCategoryStatusEnum;
import ir.comprehensive.domain.model.base.DescribableDomainModel;

import java.util.Set;

public record MyNoteCategoryModel(
        Long id,
        String title,
        String description,
        Long countOfActive,
        Long countOfInActive,
        Set<MyNoteModel> myNotes,
        MyNoteCategoryStatusEnum status

) implements DescribableDomainModel<Long> {
}
