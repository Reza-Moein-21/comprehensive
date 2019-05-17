package ir.comprehensive.model.basemodel;

import javafx.beans.property.ObjectProperty;

@FunctionalInterface
public interface Editable {
    void edit(ObjectProperty<Long> id);
}
