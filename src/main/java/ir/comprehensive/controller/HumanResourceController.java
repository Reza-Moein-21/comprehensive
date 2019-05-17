package ir.comprehensive.controller;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.model.basemodel.Editable;
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
public class HumanResourceController implements Initializable, Editable {

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
    public PersonModel createModel;
    @FXML
    public PersonModel searchModel;


    private StartController startController;
    private PersonService personService;

    public HumanResourceController(StartController startController, PersonService personService) {
        this.startController = startController;
        this.personService = personService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);

        tblPerson.setItems(personService.getAllModel(this));
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
        System.out.println("SAVE:" + createModel);
        personService.save(createModel);
        dlgCreate.close();
        tblPerson.setItems(personService.getAllModel(this));
    }

    @Override
    public void edit(ObjectProperty<Long> id) {
        PersonModel loadedModel = personService.loadModel(id.get(), this);
        dlgCreate.show();
        System.out.println("EDIT:" + createModel);
    }
}
