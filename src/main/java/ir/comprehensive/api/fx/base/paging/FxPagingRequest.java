package ir.comprehensive.api.fx.base.paging;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class FxPagingRequest<T, E extends BaseSortPropertyOptionEnum> {
    private final boolean isNoPaging;
    private int pageNumber;
    private int pageSize;
    private final T request;
    private final FxPagingSort<E>[] sorts;

    @SafeVarargs
    public static <T, E extends BaseSortPropertyOptionEnum> FxPagingRequest<T, E> of(int pageNumber, int pageSize, T request, FxPagingSort<E>... sorts) {
        return new FxPagingRequest<>(false, pageNumber, pageSize, request, sorts);
    }

    @SafeVarargs
    public static <T, E extends BaseSortPropertyOptionEnum> FxPagingRequest<T, E> ofNoPage(T request, FxPagingSort<E>... sorts) {
        return new FxPagingRequest<>(true, request, sorts);
    }

}
