package ir.comprehensive.utils;

import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidationUtils {

    private FormValidationUtils() {
    }

    public static RequiredFieldValidator getRequiredFieldValidator(String fieldTitle) {
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        requiredFieldValidator.setMessage(fieldTitle + " " + MessageUtils.Message.REQUIRED);
        return requiredFieldValidator;
    }

    public static EmailFieldValidator getEmailFieldValidator() {
        EmailFieldValidator emailFieldValidator = new EmailFieldValidator();
        emailFieldValidator.setMessage(MessageUtils.Message.WRONG_EMAIL_FORMAT);
        return emailFieldValidator;
    }

    public static PhoneNumberValidator getPhoneNumberValidator() {
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        phoneNumberValidator.setMessage(MessageUtils.Message.WRONG_PHONE_NUMBER_FORMAT);
        return phoneNumberValidator;
    }

    private static class EmailFieldValidator extends ValidatorBase {
        public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        @Override
        protected void eval() {
            if (srcControl.get() instanceof TextInputControl) {
                TextInputControl textField = (TextInputControl) srcControl.get();
                boolean isNotNull = textField.getText() != null && !textField.getText().isEmpty();
                if (isNotNull) {
                    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(textField.getText());
                    hasErrors.set(!matcher.find());
                } else {
                    hasErrors.set(false);
                }
            }
        }
    }

    private static class PhoneNumberValidator extends ValidatorBase {
        public static final Pattern VALID_PHONE_NUMBER_REGEX =
                Pattern.compile("^(\\+98|0)?[0-9]\\d{9}$", Pattern.CASE_INSENSITIVE);

        @Override
        protected void eval() {
            if (srcControl.get() instanceof TextInputControl) {
                TextInputControl textField = (TextInputControl) srcControl.get();
                Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(textField.getText() != null ? textField.getText() : "");
                boolean isNotNull = textField.getText() != null && !textField.getText().isEmpty();
                if (isNotNull) {
                    hasErrors.set(!matcher.find());
                } else {
                    hasErrors.set(false);
                }
            }
        }

    }
}
