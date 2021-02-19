package ir.comprehensive.api.fx.enums;

import ir.comprehensive.api.fx.base.paging.BaseSortPropertyOptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HadisSortPropertyOptionEnum implements BaseSortPropertyOptionEnum {
    ID("id"),
    ;
    private final String propertyName;
}
