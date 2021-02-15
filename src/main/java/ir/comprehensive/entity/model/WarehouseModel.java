package ir.comprehensive.entity.model;

import ir.comprehensive.entity.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseModel extends BaseModel<Long> {

    private String title;
    private String code;
    private String companyName;
    private String producerName;
    private Long count;
    private String description;
    private WarehouseCategoryModel category;
    private List<WarehouseTagModel> tagList = new ArrayList<>();


}
