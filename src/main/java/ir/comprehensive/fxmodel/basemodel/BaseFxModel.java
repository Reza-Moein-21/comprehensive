package ir.comprehensive.fxmodel.basemodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

import java.util.function.Predicate;


public abstract class BaseFxModel implements Comparable<BaseFxModel>, Predicate<BaseFxModel> {
    protected ObjectProperty<Long> id = new SimpleObjectProperty<>();
    protected StringProperty title = new SimpleStringProperty();
    protected ObjectProperty<CheckBox> chb = new SimpleObjectProperty<>(new CheckBox());

    public BaseFxModel() {
    }

    public BaseFxModel(String title) {
        this.title.setValue(title);
    }

    public BaseFxModel(Long id, String title) {
        this.id.setValue(id);
        this.title.setValue(title);
    }

    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public ObjectProperty<Long> idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public CheckBox getChb() {
        return chb.get();
    }

    public ObjectProperty<CheckBox> chbProperty() {
        return chb;
    }

    public void setChb(CheckBox chb) {
        this.chb.set(chb);
    }

    @Override
    public int compareTo(BaseFxModel o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public boolean test(BaseFxModel baseModel) {
        return this.getId().equals(baseModel.getId());
    }
}
