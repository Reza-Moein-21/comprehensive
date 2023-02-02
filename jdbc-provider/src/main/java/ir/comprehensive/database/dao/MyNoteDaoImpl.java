package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.BaseDaoImpl;
import ir.comprehensive.database.entity.MyNoteEntity;
import ir.comprehensive.database.mapper.MyNoteMapper;
import ir.comprehensive.database.repository.MyNoteRepository;
import ir.comprehensive.domain.dao.MyNoteDao;
import ir.comprehensive.domain.model.MyNoteModel;
import org.springframework.stereotype.Service;

@Service
public class MyNoteDaoImpl extends BaseDaoImpl<MyNoteEntity, MyNoteModel, MyNoteMapper, MyNoteRepository, Long> implements MyNoteDao {

    public MyNoteDaoImpl(MyNoteMapper mapper, MyNoteRepository repository) {
        super(mapper, repository);
    }
}
