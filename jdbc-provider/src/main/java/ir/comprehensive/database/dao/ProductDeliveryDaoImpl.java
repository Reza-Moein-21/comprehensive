package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.BaseDaoImpl;
import ir.comprehensive.database.entity.ProductDeliveryEntity;
import ir.comprehensive.database.mapper.ProductDeliveryMapper;
import ir.comprehensive.database.repository.ProductDeliveryRepository;
import ir.comprehensive.domain.dao.ProductDeliveryDao;
import ir.comprehensive.domain.model.ProductDeliveryModel;
import org.springframework.stereotype.Service;

@Service
public class ProductDeliveryDaoImpl extends BaseDaoImpl<ProductDeliveryEntity, ProductDeliveryModel, ProductDeliveryMapper, ProductDeliveryRepository, Long> implements ProductDeliveryDao {

    public ProductDeliveryDaoImpl(ProductDeliveryMapper mapper, ProductDeliveryRepository repository) {
        super(mapper, repository);
    }

}
