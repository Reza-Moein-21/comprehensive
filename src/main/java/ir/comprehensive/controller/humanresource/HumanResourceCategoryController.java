package ir.comprehensive.controller.humanresource;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.controller.StartController;
import ir.comprehensive.mapper.CategoryMapper;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.service.CategoryService;
import ir.comprehensive.service.extra.GeneralException;
import ir.comprehensive.utils.FormValidationUtils;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.Notify;
import ir.comprehensive.utils.ScreenUtils;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
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
import java.util.stream.Collectors;

@Controller
public class HumanResourceCategoryController implements Initializable {

    @FXML
    public JFXDialog dlgCreate;
    @FXML
    public JFXDialog dlgDisplay;
    @FXML
    public YesNoDialog dlgDelete;

    @FXML
    public DataTable<CategoryModel> tblCategory;
    @FXML
    public CategoryModel createModel;
    @FXML
    public CategoryModel searchModel;

    @FXML
    public JFXTextField txfTitleS;
    @FXML
    public JFXTextField txfPhoneNumberS;
    @FXML
    public JFXTextField txfFaxS;
    @FXML
    public JFXTextField txfEmailS;
    @FXML
    public JFXTextField txfAddressS;
    @FXML
    public JFXTextField txfDescriptionS;

    @FXML
    public JFXTextField txfTitleC;
    @FXML
    public JFXTextField txfPhoneNumberC;
    @FXML
    public JFXTextField txfFaxC;
    @FXML
    public JFXTextField txfEmailC;
    @FXML
    public JFXTextField txfAddressC;
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
    public JFXTextField txfPhoneNumberD;
    @FXML
    public JFXTextField txfFaxD;
    @FXML
    public JFXTextField txfEmailD;
    @FXML
    public JFXTextField txfAddressD;
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
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper mapper;

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
        vbxCreateContent.setSpacing(ScreenUtils.getActualSize(50));
        vbxCreateContent.setPrefWidth(ScreenUtils.getActualSize(1350));

        vbxHRCategory.setSpacing(ScreenUtils.getActualSize(10));
        vbxHRCategory.setPadding(new Insets(ScreenUtils.getActualSize(10), 0, ScreenUtils.getActualSize(10), 0));

        btnSearch.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
        btnShowAll.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));

        btnCreate.setPrefWidth(ScreenUtils.getActualSize(400));
        btnCreate.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));

        vbxDisplayContent.setSpacing(ScreenUtils.getActualSize(50));
        vbxDisplayContent.setPrefWidth(ScreenUtils.getActualSize(1900));

        grdCreateContent.setPadding(new Insets(ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20)));
        grdCreateContent.setHgap(ScreenUtils.getActualSize(10));
        grdCreateContent.setVgap(ScreenUtils.getActualSize(100));

        grdDisplayMain.setPadding(new Insets(ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20)));
        grdDisplayMain.setHgap(ScreenUtils.getActualSize(10));
        grdDisplayMain.setVgap(ScreenUtils.getActualSize(100));

        grdSearchFooter.setHgap(ScreenUtils.getActualSize(10));
        hbxCreateFooter.setSpacing(ScreenUtils.getActualSize(20));
        hbxCreateFooter.setPadding(new Insets(ScreenUtils.getActualSize(10)));
        grdSearchContent.setPrefHeight(ScreenUtils.getActualSize(700));
        grdSearchContent.setVgap(ScreenUtils.getActualSize(10));
        grdSearchContent.setHgap(ScreenUtils.getActualSize(10));
        //        dlgLayout
        updateDataTable();
        tblCategory.setOnEdit(selectedItem -> {
            createModel.setId(selectedItem.getId());
            txfTitleC.setText(selectedItem.getTitle());
            txfPhoneNumberC.setText(selectedItem.getPhoneNumber());
            txfFaxC.setText(selectedItem.getFax());
            txfEmailC.setText(selectedItem.getEmail());
            txfAddressC.setText(selectedItem.getAddress());
            txfDescriptionC.setText(selectedItem.getDescription());

            dlgCreate.show();

        });

        tblCategory.setOnDelete(selectedItem -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                try {
                    categoryService.delete(selectedItem.getId());
                    Notify.showSuccessMessage(MessageUtils.Message.CATEGORY + " " + MessageUtils.Message.SUCCESS_DELETE);
                    updateDataTable();
                } catch (GeneralException e) {
                    Notify.showErrorMessage(e.getMessage());
                }
                dlgDelete.close();
            });
        });

        tblCategory.setOnVisit(selectedItem -> {
            txfTitleD.setText(selectedItem.getTitle());
            txfPhoneNumberD.setText(selectedItem.getPhoneNumber());
            txfFaxD.setText(selectedItem.getFax());
            txfEmailD.setText(selectedItem.getEmail());
            txfAddressD.setText(selectedItem.getAddress());
            txfDescriptionD.setText(selectedItem.getDescription());

            dlgDisplay.show();
        });

        txfTitleC.focusedProperty().addListener(getChangeListener(txfTitleC));
        txfEmailC.focusedProperty().addListener(getChangeListener(txfEmailC));
        txfPhoneNumberC.focusedProperty().addListener(getChangeListener(txfPhoneNumberC));

        txfTitleC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.TITLE));
        txfEmailC.getValidators().add(FormValidationUtils.getEmailFieldValidator());
        txfPhoneNumberC.getValidators().add(FormValidationUtils.getPhoneNumberValidator());
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
        txfPhoneNumberS.setText(null);
        txfFaxS.setText(null);
        txfEmailS.setText(null);
        txfAddressS.setText(null);
        txfDescriptionS.setText(null);
        updateDataTable();
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        tblCategory.setItems(categoryService.search(searchModel).map(categories -> categories.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());


    }

    @FXML
    public void openCreateDialog() {
        createModel.setId(null);
        txfTitleC.setText(null);
        txfPhoneNumberC.setText(null);
        txfFaxC.setText(null);
        txfEmailC.setText(null);
        txfAddressC.setText(null);
        txfDescriptionC.setText(null);
        dlgCreate.show();
    }

    @FXML
    public void closeCreateDialog() {
        dlgCreate.close();
    }

    private boolean validateBeforeSave() {
        boolean titleValidate = txfTitleC.validate();
        boolean emailValidate = txfEmailC.validate();
        boolean phoneNumberValidate = txfPhoneNumberC.validate();

        return titleValidate && emailValidate && phoneNumberValidate;
    }

    public void save() {
        if (validateBeforeSave()) {
            try {
                categoryService.saveOrUpdate(mapper.modelToEntity(createModel));
                Notify.showSuccessMessage(MessageUtils.Message.CATEGORY + " " + MessageUtils.Message.SUCCESS_SAVE);
                dlgCreate.close();
                updateDataTable();
            } catch (GeneralException e) {
                Notify.showErrorMessage(e.getMessage());
            }

        }
    }

    private void updateDataTable() {
        tblCategory.setItems(categoryService.loadAll().map(categories -> categories.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());
    }

    public void closeDisplayDialog(ActionEvent actionEvent) {
        dlgDisplay.close();
    }
}
