package ir.comprehensive.jfxapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.ToString;

@ToString
public class HumanResourceInfo {

    private final StringProperty personCount = new SimpleStringProperty("0");
    private final StringProperty categoryCount = new SimpleStringProperty("0");

    public String getPersonCount() {
        return personCount.get();
    }

    public void setPersonCount(String personCount) {
        this.personCount.set(personCount);
    }

    public StringProperty personCountProperty() {
        return personCount;
    }

    public String getCategoryCount() {
        return categoryCount.get();
    }

    public void setCategoryCount(String categoryCount) {
        this.categoryCount.set(categoryCount);
    }

    public StringProperty categoryCountProperty() {
        return categoryCount;
    }

}
