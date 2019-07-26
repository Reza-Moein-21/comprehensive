package ir.comprehensive.controller.humanresource;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    public JFXDialogLayout dlgLayout;
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
    public TableColumn<CategoryModel, String> colTitle;
    @FXML
    public TableColumn<CategoryModel, String> colPhoneNumber;
    @FXML
    public TableColumn<CategoryModel, String> colFax;
    @FXML
    public TableColumn<CategoryModel, String> colEmail;
    @FXML
    public TableColumn<CategoryModel, String> colAddress;
    @FXML
    public TableColumn<CategoryModel, String> colDescription;
    @FXML
    public JFXButton btnSearch;
    @FXML
    public JFXButton btnShowAll;

    @Autowired
    private StartController startController;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper mapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);
        dlgDelete.setDialogContainer(startController.mainStack);
        vbxHRCategory.setSpacing(ScreenUtils.getActualSize(10));
        vbxHRCategory.setPadding(new Insets(ScreenUtils.getActualSize(10), 0, ScreenUtils.getActualSize(10), 0));
        dlgLayout.setMinWidth(ScreenUtils.getActualSize(1200));
        dlgLayout.setMinHeight(ScreenUtils.getActualSize(700));
        dlgLayout.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(5), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(5)));

        btnSearch.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
        btnShowAll.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));

        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTitle.setMinWidth(ScreenUtils.getActualSize(300));
        colTitle.setPrefWidth(ScreenUtils.getActualSize(400));

        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colPhoneNumber.setMinWidth(ScreenUtils.getActualSize(300));
        colPhoneNumber.setPrefWidth(ScreenUtils.getActualSize(400));


        colFax.setCellValueFactory(new PropertyValueFactory<>("fax"));
        colFax.setMinWidth(ScreenUtils.getActualSize(300));
        colFax.setPrefWidth(ScreenUtils.getActualSize(400));

        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setMinWidth(ScreenUtils.getActualSize(300));
        colEmail.setPrefWidth(ScreenUtils.getActualSize(400));

        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAddress.setMinWidth(ScreenUtils.getActualSize(300));
        colAddress.setPrefWidth(ScreenUtils.getActualSize(400));

        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDescription.setMinWidth(ScreenUtils.getActualSize(300));
        colDescription.setPrefWidth(ScreenUtils.getActualSize(500));


        btnCreate.setPrefWidth(ScreenUtils.getActualSize(400));
        btnCreate.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));

        grdCreateContent.setPadding(new Insets(ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20)));
        grdCreateContent.setHgap(ScreenUtils.getActualSize(10));
        grdCreateContent.setVgap(ScreenUtils.getActualSize(100));
        grdSearchFooter.setHgap(ScreenUtils.getActualSize(10));
        hbxCreateFooter.setSpacing(ScreenUtils.getActualSize(20));
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

}
