package ir.comprehensive.database.service.impl;

import ir.comprehensive.database.WarehouseCategoryEntity;
import ir.comprehensive.database.base.BaseServiceImpl;
import ir.comprehensive.database.mapper.WarehouseCategoryMapper;
import ir.comprehensive.database.model.WarehouseCategoryModel;
import ir.comprehensive.database.repository.WarehouseCategoryRepository;
import ir.comprehensive.database.service.WarehouseCategoryService;
import org.springframework.stereotype.Service;

@Service
public class WarehouseCategoryServiceImpl extends BaseServiceImpl<WarehouseCategoryEntity, WarehouseCategoryModel, WarehouseCategoryMapper, WarehouseCategoryRepository, Long> implements WarehouseCategoryService {

    public WarehouseCategoryServiceImpl(WarehouseCategoryMapper mapper, WarehouseCategoryRepository repository) {
        super(mapper, repository);
    }
}
