package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DescribableDomainModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
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
