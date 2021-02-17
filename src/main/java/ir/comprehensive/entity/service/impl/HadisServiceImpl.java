package ir.comprehensive.entity.service.impl;

import ir.comprehensive.entity.HadisEntity;
import ir.comprehensive.entity.base.BaseServiceImpl;
import ir.comprehensive.entity.mapper.HadisMapper;
import ir.comprehensive.entity.model.HadisModel;
import ir.comprehensive.entity.repository.HadisRepository;
import ir.comprehensive.entity.service.HadisService;
import org.springframework.stereotype.Service;

@Service
public class HadisServiceImpl extends BaseServiceImpl<HadisEntity, HadisModel, HadisMapper, HadisRepository, Long> implements HadisService {

    public HadisServiceImpl(HadisMapper mapper, HadisRepository repository) {
        super(mapper, repository);
    }
}
