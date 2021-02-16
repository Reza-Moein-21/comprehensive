package ir.comprehensive.service;

import ir.comprehensive.entity.MyNoteEntity;
import ir.comprehensive.entity.MyNoteTempEntity;
import ir.comprehensive.fxmapper.MyNoteTempFxMapper;
import ir.comprehensive.fxmodel.MyNoteTempFxModel;
import ir.comprehensive.repository.MyNoteTempRepository;
import ir.comprehensive.service.extra.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class MyNoteTempService implements BaseService<MyNoteTempEntity, MyNoteTempFxModel> {

    private MyNoteTempRepository repository;
    private MyNoteTempFxMapper mapper;

    @Autowired
    public MyNoteTempService(MyNoteTempRepository repository, MyNoteTempFxMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void sendToTemp(Set<Long> ids) {
        if (ids == null) {
            // TODO fix message
            throw new GeneralException("not null id");
        }
        List<MyNoteTempEntity> listForSave = new ArrayList<>();

        ids.forEach(myNoteId -> {
            if (!repository.isMyNoteExist(myNoteId)) {
                listForSave.add(new MyNoteTempEntity(new MyNoteEntity(myNoteId)));
            }
        });
        if (!listForSave.isEmpty()) {
            repository.saveAll(listForSave);
        }
    }

    public Optional<List<MyNoteTempEntity>> loadAll() {
        return Optional.of(repository.findAll());
    }

    public void deleteAll(Set<Long> ids) throws GeneralException {
        if (ids == null) {
            // TODO fix message
            throw new GeneralException("not null id");
        }

        repository.deleteAllById(ids);
    }

    @Override
    public Page<MyNoteTempFxModel> loadItem(MyNoteTempFxModel searchModel, PageRequest pageRequest) {
        Page<MyNoteTempEntity> page;
        page = repository.findAll(pageRequest);
        return page.map(mapper::entityToModel);
    }
}
