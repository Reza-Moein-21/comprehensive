package ir.comprehensive.component.datepicker;

import com.github.mfathi91.time.PersianDate;
import com.github.mfathi91.time.PersianMonth;
import ir.comprehensive.component.JavaFxComponent;
import ir.comprehensive.component.arrowchoiceboxwidget.ArrowChoiceBoxWidget;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

import java.time.LocalDate;

class PickerHeader extends JavaFxComponent {
    /**
     * calender time
     */
    ObjectProperty<LocalDate> tempTime;

    BooleanProperty isShowYear = new SimpleBooleanProperty(true);

    public PickerHeader(ObjectProperty<LocalDate> tempTime) {
        this.tempTime = tempTime;
        this.getChildren().setAll(render());
    }

    private int getMaxDay(PersianDate persianDate, PersianMonth persianMonth) {
        if (persianMonth.getValue() == 12) {
            return persianDate.getDayOfMonth() == 30 || persianDate.getDayOfMonth() == 31 ? 29 : persianDate.getDayOfMonth();
        }
        if (persianMonth.getValue() > 6) {
            return persianDate.getDayOfMonth() == 31 ? 30 : persianDate.getDayOfMonth();
        }
        return persianDate.getDayOfMonth();
    }

    @Override
    protected Node render() {
        HBox base = new HBox(ScreenUtils.getActualSize(10));
        Button btnToday = new Button(MessageUtils.Calender.TODAY);
        ArrowChoiceBoxWidget<Integer> acbYear = new ArrowChoiceBoxWidget<>();
        acbYear.setPrefWidth(ScreenUtils.getActualSize(390));
        acbYear.setItems(getYearItems());
        acbYear.valueProperty().addListener((observable, oldValue, newValue) -> {
            PersianDate d = PersianDate.fromGregorian(tempTime.getValue());
            Integer year = acbYear.getValue();
            tempTime.set(PersianDate.of(year, d.getMonth(), getMaxDay(d, d.getMonth())).toGregorian());
        });
        ArrowChoiceBoxWidget<PersianMonth> acbMonth = new ArrowChoiceBoxWidget<>();
        acbMonth.valueProperty().addListener((observable, oldValue, newValue) -> {
            PersianDate d = PersianDate.fromGregorian(tempTime.getValue());
            tempTime.set(PersianDate.of(d.getYear(), newValue, getMaxDay(d, newValue)).toGregorian());
        });
        acbMonth.setConverter(new StringConverter<PersianMonth>() {
            @Override
            public String toString(PersianMonth object) {
                return String.format("%s (%d)", object.getPersianName(), object.getValue());
            }

            @Override
            public PersianMonth fromString(String string) {
                return null;
            }
        });
        acbMonth.setPrefWidth(ScreenUtils.getActualSize(490));
        acbMonth.setItems(FXCollections.observableArrayList(PersianMonth.values()));

        btnToday.setOnAction(event -> this.tempTime.setValue(LocalDate.now()));

        PersianDate persianDate1 = PersianDate.fromGregorian(tempTime.getValue());
        acbMonth.setValue(persianDate1.getMonth());
        acbYear.setValue(persianDate1.getYear());
        tempTime.addListener((observable, oldValue, newValue) -> {
            PersianDate persianDate = PersianDate.fromGregorian(newValue);
            acbMonth.setValue(persianDate.getMonth());
            acbYear.setValue(persianDate.getYear());
        });

        base.setAlignment(Pos.CENTER);
        base.getChildren().setAll(btnToday, acbMonth, acbYear);


        isShowYear.addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                base.getChildren().setAll(btnToday, acbMonth);
            } else {
                base.getChildren().setAll(btnToday, acbMonth, acbYear);
            }
        });
        this.setStyle("-fx-border-width: 0 0 " + ScreenUtils.getActualSize(3) + " 0; -fx-border-color: #bdbdbd;-fx-padding: " + ScreenUtils.getActualSize(10) + " 0;");
        return base;
    }

    private ObservableList<Integer> getYearItems() {
        int startYear = 1370;
        ObservableList<Integer> items = FXCollections.observableArrayList();
        for (int i = 0; i <= 50; i++) {
            items.add(startYear);
            startYear++;
        }
        return items;
    }

    public LocalDate getTempTime() {
        return tempTime.get();
    }

    public void setTempTime(LocalDate tempTime) {
        this.tempTime.set(tempTime);
    }

    public ObjectProperty<LocalDate> tempTimeProperty() {
        return tempTime;
    }

    public boolean isIsShowYear() {
        return isShowYear.get();
    }

    public BooleanProperty isShowYearProperty() {
        return isShowYear;
    }

    public void setIsShowYear(boolean isShowYear) {
        this.isShowYear.set(isShowYear);
    }
}
