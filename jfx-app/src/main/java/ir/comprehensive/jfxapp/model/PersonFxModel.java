package ir.comprehensive.jfxapp.model;

import ir.comprehensive.jfxapp.model.basemodel.BaseFxModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class PersonFxModel extends BaseFxModel {
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty phoneNumber = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final ObjectProperty<List<CategoryFxModel>> categories = new SimpleObjectProperty<>(new ArrayList<>());

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public final StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public final String getDescription() {
        return description.get();
    }

    public final StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public List<CategoryFxModel> getCategories() {
        return categories.get();
    }

    public void setCategories(List<CategoryFxModel> categories) {
        this.categories.set(categories);
    }

    public ObjectProperty<List<CategoryFxModel>> categoriesProperty() {
        return categories;
    }

    @Override
    public String getTitle() {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
