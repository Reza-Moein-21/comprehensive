package ir.comprehensive.jfxapp.model;

import ir.comprehensive.jfxapp.model.basemodel.BaseFxModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.ToString;

@ToString
public class HadisFxModel extends BaseFxModel<Long> {
    private final StringProperty description = new SimpleStringProperty();

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

}
