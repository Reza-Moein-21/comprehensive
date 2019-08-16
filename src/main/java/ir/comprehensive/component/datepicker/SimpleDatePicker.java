package ir.comprehensive.component.datepicker;

import com.github.mfathi91.time.PersianDate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleDatePicker extends StackPane {

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
        ChangeListener<Boolean> focusedChangeListener = (observable, oldValue, newValue) -> {
            if (!newValue) {
                this.validate();
            }
        };
        this.focusedProperty().addListener(focusedChangeListener);
        JFXButton button = new JFXButton();
        button.focusedProperty().addListener(focusedChangeListener);
        button.setRipplerFill(Paint.valueOf("#757575"));
        ImageView imageView = new ImageView(new Image(getClass().getResource("/image/date-range.png").toExternalForm()));
        imageView.setFitWidth(ScreenUtils.getActualSize(52));
        imageView.setFitHeight(ScreenUtils.getActualSize(52));

        button.setGraphic(imageView);
        StackPane.setAlignment(button, Pos.CENTER_RIGHT);
        button.setOnAction(event -> {
            PickerContent pickerContent = new PickerContent(getPromptText(), this);
            applyFontStyle(pickerContent);
            pickerContent.setDateToPicker(value.getValue());
            pickerContent.setDialogContainer(getDialogContainer());
            pickerContent.show();
        });

        textField = new JFXTextField();
        textField.setEditable(false);
        textField.promptTextProperty().bind(this.promptText);
        textField.setLabelFloat(true);


        textField.focusedProperty().addListener(focusedChangeListener);

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

    private void applyFontStyle(Pane rootNode) {
        for (Node n : rootNode.getChildren()) {
            n.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(32) + "px;-fx-font-family: 'shabnam';");
        }
    }
}
