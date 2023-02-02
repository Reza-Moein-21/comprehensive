package ir.comprehensive.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class BaseRuntimeException extends RuntimeException implements ExceptionTemplate {
    private final String code;
    private final String reason;

}
