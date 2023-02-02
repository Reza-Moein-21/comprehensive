package ir.comprehensive.jfxapp.utils;

public class StringUtils {
    private StringUtils() {
    }

    public static String makeAnyMatch(String value) {
        return "%" + value.toUpperCase() + "%";
    }
}
