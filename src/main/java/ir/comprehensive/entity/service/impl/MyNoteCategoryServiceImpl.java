package ir.comprehensive.entity.service.impl;

import ir.comprehensive.entity.MyNoteCategoryEntity;
import ir.comprehensive.entity.base.BaseServiceImpl;
import ir.comprehensive.entity.mapper.MyNoteCategoryMapper;
import ir.comprehensive.entity.model.MyNoteCategoryModel;
import ir.comprehensive.entity.repository.MyNoteCategoryRepository;
import ir.comprehensive.entity.service.MyNoteCategoryService;
import org.springframework.stereotype.Service;

@Service
public class MyNoteCategoryServiceImpl extends BaseServiceImpl<MyNoteCategoryEntity, MyNoteCategoryModel, MyNoteCategoryMapper, MyNoteCategoryRepository, Long> implements MyNoteCategoryService {

    public MyNoteCategoryServiceImpl(MyNoteCategoryMapper mapper, MyNoteCategoryRepository repository) {
        super(mapper, repository);
    }
}
