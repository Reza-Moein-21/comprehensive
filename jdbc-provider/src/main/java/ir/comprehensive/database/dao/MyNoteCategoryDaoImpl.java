package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.BaseDaoImpl;
import ir.comprehensive.database.entity.MyNoteCategoryEntity;
import ir.comprehensive.database.mapper.MyNoteCategoryMapper;
import ir.comprehensive.database.repository.MyNoteCategoryRepository;
import ir.comprehensive.domain.dao.MyNoteCategoryDao;
import ir.comprehensive.domain.model.MyNoteCategoryModel;
import org.springframework.stereotype.Service;

@Service
public class MyNoteCategoryDaoImpl extends BaseDaoImpl<MyNoteCategoryEntity, MyNoteCategoryModel, MyNoteCategoryMapper, MyNoteCategoryRepository, Long> implements MyNoteCategoryDao {

    public MyNoteCategoryDaoImpl(MyNoteCategoryMapper mapper, MyNoteCategoryRepository repository) {
        super(mapper, repository);
    }
}
