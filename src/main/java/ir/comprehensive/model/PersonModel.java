package ir.comprehensive.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonModel extends RecursiveTreeObject<PersonModel> {
    private ObjectProperty<Long> id = new SimpleObjectProperty<>();
    private StringProperty fullName = new SimpleStringProperty();

    public final Long getId() {
        return id.get();
    }

    public final ObjectProperty<Long> idProperty() {
        return id;
    }

    public final void setId(Long id) {
        this.id.set(id);
    }

    public final String getFullName() {
        return fullName.get();
    }

    public final StringProperty fullNameProperty() {
        return fullName;
    }

    public final void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    @Override
    public String toString() {
        return fullName.get();
    }
}
