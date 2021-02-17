package ir.comprehensive.database.service.impl;

import ir.comprehensive.database.HadisEntity;
import ir.comprehensive.database.base.BaseServiceImpl;
import ir.comprehensive.database.mapper.HadisMapper;
import ir.comprehensive.database.model.HadisModel;
import ir.comprehensive.database.repository.HadisRepository;
import ir.comprehensive.database.service.HadisService;
import org.springframework.stereotype.Service;

@Service
public class HadisServiceImpl extends BaseServiceImpl<HadisEntity, HadisModel, HadisMapper, HadisRepository, Long> implements HadisService {

    public HadisServiceImpl(HadisMapper mapper, HadisRepository repository) {
        super(mapper, repository);
    }
}
