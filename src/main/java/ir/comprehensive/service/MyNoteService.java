package ir.comprehensive.service;

import ir.comprehensive.entity.MyNoteEntity;
import ir.comprehensive.entity.MyNoteCategoryEntity;
import ir.comprehensive.mapper.MyNoteMapper;
import ir.comprehensive.fxmodel.MyNoteModel;
import ir.comprehensive.repository.MyNoteRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class MyNoteService implements BaseService<MyNoteEntity,MyNoteModel> {
    private MyNoteRepository repository;
    private MyNoteMapper mapper;

    public MyNoteService(MyNoteRepository repository, MyNoteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<MyNoteEntity> load(Long id, Long myNoteCategoryId) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("null id");
        }
        MyNoteEntity searchExample = new MyNoteEntity();
        searchExample.setId(id);
        searchExample.setMyNoteCategory(new MyNoteCategoryEntity(myNoteCategoryId));
        return Optional.ofNullable(repository.findAll(Example.of(searchExample)).get(0));
    }

    public Optional<List<MyNoteEntity>> loadAll(Long myNoteCategoryId) {
        MyNoteEntity searchExample = new MyNoteEntity();
        searchExample.setMyNoteCategory(new MyNoteCategoryEntity(myNoteCategoryId));
        return Optional.of(repository.findAll(Example.of(searchExample), Sort.by(Sort.Order.desc("priority"), Sort.Order.desc("creationDate"))));
    }

    public Optional<List<MyNoteEntity>> loadAllActive(Long myNoteCategoryId) {
        MyNoteEntity myNote = new MyNoteEntity();
        myNote.setIsActive(true);
        myNote.setMyNoteCategory(new MyNoteCategoryEntity(myNoteCategoryId));
        return Optional.of(repository.findAll(Example.of(myNote), Sort.by(Sort.Order.desc("priority"), Sort.Order.desc("creationDate"))));
    }

    public List<CalenderNoteStatus> getCalenderNoteStatuses(LocalDate startDate, LocalDate endDate, Long myNoteCategoryId) {
        return repository.findNumberOfByDate(startDate, endDate, myNoteCategoryId);
    }


    public Optional<List<MyNoteEntity>> search(MyNoteModel searchExample) {
        Specification<MyNoteEntity> myNoteSpecification = getMyNoteSpecification(searchExample);

        return Optional.of(repository.findAll(myNoteSpecification, Sort.by(Sort.Order.desc("priority"), Sort.Order.desc("creationDate"))));
    }

    private Specification<MyNoteEntity> getMyNoteSpecification(MyNoteModel searchExample) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            predicateList.add(criteriaBuilder.equal(root.get("myNoteCategory").get("id"), searchExample.getMyNoteCategoryId()));

            if (searchExample.getTitle() != null && !searchExample.getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), StringUtils.makeAnyMatch(searchExample.getTitle())));
            }

            if (searchExample.getPerson() != null && searchExample.getPerson().getId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("person").get("id"), searchExample.getPerson().getId()));
            }

            if (searchExample.getDescription() != null && !searchExample.getDescription().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("description")), StringUtils.makeAnyMatch(searchExample.getDescription())));
            }

            if (!searchExample.getPriority().equals(0.0)) {
                predicateList.add(criteriaBuilder.equal(root.get("priority"), searchExample.getPriority()));
            }

            if (searchExample.isAllActive()) {
                predicateList.add(criteriaBuilder.equal(root.get("isActive"), true));
            } else if (searchExample.getIsActive() != null) {
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
    }

    private void validateEntity(MyNoteEntity myNote) throws GeneralException {
        if (myNote == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
        if (myNote.getMyNoteCategory() == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
    }

    public Optional<MyNoteEntity> save(MyNoteEntity myNote) throws GeneralException {
        validateEntity(myNote);
        myNote.setId(null);
        myNote.setIsActive(true);
        return Optional.of(repository.save(myNote));
    }

    public Optional<MyNoteEntity> update(MyNoteEntity myNote) throws GeneralException {
        validateEntity(myNote);
        if (myNote.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }

        // TODO must fix message
        MyNoteEntity loadedMyNote = repository.findById(myNote.getId()).orElseThrow(() -> new GeneralException("not found"));

        MyNoteEntity myNoteSwap = swap(myNote, loadedMyNote, "myNoteTemp");
        myNoteSwap.setInActivationDate(myNoteSwap.getIsActive() ? null : LocalDate.now());
        return Optional.of(repository.save(myNoteSwap));

    }

    public Optional<MyNoteEntity> saveOrUpdate(MyNoteEntity myNote) throws GeneralException {
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

    @Override
    public Page<MyNoteModel> loadItem(MyNoteModel searchModel, PageRequest pageRequest) {
        pageRequest.getSort().and(Sort.by(Sort.Order.desc("priority"), Sort.Order.desc("creationDate")));
        Page<MyNoteEntity> page;
        if (searchModel == null) {
            page = repository.findAll(pageRequest);
        } else {
            page = repository.findAll(getMyNoteSpecification(searchModel), pageRequest);
        }
        return page.map(mapper::entityToModel);
    }
}
