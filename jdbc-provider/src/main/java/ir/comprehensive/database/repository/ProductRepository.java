package ir.comprehensive.database.repository;

import ir.comprehensive.database.entity.ProductEntity;
import ir.comprehensive.database.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Long> {
}
