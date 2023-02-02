package ir.comprehensive.jfxapp.utils;

import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import ir.comprehensive.jfxapp.component.datepicker.SimpleDatePicker;
import javafx.scene.control.TextInputControl;

import java.time.LocalDate;
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

    public static CountValidator getCountValidator(String fieldTitle) {
        CountValidator countValidator = new CountValidator();
        countValidator.setMessage(MessageUtils.Message.WRONG_COUNT.replace("{0}", fieldTitle));
        return countValidator;
    }

    public static MaxNumberValidator getMaxNumberValidator(Long maxValue) {
        MaxNumberValidator maxNumberValidator = new MaxNumberValidator(maxValue);
        maxNumberValidator.setMessage(MessageUtils.Message.MAX_COUNT_VIOLATED);
        return maxNumberValidator;
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

    public static DateValidator getMaxDateValidator(String fieldTitle, LocalDate maxDate, String maxDateTitle) {
        return new DateValidator(fieldTitle, maxDate, maxDateTitle, null, null);
    }

    public static DateValidator getMinDateValidator(String fieldTitle, LocalDate minDate, String minDateTitle) {
        return new DateValidator(fieldTitle, null, null, minDate, minDateTitle);
    }

    public static DateValidator getBetweenDateValidator(String fieldTitle, LocalDate maxDate, String maxDateTitle, LocalDate minDate, String minDateTitle) {
        return new DateValidator(fieldTitle, maxDate, maxDateTitle, minDate, minDateTitle);
    }

    private static class DateValidator extends ValidatorBase {
        private LocalDate minDate;
        private LocalDate maxDate;
        private String fieldTitle;
        private String minDateTitle;
        private String maxDateTitle;

        public DateValidator(String fieldTitle, LocalDate maxDate, String maxDateTitle, LocalDate minDate, String minDateTitle) {
            this.minDate = minDate;
            this.maxDate = maxDate;
            this.fieldTitle = fieldTitle;
            this.minDateTitle = minDateTitle;
            this.maxDateTitle = maxDateTitle;
        }

        @Override
        protected void eval() {
            if (srcControl.get() != null && srcControl.get().getParent() instanceof SimpleDatePicker) {
                SimpleDatePicker datePicker = (SimpleDatePicker) srcControl.get().getParent();
                if (datePicker.getValue() == null) {
                    hasErrors.set(false);
                    return;
                }
                if (this.minDate != null && this.maxDate != null) {
                    hasErrors.set(datePicker.getValue().isBefore(this.minDate) || datePicker.getValue().isAfter(this.maxDate));
                    setMessage(MessageUtils.Message.BETWEEN_DATE_DENIED.replace("{0}", fieldTitle).replace("{1}", minDateTitle).replace("{2}", maxDateTitle));
                } else if (this.minDate != null) {
                    hasErrors.set(datePicker.getValue().isBefore(this.minDate));
                    setMessage(MessageUtils.Message.MIN_DATE_DENIED.replace("{0}", fieldTitle).replace("{1}", minDateTitle));
                } else if (this.maxDate != null) {
                    hasErrors.set(datePicker.getValue().isAfter(this.maxDate));
                    setMessage(MessageUtils.Message.MAX_DATE_DENIED.replace("{0}", fieldTitle).replace("{1}", maxDateTitle));
                }

            }
        }
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

    private static class MaxNumberValidator extends ValidatorBase {
        public static final Pattern VALID_NUMBER_REGEX =
                Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);
        private Long maxValue;

        public MaxNumberValidator(Long maxValue) {
            this.maxValue = maxValue;
        }

        @Override
        protected void eval() {
            if (srcControl.get() instanceof TextInputControl) {
                TextInputControl textField = (TextInputControl) srcControl.get();
                boolean isNotNull = textField.getText() != null && !textField.getText().isEmpty();
                if (isNotNull) {
                    Matcher matcher = VALID_NUMBER_REGEX.matcher(textField.getText());
                    hasErrors.set(matcher.find() && maxValue != null && (Long.parseLong(textField.getText())) > maxValue);
                } else {
                    hasErrors.set(false);
                }
            }
        }

        public Long getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(Long maxValue) {
            this.maxValue = maxValue;
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

    private static class CountValidator extends ValidatorBase {
        public static final Pattern VALID_COUNT_REGEX =
                Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);

        @Override
        protected void eval() {
            if (srcControl.get() instanceof TextInputControl) {
                TextInputControl textField = (TextInputControl) srcControl.get();
                Matcher matcher = VALID_COUNT_REGEX.matcher(textField.getText() != null ? textField.getText() : "");
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
