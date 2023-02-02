package ir.comprehensive.jfxapp.component;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;

public interface SelectBoxEvent<T> {
    void onChange(String oldValue, String newValue, ObjectProperty<ObservableList<T>> suggestItems);
}
