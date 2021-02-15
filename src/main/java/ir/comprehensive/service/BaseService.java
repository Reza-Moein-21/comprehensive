package ir.comprehensive.service;

import ir.comprehensive.fxmodel.basemodel.BaseModel;
import ir.comprehensive.service.extra.Swappable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BaseService<E, M extends BaseModel> extends Swappable<E> {

    Page<M> loadItem(M searchModel, PageRequest pageRequest);

    default Page<M> loadItem(PageRequest pageRequest) {
        return loadItem(null, pageRequest);
    }

}
