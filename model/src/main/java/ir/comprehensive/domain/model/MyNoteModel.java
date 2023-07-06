package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DescribableDomainModel;

import java.time.LocalDate;

public record MyNoteModel(
        Long id,
        String title,
        String description,
        LocalDate creationDate,
        LocalDate inActivationDate,
        Double priority,
        Boolean isActive,
        MyNoteCategoryModel myNoteCategory,
        PersonModel person,
        MyNoteTempModel myNoteTemp

) implements DescribableDomainModel<Long> {
}
