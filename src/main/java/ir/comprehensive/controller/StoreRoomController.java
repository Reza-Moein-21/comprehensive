package ir.comprehensive.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.SimpleDatePicker;
import ir.comprehensive.domain.Person;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.service.CallbackMessage;
import ir.comprehensive.service.PersonService;
import ir.comprehensive.service.ProductDeliveryService;
import ir.comprehensive.utils.FormValidationUtils;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

import static ir.comprehensive.utils.MessageUtils.getMessage;

@Controller
public class StoreRoomController implements Initializable {

    public JFXTextField txfProductName;
    public SimpleDatePicker sdpDeliveryDate;
    public SimpleDatePicker sdpDesiredDate;
    public JFXTextField txfDescription;
    public JFXComboBox<Person> cbxPerson;

    public JFXTextField txfPersonName;
    public JFXDialog createDialog;
    public HBox ddd;
    public TableView<ProductDeliveryModel> tblProductDelivery;


    private ProductDeliveryService productDeliveryService;
    private PersonService personService;


    public StackPane con;
    private SimpleDatePicker deliveryDateField;
    public JFXComboBox com;

    private StartController startController;
    private ProductDeliveryModel createModel = new ProductDeliveryModel();



    public StoreRoomController(ProductDeliveryService productDeliveryService, PersonService personService, StartController startController) {
        this.startController = startController;
        this.productDeliveryService = productDeliveryService;
        this.personService = personService;
    }

    private void fillPersonItems() {
        ObservableList<Person> allPerson = personService.getAll();
        cbxPerson.setItems(allPerson);
    }

    private void fillDataTable() {

        Task<ObservableList<ProductDeliveryModel>> fillTableTask = new Task<ObservableList<ProductDeliveryModel>>() {
            @Override
            protected ObservableList<ProductDeliveryModel> call() {
                return productDeliveryService.getAllModel();
            }
        };
        fillTableTask.setOnSucceeded(event -> tblProductDelivery.setItems(fillTableTask.getValue()));
        Executors.newCachedThreadPool().execute(fillTableTask);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        createDialog.setDialogContainer(startController.mainStack);
        createDialog.setOnDialogOpened(event -> fillPersonItems());

        // bind model for create
        createModel.personProperty().bind(cbxPerson.valueProperty());
        createModel.productNameProperty().bind(txfProductName.textProperty());
        createModel.descriptionProperty().bind(txfDescription.textProperty());

        // init input fields
        cbxPerson.getValidators().add(FormValidationUtils.getRequiredFieldValidator(getMessage("fullName"), null));
        cbxPerson.valueProperty().addListener((observable, oldValue, newValue) -> cbxPerson.validate());
        txfProductName.textProperty().addListener((observable, oldValue, newValue) -> txfProductName.validate());
        txfProductName.getValidators().add(FormValidationUtils.getRequiredFieldValidator(getMessage("productName"), null));

        fillDataTable();
    }


    public void search(ActionEvent actionEvent) {

    }

    public void showCreateDialog() {
        createDialog.show();
    }

    public void closeCreateDialog(ActionEvent actionEvent) {
        createDialog.close();
    }

    public void save() {
        if (cbxPerson.validate() && txfProductName.validate()) {
            createModel.setDeliveryDate(sdpDeliveryDate.getPersianDate().toGregorian());
            createModel.setDesiredDate(sdpDesiredDate.getPersianDate().toGregorian());
            CallbackMessage save = productDeliveryService.save(createModel);
            createDialog.close();
            fillDataTable();
        }
    }

}
