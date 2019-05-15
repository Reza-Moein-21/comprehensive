package ir.comprehensive.model;

import javafx.beans.property.*;
import javafx.collections.ObservableSet;

public class PersonModel {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty phoneNumber = new SimpleStringProperty();
    private SetProperty<CategoryModel> categories = new SimpleSetProperty<>();

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public LongProperty idProperty() {
        return id;
    }

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

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public ObservableSet<CategoryModel> getCategories() {
        return categories.get();
    }

    public void setCategories(ObservableSet<CategoryModel> categories) {
        this.categories.set(categories);
    }

    public SetProperty<CategoryModel> categoriesProperty() {
        return categories;
    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                ", phoneNumber=" + phoneNumber +
                ", categories=" + categories +
                '}';
    }
}
