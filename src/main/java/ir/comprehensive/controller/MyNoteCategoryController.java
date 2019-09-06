package ir.comprehensive.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.DataTable;
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
    public JFXTextField txfDescriptionS;

    @FXML
    public JFXDialog dlgCreate;
    @FXML
    public VBox parent;
    @FXML
    public YesNoDialog dlgDelete;
    @FXML
    public VBox vbxCreateContent;
    @FXML
    public HBox hbxCreateHeader;
    @FXML
    public GridPane grdSearchFooter;
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
    public VBox vbxCreateCenter;
    @FXML
    public TableColumn<MyNoteCategoryModel, String> colTitle;
    @FXML
    public TableColumn<MyNoteCategoryModel, Void> colGoToMyNote;
    @FXML
    public TableColumn<MyNoteCategoryModel, String> colDescription;
    @FXML
    public TableColumn<MyNoteCategoryModel, Long> colCountOfActive;
    @FXML
    public TableColumn<MyNoteCategoryModel, Long> colCountOfInActive;
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
        applyFontStyle(dlgCreate);
        applyFontStyle(dlgDelete);


        parent.setPadding(new Insets(ScreenUtils.getActualSize(10), 0, ScreenUtils.getActualSize(10), 0));
        parent.setSpacing(ScreenUtils.getActualSize(10));


        tblMyNoteCategory.setOnEdit(selectedItem -> {
            MyNoteCategoryModel editModel = myNoteCategoryService.load(selectedItem.getId()).map(mapper::entityToModel).get();
            createModel.setId(editModel.getId());
            txfTitleC.setText(editModel.getTitle());
            dlgCreate.show();

        });

        tblMyNoteCategory.setOnDelete(selectedItem -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                try {
                    myNoteCategoryService.delete(selectedItem.getId());
                    Notify.showSuccessMessage(MessageUtils.Message.MY_NOTE_CATEGORY + " " + MessageUtils.Message.SUCCESS_DELETE);
                    updateDataTable();
                } catch (GeneralException e) {
                    Notify.showErrorMessage(e.getMessage());
                }
                dlgDelete.close();
            });
        });
        addButtonToTable();
        updateDataTable();

        colTitle.setMinWidth(ScreenUtils.getActualSize(650));
        colTitle.setPrefWidth(ScreenUtils.getActualSize(700));
        colTitle.setSortable(false);

        colDescription.setMinWidth(ScreenUtils.getActualSize(950));
        colDescription.setPrefWidth(ScreenUtils.getActualSize(2300));

        colCountOfActive.setMinWidth(ScreenUtils.getActualSize(200));
        colCountOfActive.setPrefWidth(ScreenUtils.getActualSize(200));
        colCountOfActive.setResizable(false);

        colCountOfInActive.setMinWidth(ScreenUtils.getActualSize(200));
        colCountOfInActive.setPrefWidth(ScreenUtils.getActualSize(200));
        colCountOfInActive.setResizable(false);

        colGoToMyNote.setMinWidth(ScreenUtils.getActualSize(140));
        colGoToMyNote.setPrefWidth(ScreenUtils.getActualSize(140));
        colGoToMyNote.setSortable(false);
        colGoToMyNote.setResizable(false);

        txfTitleC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.TITLE));

        vbxCreateContent.setSpacing(ScreenUtils.getActualSize(50));
        vbxCreateContent.setPrefWidth(ScreenUtils.getActualSize(800));
        vbxCreateContent.setPrefHeight(ScreenUtils.getActualSize(400));

        vbxCreateCenter.setPadding(new Insets(ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(15), ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(15)));
        vbxCreateCenter.setSpacing(ScreenUtils.getActualSize(50));

        hbxCreateHeader.setPadding(new Insets(ScreenUtils.getActualSize(10)));

        hbxCreateFooter.setSpacing(ScreenUtils.getActualSize(20));
        hbxCreateFooter.setPadding(new Insets(ScreenUtils.getActualSize(10)));

        grdSearch.setPrefHeight(ScreenUtils.getActualSize(400));
        grdSearch.setMinHeight(ScreenUtils.getActualSize(400));
        grdSearch.setHgap(ScreenUtils.getActualSize(20));
        grdSearch.setVgap(ScreenUtils.getActualSize(10));
        grdSearch.setPadding(new Insets(ScreenUtils.getActualSize(42), ScreenUtils.getActualSize(25), ScreenUtils.getActualSize(5), ScreenUtils.getActualSize(25)));

        grdSearchFooter.setHgap(ScreenUtils.getActualSize(10));

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
        updateDataTable();
    }

    private void addButtonToTable() {

        Callback<TableColumn<MyNoteCategoryModel, Void>, TableCell<MyNoteCategoryModel, Void>> cellFactory = new Callback<TableColumn<MyNoteCategoryModel, Void>, TableCell<MyNoteCategoryModel, Void>>() {
            @Override
            public TableCell<MyNoteCategoryModel, Void> call(final TableColumn<MyNoteCategoryModel, Void> param) {
                return new TableCell<MyNoteCategoryModel, Void>() {

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
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        colGoToMyNote.setCellFactory(cellFactory);
    }


}
