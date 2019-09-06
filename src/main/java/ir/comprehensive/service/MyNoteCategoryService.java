package ir.comprehensive.service;

import ir.comprehensive.domain.MyNoteCategory;
import ir.comprehensive.model.MyNoteCategoryModel;
import ir.comprehensive.repository.MyNoteCategoryRepository;
import ir.comprehensive.repository.MyNoteRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.service.extra.Swappable;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MyNoteCategoryService implements Swappable<MyNoteCategory> {
    MyNoteCategoryRepository repository;
    MyNoteRepository myNoteRepository;

    public MyNoteCategoryService(MyNoteCategoryRepository repository, MyNoteRepository myNoteRepository) {
        this.repository = repository;
        this.myNoteRepository = myNoteRepository;
    }

    public Optional<MyNoteCategory> load(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("null id");
        }
        return repository.findById(id);
    }

    public Optional<List<MyNoteCategory>> loadAll() {
        return Optional.of(repository.findAll());
    }


    public Optional<List<MyNoteCategory>> search(MyNoteCategoryModel searchExample) {
        Specification<MyNoteCategory> categorySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getTitle() != null && !searchExample.getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("title"), StringUtils.makeAnyMatch(searchExample.getTitle())));
            }
            if (searchExample.getDescription() != null && !searchExample.getDescription().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("description"), StringUtils.makeAnyMatch(searchExample.getDescription())));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
        return Optional.of(repository.findAll(categorySpecification));
    }

    private void validateEntity(MyNoteCategory category) throws GeneralException {
        if (category == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
        if (category.getId() != null ? repository.isNotUnique(category.getId(), category.getTitle()) : repository.isNotUnique(category.getTitle())) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE + " " + MessageUtils.Message.MY_NOTE_CATEGORY_NOT_UNIQUE);
        }
    }

    public Optional<MyNoteCategory> save(MyNoteCategory category) throws GeneralException {
        validateEntity(category);
        category.setId(null);
        return Optional.of(repository.save(category));
    }

    public Optional<MyNoteCategory> update(MyNoteCategory category) throws GeneralException {
        validateEntity(category);
        if (category.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }
        // TODO must fix message
        MyNoteCategory loaMyNoteCategory = repository.findById(category.getId()).orElseThrow(() -> new GeneralException("not found"));

        return Optional.of(repository.save(swap(category, loaMyNoteCategory)));

    }

    public Optional<MyNoteCategory> saveOrUpdate(MyNoteCategory category) throws GeneralException {
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
}
