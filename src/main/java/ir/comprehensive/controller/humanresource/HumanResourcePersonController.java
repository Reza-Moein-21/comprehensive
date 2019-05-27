package ir.comprehensive.controller.humanresource;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.MultiSelectBox;
import ir.comprehensive.controller.StartController;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.model.basemodel.Editable;
import ir.comprehensive.service.CategoryService;
import ir.comprehensive.service.PersonService;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class HumanResourcePersonController implements Initializable, Editable {
    @FXML
    public JFXDialog dlgCreate;

    @FXML
    public TableView<PersonModel> tblPerson;

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

        tblPerson.setItems(personService.getAllModel(this));

        initSelectBox(slbCategoriesS);

        initSelectBox(slbCategoriesC);

    }

    private void initSelectBox(MultiSelectBox<CategoryModel> slbCategoriesS) {
        slbCategoriesS.cellFactoryProperty().setValue(param -> getListCell());
        slbCategoriesS.setOnChange((oldValue, newValue, suggestItems) -> {
            if (newValue.isEmpty()) {
                suggestItems.getValue().clear();
            } else {
                suggestItems.setValue(categoryService.findByTitle(newValue));
            }
        });
    }

    @FXML
    public void showAll(ActionEvent actionEvent) {
        txfFirstNameS.setText(null);
        txfLastNameS.setText(null);
        txfPhoneNumberS.setText(null);
        txfEmailS.setText(null);
        tblPerson.setItems(personService.getAllModel(this));
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        tblPerson.setItems(personService.search(searchModel, this));
    }

    @FXML
    public void openCreateDialog() {
        dlgCreate.show();
    }

    @FXML
    public void closeCreateDialog() {
        dlgCreate.close();
    }


    public void save() {
        personService.save(createModel);
        dlgCreate.close();
        tblPerson.setItems(personService.getAllModel(this));
    }

    @Override
    public void edit(ObjectProperty<Long> id) {
        dlgCreate.show();
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
