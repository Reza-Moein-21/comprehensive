package ir.comprehensive.api.fx;

import ir.comprehensive.api.fx.base.FxRequest;
import ir.comprehensive.api.fx.base.FxResponse;
import ir.comprehensive.api.fx.base.PagingSupport;
import ir.comprehensive.api.fx.enums.HadisSortPropertyOptionEnum;
import ir.comprehensive.api.fx.model.HadisFxModel;

public interface HadisApi extends PagingSupport<HadisFxModel, HadisFxModel, HadisSortPropertyOptionEnum> {

    void delete(Long id);

    FxResponse<HadisFxModel> getRandomHadis();

    FxResponse<HadisFxModel> saveOrUpdate(FxRequest<HadisFxModel> hadis);
}
