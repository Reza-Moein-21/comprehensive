package ir.comprehensive.jfxapp.model;

import ir.comprehensive.jfxapp.model.basemodel.BaseFxModel;
import javafx.beans.property.*;
import lombok.ToString;

import java.time.LocalDate;

@ToString
public class MyNoteFxModel extends BaseFxModel {
    // todo must fix later
    private Long myNoteCategoryId;
    private boolean allActive;

    private final StringProperty description = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> creationDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> inActivationDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> creationDateFrom = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> creationDateTo = new SimpleObjectProperty<>();
    private final DoubleProperty priority = new SimpleDoubleProperty();
    private final ObjectProperty<Boolean> isActive = new SimpleObjectProperty<>();
    private final ObjectProperty<MyNoteCategoryFxModel> myNoteCategory = new SimpleObjectProperty<>();
    private final ObjectProperty<PersonFxModel> person = new SimpleObjectProperty<>();

    public final String getDescription() {
        return description.get();
    }

    public final StringProperty descriptionProperty() {
        return description;
    }

    public final void setDescription(String description) {
        this.description.set(description);
    }

    public final LocalDate getCreationDate() {
        return creationDate.get();
    }

    public final ObjectProperty<LocalDate> creationDateProperty() {
        return creationDate;
    }

    public final void setCreationDate(LocalDate creationDate) {
        this.creationDate.set(creationDate);
    }

    public final LocalDate getInActivationDate() {
        return inActivationDate.get();
    }

    public final ObjectProperty<LocalDate> inActivationDateProperty() {
        return inActivationDate;
    }

    public final void setInActivationDate(LocalDate inActivationDate) {
        this.inActivationDate.set(inActivationDate);
    }

    public final LocalDate getCreationDateFrom() {
        return creationDateFrom.get();
    }

    public final ObjectProperty<LocalDate> creationDateFromProperty() {
        return creationDateFrom;
    }

    public final void setCreationDateFrom(LocalDate creationDateFrom) {
        this.creationDateFrom.set(creationDateFrom);
    }

    public final LocalDate getCreationDateTo() {
        return creationDateTo.get();
    }

    public final ObjectProperty<LocalDate> creationDateToProperty() {
        return creationDateTo;
    }

    public final void setCreationDateTo(LocalDate creationDateTo) {
        this.creationDateTo.set(creationDateTo);
    }

    public final Double getPriority() {
        return priority.get();
    }

    public final DoubleProperty priorityProperty() {
        return priority;
    }

    public final void setPriority(Double priority) {
        this.priority.set(priority);
    }

    public final Boolean getIsActive() {
        return isActive.get();
    }

    public final ObjectProperty<Boolean> isActiveProperty() {
        return isActive;
    }

    public final void setIsActive(Boolean isActive) {
        this.isActive.set(isActive);
    }

    public final MyNoteCategoryFxModel getMyNoteCategory() {
        return myNoteCategory.get();
    }

    public final ObjectProperty<MyNoteCategoryFxModel> myNoteCategoryProperty() {
        return myNoteCategory;
    }

    public final void setMyNoteCategory(MyNoteCategoryFxModel myNoteCategory) {
        this.myNoteCategory.set(myNoteCategory);
    }

    public final PersonFxModel getPerson() {
        return person.get();
    }

    public final ObjectProperty<PersonFxModel> personProperty() {
        return person;
    }

    public final void setPerson(PersonFxModel person) {
        this.person.set(person);
    }

    public Long getMyNoteCategoryId() {
        return myNoteCategoryId;
    }

    public void setMyNoteCategoryId(Long myNoteCategoryId) {
        this.myNoteCategoryId = myNoteCategoryId;
    }

    public boolean isAllActive() {
        return allActive;
    }

    public void setAllActive(boolean allActive) {
        this.allActive = allActive;
    }
}
