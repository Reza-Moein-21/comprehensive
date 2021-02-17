package ir.comprehensive.entity.service.impl;

import ir.comprehensive.entity.WarehouseEntity;
import ir.comprehensive.entity.base.BaseServiceImpl;
import ir.comprehensive.entity.mapper.WarehouseMapper;
import ir.comprehensive.entity.model.WarehouseModel;
import ir.comprehensive.entity.repository.WarehouseRepository;
import ir.comprehensive.entity.service.WarehouseService;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl extends BaseServiceImpl<WarehouseEntity, WarehouseModel, WarehouseMapper, WarehouseRepository, Long> implements WarehouseService {

    public WarehouseServiceImpl(WarehouseMapper mapper, WarehouseRepository repository) {
        super(mapper, repository);
    }
}
