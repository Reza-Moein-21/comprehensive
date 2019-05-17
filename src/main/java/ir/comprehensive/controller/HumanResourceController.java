package ir.comprehensive.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.model.basemodel.Editable;
import ir.comprehensive.service.PersonService;
import javafx.beans.property.LongProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class HumanResourceController implements Initializable, Editable {
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
        personC = null;
        tblPerson.setItems(personService.getAllModel());
    }

    public void openCreateDialog() {
        createDialog.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        createDialog.setDialogContainer(startController.mainStack);
        // bind person model
        personC.firstNameProperty().bind(txfFirstNameC.textProperty());
        txfLastNameC.textProperty().bindBidirectional(personC.lastNameProperty());
        txfPhoneNumberC.textProperty().bindBidirectional(personC.phoneNumberProperty());
        txfEmailC.textProperty().bindBidirectional(personC.emailProperty());

        tblPerson.setItems(personService.getAllModel(this));
    }

    public void search(ActionEvent actionEvent) {
    }


    @Override
    public void edit(LongProperty id) {
    }
}
