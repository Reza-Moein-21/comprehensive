package ir.comprehensive.service;

import ir.comprehensive.entity.MyNote;
import ir.comprehensive.entity.MyNoteTemp;
import ir.comprehensive.mapper.MyNoteTempMapper;
import ir.comprehensive.model.MyNoteTempModel;
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
public class MyNoteTempService implements BaseService<MyNoteTemp, MyNoteTempModel> {

    private MyNoteTempRepository repository;
    private MyNoteTempMapper mapper;

    @Autowired
    public MyNoteTempService(MyNoteTempRepository repository, MyNoteTempMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void sendToTemp(Set<Long> ids) {
        if (ids == null) {
            // TODO fix message
            throw new GeneralException("not null id");
        }
        List<MyNoteTemp> listForSave = new ArrayList<>();

        ids.forEach(myNoteId -> {
            if (!repository.isMyNoteExist(myNoteId)) {
                listForSave.add(new MyNoteTemp(new MyNote(myNoteId)));
            }
        });
        if (!listForSave.isEmpty()) {
            repository.saveAll(listForSave);
        }
    }

    public Optional<List<MyNoteTemp>> loadAll() {
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
    public Page<MyNoteTempModel> loadItem(MyNoteTempModel searchModel, PageRequest pageRequest) {
        Page<MyNoteTemp> page;
        page = repository.findAll(pageRequest);
        return page.map(mapper::entityToModel);
    }
}
