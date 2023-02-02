package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.BaseDaoImpl;
import ir.comprehensive.database.entity.WarehouseCategoryEntity;
import ir.comprehensive.database.mapper.WarehouseCategoryMapper;
import ir.comprehensive.database.repository.WarehouseCategoryRepository;
import ir.comprehensive.domain.dao.WarehouseCategoryDao;
import ir.comprehensive.domain.model.WarehouseCategoryModel;
import org.springframework.stereotype.Service;

@Service
public class WarehouseCategoryDaoImpl extends BaseDaoImpl<WarehouseCategoryEntity, WarehouseCategoryModel, WarehouseCategoryMapper, WarehouseCategoryRepository, Long> implements WarehouseCategoryDao {

    public WarehouseCategoryDaoImpl(WarehouseCategoryMapper mapper, WarehouseCategoryRepository repository) {
        super(mapper, repository);
    }
}
