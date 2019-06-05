package ir.comprehensive.service;

import ir.comprehensive.domain.Category;
import ir.comprehensive.mapper.CategoryMapper;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.repository.CategoryRepository;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService extends CallbackMessage<Category> {

    private CategoryRepository repository;
    private CategoryMapper mapper;

    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ObservableList<CategoryModel> findByTitle(String title) {
        Page<Category> categories = repository.findByTitle(title, PageRequest.of(0, 10));
        List<CategoryModel> categoryModels = categories.get().map(mapper::entityToModel).collect(Collectors.toList());
        return FXCollections.observableArrayList(categoryModels);
    }

    public ObservableList<CategoryModel> getAllModel() {
        List<CategoryModel> allModel = repository.findAll().stream().map(mapper::entityToModel).collect(Collectors.toList());
        return FXCollections.observableList(allModel);
    }


    public ObservableList<CategoryModel> search(CategoryModel searchExample) {

        Specification<Category> categorySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getTitle() != null && !searchExample.getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("title"), StringUtils.makeAnyMatch(searchExample.getTitle())));
            }
            if (searchExample.getPhoneNumber() != null && !searchExample.getPhoneNumber().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("phoneNumber"), StringUtils.makeAnyMatch(searchExample.getPhoneNumber())));
            }
            if (searchExample.getFax() != null && !searchExample.getFax().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("fax"), StringUtils.makeAnyMatch(searchExample.getFax())));
            }
            if (searchExample.getEmail() != null && !searchExample.getEmail().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("email"), StringUtils.makeAnyMatch(searchExample.getEmail())));
            }
            if (searchExample.getAddress() != null && !searchExample.getAddress().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("address"), StringUtils.makeAnyMatch(searchExample.getAddress())));
            }
            if (searchExample.getDescription() != null && !searchExample.getDescription().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("description"), StringUtils.makeAnyMatch(searchExample.getDescription())));
            }


            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
        List<CategoryModel> allModel = repository.findAll(categorySpecification).stream().map(mapper::entityToModel).collect(Collectors.toList());
        return FXCollections.observableList(allModel);
    }

    public CallbackMessage<Category> saveOrUpdate(CategoryModel model) {
        Category category = mapper.modelToEntity(model);
        if (category == null) {
            setCallbackResult(null);
            setCallbackMessage(MessageUtils.Message.ERROR_IN_SAVE);
            return this;
        }

        if (null == category.getId()) {
            setCallbackResult(repository.save(category));
            setCallbackMessage(MessageUtils.Message.CATEGORY + " " + MessageUtils.Message.SUCCESS_SAVE);
            return this;
        } else {
            Category loadedCategory = repository.findById(category.getId()).orElse(null);
            if (loadedCategory == null) {
                setCallbackResult(null);
                setCallbackMessage(MessageUtils.Message.ERROR_IN_SAVE);
                return this;
            }
            loadedCategory.setId(category.getId());
            loadedCategory.setTitle(category.getTitle());
            loadedCategory.setPhoneNumber(category.getPhoneNumber());
            loadedCategory.setFax(category.getFax());
            loadedCategory.setEmail(category.getEmail());
            loadedCategory.setAddress(category.getAddress());
            loadedCategory.setDescription(category.getDescription());

            setCallbackResult(repository.save(loadedCategory));
            setCallbackMessage(MessageUtils.Message.CATEGORY + " " + MessageUtils.Message.SUCCESS_UPDATE);
            return this;
        }

    }

    public CallbackMessage<Category> delete(Long id) {
        repository.deleteById(id);
        setCallbackResult(null);
        setCallbackMessage(MessageUtils.Message.CATEGORY + " " + MessageUtils.Message.SUCCESS_DELETE);
        return this;

    }
}
