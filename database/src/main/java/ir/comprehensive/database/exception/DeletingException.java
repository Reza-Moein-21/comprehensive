package ir.comprehensive.database.exception;

import ir.comprehensive.domain.exception.BaseRuntimeException;

public class DeletingException extends BaseRuntimeException {
    public DeletingException(String code, String reason) {
        super(code, reason);
    }
}