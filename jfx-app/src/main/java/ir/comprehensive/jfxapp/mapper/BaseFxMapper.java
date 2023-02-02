package ir.comprehensive.jfxapp.mapper;

import ir.comprehensive.domain.model.base.DomainModel;
import ir.comprehensive.jfxapp.model.basemodel.BaseFxModel;

import java.io.Serializable;

public interface BaseFxMapper<M extends DomainModel<I>, FM extends BaseFxModel, I extends Serializable> {
    FM modelToFxModel(M entity);

    M fxModelToModel(FM dto);
}
