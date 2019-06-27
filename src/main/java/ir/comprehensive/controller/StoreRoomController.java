package ir.comprehensive.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.Autocomplete;
import ir.comprehensive.component.YesNoDialog;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.component.datepicker.SimpleDatePicker;
import ir.comprehensive.domain.ProductStatus;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.model.ProductModel;
import ir.comprehensive.service.PersonService;
import ir.comprehensive.service.ProductDeliveryService;
import ir.comprehensive.service.response.ResponseStatus;
import ir.comprehensive.utils.FormValidationUtils;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.Notify;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

@Controller
public class StoreRoomController implements Initializable {

    @Autowired
    private ProductDeliveryService productDeliveryService;
    @Autowired
    private PersonService personService;
    @Autowired
    private StartController startController;


    @FXML
    public JFXComboBox<ProductStatus> cmbStatusS;
    @FXML
    public SimpleDatePicker sdpDesiredDateS;
    @FXML
    public SimpleDatePicker sdpDeliveryDateS;
    @FXML
    public JFXTextField txfProductNameS;
    @FXML
    public Autocomplete<PersonModel> autPersonS;

    @FXML
    public JFXTextField txfDescriptionC;
    @FXML
    public SimpleDatePicker sdpDesiredDateC;
    @FXML
    public SimpleDatePicker sdpDeliveryDateC;
    @FXML
    public JFXTextField txfProductNameC;
    @FXML
    public Autocomplete<PersonModel> autPersonC;
    @FXML
    public JFXComboBox<ProductStatus> cmbStatusC;

    @FXML
    public YesNoDialog dlgDelete;
    @FXML
    public JFXDialog dlgCreate;
    @FXML
    public DataTable<ProductDeliveryModel> tblProductDelivery;

    private void fillDataTable() {
        tblProductDelivery.setItems(productDeliveryService.getAllModel());
    }

    @FXML
    private ProductDeliveryModel createModel;
    @FXML
    private ProductDeliveryModel searchModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);
        dlgDelete.setDialogContainer(startController.mainStack);

        tblProductDelivery.setOnEdit(selectedItem -> {
            ProductDeliveryModel editModel = productDeliveryService.load(selectedItem.getId());
            createModel.setId(editModel.getId());
            editModel.getPerson().getTitle();
            autPersonC.setValue(editModel.getPerson());
            txfProductNameC.setText(editModel.getProduct().getTitle());
            sdpDeliveryDateC.setValue(editModel.getDeliveryDate());
            sdpDesiredDateC.setValue(editModel.getDesiredDate());
            txfDescriptionC.setText(editModel.getDescription());
            cmbStatusC.setValue(editModel.getStatus());
            cmbStatusC.setDisable(false);
            dlgCreate.show();

        });

        tblProductDelivery.setOnDelete(selectedItem -> {
            dlgDelete.show();
            dlgDelete.setOnConfirm(() -> {
                productDeliveryService.delete(selectedItem.getId());
                Notify.showSuccessMessage(MessageUtils.Message.PRODUCT + " " + MessageUtils.Message.SUCCESS_DELETE);
                dlgDelete.close();
                fillDataTable();
            });
        });

        autPersonC.setOnSearch(s -> personService.findByName(s));


        autPersonC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                autPersonC.validate();
            }
        });
        txfProductNameC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                txfProductNameC.validate();
            }
        });

        sdpDeliveryDateC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                sdpDeliveryDateC.validate();
            }
        });
        sdpDesiredDateC.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                sdpDesiredDateC.validate();
            }
        });

        autPersonC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.PERSON));
        txfProductNameC.getValidators().add(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.PRODUCT_NAME));
        sdpDeliveryDateC.setValidators(Collections.singletonList(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.DELIVERY_DATE)));
        sdpDesiredDateC.setValidators(Collections.singletonList(FormValidationUtils.getRequiredFieldValidator(MessageUtils.Message.DESIRED_DATE)));


        autPersonS.setOnSearch(s -> personService.findByName(s));

        sdpDeliveryDateC.setDialogContainer(startController.mainStack);
        sdpDesiredDateC.setDialogContainer(startController.mainStack);

        fillDataTable();
    }


    public void search(ActionEvent actionEvent) {
        productDeliveryService.search(searchModel, (result, message, status) -> {
            if (status == ResponseStatus.FAIL) {
                Notify.showErrorMessage(message);
                return;
            }
            tblProductDelivery.setItems(result);
        });
    }

    public void showCreateDialog() {
        autPersonC.setValue(null);
        txfProductNameC.setText("");
        txfDescriptionC.setText("");
        sdpDeliveryDateC.setValue(null);
        sdpDesiredDateC.setValue(null);
        cmbStatusC.setValue(ProductStatus.UNKNOWN);
        cmbStatusC.setDisable(true);
        dlgCreate.show();
    }

    public void closeCreateDialog(ActionEvent actionEvent) {
        dlgCreate.close();
    }

    private boolean validateBeforeSave() {
        boolean personValidate = autPersonC.validate();
        boolean productNameValidate = txfProductNameC.validate();
        boolean deliveryDateValidate = sdpDeliveryDateC.validate();
        boolean desiredDateValidate = sdpDesiredDateC.validate();
        return personValidate && productNameValidate && deliveryDateValidate && desiredDateValidate;
    }

    public void save() {
        if (!validateBeforeSave()) {
            return;
        }
        createModel.setProduct(new ProductModel(txfProductNameC.getText()));
        productDeliveryService.saveOrUpdate(createModel, (result, message, status) -> {
            if (status == ResponseStatus.SUCCESS) {
                dlgCreate.close();
                fillDataTable();
                Notify.showSuccessMessage(message);
            } else {
                Notify.showErrorMessage(message);
            }
        });

    }

}
