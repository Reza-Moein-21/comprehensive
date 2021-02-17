package ir.comprehensive.database.model;

import ir.comprehensive.database.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyNoteModel extends BaseModel<Long> {

    private String title;

    private String description;

    private LocalDate creationDate;

    private LocalDate inActivationDate;

    private Double priority;

    private Boolean isActive;

    private MyNoteCategoryModel myNoteCategory;

    private PersonModel person;

    private MyNoteTempModel myNoteTemp;

}
