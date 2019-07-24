package ir.comprehensive.component;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.model.basemodel.BaseModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Callback;

public class MultiSelectBox<T extends BaseModel> extends GridPane {
    private StringProperty promptText = new SimpleStringProperty();
    private SelectBoxEvent<T> onChange;

    private ObjectProperty<ObservableList<T>> suggestItems = new SimpleObjectProperty<>();
    private ObjectProperty<ObservableList<T>> selectedItems = new SimpleObjectProperty<>();
    private ObjectProperty<Callback<ListView<T>, ListCell<T>>> cellFactory = new SimpleObjectProperty<>();

    public MultiSelectBox() {
        setSelectedItems(FXCollections.observableArrayList());
        suggestItems.setValue(FXCollections.observableArrayList());

        renderView();
    }


    private void renderView() {
        addTextField();

        addColumnConstraints();

        addRowConstraints();

        addListView();
    }


    public void clean() {
        this.getSelectedItems().clear();
        this.suggestItems.getValue().clear();
    }

    private void addListView() {
        JFXListView<T> lstSuggestList = new JFXListView<>();
        JFXListView<T> lstSelectedList = new JFXListView<>();


        lstSuggestList.itemsProperty().bind(suggestItems);
        lstSuggestList.cellFactoryProperty().bind(cellFactoryProperty());
        lstSuggestList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                T selectedItem = lstSuggestList.getSelectionModel().getSelectedItem();
                if (selectedItem != null && !selectedItemsProperty().getValue().stream().anyMatch(selectedItem)) {

                    lstSelectedList.getItems().add(selectedItem);
                }
            }
        });
        lstSelectedList.itemsProperty().bind(selectedItemsProperty());
        lstSelectedList.cellFactoryProperty().bind(cellFactoryProperty());
        lstSelectedList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                T selectedItem = lstSelectedList.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    lstSelectedList.getItems().remove(selectedItem);
                }
            }
        });

        add(lstSuggestList, 0, 1);
        add(lstSelectedList, 1, 1);

    }

    private void addTextField() {
        JFXTextField txfSearch = new JFXTextField();

        txfSearch.setLabelFloat(true);
        txfSearch.promptTextProperty().bind(promptTextProperty());

        txfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (getOnChange() != null) {
                getOnChange().onChange(oldValue, newValue, suggestItems);
            }
        });
        txfSearch.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER) && !suggestItems.getValue().isEmpty()) {
                T item = suggestItems.getValue().get(0);

                if (!selectedItemsProperty().getValue().stream().anyMatch(item)) {
                    selectedItemsProperty().getValue().add(item);
                }


            }
        });

        add(txfSearch, 0, 0, 2, 1);
    }

    private RowConstraints getRowConstraints(double percentHeight) {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(percentHeight);
        return rowConstraints;
    }

    private void addRowConstraints() {
        getRowConstraints().addAll(getRowConstraints(20), getRowConstraints(80));
    }

    private ColumnConstraints getColumnConstraints(double percentWidth) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(percentWidth);
        return columnConstraints;
    }

    private void addColumnConstraints() {
        getColumnConstraints().addAll(getColumnConstraints(50), getColumnConstraints(50));
    }

    public String getPromptText() {
        return promptText.get();
    }

    public void setPromptText(String promptText) {
        this.promptText.set(promptText);
    }

    public StringProperty promptTextProperty() {
        return promptText;
    }

    public SelectBoxEvent<T> getOnChange() {
        return onChange;
    }

    public void setOnChange(SelectBoxEvent<T> onChange) {
        this.onChange = onChange;
    }

    public ObservableList getSelectedItems() {
        return selectedItems.get();
    }

    public void setSelectedItems(ObservableList selectedItems) {
        this.selectedItems.set(selectedItems);
    }

    public ObjectProperty<ObservableList<T>> selectedItemsProperty() {
        return selectedItems;
    }

    public Callback<ListView<T>, ListCell<T>> getCellFactory() {
        return cellFactory.get();
    }

    public void setCellFactory(Callback<ListView<T>, ListCell<T>> cellFactory) {
        this.cellFactory.set(cellFactory);
    }

    public ObjectProperty<Callback<ListView<T>, ListCell<T>>> cellFactoryProperty() {
        return cellFactory;
    }
}
