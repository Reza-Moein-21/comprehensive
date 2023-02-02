package ir.comprehensive.database.exception;

import ir.comprehensive.domain.exception.BaseRuntimeException;

public class PersistingException extends BaseRuntimeException {
    public PersistingException(String code, String reason) {
        super(code, reason);
    }
}
