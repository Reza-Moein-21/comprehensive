package ir.comprehensive.entity.service.impl;

import ir.comprehensive.entity.WarehouseTagEntity;
import ir.comprehensive.entity.base.BaseServiceImpl;
import ir.comprehensive.entity.mapper.WarehouseTagMapper;
import ir.comprehensive.entity.model.WarehouseTagModel;
import ir.comprehensive.entity.repository.WarehouseTagRepository;
import ir.comprehensive.entity.service.WarehouseTagService;
import org.springframework.stereotype.Service;

@Service
public class WarehouseTagServiceImpl extends BaseServiceImpl<WarehouseTagEntity, WarehouseTagModel, WarehouseTagMapper, WarehouseTagRepository, Long> implements WarehouseTagService {

    public WarehouseTagServiceImpl(WarehouseTagMapper mapper, WarehouseTagRepository repository) {
        super(mapper, repository);
    }
}
