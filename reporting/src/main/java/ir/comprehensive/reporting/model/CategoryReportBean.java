package ir.comprehensive.reporting.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryReportBean extends BaseReportBean {
    private String title;
    private String phoneNumber;
    private String fax;
    private String email;
    private String address;
    private String description;

}
