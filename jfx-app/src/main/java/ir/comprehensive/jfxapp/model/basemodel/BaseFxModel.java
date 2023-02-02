package ir.comprehensive.jfxapp.model.basemodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

import java.io.Serializable;
import java.util.function.Predicate;


public abstract class BaseFxModel<I extends Serializable> implements Predicate<BaseFxModel<I>> {
    protected ObjectProperty<I> id = new SimpleObjectProperty<>();
    protected StringProperty title = new SimpleStringProperty();
    protected ObjectProperty<CheckBox> chb = new SimpleObjectProperty<>(new CheckBox());

    public BaseFxModel() {
    }

    public BaseFxModel(String title) {
        this.title.setValue(title);
    }

    public BaseFxModel(I id, String title) {
        this.id.setValue(id);
        this.title.setValue(title);
    }

    public final I getId() {
        return id.get();
    }

    public final void setId(I id) {
        this.id.set(id);
    }

    public final ObjectProperty<I> idProperty() {
        return id;
    }

    public final String getTitle() {
        return title.get();
    }

    public final void setTitle(String title) {
        this.title.set(title);
    }

    public final StringProperty titleProperty() {
        return title;
    }

    public final CheckBox getChb() {
        return chb.get();
    }

    public final ObjectProperty<CheckBox> chbProperty() {
        return chb;
    }

    public final void setChb(CheckBox chb) {
        this.chb.set(chb);
    }

    @Override
    public boolean test(BaseFxModel baseModel) {
        return this.getId().equals(baseModel.getId());
    }
}
