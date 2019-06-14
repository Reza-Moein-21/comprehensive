package ir.comprehensive.model;

import ir.comprehensive.model.basemodel.BaseModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CategoryModel extends BaseModel {
    private StringProperty title = new SimpleStringProperty();
    private StringProperty phoneNumber = new SimpleStringProperty();
    private StringProperty fax = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
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

    @Override
    public String toString() {
        return "CategoryModel{" +
                "title=" + title +
                ", phoneNumber=" + phoneNumber +
                ", fax=" + fax +
                ", email=" + email +
                ", address=" + address +
                ", description=" + description +
                '}';
    }
}
