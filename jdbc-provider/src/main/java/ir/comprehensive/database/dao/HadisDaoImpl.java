package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.BaseDaoImpl;
import ir.comprehensive.database.entity.HadisEntity;
import ir.comprehensive.database.mapper.HadisMapper;
import ir.comprehensive.database.repository.HadisRepository;
import ir.comprehensive.domain.dao.HadisDao;
import ir.comprehensive.domain.model.HadisModel;
import org.springframework.stereotype.Service;

@Service
public class HadisDaoImpl extends BaseDaoImpl<HadisEntity, HadisModel, HadisMapper, HadisRepository, Long> implements HadisDao {

    public HadisDaoImpl(HadisMapper mapper, HadisRepository repository) {
        super(mapper, repository);
    }
}
