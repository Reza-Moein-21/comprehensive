package ir.comprehensive.component.calenderwidget;

import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class CalenderWidget extends StackPane {


    public CalenderWidget() {
        refresh();
    }

    private void refresh() {
        this.getChildren().setAll(render());
    }

    private Node render() {
        VBox base = new VBox();
        CalenderHeader calenderHeader = new CalenderHeader(this.time);

        CalenderMain calenderMainWidget = new CalenderMain(time, eventList);

        base.getChildren().setAll(calenderHeader, calenderMainWidget);

        this.setStyle(String.format("-fx-alignment: center; -fx-background-color: #fff9c4;-fx-background-radius: %s ;-fx-font-size: %s ;-fx-effect: dropshadow(three-pass-box, #bdbdbd, %s , 0, 0, 0);", ScreenUtils.getActualSize(5), ScreenUtils.getActualSize(26), ScreenUtils.getActualSize(5)));
        this.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        return base;
    }


    /**
     * calender event list
     */
    private ListProperty<CalenderEvent> eventList = new SimpleListProperty<>(this, "eventList", FXCollections.observableArrayList());

    public final void setEventList(ObservableList<CalenderEvent> value) {
        eventListProperty().set(value);
    }

    public final ObservableList<CalenderEvent> getEventList() {
        return eventList.get();
    }

    public ListProperty<CalenderEvent> eventListProperty() {
        return eventList;
    }

    /**
     * calender time
     */
    ObjectProperty<LocalDate> time = new SimpleObjectProperty<>(this, "time", LocalDate.now());

    public LocalDate getTime() {
        return time.get();
    }

    public ObjectProperty<LocalDate> timeProperty() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time.set(time);
    }


}


