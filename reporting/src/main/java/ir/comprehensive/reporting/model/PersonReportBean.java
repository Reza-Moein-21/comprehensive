package ir.comprehensive.reporting.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonReportBean extends BaseReportBean {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String description;

}
