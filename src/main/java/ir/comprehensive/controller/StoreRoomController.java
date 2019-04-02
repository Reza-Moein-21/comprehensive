package ir.comprehensive.controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import ir.comprehensive.model.ProductDeliveryModel;
import ir.comprehensive.service.ProductDeliveryService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Controller
public class StoreRoomController implements Initializable {

    ProductDeliveryService productDeliveryService;

    public StoreRoomController(ProductDeliveryService productDeliveryService) {
        this.productDeliveryService = productDeliveryService;
    }

    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, Date> deliveryDateColumn;
    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, String> personNameColumn;
    @FXML
    public JFXTreeTableColumn<ProductDeliveryModel, String> productNameColumn;
    @FXML
    public JFXTreeTableView<ProductDeliveryModel> productDeliveryTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

        deliveryDateColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProductDeliveryModel, Date> param) -> {
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


}
