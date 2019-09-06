package ir.comprehensive.model;

import ir.comprehensive.model.basemodel.BaseModel;
import javafx.beans.property.*;

import java.time.LocalDate;

public class MyNoteModel extends BaseModel {
    private StringProperty description = new SimpleStringProperty();
    private ObjectProperty<LocalDate> creationDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> inActivationDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> creationDateFrom = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> creationDateTo = new SimpleObjectProperty<>();
    private DoubleProperty priority = new SimpleDoubleProperty();
    private ObjectProperty<Boolean> isActive = new SimpleObjectProperty<>();
    private ObjectProperty<MyNoteCategoryModel> myNoteCategory = new SimpleObjectProperty<>();

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

    public final MyNoteCategoryModel getMyNoteCategory() {
        return myNoteCategory.get();
    }

    public final ObjectProperty<MyNoteCategoryModel> myNoteCategoryProperty() {
        return myNoteCategory;
    }

    public final void setMyNoteCategory(MyNoteCategoryModel myNoteCategory) {
        this.myNoteCategory.set(myNoteCategory);
    }
}
