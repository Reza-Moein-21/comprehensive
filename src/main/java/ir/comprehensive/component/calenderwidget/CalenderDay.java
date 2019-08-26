package ir.comprehensive.component.calenderwidget;

import ir.comprehensive.component.JavaFxComponent;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

import java.util.StringJoiner;

class CalenderDay extends JavaFxComponent {

    public CalenderDay() {
        refresh();
    }

    @Override
    protected Node render() {
        VBox base = new VBox();

        Label lblDayNumber = new Label();
        lblDayNumber.textProperty().bindBidirectional(dayNumber, new NumberStringConverter());
        StringJoiner styleForLilDayNumber = new StringJoiner(" ; ");
        styleForLilDayNumber.add("-fx-background-radius: " + ScreenUtils.getActualSize(3))
                .add("-fx-padding:0 " + ScreenUtils.getActualSize(8))
                .add("-fx-font-size: " + ScreenUtils.getActualSize(23));
        if (isCurrentDay) {
            styleForLilDayNumber.add("-fx-background-color: #82b1ff");
        }

        lblDayNumber.setStyle(styleForLilDayNumber.toString());
        HBox hbxDayNumber = new HBox(lblDayNumber);
        hbxDayNumber.setStyle("-fx-border-width: 0 0 " + ScreenUtils.getActualSize(2) + " 0;-fx-border-color: #bdbdbd;-fx-padding: " + ScreenUtils.getActualSize(2) + " 0;");

        content.addListener(o -> this.refresh());
        base.getChildren().setAll(hbxDayNumber);
        if (getContent() != null) {
            Pane content = getContent();
            content.setMaxWidth(Double.MAX_VALUE);
            VBox.setVgrow(content, Priority.ALWAYS);
            base.getChildren().add(content);
        }
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

    boolean isSelected;
    boolean isCurrentDay;

    /**
     * dayNumber property
     */
    IntegerProperty dayNumber = new SimpleIntegerProperty(this, "dayNumber", 0);

    public int getDayNumber() {
        return dayNumber.get();
    }

    public IntegerProperty dayNumberProperty() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber.set(dayNumber);
    }

    /**
     * content property
     */
    public ObjectProperty<Pane> content = new SimpleObjectProperty<>(this, "content", null);

    public Pane getContent() {
        return content.get();
    }

    public ObjectProperty<Pane> contentProperty() {
        return content;
    }

    public void setContent(Pane content) {
        this.content.set(content);
    }
}
