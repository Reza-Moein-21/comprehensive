package ir.comprehensive.jfxapp.model;

import ir.comprehensive.jfxapp.model.basemodel.BaseFxModel;
import lombok.ToString;

@ToString
public class ProductFxModel extends BaseFxModel {

    public ProductFxModel() {
    }

    public ProductFxModel(String title) {
        super(title);
    }

}
