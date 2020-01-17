package ir.comprehensive.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WarehouseInfo {

    private StringProperty totalWarehouse = new SimpleStringProperty("0");
    private StringProperty lostCount = new SimpleStringProperty("0");
    private StringProperty unknownCount = new SimpleStringProperty("0");
    private StringProperty receivedCount = new SimpleStringProperty("0");
    private StringProperty rejectedCount = new SimpleStringProperty("0");


    public String getTotalWarehouse() {
        return totalWarehouse.get();
    }

    public void setTotalWarehouse(String totalWarehouse) {
        this.totalWarehouse.set(totalWarehouse);
    }

    public StringProperty totalWarehouseProperty() {
        return totalWarehouse;
    }

    public String getLostCount() {
        return lostCount.get();
    }

    public void setLostCount(String lostCount) {
        this.lostCount.set(lostCount);
    }

    public StringProperty lostCountProperty() {
        return lostCount;
    }

    public String getUnknownCount() {
        return unknownCount.get();
    }

    public void setUnknownCount(String unknownCount) {
        this.unknownCount.set(unknownCount);
    }

    public StringProperty unknownCountProperty() {
        return unknownCount;
    }

    public String getReceivedCount() {
        return receivedCount.get();
    }

    public void setReceivedCount(String receivedCount) {
        this.receivedCount.set(receivedCount);
    }

    public StringProperty receivedCountProperty() {
        return receivedCount;
    }

    public String getRejectedCount() {
        return rejectedCount.get();
    }

    public void setRejectedCount(String rejectedCount) {
        this.rejectedCount.set(rejectedCount);
    }

    public StringProperty rejectedCountProperty() {
        return rejectedCount;
    }
}
