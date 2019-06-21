package ir.comprehensive.service;

import ir.comprehensive.domain.Category;
import ir.comprehensive.domain.Person;
import ir.comprehensive.mapper.PersonMapper;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.repository.PersonRepository;
import ir.comprehensive.service.response.RequestCallback;
import ir.comprehensive.service.response.ResponseStatus;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class PersonService {

    private PersonRepository repository;
    private PersonMapper mapper;

    public PersonService(PersonRepository repository, PersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ObservableList<PersonModel> findByName(String name) {
        Page<Person> people = repository.findByName(name, PageRequest.of(0, 10));
        List<PersonModel> personModels = people.get().map(mapper::entityToModel).collect(Collectors.toList());
        return FXCollections.observableArrayList(personModels);
    }

    public void loadModel(Long id, RequestCallback<PersonModel> callback) {
        PersonModel result = repository.findById(id).map(mapper::entityToModel).orElse(null);
        callback.accept(result, MessageUtils.Message.SUCCESS_LOAD, ResponseStatus.SUCCESS);
    }

    public void getAllModel(RequestCallback<ObservableList<PersonModel>> callback) {
        List<PersonModel> allModel = repository.findAll().stream().map(mapper::entityToModel).collect(Collectors.toList());
        callback.accept(FXCollections.observableList(allModel), MessageUtils.Message.SUCCESS_LOAD, ResponseStatus.SUCCESS);
    }


    public void getAll(RequestCallback<ObservableList<Person>> callback) {
        List<Person> allPerson = repository.findAll();
        callback.accept(FXCollections.observableList(allPerson), MessageUtils.Message.SUCCESS_LOAD, ResponseStatus.SUCCESS);
    }

    public void search(PersonModel searchExample, RequestCallback<ObservableList<PersonModel>> callback) {
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

        callback.accept(FXCollections.observableList(allModel), MessageUtils.Message.SUCCESS_LOAD, ResponseStatus.SUCCESS);
    }

    public void saveOrUpdate(PersonModel model, RequestCallback<Person> callback) {
        // convert to entity
        Person person = mapper.modelToEntity(model);

        // null point check after convert
        if (person == null) {
            callback.accept(null, MessageUtils.Message.ERROR_IN_SAVE, ResponseStatus.FAIL);
            return;
        }

        // apply save
        if (null == person.getId()) {
            String callbackMessage = MessageUtils.Message.PERSON + " " + MessageUtils.Message.SUCCESS_SAVE;
            callback.accept(repository.save(person), callbackMessage, ResponseStatus.SUCCESS);
            return;
        }

        // apply update

        Person loadedPerson = repository.findById(person.getId()).orElse(null);
        if (loadedPerson == null) {
            callback.accept(null, MessageUtils.Message.ERROR_IN_SAVE, ResponseStatus.FAIL);
        } else {
            loadedPerson.setId(person.getId());
            loadedPerson.setFirstName(person.getFirstName());
            loadedPerson.setLastName(person.getLastName());
            loadedPerson.setEmail(person.getEmail());
            loadedPerson.setPhoneNumber(person.getPhoneNumber());
            loadedPerson.setCategories(person.getCategories());

            String callbackMessage = MessageUtils.Message.PERSON + " " + MessageUtils.Message.SUCCESS_UPDATE;
            callback.accept(repository.save(loadedPerson), callbackMessage, ResponseStatus.SUCCESS);
        }
    }

    public void delete(Long id, RequestCallback<Long> callback) {
        repository.deleteById(id);
        String callbackMessage = MessageUtils.Message.PERSON + " " + MessageUtils.Message.SUCCESS_DELETE;
        callback.accept(id, callbackMessage, ResponseStatus.SUCCESS);
    }
}
