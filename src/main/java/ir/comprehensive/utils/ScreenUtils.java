package ir.comprehensive.utils;

import javafx.stage.Screen;

public class ScreenUtils {
    public static final double LOW = 0.25;
    public static final double MID = 0.50;
    public static final double HI = 0.70;
    public static final double EXTRA = 1;
    public static final double _4K_RESOLUTION = 2160;

    public static double getScale() {
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        return 1 / (_4K_RESOLUTION / screenHeight);
    }

    public static double getActualSize(double size) {
        return size * ScreenUtils.getScale();
    }
}
