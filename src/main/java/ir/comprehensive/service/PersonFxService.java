package ir.comprehensive.service;

import ir.comprehensive.database.entity.CategoryEntity;
import ir.comprehensive.database.entity.PersonEntity;
import ir.comprehensive.fxmapper.PersonFxMapper;
import ir.comprehensive.fxmapper.PersonReportMapper;
import ir.comprehensive.fxmodel.CategoryFxModel;
import ir.comprehensive.fxmodel.PersonFxModel;
import ir.comprehensive.fxmodel.PersonReportBean;
import ir.comprehensive.fxmodel.basemodel.BaseReportBean;
import ir.comprehensive.repository.MyNoteFxRepository;
import ir.comprehensive.repository.PersonFxRepository;
import ir.comprehensive.repository.ProductDeliveryFxRepository;
import ir.comprehensive.service.extra.GeneralException;
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
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class PersonFxService implements BaseFxService<PersonEntity, PersonFxModel> {
    private ProductDeliveryFxRepository productDeliveryRepository;
    private PersonFxRepository repository;
    private MyNoteFxRepository myNoteRepository;
    private PersonFxMapper mapper;
    private PersonReportMapper personReportMapper;

    public PersonFxService(ProductDeliveryFxRepository productDeliveryRepository, PersonFxRepository repository, MyNoteFxRepository myNoteRepository, PersonFxMapper mapper, PersonReportMapper personReportMapper) {
        this.productDeliveryRepository = productDeliveryRepository;
        this.repository = repository;
        this.myNoteRepository = myNoteRepository;
        this.mapper = mapper;
        this.personReportMapper = personReportMapper;
    }

    public Optional<List<PersonEntity>> findByName(String name) {
        Page<PersonEntity> people = repository.findByName(name, PageRequest.of(0, 10));
        return Optional.of(people.getContent());
    }

    public Optional<PersonEntity> load(Long id) throws GeneralException {
        if (id == null) {
            // TODO must use true message
            throw new GeneralException("null Id");
        }
        return repository.findById(id);
    }

    public Optional<List<PersonEntity>> loadAll() {
        return Optional.of(repository.findAll());
    }


    public Optional<List<PersonEntity>> search(PersonFxModel searchExample) {
        Specification<PersonEntity> personSpecification = getPersonSpecification(searchExample);
        return Optional.of(repository.findAll(personSpecification));
    }

    private Specification<PersonEntity> getPersonSpecification(PersonFxModel searchExample) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getFirstName() != null && !searchExample.getFirstName().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("firstName")), StringUtils.makeAnyMatch(searchExample.getFirstName())));
            }
            if (searchExample.getLastName() != null && !searchExample.getLastName().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("lastName")), StringUtils.makeAnyMatch(searchExample.getLastName())));
            }
            if (searchExample.getPhoneNumber() != null && !searchExample.getPhoneNumber().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("phoneNumber")), StringUtils.makeAnyMatch(searchExample.getPhoneNumber())));
            }
            if (searchExample.getDescription() != null && !searchExample.getDescription().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("description")), StringUtils.makeAnyMatch(searchExample.getDescription())));
            }
            if (searchExample.getEmail() != null && !searchExample.getEmail().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("email")), StringUtils.makeAnyMatch(searchExample.getEmail())));
            }
            if (searchExample.getCategories() != null && !searchExample.getCategories().isEmpty()) {
                Join<PersonEntity, CategoryEntity> categories = root.join("categories");
                predicateList.add(categories.in(searchExample.getCategories().stream().map(CategoryFxModel::getId).collect(Collectors.toSet())));
            }
            query.distinct(true);
            query.orderBy(criteriaBuilder.asc(root.get("firstName")));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }

    private void validateEntity(PersonEntity person) throws GeneralException {
        if (person == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }

        if (person.getId() != null ? repository.isNotUnique(person.getId(), person.getFirstName(), person.getLastName()) : repository.isNotUnique(person.getFirstName(), person.getLastName())) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE + " " + MessageUtils.Message.PERSON_NOT_UNIQUE);
        }
    }

    public Optional<PersonEntity> save(PersonEntity person) throws GeneralException {
        validateEntity(person);
        person.setId(null);
        return Optional.of(repository.save(person));
    }

    public Optional<PersonEntity> update(PersonEntity person) throws GeneralException {
        validateEntity(person);
        if (person.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }
        // TODO must fix message
        PersonEntity loadedPerson = repository.findById(person.getId()).orElseThrow(() -> new GeneralException("not found"));

        return Optional.of(repository.save(swap(person, loadedPerson)));

    }

    public Optional<PersonEntity> saveOrUpdate(PersonEntity person) throws GeneralException {
        return person.getId() == null ? save(person) : update(person);
    }

    public Optional<Long> delete(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("not null id");
        }
        if (productDeliveryRepository.isPersonExist(id)) {
            throw new GeneralException(MessageUtils.Message.PERSON + " " + MessageUtils.Message.USE_IN + " " + MessageUtils.Message.STOREROOM);
        }
        if (myNoteRepository.isPersonExist(id)) {
            throw new GeneralException(MessageUtils.Message.PERSON + " " + MessageUtils.Message.USE_IN + " " + MessageUtils.Message.MY_NOTE);
        }
        repository.deleteById(id);
        return Optional.of(id);
    }

    @Override
    public Page<PersonFxModel> loadItem(PersonFxModel searchModel, PageRequest pageRequest) {
        Page<PersonEntity> personPage;
        if (searchModel == null) {
            personPage = repository.findAll(pageRequest);
        } else {
            personPage = repository.findAll(getPersonSpecification(searchModel), pageRequest);
        }
        return personPage.map(mapper::entityToModel);
    }

    public List<PersonReportBean> getReportBeanList(PersonFxModel searchModel) throws GeneralException {
        return getReportBeanList(searchModel, null);
    }

    public List<PersonReportBean> getReportBeanList(PersonFxModel searchModel, Set<Long> ids) throws GeneralException {
        if (ids != null && !ids.isEmpty()) {
            List<PersonReportBean> personReportBeans = repository.findAllById(ids).stream().map(personReportMapper::entityToModel).collect(Collectors.toList());
            return BaseReportBean.fillRowNumber(personReportBeans);
        }

        List<PersonReportBean> personReportBeans = repository.findAll(getPersonSpecification(searchModel)).stream().map(personReportMapper::entityToModel).collect(Collectors.toList());
        return BaseReportBean.fillRowNumber(personReportBeans);
    }
}
