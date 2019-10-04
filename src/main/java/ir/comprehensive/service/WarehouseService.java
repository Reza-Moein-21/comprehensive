package ir.comprehensive.service;

import ir.comprehensive.domain.Warehouse;
import ir.comprehensive.domain.WarehouseTag;
import ir.comprehensive.model.WarehouseModel;
import ir.comprehensive.model.WarehouseTagModel;
import ir.comprehensive.repository.WarehouseRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.service.extra.Swappable;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class WarehouseService implements Swappable<Warehouse> {
    private WarehouseRepository repository;

    public WarehouseService(WarehouseRepository repository) {
        this.repository = repository;
    }

    public Optional<Warehouse> load(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("null id");
        }
        return repository.findById(id);
    }

    public Optional<List<Warehouse>> loadAll() {
        return Optional.of(repository.findAll());
    }


    public Optional<List<Warehouse>> search(WarehouseModel searchExample) {
        Specification<Warehouse> warehouseSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getTitle() != null && !searchExample.getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("title"), StringUtils.makeAnyMatch(searchExample.getTitle())));
            }
            if (searchExample.getCode() != null && !searchExample.getCode().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("code"), StringUtils.makeAnyMatch(searchExample.getCode())));
            }
            if (searchExample.getCompanyName() != null && !searchExample.getCompanyName().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("companyName"), StringUtils.makeAnyMatch(searchExample.getCompanyName())));
            }
            if (searchExample.getTagList() != null && !searchExample.getTagList().isEmpty()) {
                Join<Warehouse, WarehouseTag> categories = root.join("tagList");
                predicateList.add(categories.in(searchExample.getTagList().stream().map(WarehouseTagModel::getId).collect(Collectors.toSet())));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };

        return Optional.of(repository.findAll(warehouseSpecification));
    }

    private void validateEntity(Warehouse warehouse) throws GeneralException {
        if (warehouse == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
    }

    public Optional<Warehouse> save(Warehouse warehouse) throws GeneralException {
        validateEntity(warehouse);
        warehouse.setId(null);
        return Optional.of(repository.save(warehouse));
    }

    public Optional<Warehouse> update(Warehouse warehouse) throws GeneralException {
        validateEntity(warehouse);
        if (warehouse.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }

        // TODO must fix message
        Warehouse loadedWarehouse = repository.findById(warehouse.getId()).orElseThrow(() -> new GeneralException("not found"));

        return Optional.of(repository.save(swap(warehouse, loadedWarehouse)));

    }

    public Optional<Warehouse> saveOrUpdate(Warehouse warehouse) throws GeneralException {
        return warehouse.getId() == null ? save(warehouse) : update(warehouse);
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
