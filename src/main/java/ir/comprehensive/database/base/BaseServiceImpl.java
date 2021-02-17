package ir.comprehensive.database.base;

import ir.comprehensive.database.exception.DatabaseException;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static ir.comprehensive.database.exception.DatabaseException.ExceptionType.ERROR_IN_DELETE;
import static ir.comprehensive.database.exception.DatabaseException.ExceptionType.ERROR_IN_UPDATE;

public abstract class BaseServiceImpl<E extends BaseEntity<I>, M extends BaseModel<I>, P extends BaseMapper<E, M, I>, R extends BaseRepository<E, I>, I extends Serializable> implements BaseService<E, M, I> {

    protected final P mapper;

    protected final R repository;

    public BaseServiceImpl(P mapper, R repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Optional<M> findById(I id) {
        return repository.findById(id).map(mapper::entityToModel);
    }

    @Override
    public List<M> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public M save(M model) throws DatabaseException {
        E savedEntity = repository.save(mapper.modelToEntity(model));
        return mapper.entityToModel(savedEntity);
    }

    @Override
    public M update(M modelForUpdate) throws DatabaseException {
        throw DatabaseException.of(ERROR_IN_UPDATE, "Method not implemented");
    }

    @Override
    public void delete(M model) throws DatabaseException {
        if (Objects.isNull(model) || Objects.isNull(model.getId()))
            throw DatabaseException.of(ERROR_IN_DELETE, "Model is null");

        repository.deleteById(model.getId());
    }

    @Override
    public void delete(I id) throws DatabaseException {
        repository.deleteById(id);
    }
}
