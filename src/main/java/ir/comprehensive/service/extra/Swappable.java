package ir.comprehensive.service.extra;


import java.lang.reflect.Field;

public interface Swappable<T> {
    default T swap(T from, T to) {
        Field[] fromFields = from.getClass().getDeclaredFields();
        Class<?> toClass = to.getClass();

        try {
            for (Field fromField : fromFields) {
                fromField.setAccessible(true);
                Field toField = toClass.getDeclaredField(fromField.getName());
                toField.setAccessible(true);
                toField.set(to, fromField.get(from));
            }
            return to;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    default String getNumberString(long number) {
        if (number >= 1000 && number < 1_000_000) {
            return String.format("%.1f K", (number * 1.0 / 1_000));
        } else if (number >= 1_000_000) {
            return String.format("%.1f M", (number * 1.0 / 1_000_000));
        }
        return String.valueOf(number);

    }
}
