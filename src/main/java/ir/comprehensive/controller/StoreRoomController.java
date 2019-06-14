package ir.comprehensive.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.Autocomplete;
import ir.comprehensive.component.SimpleDatePicker;
import ir.comprehensive.domain.Person;
import ir.comprehensive.model.CategoryModel;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.service.CategoryService;
import ir.comprehensive.service.PersonService;
import ir.comprehensive.service.ProductDeliveryService;
import ir.comprehensive.service.response.ResponseStatus;
import ir.comprehensive.utils.Notify;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

@Controller
public class StoreRoomController implements Initializable {

    public JFXTextField txfProductName;
    public SimpleDatePicker sdpDeliveryDate;
    public SimpleDatePicker sdpDesiredDate;
    public JFXTextField txfDescription;
    public JFXComboBox<Person> cbxPerson;

    public JFXTextField txfPersonName;
    public JFXDialog dlgCreate;
    public HBox ddd;
    public TableView<ProductDeliveryModel> tblProductDelivery;
    public Autocomplete<CategoryModel> autPerson;
    List<CategoryModel> list;
    @Autowired
    private ProductDeliveryService productDeliveryService;
    @Autowired
    private PersonService personService;
    @Autowired
    private StartController startController;


    public StackPane con;
    private SimpleDatePicker deliveryDateField;
    public JFXComboBox com;
    @Autowired
    private CategoryService categoryService;



    private void fillPersonItems() {
        personService.getAll((result, message, status) -> {
            if (status == ResponseStatus.FAIL) {
                Notify.showErrorMessage(message);
            } else {
                cbxPerson.setItems(result);
            }
        });
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

    @FXML
    private ProductDeliveryModel createModel = new ProductDeliveryModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);
        dlgCreate.setOnDialogOpened(event -> fillPersonItems());

        autPerson.setOnSearch(s -> {
            categoryService.findByTitle(s, (result, message, status) -> {
                if (status == ResponseStatus.FAIL) {
                    Notify.showErrorMessage(message);
                    list = null;
                    return;
                }

                list = result;

                if (list.isEmpty()) {
                    list = null;
                }
            });


            System.out.println("Reaults" + list);
            return list;
        });
//        // bind model for create
//        createModel.personProperty().bind(cbxPerson.valueProperty());
//        createModel.productNameProperty().bind(txfProductName.textProperty());
//        createModel.descriptionProperty().bind(txfDescription.textProperty());

        // init input fields
//        cbxPerson.getValidators().add(FormValidationUtils.getRequiredFieldValidator(getMessage("fullName")));
//        cbxPerson.valueProperty().addListener((observable, oldValue, newValue) -> cbxPerson.validate());
//        txfProductName.textProperty().addListener((observable, oldValue, newValue) -> txfProductName.validate());
//        txfProductName.getValidators().add(FormValidationUtils.getRequiredFieldValidator(getMessage("productName")));

        fillDataTable();
    }


    public void search(ActionEvent actionEvent) {
        System.out.println("Autocomplete value: " + autPerson.getValue());
    }

    public void showCreateDialog() {
        dlgCreate.show();
    }

    public void closeCreateDialog(ActionEvent actionEvent) {
        dlgCreate.close();
    }

    public void save() {
        if (cbxPerson.validate() && txfProductName.validate()) {
            createModel.setDeliveryDate(sdpDeliveryDate.getPersianDate().toGregorian());
            createModel.setDesiredDate(sdpDesiredDate.getPersianDate().toGregorian());
            productDeliveryService.save(createModel, (result, message, status) -> {
                if (status == ResponseStatus.FAIL) {
                    Notify.showErrorMessage(message);
                    return;
                }
                dlgCreate.close();
                fillDataTable();
            });

        }
    }

}
