package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.BaseDaoImpl;
import ir.comprehensive.database.entity.MyNoteTempEntity;
import ir.comprehensive.database.mapper.MyNoteTempMapper;
import ir.comprehensive.database.repository.MyNoteTempRepository;
import ir.comprehensive.domain.dao.MyNoteTempDao;
import ir.comprehensive.domain.model.MyNoteTempModel;
import org.springframework.stereotype.Service;

@Service
public class MyNoteTempDaoImpl extends BaseDaoImpl<MyNoteTempEntity, MyNoteTempModel, MyNoteTempMapper, MyNoteTempRepository, Long> implements MyNoteTempDao {

    public MyNoteTempDaoImpl(MyNoteTempMapper mapper, MyNoteTempRepository repository) {
        super(mapper, repository);
    }
}
