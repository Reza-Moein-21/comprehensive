package ir.comprehensive.jfxapp.model;

import ir.comprehensive.jfxapp.model.basemodel.BaseFxModel;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class WarehouseFxModel extends BaseFxModel {

    private StringProperty code = new SimpleStringProperty();
    private StringProperty companyName = new SimpleStringProperty();
    private StringProperty producerName = new SimpleStringProperty();
    private LongProperty count = new SimpleLongProperty();
    private StringProperty description = new SimpleStringProperty();
    private ObjectProperty<WarehouseCategoryFxModel> category = new SimpleObjectProperty<>();
    private ObjectProperty<List<WarehouseTagFxModel>> tagList = new SimpleObjectProperty<>(new ArrayList<>());


    public final String getCode() {
        return code.get();
    }

    public final StringProperty codeProperty() {
        return code;
    }

    public final void setCode(String code) {
        this.code.set(code);
    }

    public final String getCompanyName() {
        return companyName.get();
    }

    public final StringProperty companyNameProperty() {
        return companyName;
    }

    public final void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public final String getProducerName() {
        return producerName.get();
    }

    public final StringProperty producerNameProperty() {
        return producerName;
    }

    public final void setProducerName(String producerName) {
        this.producerName.set(producerName);
    }

    public final long getCount() {
        return count.get();
    }

    public final LongProperty countProperty() {
        return count;
    }

    public final void setCount(long count) {
        this.count.set(count);
    }

    public final String getDescription() {
        return description.get();
    }

    public final StringProperty descriptionProperty() {
        return description;
    }

    public final void setDescription(String description) {
        this.description.set(description);
    }

    public final WarehouseCategoryFxModel getCategory() {
        return category.get();
    }

    public final void setCategory(WarehouseCategoryFxModel category) {
        this.category.set(category);
    }

    public final ObjectProperty<WarehouseCategoryFxModel> categoryProperty() {
        return category;
    }

    public final List<WarehouseTagFxModel> getTagList() {
        return tagList.get();
    }

    public final void setTagList(List<WarehouseTagFxModel> tagList) {
        this.tagList.set(tagList);
    }

    public final ObjectProperty<List<WarehouseTagFxModel>> tagListProperty() {
        return tagList;
    }
}
