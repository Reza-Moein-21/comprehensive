package ir.comprehensive.api.fx.base;

import ir.comprehensive.api.fx.base.paging.BaseSortPropertyOptionEnum;
import ir.comprehensive.api.fx.base.paging.FxPagingRequest;
import ir.comprehensive.api.fx.base.paging.FxPagingResponse;

public interface PagingSupport<RQ, RS, E extends BaseSortPropertyOptionEnum> {

    FxPagingResponse<RQ> loadPaging(FxPagingRequest<RS, E> pagingRequest);
}
