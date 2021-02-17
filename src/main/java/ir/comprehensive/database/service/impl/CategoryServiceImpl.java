package ir.comprehensive.database.service.impl;

import ir.comprehensive.database.entity.CategoryEntity;
import ir.comprehensive.database.base.BaseServiceImpl;
import ir.comprehensive.database.mapper.CategoryMapper;
import ir.comprehensive.database.model.CategoryModel;
import ir.comprehensive.database.repository.CategoryRepository;
import ir.comprehensive.database.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryEntity, CategoryModel, CategoryMapper, CategoryRepository, Long> implements CategoryService {

    public CategoryServiceImpl(CategoryMapper mapper, CategoryRepository repository) {
        super(mapper, repository);
    }

}
