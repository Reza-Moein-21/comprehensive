package ir.comprehensive.database.service.impl;

import ir.comprehensive.database.entity.MyNoteCategoryEntity;
import ir.comprehensive.database.base.BaseServiceImpl;
import ir.comprehensive.database.mapper.MyNoteCategoryMapper;
import ir.comprehensive.database.model.MyNoteCategoryModel;
import ir.comprehensive.database.repository.MyNoteCategoryRepository;
import ir.comprehensive.database.service.MyNoteCategoryService;
import org.springframework.stereotype.Service;

@Service
public class MyNoteCategoryServiceImpl extends BaseServiceImpl<MyNoteCategoryEntity, MyNoteCategoryModel, MyNoteCategoryMapper, MyNoteCategoryRepository, Long> implements MyNoteCategoryService {

    public MyNoteCategoryServiceImpl(MyNoteCategoryMapper mapper, MyNoteCategoryRepository repository) {
        super(mapper, repository);
    }
}
