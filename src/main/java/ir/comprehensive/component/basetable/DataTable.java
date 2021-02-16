package ir.comprehensive.component.basetable;

import com.jfoenix.controls.JFXButton;
import ir.comprehensive.component.basetable.pagination.PaginationModelAdapter;
import ir.comprehensive.component.basetable.pagination.TablePagination;
import ir.comprehensive.fxmodel.basemodel.BaseFxModel;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ir.comprehensive.component.basetable.pagination.TablePagination.DEFAULT_PAGE_SIZE;
import static ir.comprehensive.component.basetable.pagination.TablePagination.DEFAULT_START_PAGE;

public class DataTable<T extends BaseFxModel> extends VBox {

    private final ObservableList<TableColumn<T, ?>> columns = FXCollections.observableArrayList();

    private final TableView<T> tableView;
    private final TablePagination tablePagination;

    HBox hbxHeader = new HBox();
    Label lblNumberOfSelected = new Label();
    JFXButton btnDelete = new JFXButton();
    JFXButton btnExtra = new JFXButton();
    JFXButton btnPrint = new JFXButton();

    private Editable<T> onEdit;
    private Deletable onDelete;
    private Function<PageRequest, Page<T>> itemPage;
    private Extra onExtra;
    private Visitable<T> onVisit;
    private Printable onPrint;

    private BooleanProperty showSelect = new SimpleBooleanProperty(this, "showSelect", true);
    private BooleanProperty showEdit = new SimpleBooleanProperty(this, "showEdit", true);
    private BooleanProperty showDelete = new SimpleBooleanProperty(this, "showDelete", true);
    private BooleanProperty showExtraButton = new SimpleBooleanProperty(this, "showExtraButton", false);
    private BooleanProperty showVisit = new SimpleBooleanProperty(this, "showVisit", false);
    private BooleanProperty showPrintButton = new SimpleBooleanProperty(this, "showPrintButton", false);


    public DataTable() {
        tableView = new TableView<>();
        tablePagination = new TablePagination();
        tablePagination.setOnPaginationChange(pageRequest -> {
            Page<T> page = itemPage.apply(pageRequest);
            itemsProperty().set(FXCollections.observableArrayList(page.getContent()));
            return page;
        });

        VBox.setVgrow(tableView, Priority.ALWAYS);
        hbxHeader.setSpacing(ScreenUtils.getActualSize(50));
        hbxHeader.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(20)));
        hbxHeader.setAlignment(Pos.CENTER_LEFT);

        btnDelete.getStyleClass().addAll("table-row-button", "delete-table-row-button");
        btnDelete.setPrefWidth(ScreenUtils.getActualSize(62));
        btnDelete.setPrefHeight(ScreenUtils.getActualSize(62));
        btnDelete.setDisable(true);
        btnDelete.visibleProperty().bind(showDelete);
        btnDelete.setOnAction(event -> {
            this.onDelete.delete(this.getItems().stream().filter(t -> t.getChb().isSelected()).map(BaseFxModel::getId).collect(Collectors.toSet()));
        });


        btnPrint.getStyleClass().addAll("table-row-button", "print-table-row-button");
        btnPrint.setPrefWidth(ScreenUtils.getActualSize(62));
        btnPrint.setPrefHeight(ScreenUtils.getActualSize(62));
        btnPrint.visibleProperty().bind(showPrintButton);
        btnPrint.setOnAction(event -> {
            this.onPrint.print(this.getItems().stream().filter(t -> t.getChb().isSelected()).map(BaseFxModel::getId).collect(Collectors.toSet()));
        });

        btnExtra.getStyleClass().addAll("table-row-button", "send-table-row-button");
        btnExtra.setPrefWidth(ScreenUtils.getActualSize(62));
        btnExtra.setPrefHeight(ScreenUtils.getActualSize(62));
        btnExtra.disableProperty().bind(btnDelete.disableProperty());
        btnExtra.visibleProperty().bind(showExtraButton);
        btnExtra.setOnAction(event -> {
            this.onExtra.acceptExtra(this.getItems().stream().filter(t -> t.getChb().isSelected()).map(BaseFxModel::getId).collect(Collectors.toSet()));
        });


        hbxHeader.getChildren().addAll(wrapToCircle(lblNumberOfSelected), btnDelete, btnPrint, btnExtra);


        CustomTableColumn<T, String> rowNumberColumn = new CustomTableColumn<>(MessageUtils.Message.NUMBER_SIGN);
        rowNumberColumn.setPercentageWidth(4);
        rowNumberColumn.setResizable(false);
        rowNumberColumn.setSortable(false);
        rowNumberColumn.setStyle("-fx-alignment: CENTER");
        rowNumberColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(this.getItems().indexOf(param.getValue()) + 1 + ""));

        // all checkbox
        CustomTableColumn<T, CheckBox> selectColumn = new CustomTableColumn<>("", getAllCheckBox());
        selectColumn.setPercentageWidth(4);
        selectColumn.setResizable(false);
        selectColumn.setSortable(false);
        selectColumn.setCellValueFactory(param -> {
            this.updateDeleteInfo(lblNumberOfSelected, btnDelete);
            CheckBox chb;
            chb = param.getValue().getChb();
            chb.selectedProperty().addListener((observable, oldValue, newValue) -> this.updateDeleteInfo(lblNumberOfSelected, btnDelete));
            return new ReadOnlyObjectWrapper<>(chb);

        });
        selectColumn.visibleProperty().bind(showSelect);
        selectColumn.setStyle("-fx-alignment: CENTER");


        CustomTableColumn<T, T> editColumn = new CustomTableColumn<>(MessageUtils.Message.EDIT);
        editColumn.setPercentageWidth(4);
        editColumn.setResizable(false);
        editColumn.setSortable(false);
        editColumn.setCellFactory(param -> new EditableTableCell<>(getOnEdit()));
        editColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        editColumn.visibleProperty().bind(showEdit);

        CustomTableColumn<T, T> visitColumn = new CustomTableColumn<>(MessageUtils.Message.VISIT);
        visitColumn.setPercentageWidth(4);
        visitColumn.setResizable(false);
        visitColumn.setSortable(false);
        visitColumn.setCellFactory(param -> new VisitableTableCell<>(getOnVisit()));
        visitColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        visitColumn.visibleProperty().bind(showVisit);
        getColumns().addAll(rowNumberColumn, selectColumn, editColumn, visitColumn);
        tableView.getColumns().addAll(this.getColumns());
        columns.addListener((InvalidationListener) observable -> tableView.getColumns().setAll(columns));
        tableView.itemsProperty().bindBidirectional(this.items);
        tableView.itemsProperty().addListener((observable, oldValue, newValue) -> this.updateDeleteInfo(lblNumberOfSelected, btnDelete));
        getChildren().addAll(new BorderPane(tablePagination, null, null, null, hbxHeader), tableView);

        StringJoiner style = new StringJoiner(";");
        style.add("-fx-background-color: #ffff")
                .add("-fx-border-width:" + ScreenUtils.getActualSize(3))
                .add("-fx-border-radius:" + ScreenUtils.getActualSize(3))
                .add("-fx-border-color: #cdcdcd")
                .add("-fx-spacing:" + ScreenUtils.getActualSize(2));
        this.setStyle(style.toString());
    }

    private StackPane wrapToCircle(Label lblNumberOfSelected) {
        StackPane stackPane = new StackPane();
        stackPane.disableProperty().bindBidirectional(lblNumberOfSelected.disableProperty());
        Circle circle = new Circle();
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setRadius(ScreenUtils.getActualSize(32));
        stackPane.getChildren().addAll(circle, lblNumberOfSelected);
        return stackPane;
    }

    private void updateDeleteInfo(Label lblNumberOfSelected, JFXButton btnDelete) {
        long count = this.getItems().stream().filter(t -> t.getChb().isSelected()).count();

        if (count > 0) {
            lblNumberOfSelected.setDisable(false);

            lblNumberOfSelected.setText(String.valueOf(count));
            btnDelete.setDisable(false);

            if (count == this.getItems().size()) {
                checkBox.setSelected(true);
                checkBox.setIndeterminate(false);
            } else {
                checkBox.setIndeterminate(true);
            }
        } else {
            checkBox.setSelected(false);
            checkBox.setIndeterminate(false);
            lblNumberOfSelected.setDisable(true);
            lblNumberOfSelected.setText("0");
            btnDelete.setDisable(true);
        }
    }

    CheckBox checkBox = new CheckBox();

    private HBox getAllCheckBox() {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> this.selectAll(newValue));
        HBox hBox = new HBox(checkBox);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    public final ObjectProperty<ObservableList<T>> itemsProperty() {
        return items;
    }

    private ObjectProperty<ObservableList<T>> items =
            new SimpleObjectProperty<ObservableList<T>>(this, "items");

