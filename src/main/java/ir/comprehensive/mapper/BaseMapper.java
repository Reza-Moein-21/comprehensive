package ir.comprehensive.mapper;

public interface BaseMapper<E, M> {
    M entityToModel(E entity);

    E modelToEntity(M dto);
}
