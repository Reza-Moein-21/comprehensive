package ir.comprehensive.model.basemodel;

import javafx.beans.property.LongProperty;

@FunctionalInterface
public interface Editable {
    void edit(LongProperty id);
}
