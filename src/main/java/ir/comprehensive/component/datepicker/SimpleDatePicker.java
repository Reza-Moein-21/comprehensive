package ir.comprehensive.component.datepicker;

import com.github.mfathi91.time.PersianDate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

import java.time.LocalDate;

import static ir.comprehensive.utils.MessageUtils.getMessage;

public class SimpleDatePicker extends AnchorPane {

    private StackPane dialogContainer;


    private ObjectProperty<LocalDate> value = new SimpleObjectProperty<>();

    public final LocalDate getValue() {
        return value.get();
    }

    public final ObjectProperty<LocalDate> valueProperty() {
        return value;
    }


    private StringProperty promptText = new SimpleStringProperty(this, "promptText", "") {
        @Override
        protected void invalidated() {
            // Strip out newlines
            String txt = get();
            if (txt != null && txt.contains("\n")) {
                txt = txt.replace("\n", "");
                set(txt);
            }
        }
    };

    public final StringProperty promptTextProperty() {
        return promptText;
    }

    public final String getPromptText() {
        return promptText.get();
    }

    public final void setPromptText(String value) {
        promptText.set(value);
    }

    {
        JFXButton button = new JFXButton();
        button.setRipplerFill(Paint.valueOf("#757575"));
        button.setGraphic(new ImageView(new Image(getClass().getResource("/image/date-range.png").toExternalForm())));
        AnchorPane.setRightAnchor(button, 0D);
        button.setOnAction(event -> {
            PickerContent pickerContent = new PickerContent(getPromptText(), this);
            pickerContent.setDateToPicker(value.getValue());
            pickerContent.setDialogContainer(getDialogContainer());
            pickerContent.show();
        });

        JFXTextField textField = new JFXTextField();
        textField.setDisable(true);
        textField.promptTextProperty().bind(this.promptText);
        textField.setLabelFloat(true);


        value.addListener((observable, oldValue, newValue) -> {
            textField.setText(newValue != null ? PersianDate.fromGregorian(newValue).toString() : null);
        });
        AnchorPane.setLeftAnchor(textField, 0D);
        AnchorPane.setRightAnchor(textField, 0D);
        this.getChildren().addAll(textField, button);
    }

    public StackPane getDialogContainer() {
        return dialogContainer;
    }

    public void setDialogContainer(StackPane dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    public final void setValue(LocalDate value) {
        this.value.set(value);
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
