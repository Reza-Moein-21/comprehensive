package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.BaseDaoImpl;
import ir.comprehensive.database.entity.WarehouseEntity;
import ir.comprehensive.database.mapper.WarehouseMapper;
import ir.comprehensive.database.repository.WarehouseRepository;
import ir.comprehensive.domain.dao.WarehouseDao;
import ir.comprehensive.domain.model.WarehouseModel;
import org.springframework.stereotype.Service;

@Service
public class WarehouseDaoImpl extends BaseDaoImpl<WarehouseEntity, WarehouseModel, WarehouseMapper, WarehouseRepository, Long> implements WarehouseDao {

    public WarehouseDaoImpl(WarehouseMapper mapper, WarehouseRepository repository) {
        super(mapper, repository);
    }
}
