package ir.comprehensive.database.model;

public record SearchCriteria(String propertyPath, boolean ignoreNull, Type type, Object value) {

    public static SearchCriteria ofIgnoreNull(String propertyPath, Type criteriaType, Object value) {
        return new SearchCriteria(propertyPath, true, criteriaType, value);
    }

    public static SearchCriteria of(String propertyPath, Type criteriaType, Object value) {
        return new SearchCriteria(propertyPath, false, criteriaType, value);
    }

    public enum Type {
        LIKE, LIKE_FIRST, LIKE_END, EQUALS
    }

}
