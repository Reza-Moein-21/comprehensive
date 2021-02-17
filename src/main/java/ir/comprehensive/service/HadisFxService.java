package ir.comprehensive.service;

import ir.comprehensive.database.entity.HadisEntity;
import ir.comprehensive.fxmapper.HadisFxMapper;
import ir.comprehensive.fxmodel.HadisFxModel;
import ir.comprehensive.repository.HadisFxRepository;
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

@Service
@Transactional
public class HadisFxService implements BaseFxService<HadisEntity, HadisFxModel> {

    private HadisFxRepository repository;
    private HadisFxMapper mapper;

    public HadisFxService(HadisFxRepository repository, HadisFxMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<List<HadisEntity>> loadAll() {
        return Optional.of(repository.findAll());
    }


    public Optional<List<HadisEntity>> search(HadisFxModel searchExample) {
        Specification<HadisEntity> hadisSpecification = getHadisSpecification(searchExample);
        return Optional.of(repository.findAll(hadisSpecification));
    }

    private Specification<HadisEntity> getHadisSpecification(HadisFxModel searchExample) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchExample.getTitle() != null && !searchExample.getTitle().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), StringUtils.makeAnyMatch(searchExample.getTitle())));
            }
            if (searchExample.getDescription() != null && !searchExample.getDescription().isEmpty()) {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("description")), StringUtils.makeAnyMatch(searchExample.getDescription())));
            }


            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }

    private void validateEntity(HadisEntity hadis) throws GeneralException {
        if (hadis == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
    }

    public Optional<HadisEntity> save(HadisEntity hadis) throws GeneralException {
        validateEntity(hadis);
        hadis.setId(null);
        return Optional.of(repository.save(hadis));
    }

    public Optional<HadisEntity> update(HadisEntity hadis) throws GeneralException {
        validateEntity(hadis);
        if (hadis.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }
        // TODO must fix message
        HadisEntity loadedHadis = repository.findById(hadis.getId()).orElseThrow(() -> new GeneralException("not found"));

        return Optional.of(repository.save(swap(hadis, loadedHadis)));

    }

    public Optional<HadisEntity> saveOrUpdate(HadisEntity hadis) throws GeneralException {
        return hadis.getId() == null ? save(hadis) : update(hadis);
    }

    public Optional<Long> delete(Long id) throws GeneralException {
        if (id == null) {
            // TODO fix message
            throw new GeneralException("not null id");
        }

        repository.deleteById(id);
        return Optional.of(id);
    }

    public HadisEntity getRandomHadis() {
        Long qty = repository.countAll();
        int idx = (int)(Math.random() * qty);
        Page<HadisEntity> hadisPage = repository.findAll(PageRequest.of(idx, 1));
        HadisEntity q = null;
        if (hadisPage.hasContent()) {
            q = hadisPage.getContent().get(0);
        }
        return q;
    }

    @Override
    public Page<HadisFxModel> loadItem(HadisFxModel searchModel, PageRequest pageRequest) {
        Page<HadisEntity> page;
        if (searchModel == null) {
            page = repository.findAll(pageRequest);
        } else {
            page = repository.findAll(getHadisSpecification(searchModel), pageRequest);
        }
        return page.map(mapper::entityToModel);

    }
}
