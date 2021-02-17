package ir.comprehensive.database.service.impl;

import ir.comprehensive.database.entity.MyNoteEntity;
import ir.comprehensive.database.base.BaseServiceImpl;
import ir.comprehensive.database.mapper.MyNoteMapper;
import ir.comprehensive.database.model.MyNoteModel;
import ir.comprehensive.database.repository.MyNoteRepository;
import ir.comprehensive.database.service.MyNoteService;
import org.springframework.stereotype.Service;

@Service
public class MyNoteServiceImpl extends BaseServiceImpl<MyNoteEntity, MyNoteModel, MyNoteMapper, MyNoteRepository, Long> implements MyNoteService {

    public MyNoteServiceImpl(MyNoteMapper mapper, MyNoteRepository repository) {
        super(mapper, repository);
    }
}
