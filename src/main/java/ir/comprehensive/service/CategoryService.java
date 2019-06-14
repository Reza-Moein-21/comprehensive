package ir.comprehensive.service;

import ir.comprehensive.domain.Category;
import ir.comprehensive.mapper.CategoryMapper;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.repository.CategoryRepository;
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

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    private CategoryRepository repository;
    private CategoryMapper mapper;

    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void findByTitle(String title, RequestCallback<ObservableList<CategoryModel>> callback) {
        Page<Category> categories = repository.findByTitle(title, PageRequest.of(0, 10));
        List<CategoryModel> categoryModels = categories.get().map(mapper::entityToModel).collect(Collectors.toList());
        callback.accept(FXCollections.observableArrayList(categoryModels), MessageUtils.Message.SUCCESS_LOAD, ResponseStatus.SUCCESS);
    }

    public void getAllModel(RequestCallback<ObservableList<CategoryModel>> callback) {
        List<CategoryModel> allModel = repository.findAll().stream().map(mapper::entityToModel).collect(Collectors.toList());
        callback.accept(FXCollections.observableList(allModel), MessageUtils.Message.SUCCESS_LOAD, ResponseStatus.SUCCESS);
    }


    public void search(CategoryModel searchExample, RequestCallback<ObservableList<CategoryModel>> callback) {
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
        callback.accept(FXCollections.observableList(allModel), MessageUtils.Message.SUCCESS_LOAD, ResponseStatus.SUCCESS);
    }

    public void saveOrUpdate(CategoryModel model, RequestCallback<Category> callback) {
        // convert to Entity
        Category category = mapper.modelToEntity(model);

        // null point check after convert
        if (category == null) {
            callback.accept(null, MessageUtils.Message.ERROR_IN_SAVE, ResponseStatus.FAIL);
            return;
        }

        // apply save
        if (null == category.getId()) {
            String callbackMessage = MessageUtils.Message.CATEGORY + " " + MessageUtils.Message.SUCCESS_SAVE;
            callback.accept(repository.save(category), callbackMessage, ResponseStatus.SUCCESS);
            return;
        }

        // apply update
        Category loadedCategory = repository.findById(category.getId()).orElse(null);
        if (loadedCategory == null) {
            callback.accept(null, MessageUtils.Message.ERROR_IN_SAVE, ResponseStatus.FAIL);

        } else {
            loadedCategory.setId(category.getId());
            loadedCategory.setTitle(category.getTitle());
            loadedCategory.setPhoneNumber(category.getPhoneNumber());
            loadedCategory.setFax(category.getFax());
            loadedCategory.setEmail(category.getEmail());
            loadedCategory.setAddress(category.getAddress());
            loadedCategory.setDescription(category.getDescription());
            String callbackMessage = MessageUtils.Message.CATEGORY + " " + MessageUtils.Message.SUCCESS_UPDATE;
            callback.accept(repository.save(loadedCategory), callbackMessage, ResponseStatus.SUCCESS);
        }
    }

    public void delete(Long id, RequestCallback<Long> callback) {
        repository.deleteById(id);
        String callbackMessage = MessageUtils.Message.CATEGORY + " " + MessageUtils.Message.SUCCESS_DELETE;
        callback.accept(id, callbackMessage, ResponseStatus.SUCCESS);
    }
}
