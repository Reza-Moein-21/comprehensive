package ir.comprehensive.controller.humanresource;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.MultiSelectBox;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.controller.StartController;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.service.CategoryService;
import ir.comprehensive.service.PersonService;
import ir.comprehensive.service.response.ResponseStatus;
import ir.comprehensive.utils.FormValidationUtils;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.Notify;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class HumanResourcePersonController implements Initializable {
    @FXML
    public JFXDialog dlgCreate;
    @FXML
    public YesNoDialog dlgDelete;

    @FXML
    public DataTable<PersonModel> tblPerson;

    @FXML
    public JFXTextField txfFirstNameS;
    @FXML
    public JFXTextField txfLastNameS;
    @FXML
    public JFXTextField txfPhoneNumberS;
    @FXML
    public JFXTextField txfEmailS;
    @FXML
    public MultiSelectBox<CategoryModel> slbCategoriesS;

    @FXML
    public MultiSelectBox<CategoryModel> slbCategoriesC;

    @FXML
    public PersonModel createModel;
    @FXML
    public PersonModel searchModel;
    @FXML
    public JFXTextField txfFirstNameC;
    @FXML
    public JFXTextField txfLastNameC;
    @FXML
    public JFXTextField txfPhoneNumberC;
    @FXML
    public JFXTextField txfEmailC;


    private StartController startController;
    private PersonService personService;
    private CategoryService categoryService;

    public HumanResourcePersonController(StartController startController, PersonService personService, CategoryService categoryService) {
        this.startController = startController;
        this.personService = personService;
        this.categoryService = categoryService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);
        dlgDelete.setDialogContainer(startController.mainStack);

        updateDataTable();
        tblPerson.setOnEdit(selectedItem -> {
            createModel.setId(selectedItem.getId());
            txfFirstNameC.setText(selectedItem.getFirstName());
            txfLastNameC.setText(selectedItem.getLastName());
            txfEmailC.setText(selectedItem.getEmail());
            txfPhoneNumberC.setText(selectedItem.getPhoneNumber());
            slbCategoriesC.setSelectedItems((FXCollections.observableArrayList(selectedItem.getCategories())));
            dlgCreate.show();

        });

        tblPerson.setOnDelete(selectedItem -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                personService.delete(selectedItem.getId(), (result, message, status) -> {
                    if (status == ResponseStatus.SUCCESS) {
                        Notify.showSuccessMessage(message);
                        dlgDelete.close();
                        updateDataTable();
                    } else {
                        Notify.showErrorMessage(message);

                    }
                });
            });
        });
        initSelectBox(slbCategoriesS);

        initSelectBox(slbCategoriesC);


        txfFirstNameC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                txfFirstNameC.validate();
            }
        });
        txfLastNameC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                txfLastNameC.validate();
            }
        });
        txfEmailC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                txfEmailC.validate();
            }
        });

        txfPhoneNumberC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                txfPhoneNumberC.validate();
            }
        });

        txfFirstNameC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.FIRST_NAME));
        txfLastNameC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.LAST_NAME));
        txfEmailC.getValidators().add(FormValidationUtils.getEmailFieldValidator());
        txfPhoneNumberC.getValidators().add(FormValidationUtils.getPhoneNumberValidator());
    }

    private void initSelectBox(MultiSelectBox<CategoryModel> slbCategoriesS) {
        slbCategoriesS.cellFactoryProperty().setValue(param -> getListCell());
        slbCategoriesS.setOnChange((oldValue, newValue, suggestItems) -> {
            if (newValue.isEmpty()) {
                suggestItems.getValue().clear();
            } else {
                categoryService.findByTitle(newValue, (result, message, status) -> {
                    if (status == ResponseStatus.FAIL) {
                        Notify.showErrorMessage(message);
                        return;
                    }
                    suggestItems.setValue(result);
                });
            }
        });
    }

    @FXML
    public void showAll(ActionEvent actionEvent) {
        txfFirstNameS.setText(null);
        txfLastNameS.setText(null);
        txfPhoneNumberS.setText(null);
        txfEmailS.setText(null);
        updateDataTable();
    }

    private void updateDataTable() {
        personService.getAllModel((result, message, status) -> {
            if (status == ResponseStatus.FAIL) {
                Notify.showErrorMessage(message);
                return;
            }
            tblPerson.setItems(result);
        });
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        personService.search(searchModel, (result, message, status) -> {
            if (status == ResponseStatus.FAIL) {
                Notify.showErrorMessage(message);
                return;
            }
            tblPerson.setItems(result);
        });
    }

    @FXML
    public void openCreateDialog() {
        createModel.setId(null);
        txfFirstNameC.setText(null);
        txfLastNameC.setText(null);
        txfEmailC.setText(null);
        txfPhoneNumberC.setText(null);
        slbCategoriesC.setSelectedItems((FXCollections.observableArrayList()));
        dlgCreate.show();
    }

    @FXML
    public void closeCreateDialog() {
        dlgCreate.close();
    }

    private boolean validateBeforeSave() {
        boolean firstNameValidate = txfFirstNameC.validate();
        boolean lastNameValidate = txfLastNameC.validate();
        boolean emailValidate = txfEmailC.validate();
        boolean phoneNumberValidate = txfPhoneNumberC.validate();

        return firstNameValidate && lastNameValidate && emailValidate && phoneNumberValidate;
    }
    public void save() {
        if (validateBeforeSave()) {
            personService.saveOrUpdate(createModel, (result, message, status) -> {
                if (status == ResponseStatus.SUCCESS) {
                    dlgCreate.close();
                    updateDataTable();
                    Notify.showSuccessMessage(message);
                } else {
                    Notify.showErrorMessage(message);
                }
            });
        }
    }

    private JFXListCell<CategoryModel> getListCell() {
        return new JFXListCell<CategoryModel>() {
            @Override
            protected void updateItem(CategoryModel model, boolean empty) {
                super.updateItem(model, empty);

                if (empty || model == null || model.getTitle() == null) {
                    setText(null);
                } else {
                    setText(model.getTitle());
                }
            }
        };
    }


}
