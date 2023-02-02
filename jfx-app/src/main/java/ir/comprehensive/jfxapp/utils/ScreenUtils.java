package ir.comprehensive.jfxapp.utils;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Screen;

public class ScreenUtils {
    public static final double LOW = 0.25;
    public static final double MID = 0.50;
    public static final double HI = 0.70;
    public static final double EXTRA = 1;
    public static final double _4K_RESOLUTION = 2160;

    public DoubleProperty scaleFactor = new SimpleDoubleProperty(ScreenUtils.getScale());

    public static double getScale() {
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        return 1 / (_4K_RESOLUTION / screenHeight);
    }

    public static double getActualSize(double size) {
        return size * ScreenUtils.getScale();
    }

    public double getScaleFactor() {
        return scaleFactor.get();
    }

    public DoubleProperty scaleFactorProperty() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor.set(scaleFactor);
    }
}
