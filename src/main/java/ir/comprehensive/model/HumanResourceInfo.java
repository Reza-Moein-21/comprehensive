package ir.comprehensive.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HumanResourceInfo {

    private StringProperty personCount = new SimpleStringProperty("0");
    private StringProperty categoryCount = new SimpleStringProperty("0");

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
