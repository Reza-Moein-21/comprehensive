package ir.comprehensive.fxmapper;

public interface BaseFxMapper<E, M> {
    M entityToModel(E entity);

    E modelToEntity(M dto);
}
