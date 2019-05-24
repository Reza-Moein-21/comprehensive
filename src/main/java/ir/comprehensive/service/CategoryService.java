package ir.comprehensive.service;

import ir.comprehensive.domain.Category;
import ir.comprehensive.mapper.CategoryMapper;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.repository.CategoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
