package ir.comprehensive.entity.service.impl;

import ir.comprehensive.entity.WarehouseCategoryEntity;
import ir.comprehensive.entity.base.BaseServiceImpl;
import ir.comprehensive.entity.mapper.WarehouseCategoryMapper;
import ir.comprehensive.entity.model.WarehouseCategoryModel;
import ir.comprehensive.entity.repository.WarehouseCategoryRepository;
import ir.comprehensive.entity.service.WarehouseCategoryService;
import org.springframework.stereotype.Service;

@Service
public class WarehouseCategoryServiceImpl extends BaseServiceImpl<WarehouseCategoryEntity, WarehouseCategoryModel, WarehouseCategoryMapper, WarehouseCategoryRepository, Long> implements WarehouseCategoryService {

    public WarehouseCategoryServiceImpl(WarehouseCategoryMapper mapper, WarehouseCategoryRepository repository) {
        super(mapper, repository);
    }
}
