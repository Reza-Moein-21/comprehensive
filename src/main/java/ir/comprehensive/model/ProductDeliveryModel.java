package ir.comprehensive.model;

import ir.comprehensive.domain.Person;
import javafx.beans.property.*;

import java.time.LocalDate;

public class ProductDeliveryModel {
    private LongProperty id = new SimpleLongProperty();
    private ObjectProperty<Person> person = new SimpleObjectProperty<>();
    private StringProperty productName = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private ObjectProperty<LocalDate> deliveryDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> desiredDate = new SimpleObjectProperty<>();

    public final Long getId() {
        return id.get();
    }

    public final LongProperty idProperty() {
        return id;
    }

    public final void setId(Long id) {
        this.id.set(id);
    }

    public final Person getPerson() {
        return person.get();
    }

    public final ObjectProperty<Person> personProperty() {
        return person;
    }

    public final void setPerson(Person person) {
        this.person.set(person);
    }

    public final String getProductName() {
        return productName.get();
    }

    public final StringProperty productNameProperty() {
        return productName;
    }

    public final void setProductName(String productName) {
        this.productName.set(productName);
    }

    public final String getDescription() {
        return description.get();
    }

    public final StringProperty descriptionProperty() {
        return description;
    }

    public final void setDescription(String description) {
        this.description.set(description);
    }

    public final LocalDate getDeliveryDate() {
        return deliveryDate.get();
    }

    public final ObjectProperty<LocalDate> deliveryDateProperty() {
        return deliveryDate;
    }

    public final void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate.set(deliveryDate);
    }

    public final LocalDate getDesiredDate() {
        return desiredDate.get();
    }

    public final ObjectProperty<LocalDate> desiredDateProperty() {
        return desiredDate;
    }

    public final void setDesiredDate(LocalDate desiredDate) {
        this.desiredDate.set(desiredDate);
    }
}
