package ir.comprehensive.service;

import ir.comprehensive.entity.Hadis;
import ir.comprehensive.mapper.HadisMapper;
import ir.comprehensive.model.HadisModel;
import ir.comprehensive.repository.HadisRepository;
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
public class HadisService implements BaseService<Hadis,HadisModel> {

    private HadisRepository repository;
    private HadisMapper mapper;

    public HadisService(HadisRepository repository, HadisMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<List<Hadis>> loadAll() {
        return Optional.of(repository.findAll());
    }


    public Optional<List<Hadis>> search(HadisModel searchExample) {
        Specification<Hadis> hadisSpecification = getHadisSpecification(searchExample);
        return Optional.of(repository.findAll(hadisSpecification));
    }

    private Specification<Hadis> getHadisSpecification(HadisModel searchExample) {
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

    private void validateEntity(Hadis hadis) throws GeneralException {
        if (hadis == null) {
            throw new GeneralException(MessageUtils.Message.ERROR_IN_SAVE);
        }
    }

    public Optional<Hadis> save(Hadis hadis) throws GeneralException {
        validateEntity(hadis);
        hadis.setId(null);
        return Optional.of(repository.save(hadis));
    }

    public Optional<Hadis> update(Hadis hadis) throws GeneralException {
        validateEntity(hadis);
        if (hadis.getId() == null) {
            // TODO must fix message
            throw new GeneralException("not null id");
        }
        // TODO must fix message
        Hadis loadedHadis = repository.findById(hadis.getId()).orElseThrow(() -> new GeneralException("not found"));

        return Optional.of(repository.save(swap(hadis, loadedHadis)));

    }

    public Optional<Hadis> saveOrUpdate(Hadis hadis) throws GeneralException {
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

    public Hadis getRandomHadis() {
        Long qty = repository.countAll();
        int idx = (int)(Math.random() * qty);
        Page<Hadis> hadisPage = repository.findAll(PageRequest.of(idx, 1));
        Hadis q = null;
        if (hadisPage.hasContent()) {
            q = hadisPage.getContent().get(0);
        }
        return q;
    }

    @Override
    public Page<HadisModel> loadItem(HadisModel searchModel, PageRequest pageRequest) {
        Page<Hadis> page;
        if (searchModel == null) {
            page = repository.findAll(pageRequest);
        } else {
            page = repository.findAll(getHadisSpecification(searchModel), pageRequest);
        }
        return page.map(mapper::entityToModel);

    }
}
