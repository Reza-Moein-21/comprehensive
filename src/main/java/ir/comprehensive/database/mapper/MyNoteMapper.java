package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.MyNoteEntity;
import ir.comprehensive.database.base.BaseMapper;
import ir.comprehensive.database.model.MyNoteModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyNoteMapper extends BaseMapper<MyNoteEntity, MyNoteModel, Long> {
}
