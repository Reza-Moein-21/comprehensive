package ir.comprehensive.service;

import ir.comprehensive.domain.WarehouseCategory;
import ir.comprehensive.model.WarehouseCategoryModel;
import ir.comprehensive.repository.WarehouseCategoryRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.service.extra.Swappable;
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
public class WarehouseCategoryService implements Swappable<WarehouseCategory> {
    private WarehouseCategoryRepository repository;

    public WarehouseCategoryService(WarehouseCategoryRepository repository) {
        this.repository = repository;
    }

    public Optional<WarehouseCategory> load(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("null id");
        }
        return repository.findById(id);
    }

    public Optional<List<WarehouseCategory>> findByTitle(String title) {
        Page<WarehouseCategory> warehouseCategories = repository.findByTitle(title, PageRequest.of(0, 10));
        return Optional.of(warehouseCategories.getContent());
    }

    public Optional<List<WarehouseCategory>> loadAll() {
        return Optional.of(repository.findAll());
    }


    public Optional<List<WarehouseCategory>> search(WarehouseCategoryModel searchExample) {
        Specification<WarehouseCategory> warehouseCategorySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getTitle() != null && !searchExample.getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("title"), StringUtils.makeAnyMatch(searchExample.getTitle())));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };

        return Optional.of(repository.findAll(warehouseCategorySpecification));
    }

    private void validateEntity(WarehouseCategory warehouseCategory) throws GeneralException {
        if (warehouseCategory == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
    }

    public Optional<WarehouseCategory> save(WarehouseCategory warehouseCategory) throws GeneralException {
        validateEntity(warehouseCategory);
        warehouseCategory.setId(null);
        return Optional.of(repository.save(warehouseCategory));
    }

    public Optional<WarehouseCategory> update(WarehouseCategory warehouseCategory) throws GeneralException {
        validateEntity(warehouseCategory);
        if (warehouseCategory.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }

        // TODO must fix message
        WarehouseCategory loadedWarehouseCategory = repository.findById(warehouseCategory.getId()).orElseThrow(() -> new GeneralException("not found"));

        return Optional.of(repository.save(swap(warehouseCategory, loadedWarehouseCategory)));

    }

    public Optional<WarehouseCategory> saveOrUpdate(WarehouseCategory warehouseCategory) throws GeneralException {
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
}
