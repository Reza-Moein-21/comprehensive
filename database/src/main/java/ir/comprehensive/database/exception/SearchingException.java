package ir.comprehensive.database.exception;

import ir.comprehensive.domain.exception.BaseRuntimeException;

public class SearchingException extends BaseRuntimeException {

    public SearchingException(String code, String reason) {
        super(code, reason);
    }
}
