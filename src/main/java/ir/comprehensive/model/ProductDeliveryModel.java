package ir.comprehensive.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class ProductDeliveryModel extends RecursiveTreeObject<ProductDeliveryModel> {
    private ObjectProperty<Long> id = new SimpleObjectProperty<>();
    private StringProperty personName = new SimpleStringProperty();
    private StringProperty productName = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private ObjectProperty<Date> deliveryDate = new SimpleObjectProperty<>();
    private ObjectProperty<Date> desiredDate = new SimpleObjectProperty<>();

    public final Long getId() {
        return id.get();
    }

    public final ObjectProperty<Long> idProperty() {
        return id;
    }

    public final void setId(Long id) {
        this.id.set(id);
    }

    public final String getPersonName() {
        return personName.get();
    }

    public final StringProperty personNameProperty() {
        return personName;
    }

    public final void setPersonName(String personName) {
        this.personName.set(personName);
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

    public final Date getDeliveryDate() {
        return deliveryDate.get();
    }

    public final ObjectProperty<Date> deliveryDateProperty() {
        return deliveryDate;
    }

    public final void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate.set(deliveryDate);
    }

    public final Date getDesiredDate() {
        return desiredDate.get();
    }

    public final ObjectProperty<Date> desiredDateProperty() {
        return desiredDate;
    }

    public final void setDesiredDate(Date desiredDate) {
        this.desiredDate.set(desiredDate);
    }
}
