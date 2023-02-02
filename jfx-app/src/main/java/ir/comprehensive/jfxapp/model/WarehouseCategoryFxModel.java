package ir.comprehensive.jfxapp.model;

import ir.comprehensive.jfxapp.model.basemodel.BaseFxModel;

public class WarehouseCategoryFxModel extends BaseFxModel {

    public WarehouseCategoryFxModel() {
    }

    public WarehouseCategoryFxModel(String title) {
        super(title);
    }

    public WarehouseCategoryFxModel(Long id, String title) {
        super(id, title);
    }

    @Override
    public String toString() {
        return super.getTitle();
    }
}
