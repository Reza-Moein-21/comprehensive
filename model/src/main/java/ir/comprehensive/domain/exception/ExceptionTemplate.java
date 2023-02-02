package ir.comprehensive.domain.exception;

public interface ExceptionTemplate {
    String getCode();

    String getReason();

    default String message() {
        return String.format("%s[%s:%s] ", getClass().getSimpleName(), getCode(), getReason());
    }
}
