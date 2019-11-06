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
        public static final String IN_OUT_CONTROL = getMessage("inOutControl");
        public static final String PROJECT = getMessage("project");
        public static final String SEARCH_AND_CREATE = getMessage("searchAndCreate");
        public static final String CREATE = getMessage("create");
        public static final String INVENTORY = getMessage("inventory");
        public static final String FULL_NAME = getMessage("fullName");
        public static final String PRODUCT_NAME = getMessage("productName");
        public static final String PRODUCT = getMessage("product");
        public static final String PRODUCT_STATUS = getMessage("productStatus");
        public static final String PROJECT_DETAIL_STATUS = getMessage("projectDetailStatus");
        public static final String DESCRIPTION = getMessage("description");
        public static final String DELIVERY_DATE = getMessage("deliveryDate");
        public static final String DELIVERY_DATE_FROM = getMessage("deliveryDateFrom");
        public static final String DELIVERY_DATE_TO = getMessage("deliveryDateTo");
        public static final String DESIRED_DATE = getMessage("desiredDate");
        public static final String RECEIVED_DATE = getMessage("receivedDate");
        public static final String RECEIVED_DATE_FROM = getMessage("receivedDateFrom");
        public static final String RECEIVED_DATE_TO = getMessage("receivedDateTo");
        public static final String YEAR = getMessage("year");
        public static final String MONTH = getMessage("month");
        public static final String DAY = getMessage("day");
        public static final String SEARCH = getMessage("search");
        public static final String SELECT_CREATION_TIME_BY_CALENDER = getMessage("selectCreationTimeByCalender");
        public static final String SELECT_CREATION_TIME_BY_DATE_RANGE = getMessage("selectCreationTimeByDateRange");
        public static final String ADVANCE_SEARCH = getMessage("advanceSearch");
        public static final String SAVE = getMessage("save");
        public static final String REQUIRED = getMessage("required");
        public static final String WRONG_COUNT = getMessage("wrongCount");
        public static final String CREATE_PRODUCT = getMessage("createProduct");
        public static final String CREATE_PERSON = getMessage("createPerson");
        public static final String CREATE_CATEGORY = getMessage("createCategory");
        public static final String CANCEL = getMessage("cancel");
        public static final String OK = getMessage("ok");
        public static final String CLEAR = getMessage("clear");
        public static final String SUCCESS_LOAD = getMessage("successLoad");
        public static final String SUCCESS_SAVE = getMessage("successSave");
        public static final String SUCCESS_UPDATE = getMessage("successUpdate");
        public static final String SUCCESS_DELETE = getMessage("successDelete");
        public static final String HUMAN_RESOURCE = getMessage("humanResource");
        public static final String CATEGORIES = getMessage("categories");
        public static final String CATEGORY = getMessage("category");
        public static final String MY_NOTE_CATEGORY = getMessage("myNoteCategory");
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
        public static final String ERROR_IN_SAVE = getMessage("errorInSave");
        public static final String PERSON_NOT_UNIQUE = getMessage("personNotUnique");
        public static final String CATEGORY_NOT_UNIQUE = getMessage("categoryNotUnique");
        public static final String MY_NOTE_CATEGORY_NOT_UNIQUE = getMessage("myNoteCategoryNotUnique");
        public static final String YES = getMessage("yes");
        public static final String NO = getMessage("no");
        public static final String CONFIRM_DELETE = getMessage("confirmDelete");
        public static final String WRONG_EMAIL_FORMAT = getMessage("wrongEmailFormat");
        public static final String WRONG_PHONE_NUMBER_FORMAT = getMessage("wrongPhoneNumberFormat");
        public static final String MAX_DATE_DENIED = getMessage("maxDateDenied");
        public static final String MIN_DATE_DENIED = getMessage("minDateDenied");
        public static final String BETWEEN_DATE_DENIED = getMessage("betweenDateDenied");
        public static final String FAIL_OPERATION = getMessage("failOperation");
        public static final String PEOPLE = getMessage("people");
        public static final String NOT_FOUND = getMessage("notFound");
        public static final String UNKNOWN = getMessage("unknown");
        public static final String LOST = getMessage("lost");
        public static final String USE_IN = getMessage("useIn");
        public static final String TODAY_DATE = getMessage("todayDate");
        public static final String CREATION_DATE = getMessage("creationDate");
        public static final String IS_ACTIVE = getMessage("isActive");
        public static final String SHOW = getMessage("show");
        public static final String CREATION_DATE_FROM = getMessage("creationDateFrom");
        public static final String CREATION_DATE_TO = getMessage("creationDateTo");
        public static final String WORK_DONE_DATE = getMessage("WorkDoneDate");
        public static final String MY_NOTE = getMessage("myNote");
        public static final String PRIORITY = getMessage("priority");
        public static final String IN_PROGRESS = getMessage("inProgress");
        public static final String DONE = getMessage("done");
        public static final String VISIT = getMessage("visit");
        public static final String NONE = getMessage("none");
        public static final String NUMBER_SIGN = getMessage("numberSign");
        public static final String START_PAGE = getMessage("startPage");
        public static final String HOME = getMessage("home");
        public static final String ENTER = getMessage("enter");
        public static final String STOPPED = getMessage("stopped");
        public static final String ACTIVE = getMessage("active");
        public static final String INACTIVE = getMessage("inActive");
        public static final String WAREHOUSE = getMessage("warehouse");
        public static final String WAREHOUSE_CATEGORY = getMessage("warehouseCategory");
        public static final String CODE = getMessage("code");
        public static final String COMPANY_NAME = getMessage("companyName");
        public static final String COUNT = getMessage("count");
        public static final String  WAREHOUSE_TAG = getMessage("warehouseTag");


        private Message() {
        }
    }

    public static final class Calender {

        public static final String TODAY = getMessage("today");

        public static final String SATURDAY = getMessage("saturday");
        public static final String SUNDAY = getMessage("sunday");
        public static final String MONDAY = getMessage("monday");
        public static final String TUESDAY = getMessage("tuesday");
        public static final String WEDNESDAY = getMessage("wednesday");
        public static final String THURSDAY = getMessage("thursday");
        public static final String FRIDAY = getMessage("friday");

        private Calender() {
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
