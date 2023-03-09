package ir.comprehensive.service.warehousing;

import ir.comprehensive.domain.model.dto.WarehouseInfo;
import ir.comprehensive.service.base.Statistical;

public interface WarehouseService extends Statistical<WarehouseInfo> {
    Integer reduceWarehouseCount(Long id, Long numberOfReduction);

    Integer increaseWarehouseCount(Long id, Long numberOfIncrease);
}
