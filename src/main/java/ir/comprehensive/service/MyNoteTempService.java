package ir.comprehensive.service;

import ir.comprehensive.domain.MyNote;
import ir.comprehensive.domain.MyNoteTemp;
import ir.comprehensive.repository.MyNoteTempRepository;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.service.extra.Swappable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class MyNoteTempService implements Swappable<MyNoteTemp> {

    private MyNoteTempRepository repository;

    @Autowired
    public MyNoteTempService(MyNoteTempRepository repository) {
        this.repository = repository;
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
}
