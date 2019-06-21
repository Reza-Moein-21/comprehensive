package ir.comprehensive.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ir.comprehensive.component.Autocomplete;
import ir.comprehensive.component.SimpleDatePicker;
import ir.comprehensive.component.basetable.DataTable;
import ir.comprehensive.domain.ProductStatus;
import ir.comprehensive.model.PersonModel;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.model.ProductModel;
import ir.comprehensive.service.PersonService;
import ir.comprehensive.service.ProductDeliveryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
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
    public JFXDialog dlgCreate;
    @FXML
    public DataTable<ProductDeliveryModel> tblProductDelivery;

    private void fillDataTable() {
        tblProductDelivery.setItems(productDeliveryService.getAllModel());
    }

    @FXML
    private ProductDeliveryModel createModel = new ProductDeliveryModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        dlgCreate.setDialogContainer(startController.mainStack);


        autPersonC.setOnSearch(s -> personService.findByName(s));
        autPersonS.setOnSearch(s -> personService.findByName(s));


        fillDataTable();
    }


    public void search(ActionEvent actionEvent) {

    }

    public void showCreateDialog() {
        dlgCreate.show();
    }

    public void closeCreateDialog(ActionEvent actionEvent) {
        dlgCreate.close();
    }

    public void save() {
        ProductDeliveryModel createModel = new ProductDeliveryModel();
        createModel.setPerson(autPersonC.getValue());
        createModel.setProduct(new ProductModel(txfProductNameC.getText()));
        createModel.setDeliveryDate(sdpDeliveryDateC.getPersianDate().toGregorian());
        createModel.setDesiredDate(sdpDesiredDateC.getPersianDate().toGregorian());
        createModel.setDescription(txfDescriptionC.getText());
        productDeliveryService.save(createModel);

        dlgCreate.close();
        fillDataTable();
    }

}
