package ir.comprehensive.component.calenderwidget;

enum DayName {
    SATURDAY(0), SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4), THURSDAY(5), FRIDAY(6);

    int index;

    DayName(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
