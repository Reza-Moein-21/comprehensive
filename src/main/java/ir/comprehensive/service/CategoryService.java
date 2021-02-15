package ir.comprehensive.service;

import ir.comprehensive.entity.CategoryEntity;
import ir.comprehensive.mapper.CategoryMapper;
import ir.comprehensive.mapper.CategoryReportMapper;
import ir.comprehensive.fxmodel.*;
import ir.comprehensive.fxmodel.basemodel.BaseReportBean;
import ir.comprehensive.repository.CategoryRepository;
import ir.comprehensive.repository.PersonRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService implements BaseService<CategoryEntity, CategoryModel> {

    private CategoryRepository repository;
    private PersonRepository personRepository;
    private CategoryMapper mapper;
    private CategoryReportMapper categoryReportMapper;

    public CategoryService(CategoryRepository repository, PersonRepository personRepository, CategoryMapper mapper, CategoryReportMapper categoryReportMapper) {
        this.repository = repository;
        this.personRepository = personRepository;
        this.mapper = mapper;
        this.categoryReportMapper = categoryReportMapper;
    }

    public Optional<List<CategoryEntity>> findByTitle(String title) {
        List<CategoryEntity> categories = repository.findByTitle(title, PageRequest.of(0, 10)).getContent();
        return Optional.of(categories);
    }

    public Optional<List<CategoryEntity>> loadAll() {
        return Optional.of(repository.findAll());
    }


    public Optional<List<CategoryEntity>> search(CategoryModel searchExample) {
        Specification<CategoryEntity> categorySpecification = getCategorySpecification(searchExample);
        return Optional.of(repository.findAll(categorySpecification));
    }

    private Specification<CategoryEntity> getCategorySpecification(CategoryModel searchExample) {
        return (root, query, criteriaBuilder) -> {
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
    }

    private void validateEntity(CategoryEntity category) throws GeneralException {
        if (category == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
        if (category.getId() != null ? repository.isNotUnique(category.getId(), category.getTitle()) : repository.isNotUnique(category.getTitle())) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE + " " + MessageUtils.Message.CATEGORY_NOT_UNIQUE);
        }
    }

    public Optional<CategoryEntity> save(CategoryEntity category) throws GeneralException {
        validateEntity(category);
        category.setId(null);
        return Optional.of(repository.save(category));
    }

    public Optional<CategoryEntity> update(CategoryEntity category) throws GeneralException {
        validateEntity(category);
        if (category.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }
        // TODO must fix message
        CategoryEntity loaCategory = repository.findById(category.getId()).orElseThrow(() -> new GeneralException("not found"));

        return Optional.of(repository.save(swap(category, loaCategory)));

    }

    public Optional<CategoryEntity> saveOrUpdate(CategoryEntity category) throws GeneralException {
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

    public HumanResourceInfo getInfo() {
        HumanResourceInfo info = new HumanResourceInfo();
        info.setCategoryCount(this.getNumberString(repository.totalCount()));
        info.setPersonCount(this.getNumberString(personRepository.totalCount()));
        return info;
    }

    @Override
    public Page<CategoryModel> loadItem(CategoryModel searchModel, PageRequest pageRequest) {
        Page<CategoryEntity> page;
        if (searchModel == null) {
            page = repository.findAll(pageRequest);
        } else {
            page = repository.findAll(getCategorySpecification(searchModel), pageRequest);
        }
        return page.map(mapper::entityToModel);

    }

    public List<CategoryReportBean> getReportBeanList(CategoryModel searchModel) throws GeneralException {
        return getReportBeanList(searchModel, null);
    }

    public List<CategoryReportBean> getReportBeanList(CategoryModel searchModel, Set<Long> ids) throws GeneralException {
        if (ids != null && !ids.isEmpty()) {
            List<CategoryReportBean> categoryReportBeans = repository.findAllById(ids).stream().map(categoryReportMapper::entityToModel).collect(Collectors.toList());
            return BaseReportBean.fillRowNumber(categoryReportBeans);
        }

        List<CategoryReportBean> categoryReportBeans = repository.findAll(getCategorySpecification(searchModel)).stream().map(categoryReportMapper::entityToModel).collect(Collectors.toList());
        return BaseReportBean.fillRowNumber(categoryReportBeans);
    }

}
