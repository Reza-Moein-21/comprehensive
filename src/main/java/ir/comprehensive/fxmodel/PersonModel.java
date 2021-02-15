package ir.comprehensive.fxmodel;

import ir.comprehensive.fxmodel.basemodel.BaseModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class PersonModel extends BaseModel {
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty phoneNumber = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private ObjectProperty<List<CategoryModel>> categories = new SimpleObjectProperty<>(new ArrayList<>());

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

    public List<CategoryModel> getCategories() {
        return categories.get();
    }

    public void setCategories(List<CategoryModel> categories) {
        this.categories.set(categories);
    }

    public ObjectProperty<List<CategoryModel>> categoriesProperty() {
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
