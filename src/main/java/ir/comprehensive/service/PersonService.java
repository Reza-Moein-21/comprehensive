package ir.comprehensive.service;

import ir.comprehensive.domain.Person;
import ir.comprehensive.mapper.PersonMapper;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.repository.PersonRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ObservableList<PersonModel> getAllModel() {
        List<PersonModel> allModel = repository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
        return FXCollections.observableList(allModel);
    }

    public ObservableList<Person> getAll() {
        List<Person> allPerson = repository.findAll();
        return FXCollections.observableList(allPerson);
    }
}
