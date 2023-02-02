package ir.comprehensive.database.dao.base;

import ir.comprehensive.database.entity.base.BaseEntity;
import ir.comprehensive.database.mapper.base.BaseMapper;
import ir.comprehensive.database.repository.base.BaseRepository;
import ir.comprehensive.database.util.PageableUtils;
import ir.comprehensive.domain.exception.DAOException;
import ir.comprehensive.domain.model.base.DomainModel;
import ir.comprehensive.domain.model.base.FieldCriteria;
import ir.comprehensive.domain.model.base.PageModel;
import ir.comprehensive.domain.model.base.PageRequestModel;
import ir.comprehensive.service.base.DAOService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static ir.comprehensive.domain.exception.DAOException.ExceptionType.ERROR_IN_DELETE;
import static ir.comprehensive.domain.exception.DAOException.ExceptionType.ERROR_IN_UPDATE;

public abstract class BaseDaoImpl<E extends BaseEntity<I>, M extends DomainModel<I>, P extends BaseMapper<E, M, I>, R extends BaseRepository<E, I>, I extends Serializable> implements DAOService<M, I> {

    protected final P mapper;

    protected final R repository;

    public BaseDaoImpl(P mapper, R repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Optional<M> findById(I id) {
        return repository.findById(id).map(mapper::entityToModel);
    }

    @Override
    public PageModel<M, I> findAll(PageRequestModel pageRequest) throws DAOException {
        PageRequest springDatePageRequest = covertToSpringDataPageRequest(pageRequest);

        Page<E> findAllPage = repository
                .findAll(springDatePageRequest);

        return convertToPageModel(findAllPage);
    }

    @Override
    public M save(M model) throws DAOException {
        E savedEntity = repository.save(mapper.modelToEntity(model));
        return mapper.entityToModel(savedEntity);
    }

    @Override
    public M save(M model, String... ignoredFields) throws DAOException {
        return null;
    }

    @Override
    public void delete(M model) throws DAOException {
        if (Objects.isNull(model) || Objects.isNull(model.getId()))
            throw DAOException.of(ERROR_IN_DELETE, "Model is null");

        repository.deleteById(model.getId());
    }

    @Override
    public List<M> search(FieldCriteria... searchCriteria) throws DAOException {
        return null;
    }

    @Override
    public PageModel<M, I> search(PageRequestModel pageRequest, FieldCriteria... searchCriteria) throws DAOException {
        return null;
    }

    @Override
    public void deleteAll(Set<I> ids) {

    }

    @Override
    public void deleteById(I id) {

    }

    private PageModel<M, I> convertToPageModel(Page<E> page) {
        return PageModel.<M, I>builder()
                .content(page
                        .getContent()
                        .stream()
                        .map(mapper::entityToModel)
                        .collect(Collectors.toList()))
                .numberOfElements(page.getNumberOfElements())
                .currentPage(page.getNumber())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();

    }

    private PageRequest covertToSpringDataPageRequest(PageRequestModel pageRequest) {
        Sort.Order[] orders = Arrays.stream(pageRequest.getSorts())
                .map(s -> new Sort.Order(convertToSpringSortDirection(s.direction), s.property))
                .toArray(size -> new Sort.Order[size]);

        return PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), Sort.by(orders));
    }


    private Sort.Direction convertToSpringSortDirection(PageRequestModel.SortModel.DirectionEnum direction) {
        return Optional
                .ofNullable(direction)
                .map(d -> PageRequestModel.SortModel.DirectionEnum.ASC.equals(d)
                        ? Sort.Direction.ASC
                        : Sort.Direction.DESC)
                .orElse(Sort.DEFAULT_DIRECTION);
    }
}
