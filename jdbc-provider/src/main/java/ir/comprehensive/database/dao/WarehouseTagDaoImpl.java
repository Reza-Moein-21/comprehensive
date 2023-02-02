package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.BaseDaoImpl;
import ir.comprehensive.database.entity.WarehouseTagEntity;
import ir.comprehensive.database.mapper.WarehouseTagMapper;
import ir.comprehensive.database.repository.WarehouseTagRepository;
import ir.comprehensive.domain.dao.WarehouseTagDao;
import ir.comprehensive.domain.model.WarehouseTagModel;
import org.springframework.stereotype.Service;

@Service
public class WarehouseTagDaoImpl extends BaseDaoImpl<WarehouseTagEntity, WarehouseTagModel, WarehouseTagMapper, WarehouseTagRepository, Long> implements WarehouseTagDao {

    public WarehouseTagDaoImpl(WarehouseTagMapper mapper, WarehouseTagRepository repository) {
        super(mapper, repository);
    }
}
