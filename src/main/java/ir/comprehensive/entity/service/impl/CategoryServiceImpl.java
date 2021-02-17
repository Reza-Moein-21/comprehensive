package ir.comprehensive.entity.service.impl;

import ir.comprehensive.entity.CategoryEntity;
import ir.comprehensive.entity.base.BaseServiceImpl;
import ir.comprehensive.entity.mapper.CategoryMapper;
import ir.comprehensive.entity.model.CategoryModel;
import ir.comprehensive.entity.repository.CategoryRepository;
import ir.comprehensive.entity.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryEntity, CategoryModel, CategoryMapper, CategoryRepository, Long> implements CategoryService {

    public CategoryServiceImpl(CategoryMapper mapper, CategoryRepository repository) {
        super(mapper, repository);
    }

}
