package ir.comprehensive.model;

import javafx.beans.property.*;
import javafx.collections.ObservableSet;

public class CategoryModel {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty phoneNumber = new SimpleStringProperty();
    private StringProperty fax = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private SetProperty<PersonModel> people = new SimpleSetProperty<>();

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
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

    public String getFax() {
        return fax.get();
    }

    public void setFax(String fax) {
        this.fax.set(fax);
    }

    public StringProperty faxProperty() {
        return fax;
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

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public ObservableSet<PersonModel> getPeople() {
        return people.get();
    }

    public void setPeople(ObservableSet<PersonModel> people) {
        this.people.set(people);
    }

    public SetProperty<PersonModel> peopleProperty() {
        return people;
    }
}
