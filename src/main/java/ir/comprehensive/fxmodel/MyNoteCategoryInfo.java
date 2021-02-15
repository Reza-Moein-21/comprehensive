package ir.comprehensive.fxmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MyNoteCategoryInfo {

    private StringProperty totalCount = new SimpleStringProperty("0");
    private StringProperty inProgressCount = new SimpleStringProperty("0");
    private StringProperty doneCount = new SimpleStringProperty("0");
    private StringProperty stoppedCount = new SimpleStringProperty("0");


    public MyNoteCategoryInfo() {
    }

    public MyNoteCategoryInfo(String totalCount, String inProgressCount, String doneCount, String stoppedCount) {
        this.totalCount.setValue(totalCount);
        this.inProgressCount.setValue(inProgressCount);
        this.doneCount.setValue(doneCount);
        this.stoppedCount.setValue(stoppedCount);
    }

    public String getTotalCount() {
        return totalCount.get();
    }

    public void setTotalCount(String totalCount) {
        this.totalCount.set(totalCount);
    }

    public StringProperty totalCountProperty() {
        return totalCount;
    }

    public String getInProgressCount() {
        return inProgressCount.get();
    }

    public void setInProgressCount(String inProgressCount) {
        this.inProgressCount.set(inProgressCount);
    }

    public StringProperty inProgressCountProperty() {
        return inProgressCount;
    }

    public String getDoneCount() {
        return doneCount.get();
    }

    public void setDoneCount(String doneCount) {
        this.doneCount.set(doneCount);
    }

    public StringProperty doneCountProperty() {
        return doneCount;
    }

    public String getStoppedCount() {
        return stoppedCount.get();
    }

    public void setStoppedCount(String stoppedCount) {
        this.stoppedCount.set(stoppedCount);
    }

    public StringProperty stoppedCountProperty() {
        return stoppedCount;
    }
}
