package ir.comprehensive.jfxapp.component.arrowchoiceboxwidget;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;

public class ArrowChoiceBoxWidget<T> extends StackPane {

    private static <T> StringConverter<T> defaultStringConverter() {
        return new StringConverter<T>() {
            @Override
            public String toString(T t) {
                return t == null ? null : t.toString();
            }

            @Override
            public T fromString(String string) {
                return (T) string;
            }
        };
    }

    public ObjectProperty<StringConverter<T>> converterProperty() {
        return converter;
    }

    private ObjectProperty<StringConverter<T>> converter =
            new SimpleObjectProperty<StringConverter<T>>(this, "converter", ArrowChoiceBoxWidget.<T>defaultStringConverter());

    public final void setConverter(StringConverter<T> value) {
        converterProperty().set(value);
    }

    public final StringConverter<T> getConverter() {
        return converterProperty().get();
    }

    public ObjectProperty<T> valueProperty() {
        return value;
    }

    private ObjectProperty<T> value = new SimpleObjectProperty<T>(this, "value");

    public final void setValue(T value) {
        valueProperty().set(value);
    }

    public final T getValue() {
        return valueProperty().get();
    }


    private ObjectProperty<ObservableList<T>> items = new SimpleObjectProperty<ObservableList<T>>(this, "items");

    public final void setItems(ObservableList<T> value) {
        itemsProperty().set(value);
    }

    public final ObservableList<T> getItems() {
        return items.get();
    }

    public ObjectProperty<ObservableList<T>> itemsProperty() {
        return items;
    }

    public ArrowChoiceBoxWidget() {
        this.getChildren().setAll(render());
    }

    private Node render() {
        HBox base = new HBox();
        Button btnDecrease = new Button("<<");
        Button btnIncrease = new Button(">>");
        ComboBox<T> cmbMid = new ComboBox<>();
        cmbMid.setMaxWidth(Double.MAX_VALUE);
        cmbMid.getStylesheets().add(getClass().getResource("/css/arrowchoiceboxwidget/arrowChoiceBoxWidget.css").toExternalForm());
        HBox.setHgrow(cmbMid, Priority.ALWAYS);
        this.itemsProperty().bindBidirectional(cmbMid.itemsProperty());
        this.valueProperty().bindBidirectional(cmbMid.valueProperty());
        this.converterProperty().bindBidirectional(cmbMid.converterProperty());

        btnDecrease.setOnAction(event -> {
            int selectedIndex = cmbMid.getSelectionModel().getSelectedIndex();
            if (selectedIndex - 1 >= 0) {
                this.setValue(cmbMid.getItems().get(selectedIndex - 1));
            } else {
                this.setValue(cmbMid.getItems().get(cmbMid.getItems().size() - 1));
            }
        });

        btnIncrease.setOnAction(event -> {
            int selectedIndex = cmbMid.getSelectionModel().getSelectedIndex();
            if (selectedIndex + 1 < cmbMid.getItems().size()) {
                this.setValue(cmbMid.getItems().get(selectedIndex + 1));
            } else {
                this.setValue(cmbMid.getItems().get(0));
            }
        });

        base.getChildren().setAll(btnIncrease, cmbMid, btnDecrease);

        return base;
    }
}
