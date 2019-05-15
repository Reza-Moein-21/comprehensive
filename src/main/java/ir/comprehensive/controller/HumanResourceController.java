package ir.comprehensive.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.service.PersonService;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class HumanResourceController implements Initializable {
    public JFXDialog createDialog;

    public JFXTextField txfFirstNameC;
    public JFXTextField txfLastNameC;
    public JFXTextField txfPhoneNumberC;
    public JFXTextField txfEmailC;
    public JFXComboBox<CategoryModel> cbxCategoriesC;
    public TableView<PersonModel> tblPerson;

    private StartController startController;
    private PersonService personService;
    private PersonModel personC = new PersonModel();

    public HumanResourceController(StartController startController, PersonService personService) {
        this.startController = startController;
        this.personService = personService;
    }

    public void closeCreateDialog() {
        createDialog.close();
    }

    public void save() {
        personService.save(personC);
        createDialog.close();
    }

    public void openCreateDialog() {
        createDialog.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        createDialog.setDialogContainer(startController.mainStack);
        // bind person model
        txfFirstNameC.textProperty().bindBidirectional(personC.firstNameProperty());
        txfLastNameC.textProperty().bindBidirectional(personC.lastNameProperty());
        txfPhoneNumberC.textProperty().bindBidirectional(personC.phoneNumberProperty());
        txfEmailC.textProperty().bindBidirectional(personC.emailProperty());

        tblPerson.setItems(personService.getAllModel());
    }
}
