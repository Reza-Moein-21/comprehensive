package ir.comprehensive.api.fx.base.paging;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FxPagingSort<E extends BaseSortPropertyOptionEnum> {
    private E sortOption;
    private FxSortType sortType;

    public static <E extends BaseSortPropertyOptionEnum> FxPagingSort<E> of(E sortOption, FxSortType sortType) {
        return new FxPagingSort<>(sortOption, sortType);
    }
}
