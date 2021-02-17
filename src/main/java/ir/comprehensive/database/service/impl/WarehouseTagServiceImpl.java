package ir.comprehensive.database.service.impl;

import ir.comprehensive.database.WarehouseTagEntity;
import ir.comprehensive.database.base.BaseServiceImpl;
import ir.comprehensive.database.mapper.WarehouseTagMapper;
import ir.comprehensive.database.model.WarehouseTagModel;
import ir.comprehensive.database.repository.WarehouseTagRepository;
import ir.comprehensive.database.service.WarehouseTagService;
import org.springframework.stereotype.Service;

@Service
public class WarehouseTagServiceImpl extends BaseServiceImpl<WarehouseTagEntity, WarehouseTagModel, WarehouseTagMapper, WarehouseTagRepository, Long> implements WarehouseTagService {

    public WarehouseTagServiceImpl(WarehouseTagMapper mapper, WarehouseTagRepository repository) {
        super(mapper, repository);
    }
}
