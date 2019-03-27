package ir.comperhensive.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class MessageUtils {
    private static ResourceBundle messageBundle = ResourceBundle.getBundle("messages", new ResourceControl());

    private MessageUtils() {
    }

    public static String getMessage(String key) {
        return messageBundle.getString(key);
    }

    public static ResourceBundle getMessageBundle() {
        return messageBundle;
    }
}

class ResourceControl extends ResourceBundle.Control {
    @Override
    public ResourceBundle newBundle(String baseName, Locale locale,
                                    String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException,
            IOException {
        String bundleName = toBundleName(baseName, locale);
        String resName = toResourceName(bundleName, "properties");
        InputStream stream = loader.getResourceAsStream(resName);
        assert stream != null;
        return new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
    }

}
