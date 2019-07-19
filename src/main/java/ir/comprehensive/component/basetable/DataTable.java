package ir.comprehensive.component.basetable;

import com.jfoenix.controls.JFXButton;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class DataTable<T> extends TableView<T> {

    private Editable<T> onEdit;
    private Deletable<T> onDelete;


    public DataTable() {

        TableColumn<T, T> editColumn = new TableColumn<>(MessageUtils.Message.EDIT);
        editColumn.setPrefWidth(120);
        editColumn.setResizable(false);
        editColumn.setCellFactory(param -> new EditableTableCell<>(getOnEdit()));
        editColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        TableColumn<T, T> deleteColumn = new TableColumn<>(MessageUtils.Message.DELETE);
        deleteColumn.setPrefWidth(120);
        deleteColumn.setResizable(false);
        deleteColumn.setCellFactory(param -> new DeletableTableCell<>(getOnDelete()));
        deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        getColumns().addAll(editColumn, deleteColumn);
    }

    public Editable<T> getOnEdit() {
        return onEdit;
    }

    public void setOnEdit(Editable<T> onEdit) {
        this.onEdit = onEdit;
    }

    public Deletable<T> getOnDelete() {
        return onDelete;
    }

    public void setOnDelete(Deletable<T> onDelete) {
        this.onDelete = onDelete;
    }
}

class EditableTableCell<T> extends TableCell<T, T> {
    private final JFXButton editButton = new JFXButton();

    public EditableTableCell(Editable<T> editable) {
        editButton.setOnAction(event -> editable.edit(getItem()));
        editButton.setPrefWidth(ScreenUtils.getActualSize(52));
        editButton.setPrefHeight(ScreenUtils.getActualSize(52));
        editButton.getStyleClass().addAll("table-row-button", "edit-table-row-button");
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null) {
            setGraphic(null);
        } else {
            HBox hBox = new HBox(editButton);
            hBox.setAlignment(Pos.CENTER);
            setGraphic(hBox);
        }
    }

}

class DeletableTableCell<T> extends TableCell<T, T> {
    private final JFXButton deleteButton = new JFXButton();

    public DeletableTableCell(Deletable<T> deletable) {
        deleteButton.setOnAction(event -> deletable.delete(getItem()));
        deleteButton.setPrefWidth(ScreenUtils.getActualSize(52));
        deleteButton.setPrefHeight(ScreenUtils.getActualSize(52));
        deleteButton.getStyleClass().addAll("table-row-button", "delete-table-row-button");
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null) {
            setGraphic(null);
        } else {
            HBox hBox = new HBox(deleteButton);
            hBox.setAlignment(Pos.CENTER);
            setGraphic(hBox);
        }
    }

}
