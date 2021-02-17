package ir.comprehensive.entity.service.impl;

import ir.comprehensive.entity.MyNoteEntity;
import ir.comprehensive.entity.base.BaseServiceImpl;
import ir.comprehensive.entity.mapper.MyNoteMapper;
import ir.comprehensive.entity.model.MyNoteModel;
import ir.comprehensive.entity.repository.MyNoteRepository;
import ir.comprehensive.entity.service.MyNoteService;
import org.springframework.stereotype.Service;

@Service
public class MyNoteServiceImpl extends BaseServiceImpl<MyNoteEntity, MyNoteModel, MyNoteMapper, MyNoteRepository, Long> implements MyNoteService {

    public MyNoteServiceImpl(MyNoteMapper mapper, MyNoteRepository repository) {
        super(mapper, repository);
    }
}
