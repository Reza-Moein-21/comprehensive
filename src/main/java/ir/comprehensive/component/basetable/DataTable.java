package ir.comprehensive.component.basetable;

import com.jfoenix.controls.JFXButton;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class DataTable<T> extends TableView<T> {

    private Editable<T> onEdit;
    private Deletable<T> onDelete;
    private Visitable<T> onVisit;

    private BooleanProperty showEdit = new SimpleBooleanProperty(this, "showEdit", true);
    private BooleanProperty showDelete = new SimpleBooleanProperty(this, "showDelete", true);
    private BooleanProperty showVisit = new SimpleBooleanProperty(this, "showVisit", false);


    public DataTable() {
        TableColumn<T, String> rowNumberColumn = new TableColumn<>(MessageUtils.Message.NUMBER_SIGN);
        rowNumberColumn.setPrefWidth(ScreenUtils.getActualSize(120));
        rowNumberColumn.setResizable(false);
        rowNumberColumn.setSortable(false);
        rowNumberColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(this.getItems().indexOf(param.getValue()) + 1 + ""));

        TableColumn<T, T> editColumn = new TableColumn<>(MessageUtils.Message.EDIT);
        editColumn.setPrefWidth(ScreenUtils.getActualSize(120));
        editColumn.setResizable(false);
        editColumn.setSortable(false);
        editColumn.setCellFactory(param -> new EditableTableCell<>(getOnEdit()));
        editColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        editColumn.visibleProperty().bind(showEdit);

        TableColumn<T, T> deleteColumn = new TableColumn<>(MessageUtils.Message.DELETE);
        deleteColumn.setPrefWidth(ScreenUtils.getActualSize(120));
        deleteColumn.setResizable(false);
        deleteColumn.setSortable(false);
        deleteColumn.setCellFactory(param -> new DeletableTableCell<>(getOnDelete()));
        deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteColumn.visibleProperty().bind(showDelete);

        TableColumn<T, T> visitColumn = new TableColumn<>(MessageUtils.Message.VISIT);
        visitColumn.setPrefWidth(ScreenUtils.getActualSize(140));
        visitColumn.setResizable(false);
        visitColumn.setSortable(false);
        visitColumn.setCellFactory(param -> new VisitableTableCell<>(getOnVisit()));
        visitColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        visitColumn.visibleProperty().bind(showVisit);

        getColumns().addAll(rowNumberColumn, editColumn, deleteColumn, visitColumn);
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

    public Visitable<T> getOnVisit() {
        return onVisit;
    }

    public void setOnVisit(Visitable<T> onVisit) {
        this.onVisit = onVisit;
    }

    public final boolean isShowEdit() {
        return showEdit.get();
    }

    public final BooleanProperty showEditProperty() {
        return showEdit;
    }

    public final void setShowEdit(boolean showEdit) {
        this.showEdit.set(showEdit);
    }

    public final boolean isShowDelete() {
        return showDelete.get();
    }

    public final BooleanProperty showDeleteProperty() {
        return showDelete;
    }

    public final void setShowDelete(boolean showDelete) {
        this.showDelete.set(showDelete);
    }

    public final boolean isShowVisit() {
        return showVisit.get();
    }

    public final BooleanProperty showVisitProperty() {
        return showVisit;
    }

    public final void setShowVisit(boolean showVisit) {
        this.showVisit.set(showVisit);
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

class VisitableTableCell<T> extends TableCell<T, T> {
    private final JFXButton visitButton = new JFXButton();

    public VisitableTableCell(Visitable<T> visitable) {
        visitButton.setOnAction(event -> visitable.visit(getItem()));
        visitButton.setPrefWidth(ScreenUtils.getActualSize(52));
        visitButton.setPrefHeight(ScreenUtils.getActualSize(52));
        visitButton.getStyleClass().addAll("table-row-button", "visit-table-row-button");
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null) {
            setGraphic(null);
        } else {
            HBox hBox = new HBox(visitButton);
            hBox.setAlignment(Pos.CENTER);
            setGraphic(hBox);
        }
    }

}