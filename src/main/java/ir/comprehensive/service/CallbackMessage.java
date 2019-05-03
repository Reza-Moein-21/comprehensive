package ir.comprehensive.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallbackMessage<T> {
    private String callbackMessage;
    private T callbackResult;
}
