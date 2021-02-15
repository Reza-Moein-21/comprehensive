package ir.comprehensive.entity.model;

import ir.comprehensive.entity.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonModel extends BaseModel<Long> {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String description;

    private String fullName = this.firstName + " " + this.lastName;

    private Set<CategoryModel> categories;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
