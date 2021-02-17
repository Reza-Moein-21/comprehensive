package ir.comprehensive.database.service.impl;

import ir.comprehensive.database.MyNoteTempEntity;
import ir.comprehensive.database.base.BaseServiceImpl;
import ir.comprehensive.database.mapper.MyNoteTempMapper;
import ir.comprehensive.database.model.MyNoteTempModel;
import ir.comprehensive.database.repository.MyNoteTempRepository;
import ir.comprehensive.database.service.MyNoteTempService;
import org.springframework.stereotype.Service;

@Service
public class MyNoteTempServiceImpl extends BaseServiceImpl<MyNoteTempEntity, MyNoteTempModel, MyNoteTempMapper, MyNoteTempRepository, Long> implements MyNoteTempService {

    public MyNoteTempServiceImpl(MyNoteTempMapper mapper, MyNoteTempRepository repository) {
        super(mapper, repository);
    }
}
