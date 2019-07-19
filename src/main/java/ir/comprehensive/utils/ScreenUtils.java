package ir.comprehensive.utils;

import javafx.stage.Screen;

public class ScreenUtils {
    public static final double LOW = 0.25;
    public static final double MID = 0.50;
    public static final double HI = 0.70;
    public static final double EXTRA = 1;

    public static double getScale() {
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        double scale = 0;
        if (screenHeight >= 480 && screenHeight < 720) {
            scale = LOW;
        } else if (screenHeight >= 720 && screenHeight < 1020) {
            scale = MID;
        } else if (screenHeight >= 1020 && screenHeight < 1440) {
            scale = HI;
        } else if (screenHeight >= 1440) {
            scale = EXTRA;
        }
        return scale;
    }

    public static double getActualSize(double size) {
        return size * ScreenUtils.getScale();
    }
}
