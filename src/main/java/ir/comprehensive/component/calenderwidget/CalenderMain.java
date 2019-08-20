package ir.comprehensive.component.calenderwidget;

import com.github.mfathi91.time.PersianDate;
import com.github.mfathi91.time.PersianMonth;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

class CalenderMain extends StackPane {
    PersianMonth persianMonth;
    Integer year;
    ListProperty<CalenderEvent> events = new SimpleListProperty<>(this, "events");

    public CalenderMain(CalenderHeader calenderHeader, ListProperty<CalenderEvent> eventList) {
        persianMonth = calenderHeader.getPersianMonth();
        year = calenderHeader.getYear();
        events.bind(eventList);
        events.addListener((observable, oldValue, newValue) -> refresh());

        refresh();
    }

    private boolean refresh() {
        return this.getChildren().setAll(render());
    }

    private Node render() {
        GridPane grdCenter = new GridPane();
        grdCenter.setHgap(ScreenUtils.getActualSize(10));
        grdCenter.setVgap(ScreenUtils.getActualSize(10));

        Label lblSat = new Label(MessageUtils.Calender.SATURDAY);
        Label lblSun = new Label(MessageUtils.Calender.SUNDAY);
        Label lblMon = new Label(MessageUtils.Calender.MONDAY);
        Label lblTue = new Label(MessageUtils.Calender.TUESDAY);
        Label lblWed = new Label(MessageUtils.Calender.WEDNESDAY);
        Label lblThu = new Label(MessageUtils.Calender.THURSDAY);
        Label lblFri = new Label(MessageUtils.Calender.FRIDAY);

        String hbxStyle = "-fx-alignment: center";


        HBox hbxSat = new HBox(lblSat);
        hbxSat.setStyle(hbxStyle);
        HBox hbxSun = new HBox(lblSun);
        hbxSun.setStyle(hbxStyle);
        HBox hbxMon = new HBox(lblMon);
        hbxMon.setStyle(hbxStyle);
        HBox hbxTue = new HBox(lblTue);
        hbxTue.setStyle(hbxStyle);
        HBox hbxWed = new HBox(lblWed);
        hbxWed.setStyle(hbxStyle);
        HBox hbxThu = new HBox(lblThu);
        hbxThu.setStyle(hbxStyle);
        HBox hbxFri = new HBox(lblFri);
        hbxFri.setStyle(hbxStyle);


        grdCenter.add(hbxSat, DayName.SATURDAY.getIndex(), 0);
        grdCenter.add(hbxSun, DayName.SUNDAY.getIndex(), 0);
        grdCenter.add(hbxMon, DayName.MONDAY.getIndex(), 0);
        grdCenter.add(hbxTue, DayName.TUESDAY.getIndex(), 0);
        grdCenter.add(hbxWed, DayName.WEDNESDAY.getIndex(), 0);
        grdCenter.add(hbxThu, DayName.THURSDAY.getIndex(), 0);
        grdCenter.add(hbxFri, DayName.FRIDAY.getIndex(), 0);


        int currentRow = 1;

        for (int i = 1; i <= getMaxMonthLength(persianMonth.getValue()); i++) {
            PersianDate currentDate = PersianDate.of(year, persianMonth, i);
            DayName week = DayName.valueOf(currentDate.getDayOfWeek().name());

            CalenderDay calenderDay = new CalenderDay();
            VBox vBox = new VBox();
            if (events != null && !events.isEmpty()) {
                String lblStyle = "-fx-font-size: " + ScreenUtils.getActualSize(22);

                CalenderEvent calenderEvent1 = events.get(events.size() - 1);
                if (calenderEvent1.getTime().equals(currentDate.toGregorian())) {
                    Label lblEventTitle1 = new Label();
                    lblEventTitle1.setStyle(lblStyle);
                    lblEventTitle1.setText(calenderEvent1.getTitle());
                    vBox.getChildren().add(lblEventTitle1);
                }

                if (events.size() > 1) {
                    Label lblEventTitle2 = new Label();
                    CalenderEvent calenderEvent2 = events.get(events.size() - 2);
                    if (calenderEvent2.getTime().equals(currentDate.toGregorian())) {
                        lblEventTitle2.setStyle(lblStyle);
                        lblEventTitle2.setText(calenderEvent2.getTitle());
                        vBox.getChildren().add(lblEventTitle2);
                    }
                }

            }
            calenderDay.setContent(vBox);

            calenderDay.setOnMouseClicked(event -> {
                System.out.println("**(*(");
            });
            calenderDay.setStyle(String.format("-fx-background-color: #ffff;-fx-padding: %s;-fx-pref-width: %s ;-fx-pref-height: %s ;-fx-border-radius: %s;-fx-border-color: #9e9e9e;-fx-border-width: %s",
                    ScreenUtils.getActualSize(5), ScreenUtils.getActualSize(150), ScreenUtils.getActualSize(130), ScreenUtils.getActualSize(5), ScreenUtils.getActualSize(3)
            ));
            calenderDay.setDayNumber(i);

            grdCenter.setStyle("-fx-padding: " + ScreenUtils.getActualSize(10) + ";");
            grdCenter.add(calenderDay, week.getIndex(), week.getIndex() == DayName.FRIDAY.getIndex() ? currentRow++ : currentRow);
        }

        return grdCenter;
    }

    private int getMaxMonthLength(int monthNumber) {
        if (monthNumber == 12) {
            return 29;
        }
        if (monthNumber <= 6) {
            return 31;
        }
        return 30;
    }
}
