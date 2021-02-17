package ir.comprehensive.service;

import ir.comprehensive.database.entity.MyNoteCategoryEntity;
import ir.comprehensive.database.enums.MyNoteCategoryStatusEnum;
import ir.comprehensive.fxmapper.MyNoteCategoryFxMapper;
import ir.comprehensive.fxmodel.MyNoteCategoryInfo;
import ir.comprehensive.fxmodel.MyNoteCategoryFxModel;
import ir.comprehensive.repository.MyNoteCategoryFxRepository;
import ir.comprehensive.repository.MyNoteFxRepository;
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
public class MyNoteCategoryFxService implements BaseFxService<MyNoteCategoryEntity, MyNoteCategoryFxModel> {
    MyNoteCategoryFxRepository repository;
    MyNoteFxRepository myNoteRepository;
    MyNoteCategoryFxMapper mapper;

    public MyNoteCategoryFxService(MyNoteCategoryFxRepository repository, MyNoteFxRepository myNoteRepository, MyNoteCategoryFxMapper mapper) {
        this.repository = repository;
        this.myNoteRepository = myNoteRepository;
        this.mapper = mapper;
    }

    public Optional<MyNoteCategoryEntity> load(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("null id");
        }
        return repository.findById(id);
    }

    public Optional<List<MyNoteCategoryEntity>> loadAll() {
        return Optional.of(repository.findAll());
    }


    public Optional<List<MyNoteCategoryEntity>> search(MyNoteCategoryFxModel searchExample) {
        Specification<MyNoteCategoryEntity> categorySpecification = getMyNoteCategorySpecification(searchExample);
        return Optional.of(repository.findAll(categorySpecification));
    }

    private Specification<MyNoteCategoryEntity> getMyNoteCategorySpecification(MyNoteCategoryFxModel searchExample) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getTitle() != null && !searchExample.getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), StringUtils.makeAnyMatch(searchExample.getTitle())));
            }
            if (searchExample.getDescription() != null && !searchExample.getDescription().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("description")), StringUtils.makeAnyMatch(searchExample.getDescription())));
            }
            if (searchExample.getStatus() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("status"), searchExample.getStatus()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }

    private void validateEntity(MyNoteCategoryEntity category) throws GeneralException {
        if (category == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
        if (category.getId() != null ? repository.isNotUnique(category.getId(), category.getTitle()) : repository.isNotUnique(category.getTitle())) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE + " " + MessageUtils.Message.MY_NOTE_CATEGORY_NOT_UNIQUE);
        }
    }

    public Optional<MyNoteCategoryEntity> save(MyNoteCategoryEntity category) throws GeneralException {
        validateEntity(category);
        category.setId(null);
        return Optional.of(repository.save(category));
    }

    public Optional<MyNoteCategoryEntity> update(MyNoteCategoryEntity category) throws GeneralException {
        validateEntity(category);
        if (category.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }
        // TODO must fix message
        MyNoteCategoryEntity loaMyNoteCategory = repository.findById(category.getId()).orElseThrow(() -> new GeneralException("not found"));
        loaMyNoteCategory.setId(category.getId());
        loaMyNoteCategory.setTitle(category.getTitle());
        loaMyNoteCategory.setDescription(category.getDescription());
        loaMyNoteCategory.setStatus(category.getStatus());
        return Optional.of(repository.save(loaMyNoteCategory));

    }

    public Optional<MyNoteCategoryEntity> saveOrUpdate(MyNoteCategoryEntity category) throws GeneralException {
        return category.getId() == null ? save(category) : update(category);
    }

    public Optional<Long> delete(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("not null id");
        }

        repository.deleteById(id);
        return Optional.of(id);
    }


    public MyNoteCategoryInfo getInfo() {
        MyNoteCategoryInfo info = new MyNoteCategoryInfo();
        info.setTotalCount(this.getNumberString(repository.totalCount()));
        info.setDoneCount(this.getNumberString(repository.countByStatus(MyNoteCategoryStatusEnum.DONE)));
        info.setInProgressCount(this.getNumberString(repository.countByStatus(MyNoteCategoryStatusEnum.IN_PROGRESS)));
        info.setStoppedCount(this.getNumberString(repository.countByStatus(MyNoteCategoryStatusEnum.STOPPED)));
        return info;
    }

    @Override
    public Page<MyNoteCategoryFxModel> loadItem(MyNoteCategoryFxModel searchModel, PageRequest pageRequest) {
        Page<MyNoteCategoryEntity> page;
        if (searchModel == null) {
            page = repository.findAll(pageRequest);
        } else {
            page = repository.findAll(getMyNoteCategorySpecification(searchModel), pageRequest);
        }
        return page.map(mapper::entityToModel);
    }
}
