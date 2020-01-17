package ir.comprehensive.mapper;

import ir.comprehensive.domain.MyNoteTemp;
import ir.comprehensive.model.MyNoteTempModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteTempMapper extends BaseMapper<MyNoteTemp, MyNoteTempModel> {

    @Override
    MyNoteTempModel entityToModel(MyNoteTemp entity);

    @Override
    MyNoteTemp modelToEntity(MyNoteTempModel dto);
}
