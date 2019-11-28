package ir.comprehensive.service;

import ir.comprehensive.domain.Category;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.repository.CategoryRepository;
import ir.comprehensive.repository.PersonRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.service.extra.Swappable;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService implements Swappable<Category> {

    private CategoryRepository repository;
    private PersonRepository personRepository;

    public CategoryService(CategoryRepository repository, PersonRepository personRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
    }

    public Optional<List<Category>> findByTitle(String title) {
        List<Category> categories = repository.findByTitle(title, PageRequest.of(0, 10)).getContent();
        return Optional.of(categories);
    }

    public Optional<List<Category>> loadAll() {
        return Optional.of(repository.findAll());
    }


    public Optional<List<Category>> search(CategoryModel searchExample) {
        Specification<Category> categorySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getTitle() != null && !searchExample.getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), StringUtils.makeAnyMatch(searchExample.getTitle())));
            }
            if (searchExample.getPhoneNumber() != null && !searchExample.getPhoneNumber().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("phoneNumber")), StringUtils.makeAnyMatch(searchExample.getPhoneNumber())));
            }
            if (searchExample.getFax() != null && !searchExample.getFax().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("fax")), StringUtils.makeAnyMatch(searchExample.getFax())));
            }
            if (searchExample.getEmail() != null && !searchExample.getEmail().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("email")), StringUtils.makeAnyMatch(searchExample.getEmail())));
            }
            if (searchExample.getAddress() != null && !searchExample.getAddress().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("address")), StringUtils.makeAnyMatch(searchExample.getAddress())));
            }
            if (searchExample.getDescription() != null && !searchExample.getDescription().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("description")), StringUtils.makeAnyMatch(searchExample.getDescription())));
            }


            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
        return Optional.of(repository.findAll(categorySpecification));
    }

    private void validateEntity(Category category) throws GeneralException {
        if (category == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
        if (category.getId() != null ? repository.isNotUnique(category.getId(), category.getTitle()) : repository.isNotUnique(category.getTitle())) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE + " " + MessageUtils.Message.CATEGORY_NOT_UNIQUE);
        }
    }

    public Optional<Category> save(Category category) throws GeneralException {
        validateEntity(category);
        category.setId(null);
        return Optional.of(repository.save(category));
    }

    public Optional<Category> update(Category category) throws GeneralException {
        validateEntity(category);
        if (category.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }
        // TODO must fix message
        Category loaCategory = repository.findById(category.getId()).orElseThrow(() -> new GeneralException("not found"));

        return Optional.of(repository.save(swap(category, loaCategory)));

    }

    public Optional<Category> saveOrUpdate(Category category) throws GeneralException {
        return category.getId() == null ? save(category) : update(category);
    }

    public Optional<Long> delete(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("not null id");
        }

        if (personRepository.isCategoryExist(id)) {
            throw new GeneralException(MessageUtils.Message.CATEGORY + " " + MessageUtils.Message.USE_IN + " " + MessageUtils.Message.PEOPLE);
        }
        repository.deleteById(id);
        return Optional.of(id);
    }
}
