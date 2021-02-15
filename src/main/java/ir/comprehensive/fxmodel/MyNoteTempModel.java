package ir.comprehensive.fxmodel;

import ir.comprehensive.fxmodel.basemodel.BaseModel;
import javafx.beans.property.*;

public class MyNoteTempModel extends BaseModel {

    private StringProperty description = new SimpleStringProperty();

    private DoubleProperty priority = new SimpleDoubleProperty();

    private StringProperty projectName = new SimpleStringProperty();

    private ObjectProperty<PersonModel> person = new SimpleObjectProperty<>();


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

    public PersonModel getPerson() {
        return person.get();
    }

    public void setPerson(PersonModel person) {
        this.person.set(person);
    }

    public ObjectProperty<PersonModel> personProperty() {
        return person;
    }
}
