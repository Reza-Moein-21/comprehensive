package ir.comperhensive.utils;

import java.util.ResourceBundle;

public class MessageUtils {
    private static ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public static String getMessage(String key) {
        return bundle.getString(key);
    }
}
