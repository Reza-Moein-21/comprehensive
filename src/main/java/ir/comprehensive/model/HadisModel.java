package ir.comprehensive.model;

import ir.comprehensive.model.basemodel.BaseModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HadisModel extends BaseModel {
    private StringProperty description = new SimpleStringProperty();

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @Override
    public String toString() {
        return "HadisModel{" +
                "description=" + description +
                ", id=" + id +
                ", title=" + title +
                '}';
    }
}
