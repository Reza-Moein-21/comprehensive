package ir.comprehensive.database.mapper.base;

import ir.comprehensive.database.entity.base.BaseEntity;
import ir.comprehensive.domain.model.base.DomainModel;

import java.io.Serializable;

public interface BaseMapper<E extends BaseEntity<I>, M extends DomainModel<I>, I extends Serializable> {

    M entityToModel(E entity);

    E modelToEntity(M model);

}
