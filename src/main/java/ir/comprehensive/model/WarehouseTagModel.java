package ir.comprehensive.model;

import ir.comprehensive.model.basemodel.BaseModel;

public class WarehouseTagModel extends BaseModel {
    public WarehouseTagModel() {
    }

    public WarehouseTagModel(String title) {
        super(title);
    }

    public WarehouseTagModel(Long id, String title) {
        super(id, title);
    }
}
