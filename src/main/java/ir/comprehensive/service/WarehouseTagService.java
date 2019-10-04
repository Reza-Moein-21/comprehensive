package ir.comprehensive.service;

import ir.comprehensive.domain.WarehouseTag;
import ir.comprehensive.repository.WarehouseTagRepository;
import ir.comprehensive.service.extra.Swappable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WarehouseTagService implements Swappable<WarehouseTag> {

    @Autowired
    WarehouseTagRepository repository;

    public Optional<List<WarehouseTag>> findByTitle(String title) {
        Page<WarehouseTag> warehouseTags = repository.findByTitle(title, PageRequest.of(0, 10));
        System.out.println(warehouseTags);
        return Optional.of(warehouseTags.getContent());
    }
}
