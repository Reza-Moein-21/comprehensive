package ir.comprehensive.utils;

import com.jfoenix.validation.RequiredFieldValidator;
import javafx.scene.Node;

import static ir.comprehensive.utils.MessageUtils.getMessage;

public class FormValidationUtils {

    private FormValidationUtils() {
    }

    public static RequiredFieldValidator getRequiredFieldValidator(String fieldTitle, Node icon) {
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        requiredFieldValidator.setMessage(fieldTitle + " " + getMessage("required"));
        requiredFieldValidator.setIcon(icon);
        return requiredFieldValidator;
    }
}
