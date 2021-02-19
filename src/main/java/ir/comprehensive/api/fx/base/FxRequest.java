package ir.comprehensive.api.fx.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FxRequest<T> {
    private T request;

    public static <T> FxRequest<T> of(T request) {
        return new FxRequest<>(request);
    }
}
