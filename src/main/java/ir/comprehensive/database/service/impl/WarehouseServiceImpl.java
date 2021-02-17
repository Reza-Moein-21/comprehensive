package ir.comprehensive.database.service.impl;

import ir.comprehensive.database.WarehouseEntity;
import ir.comprehensive.database.base.BaseServiceImpl;
import ir.comprehensive.database.mapper.WarehouseMapper;
import ir.comprehensive.database.model.WarehouseModel;
import ir.comprehensive.database.repository.WarehouseRepository;
import ir.comprehensive.database.service.WarehouseService;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl extends BaseServiceImpl<WarehouseEntity, WarehouseModel, WarehouseMapper, WarehouseRepository, Long> implements WarehouseService {

    public WarehouseServiceImpl(WarehouseMapper mapper, WarehouseRepository repository) {
        super(mapper, repository);
    }
}
