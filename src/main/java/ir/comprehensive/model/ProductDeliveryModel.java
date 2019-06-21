package ir.comprehensive.model;

import ir.comprehensive.domain.ProductStatus;
import ir.comprehensive.model.basemodel.BaseModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class ProductDeliveryModel extends BaseModel {
    private ObjectProperty<PersonModel> person = new SimpleObjectProperty<>();
    private ObjectProperty<ProductModel> product = new SimpleObjectProperty();
    private StringProperty description = new SimpleStringProperty();
    private ObjectProperty<LocalDate> deliveryDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> desiredDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> receivedDate = new SimpleObjectProperty<>();
    private ObjectProperty<ProductStatus> status = new SimpleObjectProperty<>();

    public final PersonModel getPerson() {
        return person.get();
    }

    public final ObjectProperty<PersonModel> personProperty() {
        return person;
    }

    public final void setPerson(PersonModel person) {
        this.person.set(person);
    }

    public final ProductModel getProduct() {
        return product.get();
    }

    public final ObjectProperty<ProductModel> productProperty() {
        return product;
    }

    public final void setProduct(ProductModel product) {
        this.product.set(product);
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

    public final LocalDate getReceivedDate() {
        return receivedDate.get();
    }

    public final ObjectProperty<LocalDate> receivedDateProperty() {
        return receivedDate;
    }

    public final void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate.set(receivedDate);
    }

    public final ProductStatus getStatus() {
        return status.get();
    }

    public final ObjectProperty<ProductStatus> statusProperty() {
        return status;
    }

    public final void setStatus(ProductStatus status) {
        this.status.set(status);
    }
}
