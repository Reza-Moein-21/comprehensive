package ir.comprehensive.component.datepicker;

import com.github.mfathi91.time.PersianDate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleDatePicker extends AnchorPane {

    private StackPane dialogContainer;
    JFXTextField textField;


    private ObjectProperty<LocalDate> value = new SimpleObjectProperty<>();


    public final LocalDate getValue() {
        return value.get();
    }

    public final ObjectProperty<LocalDate> valueProperty() {
        return value;
    }
    private ObjectProperty<List<ValidatorBase>> validators = new SimpleObjectProperty<>(new ArrayList<>());


    public final List<ValidatorBase> getValidators() {
        return validators.get();
    }

    public final ObjectProperty<List<ValidatorBase>> validatorsProperty() {
        return validators;
    }

    public final void setValidators(List<ValidatorBase> validators) {
        this.validators.set(validators);
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

        textField = new JFXTextField();
        textField.setEditable(false);
        textField.promptTextProperty().bind(this.promptText);
        textField.setLabelFloat(true);


        value.addListener((observable, oldValue, newValue) -> {
            textField.setText(newValue != null ? PersianDate.fromGregorian(newValue).toString() : null);
        });
        validators.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                textField.getValidators().clear();
                return;
            }
            textField.getValidators().setAll(newValue);
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

    public boolean validate() {
        return textField.validate();
    }
}