//    public final void setItems(ObservableList<T> value) {
//        itemsProperty().set(value);
//    }

    public final void refresh() {
        Page<T> page = getItemPage().apply(PageRequest.of(DEFAULT_START_PAGE, DEFAULT_PAGE_SIZE));
        tablePagination.update(new PaginationModelAdapter(page));
        itemsProperty().set(FXCollections.observableArrayList(page.getContent()));
    }

    public final ObservableList<T> getItems() {
        return items.get();
    }

    public ObservableList<TableColumn<T, ?>> getColumns() {
        return columns;
    }

    private void selectAll(boolean checked) {
        this.getItems().forEach(t -> t.getChb().setSelected(checked));
    }

    public Editable<T> getOnEdit() {
        return onEdit;
    }

    public void setOnEdit(Editable<T> onEdit) {
        this.onEdit = onEdit;
    }

    public Function<PageRequest, Page<T>> getItemPage() {
        return itemPage;
    }

    public void setItemPage(Function<PageRequest, Page<T>> itemPage) {
        this.itemPage = itemPage;
    }

    public Deletable getOnDelete() {
        return onDelete;
    }

    public void setOnDelete(Deletable onDelete) {
        this.onDelete = onDelete;
    }

    public Extra getOnExtra() {
        return onExtra;
    }

    public void setOnExtra(Extra onExtra) {
        this.onExtra = onExtra;
    }

    public Visitable<T> getOnVisit() {
        return onVisit;
    }

    public void setOnVisit(Visitable<T> onVisit) {
        this.onVisit = onVisit;
    }

    public Printable getOnPrint() {
        return onPrint;
    }

    public void setOnPrint(Printable onPrint) {
        this.onPrint = onPrint;
    }

    public boolean isShowSelect() {
        return showSelect.get();
    }

    public BooleanProperty showSelectProperty() {
        return showSelect;
    }

    public void setShowSelect(boolean showSelect) {
        this.showSelect.set(showSelect);
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

    public final boolean isShowExtraButton() {
        return showExtraButton.get();
    }

    public final BooleanProperty showExtraButtonProperty() {
        return showExtraButton;
    }

    public final void setShowExtraButton(boolean showExtraButton) {
        this.showExtraButton.set(showExtraButton);
    }

    public final boolean getShowPrintButton() {
        return showPrintButton.get();
    }

    public final BooleanProperty showPrintButtonProperty() {
        return showPrintButton;
    }

    public final void setShowPrintButton(boolean showPrintButton) {
        this.showPrintButton.set(showPrintButton);
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