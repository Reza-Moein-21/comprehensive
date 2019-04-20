package ir.comprehensive.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import ir.comprehensive.component.SimpleDatePicker;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.service.ProductDeliveryService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Controller
public class StoreRoomController implements Initializable {

    public JFXDialog dialog;
    public StackPane con;
    public StackPane tt;
    public SimpleDatePicker deliveryDateField;
    public JFXComboBox com;
    private ProductDeliveryService productDeliveryService;
    @FXML
    private ObjectProperty<ProductDeliveryModel> model = new SimpleObjectProperty<>(new ProductDeliveryModel());

    @FXML
    public JFXTextField productNameTextField;
    @FXML
    public JFXTextField personNameTextField;
    @FXML
    public JFXDatePicker deliveryDateDatePicker;
    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, LocalDate> deliveryDateColumn;
    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, String> personNameColumn;
    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, String> productNameColumn;
    @FXML
    public JFXTreeTableView<ProductDeliveryModel> productDeliveryTable;


    public StoreRoomController(ProductDeliveryService productDeliveryService) {
        this.productDeliveryService = productDeliveryService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        productNameTextField.textProperty().bindBidirectional(model.productNameProperty());

//        deliveryDateDatePicker.valueProperty().bindBidirectional(model.deliveryDateProperty());

        ObservableList<ProductDeliveryModel> allModel = productDeliveryService.getAllModel();
        final TreeItem<ProductDeliveryModel> root = new RecursiveTreeItem<>(allModel, RecursiveTreeObject::getChildren);


        productNameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, String> param) -> {
            if (productNameColumn.validateValue(param)) {
                return param.getValue().getValue().productNameProperty();
            } else {
                return productNameColumn.getComputedValue(param);
            }
        });

        personNameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, String> param) -> {
            if (personNameColumn.validateValue(param)) {
                return param.getValue().getValue().personNameProperty();
            } else {
                return personNameColumn.getComputedValue(param);
            }
        });

        deliveryDateColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, LocalDate> param) -> {
            if (deliveryDateColumn.validateValue(param)) {
                return param.getValue().getValue().deliveryDateProperty();
            } else {
                return deliveryDateColumn.getComputedValue(param);
            }
        });

        productDeliveryTable.setRoot(root);
        productDeliveryTable.setShowRoot(false);
        productDeliveryTable.setEditable(false);

    }


    public void search(ActionEvent actionEvent) {
        System.out.println(deliveryDateField.getPersianDate());
//        ObservableList<ProductDeliveryModel> allModel = productDeliveryService.search(model);
//        final TreeItem<ProductDeliveryModel> root = new RecursiveTreeItem<>(allModel, RecursiveTreeObject::getChildren);

        productNameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, String> param) -> {
            if (productNameColumn.validateValue(param)) {
                return param.getValue().getValue().productNameProperty();
            } else {
                return productNameColumn.getComputedValue(param);
            }
        });

        personNameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, String> param) -> {
            if (personNameColumn.validateValue(param)) {
                return param.getValue().getValue().personNameProperty();
            } else {
                return personNameColumn.getComputedValue(param);
            }
        });

        deliveryDateColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, LocalDate> param) -> {
            if (deliveryDateColumn.validateValue(param)) {
                return param.getValue().getValue().deliveryDateProperty();
            } else {
                return deliveryDateColumn.getComputedValue(param);
            }
        });

//        productDeliveryTable.setRoot(root);
        productDeliveryTable.setShowRoot(false);
        productDeliveryTable.setEditable(false);
    }

    public void create(ActionEvent actionEvent) {
        dialog.show(tt);
    }
}
