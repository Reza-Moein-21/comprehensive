package ir.comprehensive.entity.base;

import java.io.Serializable;

public interface BaseMapper<E extends BaseEntity<I>, M extends BaseModel<I>, I extends Serializable> {

    M entityToModel(E entity);

    E modelToEntity(M model);

}
