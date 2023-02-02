package ir.comprehensive.jfxapp.model;

import ir.comprehensive.domain.enums.MyNoteCategoryStatusEnum;
import ir.comprehensive.jfxapp.model.basemodel.BaseFxModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class MyNoteCategoryFxModel extends BaseFxModel {

    private final StringProperty description = new SimpleStringProperty();
    private final ObjectProperty<Long> countOfActive = new SimpleObjectProperty<>();
    private final ObjectProperty<Long> countOfInActive = new SimpleObjectProperty<>();
    private final ObjectProperty<MyNoteCategoryStatusEnum> status = new SimpleObjectProperty<>();
    private final ObjectProperty<List<MyNoteFxModel>> myNotes = new SimpleObjectProperty<>(new ArrayList<>());


    public MyNoteCategoryFxModel() {
    }

    public MyNoteCategoryFxModel(Long id) {
        setId(id);
    }

    public MyNoteCategoryFxModel(Long id, String title) {
        super(title);
        setId(id);
    }

    public final String getDescription() {
        return description.get();
    }

    public final StringProperty descriptionProperty() {
        return description;
    }

    public final void setDescription(String description) {
        this.description.set(description);
    }

    public final Long getCountOfActive() {
        return countOfActive.get();
    }

    public final ObjectProperty<Long> countOfActiveProperty() {
        return countOfActive;
    }

    public final void setCountOfActive(Long countOfActive) {
        this.countOfActive.set(countOfActive);
    }

    public final Long getCountOfInActive() {
        return countOfInActive.get();
    }

    public final ObjectProperty<Long> countOfInActiveProperty() {
        return countOfInActive;
    }

    public final void setCountOfInActive(Long countOfInActive) {
        this.countOfInActive.set(countOfInActive);
    }

    public final MyNoteCategoryStatusEnum getStatus() {
        return status.get();
    }

    public final ObjectProperty<MyNoteCategoryStatusEnum> statusProperty() {
        return status;
    }

    public final void setStatus(MyNoteCategoryStatusEnum status) {
        this.status.set(status);
    }

    public final List<MyNoteFxModel> getMyNotes() {
        return myNotes.get();

    }

    public final ObjectProperty<List<MyNoteFxModel>> myNotesProperty() {
        return myNotes;
    }

    public final void setMyNotes(List<MyNoteFxModel> myNotes) {
        this.myNotes.set(myNotes);
    }
}
