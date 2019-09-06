package ir.comprehensive.model;

import ir.comprehensive.model.basemodel.BaseModel;

public class MyNoteCategoryModel extends BaseModel {

    public MyNoteCategoryModel() {
    }

    public MyNoteCategoryModel(Long id) {
        setId(id);
    }

    public MyNoteCategoryModel(Long id, String title) {
        super(title);
        setId(id);
    }


}
