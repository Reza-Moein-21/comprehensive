package ir.comprehensive.component.datepicker;

import ir.comprehensive.component.JavaFxComponent;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PickerFooter extends JavaFxComponent {
    PickerDialog pickerDialog;
    BooleanProperty isAzan = new SimpleBooleanProperty();

    public PickerFooter(PickerDialog pickerDialog) {
        this.pickerDialog = pickerDialog;
        this.refresh();
    }

    @Override
    protected Node render() {
        HBox base = new HBox(ScreenUtils.getActualSize(10));
        Button btnClear = new Button(MessageUtils.Message.CLEAR);
        Button btnCancel = new Button(MessageUtils.Message.CANCEL);
        btnClear.setOnAction(event -> {
            pickerDialog.parent.setValue(null);
            pickerDialog.close();
        });
        btnCancel.setOnAction(event -> {
            pickerDialog.close();
        });
        isAzanProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                base.getChildren().setAll(btnCancel);
            } else {
                base.getChildren().setAll(btnClear, btnCancel);
            }
        });
        if (isAzanProperty().get()) {
            base.getChildren().setAll(btnCancel);
        } else {
            base.getChildren().setAll(btnClear, btnCancel);
        }
        this.setStyle("-fx-border-width: " + ScreenUtils.getActualSize(3) + " 0 0 0; -fx-border-color: #bdbdbd;-fx-padding: " + ScreenUtils.getActualSize(10) + " 0;");
        return base;
    }

    public boolean isIsAzan() {
        return isAzan.get();
    }

    public BooleanProperty isAzanProperty() {
        return isAzan;
    }

    public void setIsAzan(boolean isAzan) {
        this.isAzan.set(isAzan);
    }
}
