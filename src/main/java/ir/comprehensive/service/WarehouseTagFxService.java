package ir.comprehensive.service;

import ir.comprehensive.database.entity.WarehouseTagEntity;
import ir.comprehensive.fxmapper.WarehouseTagFxMapper;
import ir.comprehensive.fxmodel.WarehouseTagFxModel;
import ir.comprehensive.repository.WarehouseTagFxRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WarehouseTagFxService implements BaseFxService<WarehouseTagEntity, WarehouseTagFxModel> {
    private WarehouseTagFxRepository repository;
    private WarehouseTagFxMapper mapper;

    public WarehouseTagFxService(WarehouseTagFxRepository repository, WarehouseTagFxMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<List<WarehouseTagEntity>> findByTitle(String title) {
        Page<WarehouseTagEntity> warehouseTags = repository.findByTitle(title.replace("%", "\\%"), PageRequest.of(0, 10));
        return Optional.of(warehouseTags.getContent());
    }

    @Override
    public Page<WarehouseTagFxModel> loadItem(WarehouseTagFxModel searchModel, PageRequest pageRequest) {
        Page<WarehouseTagEntity> page;
        page = repository.findAll(pageRequest);
        return page.map(mapper::entityToModel);
    }
}
