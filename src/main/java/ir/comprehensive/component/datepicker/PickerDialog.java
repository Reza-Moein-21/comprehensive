package ir.comprehensive.component.datepicker;

import com.jfoenix.controls.JFXDialog;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class PickerDialog extends JFXDialog {
    public SimpleDatePicker parent;
    /**
     * calender time
     */
    ObjectProperty<LocalDate> tempTime;
    // todo must add header text for new datePicker
    private String header;

    public PickerDialog(String header, SimpleDatePicker parent) {
        this.header = header;
        this.parent = parent;
        this.tempTime = new SimpleObjectProperty<>(this, "tempTime", this.parent.getValue() == null ? LocalDate.now() : this.parent.getValue());

        VBox base = new VBox();

        PickerHeader pickerHeader = new PickerHeader(this.tempTime);
        pickerHeader.isShowYear.bind(parent.isShowYear);

        PickerMain pickerMain = new PickerMain(this);
        PickerFooter pickerFooter = new PickerFooter(this);
        base.getChildren().setAll(pickerHeader, pickerMain, pickerFooter);

        base.setStyle(String.format("-fx-alignment: center; -fx-background-color: #fff9c4;-fx-background-radius: %s ;-fx-effect: dropshadow(three-pass-box, #bdbdbd, %s , 0, 0, 0);", ScreenUtils.getActualSize(5), ScreenUtils.getActualSize(5)));
        base.setAlignment(Pos.CENTER);
        this.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        setContent(base);
    }

}

