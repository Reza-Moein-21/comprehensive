package ir.comprehensive.service;

import ir.comprehensive.domain.Category;
import ir.comprehensive.domain.Person;
import ir.comprehensive.mapper.PersonMapper;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.model.basemodel.Editable;
import ir.comprehensive.repository.PersonRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static ir.comprehensive.utils.MessageUtils.getMessage;


@Service
@Transactional
public class PersonService extends CallbackMessage<Person> {

    private PersonRepository repository;
    private PersonMapper mapper;

    public PersonService(PersonRepository repository, PersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PersonModel loadModel(Long id, Editable onEdit) {
        Optional<Person> personOptional = repository.findById(id);
        return personOptional.map(person -> {
            PersonModel model = mapper.entityToModel(person);
            model.setOnEdit(onEdit);
            return model;
        }).orElse(null);
    }
    public ObservableList<PersonModel> getAllModel() {
        List<PersonModel> allModel = repository.findAll().stream().map(mapper::entityToModel).collect(Collectors.toList());
        return FXCollections.observableList(allModel);
    }

    public ObservableList<PersonModel> getAllModel(Editable onEdit) {
        List<PersonModel> allModel = repository.findAll().stream().map(mapper::entityToModel).peek(model -> model.setOnEdit(onEdit)).collect(Collectors.toList());
        return FXCollections.observableList(allModel);
    }

    public ObservableList<Person> getAll() {
        List<Person> allPerson = repository.findAll();
        return FXCollections.observableList(allPerson);
    }

    public CallbackMessage save(PersonModel model) {
        setCallbackResult(repository.save(mapper.modelToEntity(model)));
        setCallbackMessage(getMessage("person") + " " + getMessage("successSave"));
        return this;
    }

    public ObservableList<PersonModel> search(PersonModel searchExample, Editable onEdit) {

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
        List<PersonModel> allModel = repository.findAll(personSpecification).stream().map(mapper::entityToModel).peek(model -> model.setOnEdit(onEdit)).collect(Collectors.toList());
        return FXCollections.observableList(allModel);
    }
}
