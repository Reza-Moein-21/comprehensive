package ir.comprehensive.service.response;

@FunctionalInterface
public interface RequestCallback<T> {
    void accept(T result, String message, ResponseStatus status);
}
