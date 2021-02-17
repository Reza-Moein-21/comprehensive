package ir.comprehensive.database.base;

import ir.comprehensive.database.exception.DatabaseException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity<I>, M extends BaseModel<I>, I extends Serializable> {

    Optional<M> findById(I id);

    List<M> findAll();

    M save(M model) throws DatabaseException;

    M update(M modelForUpdate) throws DatabaseException;

    void delete(M model) throws DatabaseException;

    void delete(I id) throws DatabaseException;
}
