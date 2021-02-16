package ir.comprehensive.fxmodel;

import ir.comprehensive.fxmodel.basemodel.BaseFxModel;
import javafx.beans.property.*;

public class MyNoteTempFxModel extends BaseFxModel {

    private StringProperty description = new SimpleStringProperty();

    private DoubleProperty priority = new SimpleDoubleProperty();

    private StringProperty projectName = new SimpleStringProperty();

    private ObjectProperty<PersonFxModel> person = new SimpleObjectProperty<>();


    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getProjectName() {
        return projectName.get();
    }

    public void setProjectName(String projectName) {
        this.projectName.set(projectName);
    }

    public StringProperty projectNameProperty() {
        return projectName;
    }

    public double getPriority() {
        return priority.get();
    }

    public void setPriority(double priority) {
        this.priority.set(priority);
    }

    public DoubleProperty priorityProperty() {
        return priority;
    }

    public PersonFxModel getPerson() {
        return person.get();
    }

    public void setPerson(PersonFxModel person) {
        this.person.set(person);
    }

    public ObjectProperty<PersonFxModel> personProperty() {
        return person;
    }
}
