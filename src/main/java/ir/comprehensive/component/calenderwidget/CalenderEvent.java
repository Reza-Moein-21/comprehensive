package ir.comprehensive.component.calenderwidget;

import java.time.LocalDate;

public class CalenderEvent {
    String title;
    LocalDate time;
    String startDate;
    String endDate;

    public CalenderEvent(LocalDate time) {
        this.time = time;

    }

    public CalenderEvent(LocalDate time, String title) {
        this(time);
        this.title = title;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
