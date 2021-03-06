package ir.comprehensive.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.fxmapper.HadisFxMapper;
import ir.comprehensive.fxmodel.HadisFxModel;
import ir.comprehensive.service.HadisFxService;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.FormValidationUtils;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.Notify;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class HadisController implements Initializable {

    @FXML
    public JFXDialog dlgCreate;
    @FXML
    public JFXDialog dlgDisplay;
    @FXML
    public YesNoDialog dlgDelete;

    @FXML
    public DataTable<HadisFxModel> tblHadis;
    @FXML
    public HadisFxModel createModel;
    @FXML
    public HadisFxModel searchModel;

    @FXML
    public JFXTextField txfTitleS;
    @FXML
    public JFXTextField txfDescriptionS;

    @FXML
    public JFXTextField txfTitleC;
    @FXML
    public JFXTextField txfDescriptionC;
    @FXML
    public VBox vbxHRCategory;
    @FXML
    public GridPane grdCreateContent;
    @FXML
    public HBox hbxCreateFooter;
    @FXML
    public GridPane grdSearchFooter;
    @FXML
    public GridPane grdSearchContent;
    @FXML
    public JFXButton btnCreate;
    @FXML
    public JFXButton btnSearch;
    @FXML
    public JFXButton btnShowAll;
    @FXML
    public VBox vbxCreateContent;
    @FXML
    public HBox hbxCreateHeader;
    @FXML
    public JFXTextField txfTitleD;
    @FXML
    public JFXTextField txfDescriptionD;
    @FXML
    public GridPane grdDisplayMain;
    @FXML
    public VBox vbxDisplayContent;
    @FXML
    public HBox hbxDisplayFooter;
    @FXML
    public HBox hbxDisplayHeader;


    @Autowired
    private StartController startController;
    @Autowired
    private HadisFxService hadisService;
    @Autowired
    private HadisFxMapper mapper;

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

        hbxCreateHeader.setPadding(new Insets(ScreenUtils.getActualSize(10)));
        hbxDisplayFooter.setPadding(new Insets(ScreenUtils.getActualSize(20)));
        hbxDisplayHeader.setPadding(new Insets(ScreenUtils.getActualSize(10)));
        vbxCreateContent.setSpacing(ScreenUtils.getActualSize(10));
        vbxCreateContent.setPrefWidth(ScreenUtils.getActualSize(1100));

        vbxHRCategory.setSpacing(ScreenUtils.getActualSize(10));
        vbxHRCategory.setPadding(new Insets(ScreenUtils.getActualSize(10), 0, ScreenUtils.getActualSize(10), 0));

        btnSearch.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
        btnShowAll.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));

        btnCreate.setPrefWidth(ScreenUtils.getActualSize(400));
        btnCreate.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));

        vbxDisplayContent.setSpacing(ScreenUtils.getActualSize(10));
        vbxDisplayContent.setPrefWidth(ScreenUtils.getActualSize(1100));

        grdCreateContent.setPadding(new Insets(ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20)));
        grdCreateContent.setHgap(ScreenUtils.getActualSize(10));
        grdCreateContent.setVgap(ScreenUtils.getActualSize(100));

        grdDisplayMain.setPadding(new Insets(ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20)));
        grdDisplayMain.setHgap(ScreenUtils.getActualSize(10));
        grdDisplayMain.setVgap(ScreenUtils.getActualSize(100));

        grdSearchFooter.setHgap(ScreenUtils.getActualSize(10));
        hbxCreateFooter.setSpacing(ScreenUtils.getActualSize(20));
        hbxCreateFooter.setPadding(new Insets(ScreenUtils.getActualSize(15)));
        grdSearchContent.setPrefHeight(ScreenUtils.getActualSize(400));
        grdSearchContent.setVgap(ScreenUtils.getActualSize(50));
        grdSearchContent.setHgap(ScreenUtils.getActualSize(10));
        //        dlgLayout
        tblHadis.setItemPage(pageRequest -> hadisService.loadItem(searchModel,pageRequest));
        tblHadis.refresh();
        tblHadis.setOnEdit(selectedItem -> {
            createModel.setId(selectedItem.getId());
            txfTitleC.setText(selectedItem.getTitle());
            txfDescriptionC.setText(selectedItem.getDescription());

            dlgCreate.show();

        });

        tblHadis.setOnDelete(selectedIds -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                try {
                    selectedIds.forEach(hadisService::delete);
                    Notify.showSuccessMessage(MessageUtils.Message.CATEGORY + " " + MessageUtils.Message.SUCCESS_DELETE);
                    tblHadis.refresh();
                } catch (GeneralException e) {
                    Notify.showErrorMessage(e.getMessage());
                }
                dlgDelete.close();
            });
        });

        tblHadis.setOnVisit(selectedItem -> {
            txfTitleD.setText(selectedItem.getTitle());
            txfDescriptionD.setText(selectedItem.getDescription());

            dlgDisplay.show();
        });

        txfTitleC.focusedProperty().addListener(getChangeListener(txfTitleC));

        txfTitleC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.TITLE));
    }

    private ChangeListener<? super Boolean> getChangeListener(JFXTextField textField) {
        return (observable, oldValue, newValue) -> {
            if (!newValue) {
                textField.validate();
            }
        };
    }

    @FXML
    public void showAll(ActionEvent actionEvent) {
        txfTitleS.setText(null);
        txfDescriptionS.setText(null);
        tblHadis.refresh();
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        tblHadis.refresh();
    }

    @FXML
    public void openCreateDialog() {
        createModel.setId(null);
        txfTitleC.setText(null);
        txfDescriptionC.setText(null);
        dlgCreate.show();
    }

    @FXML
    public void closeCreateDialog() {
        dlgCreate.close();
    }

    private boolean validateBeforeSave() {
        boolean titleValidate = txfTitleC.validate();
        return titleValidate;
    }

    public void save() {
        if (validateBeforeSave()) {
            try {
                hadisService.saveOrUpdate(mapper.modelToEntity(createModel));
                Notify.showSuccessMessage(MessageUtils.Message.HADIS + " " + MessageUtils.Message.SUCCESS_SAVE);
                dlgCreate.close();
                tblHadis.refresh();
            } catch (GeneralException e) {
                Notify.showErrorMessage(e.getMessage());
            }

        }
    }

    public void closeDisplayDialog(ActionEvent actionEvent) {
        dlgDisplay.close();
    }
}
