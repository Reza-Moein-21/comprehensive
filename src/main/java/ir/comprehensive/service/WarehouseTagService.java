package ir.comprehensive.service;

import ir.comprehensive.entity.WarehouseTagEntity;
import ir.comprehensive.mapper.WarehouseTagMapper;
import ir.comprehensive.fxmodel.WarehouseTagModel;
import ir.comprehensive.repository.WarehouseTagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WarehouseTagService implements BaseService<WarehouseTagEntity, WarehouseTagModel> {
    private WarehouseTagRepository repository;
    private WarehouseTagMapper mapper;

    public WarehouseTagService(WarehouseTagRepository repository, WarehouseTagMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<List<WarehouseTagEntity>> findByTitle(String title) {
        Page<WarehouseTagEntity> warehouseTags = repository.findByTitle(title.replace("%", "\\%"), PageRequest.of(0, 10));
        return Optional.of(warehouseTags.getContent());
    }

    @Override
    public Page<WarehouseTagModel> loadItem(WarehouseTagModel searchModel, PageRequest pageRequest) {
        Page<WarehouseTagEntity> page;
        page = repository.findAll(pageRequest);
        return page.map(mapper::entityToModel);
    }
}
