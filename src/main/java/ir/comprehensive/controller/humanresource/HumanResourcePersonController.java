package ir.comprehensive.controller.humanresource;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.MultiSelectBox;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.controller.StartController;
import ir.comprehensive.mapper.CategoryMapper;
import ir.comprehensive.mapper.PersonMapper;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.service.CategoryService;
import ir.comprehensive.service.PersonService;
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
import javafx.scene.control.TableColumn;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    @FXML
    public VBox parent;
    @FXML
    public JFXDialogLayout dlgCreatLayout;
    @FXML
    public GridPane grdCreateContent;
    @FXML
    public HBox hbxCreateFooter;
    @FXML
    public GridPane grdSearchContent;
    @FXML
    public TableColumn<PersonModel, String> colFirstName;
    @FXML
    public TableColumn<PersonModel, String> colLastName;
    @FXML
    public TableColumn<PersonModel, String> colPhoneNumber;
    @FXML
    public TableColumn<PersonModel, String> colEmail;

    @Autowired
    private StartController startController;
    @Autowired
    private PersonService personService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private PersonMapper mapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);
        dlgDelete.setDialogContainer(startController.mainStack);

        parent.setPadding(new Insets(ScreenUtils.getActualSize(10), 0, ScreenUtils.getActualSize(10), 0));
        parent.setSpacing(ScreenUtils.getActualSize(10));

        dlgCreatLayout.setPadding(new Insets(ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(5), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(5)));
        dlgCreatLayout.setMinWidth(ScreenUtils.getActualSize(1024));
        dlgCreatLayout.setMinHeight(ScreenUtils.getActualSize(10));

        grdCreateContent.setPadding(new Insets(ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(40), ScreenUtils.getActualSize(20)));
        grdCreateContent.setHgap(ScreenUtils.getActualSize(10));
        grdCreateContent.setVgap(ScreenUtils.getActualSize(100));

        hbxCreateFooter.setSpacing(ScreenUtils.getActualSize(20));

        grdSearchContent.setPrefHeight(ScreenUtils.getActualSize(700));
        grdSearchContent.setVgap(ScreenUtils.getActualSize(10));
        grdSearchContent.setHgap(ScreenUtils.getActualSize(10));

        colFirstName.setMinWidth(ScreenUtils.getActualSize(200));
        colFirstName.setPrefWidth(ScreenUtils.getActualSize(400));

        colLastName.setMinWidth(ScreenUtils.getActualSize(200));
        colLastName.setPrefWidth(ScreenUtils.getActualSize(400));

        colPhoneNumber.setMinWidth(ScreenUtils.getActualSize(200));
        colPhoneNumber.setPrefWidth(ScreenUtils.getActualSize(400));

        colEmail.setMinWidth(ScreenUtils.getActualSize(200));
        colEmail.setPrefWidth(ScreenUtils.getActualSize(400));

        slbCategoriesC.setPrefHeight(ScreenUtils.getActualSize(320));

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
                try {
                    personService.delete(selectedItem.getId());
                    Notify.showSuccessMessage(MessageUtils.Message.PERSON + " " + MessageUtils.Message.SUCCESS_DELETE);
                    updateDataTable();
                } catch (GeneralException e) {
                    Notify.showErrorMessage(e.getMessage());
                }
                dlgDelete.close();
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

                suggestItems.setValue(categoryService.findByTitle(newValue).map(categories -> categories.stream().map(categoryMapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());
            }
        });
    }

    @FXML
    public void showAll(ActionEvent actionEvent) {
        txfFirstNameS.setText(null);
        txfLastNameS.setText(null);
        txfPhoneNumberS.setText(null);
        txfEmailS.setText(null);
        slbCategoriesS.clean();
        updateDataTable();
    }

    private void updateDataTable() {
        tblPerson.setItems(personService.loadAll().map(people -> people.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());

    }

    @FXML
    public void search(ActionEvent actionEvent) {
        tblPerson.setItems(personService.search(searchModel).map(categories -> categories.stream().map(mapper::entityToModel).collect(Collectors.toList())).map(FXCollections::observableArrayList).get());
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
            try {
                personService.saveOrUpdate(mapper.modelToEntity(createModel));
                dlgCreate.close();
                updateDataTable();
                Notify.showSuccessMessage(MessageUtils.Message.PERSON + " " + MessageUtils.Message.SUCCESS_SAVE);
            } catch (GeneralException e) {
                Notify.showErrorMessage(e.getMessage());
            }
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
