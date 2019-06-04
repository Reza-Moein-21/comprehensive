package ir.comprehensive.service;

import ir.comprehensive.domain.Category;
import ir.comprehensive.domain.Person;
import ir.comprehensive.mapper.PersonMapper;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.repository.PersonRepository;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class PersonService extends CallbackMessage<Person> {

    private PersonRepository repository;
    private PersonMapper mapper;

    public PersonService(PersonRepository repository, PersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PersonModel loadModel(Long id) {
        return repository.findById(id).map(mapper::entityToModel).orElse(null);
    }

    public ObservableList<PersonModel> getAllModel() {
        List<PersonModel> allModel = repository.findAll().stream().map(mapper::entityToModel).collect(Collectors.toList());
        return FXCollections.observableList(allModel);
    }


    public ObservableList<Person> getAll() {
        List<Person> allPerson = repository.findAll();
        return FXCollections.observableList(allPerson);
    }

    public ObservableList<PersonModel> search(PersonModel searchExample) {

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

            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
        List<PersonModel> allModel = repository.findAll(personSpecification).stream().map(mapper::entityToModel).collect(Collectors.toList());
        return FXCollections.observableList(allModel);
    }

    public CallbackMessage<Person> saveOrUpdate(PersonModel model) {
        Person person = mapper.modelToEntity(model);
        if (person == null) {
            setCallbackResult(null);
            setCallbackMessage(MessageUtils.Message.ERROR_IN_SAVE);
            return this;
        }

        if (null == person.getId()) {
            setCallbackResult(repository.save(person));
            setCallbackMessage(MessageUtils.Message.PERSON + " " + MessageUtils.Message.SUCCESS_SAVE);
            return this;
        } else {
            Person loadedPerson = repository.findById(person.getId()).orElse(null);
            if (loadedPerson == null) {
                setCallbackResult(null);
                setCallbackMessage(MessageUtils.Message.ERROR_IN_SAVE);
                return this;
            }
            loadedPerson.setId(person.getId());
            loadedPerson.setFirstName(person.getFirstName());
            loadedPerson.setLastName(person.getLastName());
            loadedPerson.setEmail(person.getEmail());
            loadedPerson.setPhoneNumber(person.getPhoneNumber());
            loadedPerson.setCategories(person.getCategories());

            setCallbackResult(repository.save(loadedPerson));
            setCallbackMessage(MessageUtils.Message.PERSON + " " + MessageUtils.Message.SUCCESS_UPDATE);
            return this;
        }

    }

    public CallbackMessage<Person> delete(Long id) {
        repository.deleteById(id);
        setCallbackResult(null);
        setCallbackMessage(MessageUtils.Message.PERSON + " " + MessageUtils.Message.SUCCESS_DELETE);
        return this;

    }
}
