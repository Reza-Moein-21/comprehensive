package ir.comprehensive.common.exception;

public abstract class BaseRuntimeException extends RuntimeException implements ExceptionTemplate {
    private final String code;
    private final String reason;

    public BaseRuntimeException(String code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getReason() {
        return reason;
    }
}
