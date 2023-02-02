package ir.comprehensive.domain.exception;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class BaseException extends Exception implements ExceptionTemplate {
    private String code;
    private String reason;
}
