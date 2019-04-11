package ir.comprehensive.component;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

import static ir.comprehensive.utils.MessageUtils.getMessage;

public class SimpleDatePicker extends HBox {

    private DateField yearField;
    private Separator yearFieldSeparator;
    private DateField monthField;
    private Separator monthFieldSeparator;
    private DateField dayField;
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();

    {
        this.yearField = new DateField(getMessage("year"));
        this.monthField = new DateField(getMessage("month"));
        this.dayField = new DateField(getMessage("day"));
        this.yearFieldSeparator = new Separator();
        this.monthFieldSeparator = new Separator();
        yearFieldSeparator.setStyle("-fx-background-color: #212121; -fx-rotate: 60; -fx-pref-width: 20;");
        monthFieldSeparator.setStyle("-fx-background-color: #212121; -fx-rotate: 60; -fx-pref-width: 20;");

        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #E3F2FD; -fx-background-radius: 10;");
        this.getChildren().addAll(dayField, yearFieldSeparator, monthField, monthFieldSeparator, yearField);
    }


    private class DateField extends VBox {
        private JFXTextField dateFieldInput;
        private Label dateFieldLabel;

        {
            this.setSpacing(5);
            this.dateFieldInput = new JFXTextField();
            this.dateFieldLabel = new Label();
            this.dateFieldLabel.setAlignment(Pos.CENTER);
            this.setAlignment(Pos.CENTER);
            this.getChildren().addAll(dateFieldInput, dateFieldLabel);
        }

        DateField(String title) {
            this.dateFieldLabel.setText(title);
        }
    }
}
