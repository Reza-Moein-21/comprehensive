package ir.comprehensive.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
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


    private ProductDeliveryService productDeliveryService;
    private PersonService personService;
    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, String> descriptionColumn;

    public StackPane con;
    private SimpleDatePicker deliveryDateField;
    public JFXComboBox com;
    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, LocalDate> desiredDateColumn;
    private StartController startController;
    private ProductDeliveryModel createModel = new ProductDeliveryModel();
    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, LocalDate> deliveryDateColumn;
    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, Person> personColumn;
    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, String> productNameColumn;
    @FXML
    public JFXTreeTableView<ProductDeliveryModel> productDeliveryTable;


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

        Task<TreeItem<ProductDeliveryModel>> task = new Task<TreeItem<ProductDeliveryModel>>() {
            @Override
            protected TreeItem<ProductDeliveryModel> call() {
                ObservableList<ProductDeliveryModel> allModel = productDeliveryService.getAllModel();
                return new RecursiveTreeItem<>(allModel, RecursiveTreeObject::getChildren);
            }
        };
        task.setOnSucceeded(event -> {
            TreeItem<ProductDeliveryModel> root = task.getValue();
            productNameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, String> param) -> param.getValue().getValue().productNameProperty());

            personColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, Person> param) -> param.getValue().getValue().personProperty());

            deliveryDateColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, LocalDate> param) -> param.getValue().getValue().deliveryDateProperty());
            desiredDateColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, LocalDate> param) -> param.getValue().getValue().desiredDateProperty());
            descriptionColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, String> param) -> param.getValue().getValue().descriptionProperty());
            productDeliveryTable.setRoot(root);
            productDeliveryTable.setShowRoot(false);
            productDeliveryTable.setEditable(false);
        });
        Executors.newCachedThreadPool().execute(task);
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
