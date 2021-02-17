package ir.comprehensive.entity.service.impl;

import ir.comprehensive.entity.MyNoteTempEntity;
import ir.comprehensive.entity.base.BaseServiceImpl;
import ir.comprehensive.entity.mapper.MyNoteTempMapper;
import ir.comprehensive.entity.model.MyNoteTempModel;
import ir.comprehensive.entity.repository.MyNoteTempRepository;
import ir.comprehensive.entity.service.MyNoteTempService;
import org.springframework.stereotype.Service;

@Service
public class MyNoteTempServiceImpl extends BaseServiceImpl<MyNoteTempEntity, MyNoteTempModel, MyNoteTempMapper, MyNoteTempRepository, Long> implements MyNoteTempService {

    public MyNoteTempServiceImpl(MyNoteTempMapper mapper, MyNoteTempRepository repository) {
        super(mapper, repository);
    }
}
