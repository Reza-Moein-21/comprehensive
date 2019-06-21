package ir.comprehensive.model;

import ir.comprehensive.model.basemodel.BaseModel;

public class ProductModel extends BaseModel {

    public ProductModel() {
    }

    public ProductModel(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
