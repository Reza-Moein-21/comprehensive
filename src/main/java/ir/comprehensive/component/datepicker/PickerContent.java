package ir.comprehensive.component.datepicker;

import com.github.mfathi91.time.PersianDate;
import com.github.mfathi91.time.PersianMonth;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import ir.comprehensive.utils.MessageUtils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PickerContent extends JFXDialog implements Picker {
    private String header;
    JFXComboBox<Integer> cmbYear;
    JFXComboBox<PersianMonth> cmbMonth;
    DayContainer dayContainer;


    private SimpleDatePicker parent;


    public PickerContent(String header, SimpleDatePicker parent) {
        this.header = header;
        this.parent = parent;


        setContent(getContent());
    }

    @Override
    public Region getContent() {
        Label lblHeader = new Label(header);
        lblHeader.setStyle("-fx-text-fill: #fafafa");
        HBox headerContainer = new HBox(lblHeader);
        headerContainer.setStyle("-fx-alignment: center-left;-fx-border-width: 0 0 1 0;-fx-border-color: black;-fx-padding: 10;-fx-background-color: #1976d2;-fx-background-radius: 10 10 0 0; -fx-pref-height: 80");

        cmbYear = new JFXComboBox<>(getYearItems());
        cmbYear.setPrefWidth(200);
        cmbMonth = new JFXComboBox<>(getMonthItems());
        cmbMonth.setPrefWidth(400);
        cmbMonth.setConverter(new StringConverter<PersianMonth>() {
            @Override
            public String toString(PersianMonth object) {
                return object.getPersianName();
            }

            @Override
            public PersianMonth fromString(String string) {
                return null;
            }
        });
        HBox yearMonthContainer = new HBox(cmbYear, cmbMonth);
        yearMonthContainer.setStyle("-fx-spacing: 10");


        dayContainer = new DayContainer(1);
        dayContainer.setStyle("-fx-hgap: 20;-fx-vgap:10;-fx-background-color: #dcedc8;");

        JFXButton btnClear = new JFXButton(MessageUtils.Message.CLEAR);
        btnClear.setOnAction(event -> {
            parent.setValue(null);
            this.close();
        });

        JFXButton btnOk = new JFXButton(MessageUtils.Message.OK);
        btnOk.setOnAction(event -> {
            LocalDate localDate = PersianDate.of(cmbYear.getValue(), cmbMonth.getValue().getValue(), dayContainer.getDay()).toGregorian();
            parent.setValue(localDate);
            this.close();
        });
        JFXButton btnCancel = new JFXButton(MessageUtils.Message.CANCEL);
        btnCancel.setOnAction(event -> this.close());

        BorderPane footerContainer = new BorderPane();
        footerContainer.setStyle("-fx-padding: 5 20");
        footerContainer.setRight(btnClear);
        footerContainer.setLeft(new HBox(btnOk, btnCancel));

        VBox parent = new VBox(headerContainer, yearMonthContainer, dayContainer, footerContainer);
        parent.setStyle("-fx-spacing: 10;-fx-pref-width: 600;");
        parent.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        return parent;
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

    private ObservableList<PersianMonth> getMonthItems() {
        return FXCollections.observableArrayList(PersianMonth.values());
    }


    @Override
    public void setDateToPicker(LocalDate date) {
        PersianDate persianDate = Objects.isNull(date) ? PersianDate.now() : PersianDate.fromGregorian(date);
        cmbYear.setValue(persianDate.getYear());
        cmbMonth.setValue(persianDate.getMonth());
        dayContainer.setDay(persianDate.getDayOfMonth());
    }

    class DayContainer extends TilePane {
        IntegerProperty day = new SimpleIntegerProperty();

        public final int getDay() {
            return day.get();
        }

        public final IntegerProperty dayProperty() {
            return day;
        }

        public final void setDay(int day) {
            getChildren().setAll(getItems(day));
            this.day.set(day);
        }


        public DayContainer(Integer dayNumber) {

            getChildren().setAll(getItems(dayNumber));

        }

        private List<JFXButton> getItems(Integer selectedDay) {
            List<JFXButton> buttonList = new ArrayList<>();
            for (int i = 1; i <= 31; i++) {
                JFXButton btnDay = new JFXButton(String.valueOf(i));
                btnDay.setId(String.valueOf(i));
                btnDay.setOnAction(event -> {
                    JFXButton current = (JFXButton) event.getSource();
                    setDay(Integer.parseInt(current.getId()));
                });
                if (selectedDay.equals(i)) {
                    btnDay.setStyle("-fx-background-color: #fdd835;");
                }
                buttonList.add(btnDay);
            }

            return buttonList;
        }
    }
}

