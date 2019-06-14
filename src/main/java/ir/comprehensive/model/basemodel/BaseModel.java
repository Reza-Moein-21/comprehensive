package ir.comprehensive.model.basemodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.function.Predicate;


public abstract class BaseModel implements Comparable<BaseModel>, Predicate<BaseModel> {
    protected ObjectProperty<Long> id = new SimpleObjectProperty<>();
    protected StringProperty title = new SimpleStringProperty();

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

    @Override
    public int compareTo(BaseModel o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public boolean test(BaseModel baseModel) {
        return this.getId().equals(baseModel.getId());
    }
}
