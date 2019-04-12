package ir.comprehensive.component;

import com.github.mfathi91.time.PersianDate;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

import static ir.comprehensive.utils.MessageUtils.getMessage;

public class SimpleDatePicker extends HBox {

    private DateField yearField = new DateField(DateFieldType.YEAR);
    private DateSeparator yearSeparator = new DateSeparator();
    private DateField monthField = new DateField(DateFieldType.MONTH);
    private DateSeparator monthSeparator = new DateSeparator();
    private DateField dayField = new DateField(DateFieldType.DAY);
    private ObjectProperty<PersianDate> persianDate = new SimpleObjectProperty<>();

    {
        setMargin(yearSeparator, new Insets(30, 10, 0, 0));
        setMargin(monthSeparator, new Insets(30, 10, 0, 0));

        this.setStyle("-fx-border-width: 1 0;-fx-border-radius: 25; -fx-border-color: #212121; -fx-padding: 10;-fx-spacing: 5;-fx-alignment: center");
        this.getChildren().addAll(dayField, yearSeparator, monthField, monthSeparator, yearField);
    }

    public PersianDate getPersianDate() {
        int year = Integer.valueOf(yearField.getText());
        int month = Integer.valueOf(monthField.getText());
        int day = Integer.valueOf(dayField.getText());
        persianDate.set(PersianDate.of(year, month, day));
        return persianDate.get();
    }

    public ObjectProperty<PersianDate> persianDateProperty() {
        return persianDate;
    }

    public void setPersianDate(PersianDate persianDate) {
        yearField.setText(Integer.toString(persianDate.getYear()));
        monthField.setText(Integer.toString(persianDate.getMonthValue()));
        dayField.setText(Integer.toString(persianDate.getDayOfMonth()));
        this.persianDate.set(persianDate);
    }

    private class DateField extends JFXTextField {
        DateFieldType type;

        DateField(DateFieldType type) {
            this.setLabelFloat(true);

            this.type = type;
            switch (type) {
                case YEAR:
                    this.setPromptText(getMessage("year"));
                    this.textProperty().addListener((observable, oldValue, newValue) -> checkInput(newValue, 4, 1998, 1));
                    break;
                case MONTH:
                    this.setPromptText(getMessage("month"));
                    this.textProperty().addListener((observable, oldValue, newValue) -> checkInput(newValue, 2, 12, 1));
                    break;
                case DAY:
                    this.setPromptText(getMessage("day"));
                    this.textProperty().addListener((observable, oldValue, newValue) -> checkInput(newValue, 2, 31, 1));
                    break;
                default:
                    break;
            }
        }

        private void checkInput(String newValue, int length, Integer max, Integer min) {
            if (!newValue.matches("\\d*")) {
                this.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (this.getText().length() >= length) {
                this.setText(this.getText().substring(0, length));
            }
            if (!this.getText().isEmpty() && max != null) {
                if (Integer.valueOf(this.getText()) > max) {
                    this.setText(max.toString());
                }
                if (Integer.valueOf(this.getText()) < min) {
                    this.setText(min.toString());
                }
            }
        }
    }

    private class DateSeparator extends Line {
        DateSeparator() {
            setStartX(0.0f);
            setStartY(0.0f);
            setEndX(20.0f);
            setEndY(50.0f);
        }
    }

    private enum DateFieldType {
        YEAR, MONTH, DAY
    }
}
