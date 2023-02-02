package ir.comprehensive.service;

import ir.comprehensive.service.base.Statistical;
import ir.comprehensive.service.dto.WarehouseInfo;

public interface WarehouseService extends Statistical<WarehouseInfo> {
    Integer reduceWarehouseCount(Long id, Long numberOfReduction);

    Integer increaseWarehouseCount(Long id, Long numberOfIncrease);
}
