package ir.comprehensive.database.exception;

import ir.comprehensive.common.exception.BaseRuntimeException;

public class PersistingException extends BaseRuntimeException {
    public PersistingException(String code, String reason) {
        super(code, reason);
    }
}
