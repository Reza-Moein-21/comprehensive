package ir.comprehensive.entity.mapper;

import ir.comprehensive.entity.MyNoteEntity;
import ir.comprehensive.entity.base.BaseMapper;
import ir.comprehensive.entity.model.MyNoteModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteMapper extends BaseMapper<MyNoteEntity, MyNoteModel, Long> {
}
