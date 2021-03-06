package ir.comprehensive.database.model;

import ir.comprehensive.database.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel extends BaseModel<Long> {

    private String title;

    private String phoneNumber;

    private String fax;

    private String email;

    private String address;

    private String description;

}
