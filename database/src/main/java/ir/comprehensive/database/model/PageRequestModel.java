package ir.comprehensive.database.model;

import java.util.Optional;


/**
 * @param page Current page number requested. minimum is one.
 * @param size Size of each page
 * @param sorts Arrays of sort
 */
public record PageRequestModel(int page, int size, SortModel[] sorts) {

    public static PageRequestModel of(int page, int size, SortModel... sorts) {
        return new PageRequestModel(page, size, Optional.ofNullable(sorts).orElse(new SortModel[0]));
    }

    public static PageRequestModel ofSize(int pageSize) {
        return of(1, pageSize);
    }


    public record SortModel(String property, DirectionEnum direction) {
        public enum DirectionEnum {
            ASC, DESC
        }

    }
}
