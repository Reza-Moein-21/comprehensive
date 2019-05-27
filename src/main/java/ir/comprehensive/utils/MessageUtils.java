package ir.comprehensive.utils;

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


    public static final class Message {
        public static final String APP_TITLE = getMessage("appTitle");
        public static final String RECEIVED = getMessage("received");
        public static final String REJECTED = getMessage("rejected");
        public static final String STOREROOM = getMessage("storeroom");
        public static final String PROJECT = getMessage("project");
        public static final String SEARCH_AND_CREATE = getMessage("searchAndCreate");
        public static final String CREATE = getMessage("create");
        public static final String INVENTORY = getMessage("inventory");
        public static final String FULL_NAME = getMessage("fullName");
        public static final String PRODUCT_NAME = getMessage("productName");
        public static final String PRODUCT = getMessage("product");
        public static final String DESCRIPTION = getMessage("description");
        public static final String DELIVERY_DATE = getMessage("deliveryDate");
        public static final String DESIRED_DATE = getMessage("desiredDate");
        public static final String RECEIVED_DATE = getMessage("receivedDate");
        public static final String YEAR = getMessage("year");
        public static final String MONTH = getMessage("month");
        public static final String DAY = getMessage("day");
        public static final String SEARCH = getMessage("search");
        public static final String SAVE = getMessage("save");
        public static final String REQUIRED = getMessage("required");
        public static final String CREATE_PRODUCT = getMessage("createProduct");
        public static final String CREATE_PERSON = getMessage("createPerson");
        public static final String CANCEL = getMessage("cancel");
        public static final String SUCCESS_SAVE = getMessage("successSave");
        public static final String HUMAN_RESOURCE = getMessage("humanResource");
        public static final String CATEGORIES = getMessage("categories");
        public static final String CATEGORY = getMessage("category");
        public static final String EMAIL = getMessage("email");
        public static final String PHONE_NUMBER = getMessage("phoneNumber");
        public static final String LAST_NAME = getMessage("lastName");
        public static final String FIRST_NAME = getMessage("firstName");
        public static final String PERSON = getMessage("person");
        public static final String TITLE = getMessage("title");
        public static final String FAX = getMessage("fax");
        public static final String ADDRESS = getMessage("address");
        public static final String EDIT = getMessage("edit");
        public static final String EDIT_BUTTON = getMessage("editButton");
        public static final String SHOW_ALL = getMessage("showAll");
        public static final String SELECTED = getMessage("selected");
        public static final String SUGGESTS = getMessage("suggests");
        public static final String DELETE = getMessage("delete");

        private Message() {
        }
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
