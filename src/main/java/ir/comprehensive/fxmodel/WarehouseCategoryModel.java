package ir.comprehensive.fxmodel;

import ir.comprehensive.fxmodel.basemodel.BaseModel;

public class WarehouseCategoryModel extends BaseModel {

    public WarehouseCategoryModel() {
    }

    public WarehouseCategoryModel(String title) {
        super(title);
    }

    public WarehouseCategoryModel(Long id, String title) {
        super(id, title);
    }

    @Override
    public String toString() {
        return super.getTitle();
    }
}
