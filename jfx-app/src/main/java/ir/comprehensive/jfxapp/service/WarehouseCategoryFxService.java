package ir.comprehensive.jfxapp.service;

import ir.comprehensive.database.entity.WarehouseCategoryEntity;
import ir.comprehensive.fxmapper.WarehouseCategoryFxMapper;
import ir.comprehensive.fxmodel.WarehouseCategoryFxModel;
import ir.comprehensive.repository.WarehouseCategoryFxRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WarehouseCategoryFxService implements BaseFxService<WarehouseCategoryEntity, WarehouseCategoryFxModel> {
    private WarehouseCategoryFxRepository repository;
    private WarehouseCategoryFxMapper mapper;

    public WarehouseCategoryFxService(WarehouseCategoryFxRepository repository, WarehouseCategoryFxMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<WarehouseCategoryEntity> load(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("null id");
        }
        return repository.findById(id);
    }

    public Optional<List<WarehouseCategoryEntity>> findByTitle(String title) {
        Page<WarehouseCategoryEntity> warehouseCategories = repository.findByTitle(title, PageRequest.of(0, 10));
        return Optional.of(warehouseCategories.getContent());
    }

    public Optional<List<WarehouseCategoryEntity>> loadAll() {
        return Optional.of(repository.findAll());
    }


    public Optional<List<WarehouseCategoryEntity>> search(WarehouseCategoryFxModel searchExample) {
        Specification<WarehouseCategoryEntity> warehouseCategorySpecification = getWarehouseCategorySpecification(searchExample);

        return Optional.of(repository.findAll(warehouseCategorySpecification));
    }

    private Specification<WarehouseCategoryEntity> getWarehouseCategorySpecification(WarehouseCategoryFxModel searchExample) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getTitle() != null && !searchExample.getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), StringUtils.makeAnyMatch(searchExample.getTitle())));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }

    private void validateEntity(WarehouseCategoryEntity warehouseCategory) throws GeneralException {
        if (warehouseCategory == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
    }

    public Optional<WarehouseCategoryEntity> save(WarehouseCategoryEntity warehouseCategory) throws GeneralException {
        validateEntity(warehouseCategory);
        warehouseCategory.setId(null);
        return Optional.of(repository.save(warehouseCategory));
    }

    public Optional<WarehouseCategoryEntity> update(WarehouseCategoryEntity warehouseCategory) throws GeneralException {
        validateEntity(warehouseCategory);
        if (warehouseCategory.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }

        // TODO must fix message
        WarehouseCategoryEntity loadedWarehouseCategory = repository.findById(warehouseCategory.getId()).orElseThrow(() -> new GeneralException("not found"));

        return Optional.of(repository.save(swap(warehouseCategory, loadedWarehouseCategory)));

    }

    public Optional<WarehouseCategoryEntity> saveOrUpdate(WarehouseCategoryEntity warehouseCategory) throws GeneralException {
        return warehouseCategory.getId() == null ? save(warehouseCategory) : update(warehouseCategory);
    }


    public Optional<Long> delete(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("not null id");
        }

        repository.deleteById(id);
        return Optional.of(id);
    }

    @Override
    public Page<WarehouseCategoryFxModel> loadItem(WarehouseCategoryFxModel searchModel, PageRequest pageRequest) {
        Page<WarehouseCategoryEntity> page;
        if (searchModel == null) {
            page = repository.findAll(pageRequest);
        } else {
            page = repository.findAll(getWarehouseCategorySpecification(searchModel), pageRequest);
        }
        return page.map(mapper::entityToModel);
    }
}
