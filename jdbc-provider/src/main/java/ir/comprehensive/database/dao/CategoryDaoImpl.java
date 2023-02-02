package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.BaseDaoImpl;
import ir.comprehensive.database.entity.CategoryEntity;
import ir.comprehensive.database.mapper.CategoryMapper;
import ir.comprehensive.database.repository.CategoryRepository;
import ir.comprehensive.domain.dao.CategoryDao;
import ir.comprehensive.domain.model.CategoryModel;
import org.springframework.stereotype.Service;

@Service
public class CategoryDaoImpl extends BaseDaoImpl<CategoryEntity, CategoryModel, CategoryMapper, CategoryRepository, Long> implements CategoryDao {

    public CategoryDaoImpl(CategoryMapper mapper, CategoryRepository repository) {
        super(mapper, repository);
    }

}
