package ir.comprehensive.api.fx.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FxResponse<T> {
    private boolean hasError;
    private String message;
    private T response;

    public static <T> FxResponse<T> of(boolean hasError, String message, T response) {
        return new FxResponse<>(hasError, message, response);
    }

    public static <T> FxResponse<T> ofSuccess(T response) {
        return FxResponse.ofSuccess("Success response", response);
    }

    public static <T> FxResponse<T> ofSuccess(String message, T response) {
        return FxResponse.of(false, message, response);
    }

    public static <T> FxResponse<T> ofError(String message) {
        return FxResponse.ofError(message, null);
    }

    public static <T> FxResponse<T> ofError(T response) {
        return FxResponse.ofError("Error response", response);
    }

    public static <T> FxResponse<T> ofError(String message, T response) {
        return FxResponse.of(true, message, response);
    }


}
