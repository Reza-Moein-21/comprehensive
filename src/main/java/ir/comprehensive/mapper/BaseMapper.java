package ir.comprehensive.mapper;

public interface BaseMapper<E, D> {
    D entityToDto(E entity);

    E dtoToEntity(D dto);
}
