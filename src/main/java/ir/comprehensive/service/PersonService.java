package ir.comprehensive.service;

import ir.comprehensive.domain.Person;
import ir.comprehensive.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PersonService {

    private PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> fetchAll() {
        return repository.findAll();
    }
}
