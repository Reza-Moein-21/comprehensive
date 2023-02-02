package ir.comprehensive.jfxapp.controller.warehouse;

import ir.comprehensive.jfxapp.utils.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class WarehouseBaseController implements Initializable {
    @FXML
    public Tab tabWarehouse;
    @FXML
    public Tab tabWarehouseCategories;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabWarehouse.setStyle(String.format("-fx-padding: %s %s", ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
        tabWarehouseCategories.setStyle(String.format("-fx-padding: %s %s", ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
    }
}
