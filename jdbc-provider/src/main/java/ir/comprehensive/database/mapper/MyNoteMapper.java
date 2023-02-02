package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.MyNoteEntity;
import ir.comprehensive.database.mapper.base.BaseMapper;
import ir.comprehensive.domain.model.MyNoteModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyNoteMapper extends BaseMapper<MyNoteEntity, MyNoteModel, Long> {
}
