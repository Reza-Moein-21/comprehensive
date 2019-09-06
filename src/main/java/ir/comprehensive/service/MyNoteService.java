package ir.comprehensive.service;

import ir.comprehensive.domain.MyNote;
import ir.comprehensive.domain.MyNoteCategory;
import ir.comprehensive.model.MyNoteModel;
import ir.comprehensive.repository.MyNoteRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.service.extra.Swappable;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MyNoteService implements Swappable<MyNote> {
    private MyNoteRepository repository;

    public MyNoteService(MyNoteRepository repository) {
        this.repository = repository;
    }

    public Optional<MyNote> load(Long id, Long myNoteCategoryId) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("null id");
        }
        MyNote searchExample = new MyNote();
        searchExample.setId(id);
        searchExample.setMyNoteCategory(new MyNoteCategory(myNoteCategoryId));
        return Optional.ofNullable(repository.findAll(Example.of(searchExample)).get(0));
    }

    public Optional<List<MyNote>> loadAll(Long myNoteCategoryId) {
        MyNote searchExample = new MyNote();
        searchExample.setMyNoteCategory(new MyNoteCategory(myNoteCategoryId));
        return Optional.of(repository.findAll(Example.of(searchExample), Sort.by(Sort.Order.desc("priority"), Sort.Order.desc("creationDate"))));
    }

    public Optional<List<MyNote>> loadAllActive(Long myNoteCategoryId) {
        MyNote myNote = new MyNote();
        myNote.setIsActive(true);
        myNote.setMyNoteCategory(new MyNoteCategory(myNoteCategoryId));
        return Optional.of(repository.findAll(Example.of(myNote), Sort.by(Sort.Order.desc("priority"), Sort.Order.desc("creationDate"))));
    }

    public List<CalenderNoteStatus> getCalenderNoteStatuses(LocalDate startDate, LocalDate endDate, Long myNoteCategoryId) {
        return repository.findNumberOfByDate(startDate, endDate, myNoteCategoryId);
    }


    public Optional<List<MyNote>> search(MyNoteModel searchExample, Long myNoteCategoryId) {
        Specification<MyNote> myNoteSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            predicateList.add(criteriaBuilder.equal(root.get("myNoteCategory").get("id"), myNoteCategoryId));

            if (searchExample.getTitle() != null && !searchExample.getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("title"), StringUtils.makeAnyMatch(searchExample.getTitle())));
            }

            if (!searchExample.getPriority().equals(0.0)) {
                predicateList.add(criteriaBuilder.equal(root.get("priority"), searchExample.getPriority()));
            }

            if (searchExample.getIsActive() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("isActive"), searchExample.getIsActive()));
            }


            if (searchExample.getCreationDate() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("creationDate"), searchExample.getCreationDate()));
            }

            if (searchExample.getCreationDateFrom() != null) {
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), searchExample.getCreationDateFrom()));
            }
            if (searchExample.getCreationDateTo() != null) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), searchExample.getCreationDateTo()));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };

        return Optional.of(repository.findAll(myNoteSpecification, Sort.by(Sort.Order.desc("priority"), Sort.Order.desc("creationDate"))));
    }

    private void validateEntity(MyNote myNote) throws GeneralException {
        if (myNote == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
        if (myNote.getMyNoteCategory() == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
    }

    public Optional<MyNote> save(MyNote myNote) throws GeneralException {
        validateEntity(myNote);
        myNote.setId(null);
        myNote.setIsActive(true);
        return Optional.of(repository.save(myNote));
    }

    public Optional<MyNote> update(MyNote myNote) throws GeneralException {
        validateEntity(myNote);
        if (myNote.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }

        // TODO must fix message
        MyNote loadedMyNote = repository.findById(myNote.getId()).orElseThrow(() -> new GeneralException("not found"));

        MyNote myNoteSwap = swap(myNote, loadedMyNote);
        myNoteSwap.setInActivationDate(myNoteSwap.getIsActive() ? null : LocalDate.now());
        return Optional.of(repository.save(myNoteSwap));

    }

    public Optional<MyNote> saveOrUpdate(MyNote myNote) throws GeneralException {
        return myNote.getId() == null ? save(myNote) : update(myNote);
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
