package ir.comprehensive.model;

import ir.comprehensive.model.basemodel.BaseReportBean;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseReportBean extends BaseReportBean {
    private String title;
    private String code;
    private String companyName;
    private String producerName;
    private String count;
    private String description;
    private String category;
    private String tagList;
}
