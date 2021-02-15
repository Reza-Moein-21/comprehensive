package ir.comprehensive.fxmodel;

import ir.comprehensive.entity.ProductStatusEnum;
import ir.comprehensive.fxmodel.basemodel.BaseModel;
import javafx.beans.property.*;

import java.time.LocalDate;

public class ProductDeliveryModel extends BaseModel {
    private ObjectProperty<PersonModel> person = new SimpleObjectProperty<>();
    private ObjectProperty<WarehouseModel> product = new SimpleObjectProperty();
    private StringProperty description = new SimpleStringProperty();
    private LongProperty count = new SimpleLongProperty();
    private ObjectProperty<LocalDate> deliveryDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> deliveryDateFrom = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> deliveryDateTo = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> desiredDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> receivedDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> receivedDateFrom = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> receivedDateTo = new SimpleObjectProperty<>();
    private ObjectProperty<ProductStatusEnum> status = new SimpleObjectProperty<>();

    public final PersonModel getPerson() {
        return person.get();
    }

    public final ObjectProperty<PersonModel> personProperty() {
        return person;
    }

    public final void setPerson(PersonModel person) {
        this.person.set(person);
    }

    public WarehouseModel getProduct() {
        return product.get();
    }

    public void setProduct(WarehouseModel product) {
        this.product.set(product);
    }

    public ObjectProperty<WarehouseModel> productProperty() {
        return product;
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

    public Long getCount() {
        return count.get();
    }

    public void setCount(Long count) {
        this.count.set(count);
    }

    public LongProperty countProperty() {
        return count;
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

    public final LocalDate getDeliveryDateFrom() {
        return deliveryDateFrom.get();
    }

    public final ObjectProperty<LocalDate> deliveryDateFromProperty() {
        return deliveryDateFrom;
    }

    public final void setDeliveryDateFrom(LocalDate deliveryDateFrom) {
        this.deliveryDateFrom.set(deliveryDateFrom);
    }

    public final LocalDate getDeliveryDateTo() {
        return deliveryDateTo.get();
    }

    public final ObjectProperty<LocalDate> deliveryDateToProperty() {
        return deliveryDateTo;
    }

    public final void setDeliveryDateTo(LocalDate deliveryDateTo) {
        this.deliveryDateTo.set(deliveryDateTo);
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

    public final LocalDate getReceivedDateFrom() {
        return receivedDateFrom.get();
    }

    public final ObjectProperty<LocalDate> receivedDateFromProperty() {
        return receivedDateFrom;
    }

    public final void setReceivedDateFrom(LocalDate receivedDateFrom) {
        this.receivedDateFrom.set(receivedDateFrom);
    }

    public final LocalDate getReceivedDateTo() {
        return receivedDateTo.get();
    }

    public final ObjectProperty<LocalDate> receivedDateToProperty() {
        return receivedDateTo;
    }

    public final void setReceivedDateTo(LocalDate receivedDateTo) {
        this.receivedDateTo.set(receivedDateTo);
    }

    public final ProductStatusEnum getStatus() {
        return status.get();
    }

    public final ObjectProperty<ProductStatusEnum> statusProperty() {
        return status;
    }

    public final void setStatus(ProductStatusEnum status) {
        this.status.set(status);
    }
}
