package ir.comprehensive.controller.humanresource;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.controller.StartController;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.service.CategoryService;
import ir.comprehensive.utils.FormValidationUtils;
import ir.comprehensive.utils.MessageUtils;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

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


    private StartController startController;
    private CategoryService categoryService;

    public HumanResourceCategoryController(StartController startController, CategoryService categoryService) {
        this.startController = startController;
        this.categoryService = categoryService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);
        dlgDelete.setDialogContainer(startController.mainStack);

        refreshTable(categoryService.getAllModel());
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
                categoryService.delete(selectedItem.getId());
                dlgDelete.close();
                refreshTable(categoryService.getAllModel());
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
        refreshTable(categoryService.getAllModel());
    }

    private void refreshTable(ObservableList<CategoryModel> allModel) {
        tblCategory.setItems(allModel);
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        refreshTable(categoryService.search(searchModel));
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
            categoryService.saveOrUpdate(createModel);
            dlgCreate.close();
            refreshTable(categoryService.getAllModel());

        }
    }

}
