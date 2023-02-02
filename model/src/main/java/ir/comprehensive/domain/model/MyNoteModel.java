package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DescribableDomainModel;
import ir.comprehensive.domain.model.base.DomainModel;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class MyNoteModel extends DescribableDomainModel<Long> {

    private LocalDate creationDate;

    private LocalDate inActivationDate;

    private Double priority;

    private Boolean isActive;

    private MyNoteCategoryModel myNoteCategory;

    private PersonModel person;

    private MyNoteTempModel myNoteTemp;

}
