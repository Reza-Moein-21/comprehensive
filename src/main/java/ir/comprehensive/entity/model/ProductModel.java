package ir.comprehensive.entity.model;

import ir.comprehensive.entity.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel extends BaseModel<Long> {

    private String title;

}

