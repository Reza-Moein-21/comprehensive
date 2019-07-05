package ir.comprehensive.service;

import ir.comprehensive.domain.Category;
import ir.comprehensive.domain.Person;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.repository.PersonRepository;
import ir.comprehensive.repository.ProductDeliveryRepository;
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
public class PersonService implements Swappable<Person> {
    private ProductDeliveryRepository productDeliveryRepository;
    private PersonRepository repository;


    public PersonService(ProductDeliveryRepository productDeliveryRepository, PersonRepository repository) {
        this.productDeliveryRepository = productDeliveryRepository;
        this.repository = repository;
    }

    public Optional<List<Person>> findByName(String name) {
        Page<Person> people = repository.findByName(name, PageRequest.of(0, 10));
        return Optional.of(people.getContent());
    }

    public Optional<Person> load(Long id) throws GeneralException {
        if (id == null) {
            // TODO must use true message
            throw new GeneralException("null Id");
        }
        return repository.findById(id);
    }

    public Optional<List<Person>> loadAll() {
        return Optional.of(repository.findAll());
    }


    public Optional<List<Person>> search(PersonModel searchExample) {
        Specification<Person> personSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getFirstName() != null && !searchExample.getFirstName().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("firstName"), StringUtils.makeAnyMatch(searchExample.getFirstName())));
            }
            if (searchExample.getLastName() != null && !searchExample.getLastName().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("lastName"), StringUtils.makeAnyMatch(searchExample.getLastName())));
            }
            if (searchExample.getPhoneNumber() != null && !searchExample.getPhoneNumber().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("phoneNumber"), StringUtils.makeAnyMatch(searchExample.getPhoneNumber())));
            }
            if (searchExample.getEmail() != null && !searchExample.getEmail().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("email"), StringUtils.makeAnyMatch(searchExample.getEmail())));
            }
            if (searchExample.getCategories() != null && !searchExample.getCategories().isEmpty()) {
                Join<Person, Category> categories = root.join("categories");
                predicateList.add(categories.in(searchExample.getCategories().stream().map(CategoryModel::getId).collect(Collectors.toSet())));
            }
            query.distinct(true);
            query.orderBy(criteriaBuilder.asc(root.get("firstName")));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
        return Optional.of(repository.findAll(personSpecification));
    }

    private void validateEntity(Person person) throws GeneralException {
        if (person == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
    }

    public Optional<Person> save(Person person) throws GeneralException {
        validateEntity(person);
        person.setId(null);
        return Optional.of(repository.save(person));
    }

    public Optional<Person> update(Person person) throws GeneralException {
        validateEntity(person);
        if (person.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }
        // TODO must fix message
        Person loadedPerson = repository.findById(person.getId()).orElseThrow(() -> new GeneralException("not found"));

        return Optional.of(repository.save(swap(person, loadedPerson)));

    }

    public Optional<Person> saveOrUpdate(Person person) throws GeneralException {
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
        repository.deleteById(id);
        return Optional.of(id);
    }
}
