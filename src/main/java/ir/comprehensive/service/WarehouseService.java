package ir.comprehensive.service;

import ir.comprehensive.domain.Warehouse;
import ir.comprehensive.domain.WarehouseTag;
import ir.comprehensive.model.WarehouseModel;
import ir.comprehensive.model.WarehouseTagModel;
import ir.comprehensive.repository.ProductDeliveryRepository;
import ir.comprehensive.repository.WarehouseRepository;
import ir.comprehensive.repository.WarehouseTagRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.service.extra.Swappable;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private ProductDeliveryRepository productDeliveryRepository;
    private WarehouseTagRepository warehouseTagRepository;

    public WarehouseService(WarehouseRepository repository, ProductDeliveryRepository productDeliveryRepository, WarehouseTagRepository warehouseTagRepository) {
        this.repository = repository;
        this.productDeliveryRepository = productDeliveryRepository;
        this.warehouseTagRepository = warehouseTagRepository;
    }

    public Optional<Warehouse> load(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("null id");
        }
        return repository.findById(id);
    }

    public Optional<List<Warehouse>> findByName(String name) {
        Page<Warehouse> warehouses = repository.findByName(name, PageRequest.of(0, 10));
        return Optional.of(warehouses.getContent());
    }

    public Optional<List<Warehouse>> loadAll() {
        return Optional.of(repository.findAll());
    }


    public Optional<List<Warehouse>> search(WarehouseModel searchExample) {
        Specification<Warehouse> warehouseSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getTitle() != null && !searchExample.getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), StringUtils.makeAnyMatch(searchExample.getTitle())));
            }
            if (searchExample.getCode() != null && !searchExample.getCode().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), StringUtils.makeAnyMatch(searchExample.getCode())));
            }
            if (searchExample.getCompanyName() != null && !searchExample.getCompanyName().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("companyName")), StringUtils.makeAnyMatch(searchExample.getCompanyName())));
            }
            if (searchExample.getCategory() != null && searchExample.getCategory().getId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("category").get("id"), searchExample.getCategory().getId()));
            }
            if (searchExample.getTagList() != null && !searchExample.getTagList().isEmpty()) {
                Join<Warehouse, WarehouseTag> categories = root.join("tagList");
                predicateList.add(categories.in(searchExample.getTagList().stream().map(WarehouseTagModel::getId).collect(Collectors.toSet())));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };

        return Optional.of(repository.findAll(warehouseSpecification).stream().distinct().collect(Collectors.toList()));
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
        List<WarehouseTag> newTagListForSave = new ArrayList<>();

        if (warehouse.getTagList() != null) {
            for (WarehouseTag warehouseTag : warehouse.getTagList()) {
                if (warehouseTag.getId() != null) {
                    newTagListForSave.add(warehouseTagRepository.findById(warehouseTag.getId()).orElse(null));
                } else {
                    newTagListForSave.add(warehouseTag);
                }
            }
        }

        warehouse.setTagList(newTagListForSave);

        return warehouse.getId() == null ? save(warehouse) : update(warehouse);
    }


    public Optional<Long> delete(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("not null id");
        }
        if (productDeliveryRepository.isWarehouseExist(id)) {
            throw new GeneralException(MessageUtils.Message.PRODUCT + " " + MessageUtils.Message.USE_IN + " " + MessageUtils.Message.STOREROOM);
        }

        repository.deleteById(id);

        return Optional.of(id);
    }
}
