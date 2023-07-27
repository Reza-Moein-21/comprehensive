package ir.comprehensive.database.internal.mapper;

import ir.comprehensive.domain.model.base.DomainModel;

public interface BaseMapper<M extends DomainModel<?>, R extends org.jooq.Record> {
    M recordToModel(R record);
}