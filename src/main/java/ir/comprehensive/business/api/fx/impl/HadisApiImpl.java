package ir.comprehensive.business.api.fx.impl;

import ir.comprehensive.api.fx.HadisApi;
import ir.comprehensive.api.fx.base.FxRequest;
import ir.comprehensive.api.fx.base.FxResponse;
import ir.comprehensive.api.fx.base.paging.FxPagingRequest;
import ir.comprehensive.api.fx.base.paging.FxPagingResponse;
import ir.comprehensive.api.fx.enums.HadisSortPropertyOptionEnum;
import ir.comprehensive.api.fx.model.HadisFxModel;
import org.springframework.stereotype.Component;

@Component
public class HadisApiImpl implements HadisApi {

    @Override
    public void delete(Long id) {

    }

    @Override
    public FxResponse<HadisFxModel> getRandomHadis() {
        return FxResponse.ofError("Not implemented");
    }

    @Override
    public FxResponse<HadisFxModel> saveOrUpdate(FxRequest<HadisFxModel> hadis) {
        return null;
    }


    @Override
    public FxPagingResponse<HadisFxModel> loadPaging(FxPagingRequest<HadisFxModel, HadisSortPropertyOptionEnum> pagingRequest) {
        return FxPagingResponse.<HadisFxModel>builder().build();
    }
}
