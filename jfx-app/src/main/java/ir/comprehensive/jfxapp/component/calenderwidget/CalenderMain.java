package ir.comprehensive.jfxapp.component.calenderwidget;

import animatefx.animation.FadeIn;
import com.github.mfathi91.time.PersianDate;
import com.github.mfathi91.time.PersianMonth;
import ir.comprehensive.jfxapp.component.JavaFxComponent;
import ir.comprehensive.jfxapp.utils.MessageUtils;
import ir.comprehensive.jfxapp.utils.ScreenUtils;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.StringJoiner;

class CalenderMain extends JavaFxComponent {
    ObjectProperty<LocalDate> time;

    ListProperty<CalenderEvent> events = new SimpleListProperty<>(this, "events");

    public CalenderMain(ObjectProperty<LocalDate> time, ListProperty<CalenderEvent> eventList) {
        this.time = time;

        events.bind(eventList);
        events.addListener((observable, oldValue, newValue) -> refresh());
        this.time.addListener((observable, oldValue, newValue) -> refresh());
        refresh();
    }

    public void refresh() {
        this.getChildren().setAll(render());
        FadeIn fadeIn = new FadeIn(this);
        fadeIn.setSpeed(2);
        fadeIn.play();
    }

    private HBox getDayLabel(Label dayLabel) {

        HBox hbxSat = new HBox(dayLabel);
        hbxSat.setStyle(new StringJoiner(" ; ")
                .add("-fx-background-color: #eeeeee")
                .add("-fx-alignment: center")
                .add("-fx-padding: " + ScreenUtils.getActualSize(5))
                .add("-fx-pref-width:  " + ScreenUtils.getActualSize(170))
                .add("-fx-pref-height: " + ScreenUtils.getActualSize(50))
                .add("-fx-border-radius: " + ScreenUtils.getActualSize(5))
                .add("-fx-background-radius: " + ScreenUtils.getActualSize(5))
                .add("-fx-border-color: #9e9e9e")
                .add("-fx-border-width: " + ScreenUtils.getActualSize(2)).toString());
        return hbxSat;
    }

    @Override
    protected Node render() {
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

        grdCenter.add(getDayLabel(lblSat), DayName.SATURDAY.getIndex(), 0);
        grdCenter.add(getDayLabel(lblSun), DayName.SUNDAY.getIndex(), 0);
        grdCenter.add(getDayLabel(lblMon), DayName.MONDAY.getIndex(), 0);
        grdCenter.add(getDayLabel(lblTue), DayName.TUESDAY.getIndex(), 0);
        grdCenter.add(getDayLabel(lblWed), DayName.WEDNESDAY.getIndex(), 0);
        grdCenter.add(getDayLabel(lblThu), DayName.THURSDAY.getIndex(), 0);
        grdCenter.add(getDayLabel(lblFri), DayName.FRIDAY.getIndex(), 0);


        int currentRow = 1;
        PersianMonth persianMonth = PersianDate.fromGregorian(time.get()).getMonth();
        Integer year = PersianDate.fromGregorian(time.get()).getYear();
        Integer day = PersianDate.fromGregorian(time.get()).getDayOfMonth();

        for (int i = 1; i <= getMaxMonthLength(persianMonth.getValue()); i++) {
            PersianDate currentDate = PersianDate.of(year, persianMonth, i);
            DayName week = DayName.valueOf(currentDate.getDayOfWeek().name());

            CalenderDay calenderDay = new CalenderDay();
            calenderDay.isSelected = (i == day);

            // check is current day
            PersianDate selectedTime = PersianDate.fromGregorian(time.getValue());
            PersianMonth selectedMonth = selectedTime.getMonth();
            int selectedYear = selectedTime.getYear();
            PersianDate now = PersianDate.now();
            PersianMonth currentMonth = now.getMonth();
            int currentYear = now.getYear();

            if (selectedYear == currentYear && selectedMonth == currentMonth) {
                calenderDay.isCurrentDay = (i == PersianDate.now().getDayOfMonth());
            }

            // TODO must move this code to CalenderDay component
            VBox vBox = new VBox();
            if (events != null && !events.isEmpty()) {
                String lblStyle = "-fx-font-size: " + ScreenUtils.getActualSize(20);

                // TODO most complete latter
                events.forEach(calenderEvent -> {
                    if (calenderEvent.getTime().equals(currentDate.toGregorian())) {
                    Label lblEventTitle1 = new Label();
                    lblEventTitle1.setStyle(lblStyle);
                        lblEventTitle1.setText(calenderEvent.getTitle());
                    vBox.getChildren().add(lblEventTitle1);
                }

                });

            }
            calenderDay.setContent(vBox);

            calenderDay.setOnMouseClicked(event -> {
                PersianDate d = PersianDate.fromGregorian(time.get());

                Integer selectedDay = calenderDay.dayNumber.getValue();

                time.set(PersianDate.of(d.getYear(), d.getMonth(), selectedDay).toGregorian());

            });

            calenderDay.setDayNumber(i);

            grdCenter.setStyle("-fx-padding: " + ScreenUtils.getActualSize(10) + ";-fx-alignment: center");
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
