package ir.comprehensive.component.datepicker;

import ir.comprehensive.component.JavaFxComponent;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.util.converter.NumberStringConverter;

import java.util.StringJoiner;

class PickerDay extends JavaFxComponent {

    boolean isSelected;
    boolean isCurrentDay;
    /**
     * dayNumber property
     */
    IntegerProperty dayNumber = new SimpleIntegerProperty(this, "dayNumber", 0);

    public PickerDay() {
        this.refresh();
    }

    @Override
    protected Node render() {
        StackPane base = new StackPane();

        Label lblDayNumber = new Label();
        lblDayNumber.textProperty().bindBidirectional(dayNumber, new NumberStringConverter());
        StringJoiner styleForLilDayNumber = new StringJoiner(" ; ");
        styleForLilDayNumber.add("-fx-background-radius: " + ScreenUtils.getActualSize(3))
                .add("-fx-padding:0 " + ScreenUtils.getActualSize(8))
                .add("-fx-font-size: " + ScreenUtils.getActualSize(38));
        if (isCurrentDay) {
            styleForLilDayNumber.add("-fx-background-color: #82b1ff");
        }

        lblDayNumber.setStyle(styleForLilDayNumber.toString());

        base.getChildren().setAll(lblDayNumber);

        StringJoiner styleForThis = new StringJoiner(" ; ");
        styleForThis
                .add("-fx-alignment: center")
                .add("-fx-padding: " + ScreenUtils.getActualSize(3))
                .add("-fx-pref-width:  " + ScreenUtils.getActualSize(155))
                .add("-fx-pref-height: " + ScreenUtils.getActualSize(130))
                .add("-fx-min-height: " + ScreenUtils.getActualSize(130))
                .add("-fx-border-radius: " + ScreenUtils.getActualSize(5))
                .add("-fx-background-radius: " + ScreenUtils.getActualSize(5))
                .add("-fx-border-color: #9e9e9e")
                .add("-fx-border-width: " + ScreenUtils.getActualSize(2));
        if (isSelected) {
            styleForThis.add("-fx-background-color: #ccff90");
            this.setStyle(styleForThis.toString());
        } else {
            styleForThis.add("-fx-background-color: #ffffff");
            this.setStyle(styleForThis.toString());
        }

        return base;
    }

    public void refresh() {
        this.getChildren().setAll(render());
    }

    public int getDayNumber() {
        return dayNumber.get();
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber.set(dayNumber);
    }

    public IntegerProperty dayNumberProperty() {
        return dayNumber;
    }

}
