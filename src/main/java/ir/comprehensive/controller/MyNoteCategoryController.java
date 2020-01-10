package ir.comprehensive.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.CustomTableColumn;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.domain.MyNoteCategoryStatus;
import ir.comprehensive.mapper.MyNoteCategoryMapper;
import ir.comprehensive.model.MyNoteCategoryModel;
import ir.comprehensive.service.MyNoteCategoryService;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.FormValidationUtils;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.Notify;
import ir.comprehensive.utils.ScreenUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class MyNoteCategoryController implements Initializable {
    public static Long myNoteCategoryId;


    @Autowired
    StartController startController;
    @Autowired
    MyNoteCategoryService myNoteCategoryService;
    @Autowired
    MyNoteCategoryMapper mapper;

    @FXML
    MyNoteCategoryModel createModel;
    @FXML
    MyNoteCategoryModel searchModel;
    @FXML
    public JFXTextField txfTitleC;
    @FXML
    public JFXTextField txfTitleS;
    @FXML
    public JFXTextField txfDescriptionC;
    @FXML
    public JFXComboBox<MyNoteCategoryStatus> cmbStatusC;
    @FXML
    public JFXComboBox<MyNoteCategoryStatus> cmbStatusS;
    @FXML
    public JFXTextField txfDescriptionS;

    @FXML
    public JFXTextField txfTitleD;
    @FXML
    public JFXTextField txfStatusD;
    @FXML
    public JFXTextField txfCountOfActiveD;
    @FXML
    public JFXTextField txfCountOfInActiveD;
    @FXML
    public JFXTextField txfDescriptionD;

    @FXML
    public HBox hbxDisplayFooter;
    @FXML
    public GridPane grdDisplayMain;

    @FXML
    public HBox hbxDisplayHeader;
    @FXML
    public VBox vbxDisplayContent;
    @FXML
    public JFXDialog dlgCreate;
    @FXML
    public JFXDialog dlgDisplay;
    @FXML
    public VBox parent;
    @FXML
    public YesNoDialog dlgDelete;
    @FXML
    public VBox vbxCreateContent;
    @FXML
    public HBox hbxCreateHeader;
    @FXML
    public HBox hbxSearchFooter;
    @FXML
    public JFXButton btnCreate;
    @FXML
    public JFXButton btnSearch;
    @FXML
    public JFXButton btnShowAll;
    @FXML
    public GridPane grdSearch;
    @FXML
    public HBox hbxCreateFooter;
    @FXML
    public GridPane grdCreateCenter;
    @FXML
    public CustomTableColumn<MyNoteCategoryModel, Void> colGoToMyNote;
    @FXML
    public DataTable<MyNoteCategoryModel> tblMyNoteCategory;

    private void applyFontStyle(Pane rootNode) {
        for (Node n : rootNode.getChildren()) {
            n.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(32) + "px;-fx-font-family: 'shabnam';");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);
        dlgDelete.setDialogContainer(startController.mainStack);
        dlgDisplay.setDialogContainer(startController.mainStack);
        applyFontStyle(dlgCreate);
        applyFontStyle(dlgDelete);
        applyFontStyle(dlgDisplay);

        grdDisplayMain.setPadding(new Insets(ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20)));
        grdDisplayMain.setHgap(ScreenUtils.getActualSize(10));
        grdDisplayMain.setVgap(ScreenUtils.getActualSize(100));
        hbxDisplayFooter.setPadding(new Insets(ScreenUtils.getActualSize(20)));

        vbxDisplayContent.setSpacing(ScreenUtils.getActualSize(50));
        vbxDisplayContent.setPrefWidth(ScreenUtils.getActualSize(1900));
        hbxDisplayHeader.setPadding(new Insets(ScreenUtils.getActualSize(10)));
        parent.setPadding(new Insets(ScreenUtils.getActualSize(10), 0, ScreenUtils.getActualSize(10), 0));
        parent.setSpacing(ScreenUtils.getActualSize(10));


        tblMyNoteCategory.setOnEdit(selectedItem -> {
            MyNoteCategoryModel editModel = myNoteCategoryService.load(selectedItem.getId()).map(mapper::entityToModel).get();
            createModel.setId(editModel.getId());
            txfTitleC.setText(editModel.getTitle());
            txfDescriptionC.setText(editModel.getDescription());
            cmbStatusC.setValue(editModel.getStatus());
            dlgCreate.show();

        });

        tblMyNoteCategory.setOnDelete(selectedIds -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                try {
                    selectedIds.forEach(myNoteCategoryService::delete);
                    Notify.showSuccessMessage(MessageUtils.Message.MY_NOTE_CATEGORY + " " + MessageUtils.Message.SUCCESS_DELETE);
                    updateDataTable();
                } catch (GeneralException e) {
                    Notify.showErrorMessage(e.getMessage());
                }
                dlgDelete.close();
            });
        });
        tblMyNoteCategory.setOnVisit(selectedItem -> {
            txfTitleD.setText(selectedItem.getTitle());
            txfStatusD.setText(selectedItem.getStatus().getTitle());
            txfCountOfActiveD.setText(selectedItem.getCountOfActive().toString());
            txfCountOfInActiveD.setText(selectedItem.getCountOfInActive().toString());
            txfDescriptionD.setText(selectedItem.getDescription());
            dlgDisplay.show();
        });
        addButtonToTable();
        updateDataTable();

        txfTitleC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.TITLE));

        vbxCreateContent.setSpacing(ScreenUtils.getActualSize(70));
        vbxCreateContent.setPrefWidth(ScreenUtils.getActualSize(1300));
        vbxCreateContent.setPrefHeight(ScreenUtils.getActualSize(400));

        grdCreateCenter.setPadding(new Insets(ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(15), ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(15)));
        grdCreateCenter.setHgap(ScreenUtils.getActualSize(30));
        grdCreateCenter.setVgap(ScreenUtils.getActualSize(80));

        hbxCreateHeader.setPadding(new Insets(ScreenUtils.getActualSize(10)));

        hbxCreateFooter.setSpacing(ScreenUtils.getActualSize(20));
        hbxCreateFooter.setPadding(new Insets(ScreenUtils.getActualSize(10)));

        grdSearch.setPrefHeight(ScreenUtils.getActualSize(400));
        grdSearch.setMinHeight(ScreenUtils.getActualSize(400));
        grdSearch.setHgap(ScreenUtils.getActualSize(20));
        grdSearch.setVgap(ScreenUtils.getActualSize(10));
        grdSearch.setPadding(new Insets(ScreenUtils.getActualSize(42), ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(5), ScreenUtils.getActualSize(25)));

        hbxSearchFooter.setSpacing(ScreenUtils.getActualSize(20));

        btnCreate.setPrefWidth(ScreenUtils.getActualSize(400));
        btnCreate.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));


        btnSearch.setPrefWidth(ScreenUtils.getActualSize(400));
        btnSearch.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));

        btnShowAll.setPrefWidth(ScreenUtils.getActualSize(400));
        btnShowAll.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
    }


    private void updateDataTable() {
        tblMyNoteCategory.setItems(myNoteCategoryService.loadAll()
                .map(myNotes -> myNotes.stream().map(mapper::entityToModel).collect(Collectors.toList()))
                .map(FXCollections::observableArrayList).get());
    }

    @FXML
    public void closeCreateDialog(ActionEvent actionEvent) {
        dlgCreate.close();
    }

    @FXML
    public void showCreateDialog(ActionEvent actionEvent) {
        createModel.setId(null);
        txfTitleC.setText("");
        txfDescriptionC.setText("");
        cmbStatusC.setValue(MyNoteCategoryStatus.IN_PROGRESS);
        dlgCreate.show();
    }

    private boolean validateBeforeSave() {
        return txfTitleC.validate();
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        if (validateBeforeSave()) {
            try {
                myNoteCategoryService.saveOrUpdate(mapper.modelToEntity(createModel));
                dlgCreate.close();
                updateDataTable();

                Notify.showSuccessMessage(MessageUtils.Message.MY_NOTE_CATEGORY + " " + MessageUtils.Message.SUCCESS_SAVE);
            } catch (GeneralException e) {
                Notify.showErrorMessage(e.getMessage());
            }
        }
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        tblMyNoteCategory.setItems(myNoteCategoryService.search(searchModel).map(categories -> categories.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());

    }

    @FXML
    public void showAll(ActionEvent actionEvent) {
        txfTitleS.setText(null);
        txfDescriptionS.setText(null);
        cmbStatusS.setValue(null);
        updateDataTable();
    }

    private void addButtonToTable() {

        Callback<TableColumn<MyNoteCategoryModel, Void>, TableCell<MyNoteCategoryModel, Void>> cellFactory = new Callback<TableColumn<MyNoteCategoryModel, Void>, TableCell<MyNoteCategoryModel, Void>>() {
            @Override
            public TableCell<MyNoteCategoryModel, Void> call(final TableColumn<MyNoteCategoryModel, Void> param) {
                TableCell<MyNoteCategoryModel, Void> tableCell = new TableCell<MyNoteCategoryModel, Void>() {

                    private final Button btn = new Button(MessageUtils.Message.ENTER);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            MyNoteCategoryModel data = getTableView().getItems().get(getIndex());
                            MyNoteCategoryController.myNoteCategoryId = data.getId();
                            startController.navigateToView(ViewName.MY_NOTEBOOK, MessageUtils.Message.MY_NOTE + ": (" + data.getTitle() + ")");
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(btn);
                            hBox.setAlignment(Pos.BASELINE_CENTER);
                            setGraphic(hBox);
                        }
                    }
                };
                tableCell.setStyle("-fx-alignment: CENTER");
                return tableCell;
            }
        };

        colGoToMyNote.setCellFactory(cellFactory);
    }


    public void closeDisplayDialog(ActionEvent actionEvent) {
        dlgDisplay.close();
    }
}
