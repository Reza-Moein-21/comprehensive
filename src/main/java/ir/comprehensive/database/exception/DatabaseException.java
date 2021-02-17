package ir.comprehensive.database.exception;

import java.util.Objects;

public class DatabaseException extends RuntimeException {

    private DatabaseException() {
    }

    private DatabaseException(String message) {
        super(message);
    }

    private DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    private DatabaseException(Throwable cause) {
        super(cause);
    }

    private DatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static DatabaseException of(ExceptionType type, String message) {
        if (Objects.isNull(type) || Objects.isNull(message))
            return new DatabaseException();

        return new DatabaseException(String.join(":", type.getTitle(), message));
    }

    public enum ExceptionType {
        ERROR_IN_SAVE("Error save entity"),
        ERROR_IN_DELETE("Error delete entity"),
        ERROR_IN_UPDATE("Error update entity"),
        ;

        ExceptionType(String title) {
            this.title = title;
        }

        private String title;

        public String getTitle() {
            return title;
        }
    }

}
