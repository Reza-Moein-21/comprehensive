package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DescribableDomainModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class WarehouseModel extends DescribableDomainModel<Long> {

    private String code;
    private String companyName;
    private String producerName;
    private Long count;
    private WarehouseCategoryModel category;
    private List<WarehouseTagModel> tagList = new ArrayList<>();


}
