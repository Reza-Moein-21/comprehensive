package ir.comprehensive.component;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;

public interface SelectBoxEvent<T> {
    void onChange(String oldValue, String newValue, ObjectProperty<ObservableList<T>> suggestItems);
}
