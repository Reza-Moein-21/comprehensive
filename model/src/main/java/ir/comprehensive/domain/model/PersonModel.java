package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DescribableDomainModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonModel extends DescribableDomainModel<Long> {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    // TODO Consider to remove this and use title filed instead
    private String fullName;

    public String getFullName() {
        return this.getTitle();
    }

    private Set<CategoryModel> categories;

}
