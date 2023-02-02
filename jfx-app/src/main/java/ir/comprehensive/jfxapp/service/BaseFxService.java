package ir.comprehensive.jfxapp.service;

import ir.comprehensive.fxmodel.basemodel.BaseFxModel;
import ir.comprehensive.service.extra.Swappable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BaseFxService<E, M extends BaseFxModel> extends Swappable<E> {

    Page<M> loadItem(M searchModel, PageRequest pageRequest);

    default Page<M> loadItem(PageRequest pageRequest) {
        return loadItem(null, pageRequest);
    }

}
