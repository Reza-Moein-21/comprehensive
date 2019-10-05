package ir.comprehensive.controller.warehouse;

import ir.comprehensive.component.Card;
import ir.comprehensive.controller.StartController;
import ir.comprehensive.controller.ViewName;
import ir.comprehensive.utils.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class WarehouseHomeController implements Initializable {
    @FXML
    public Card crdStoreRoom;
    @FXML
    public Card crdWarehouse;
    @FXML
    public FlowPane masonry;

    private StartController startController;

    public WarehouseHomeController(StartController startController) {
        this.startController = startController;
    }

    @FXML
    public void goToStoreroom() {
        startController.navigateToView(ViewName.STORE_ROOM);
    }

    @FXML
    public void goToWarehouse() {
        startController.navigateToView(ViewName.WAREHOUSE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        crdStoreRoom.setPrefWidth(ScreenUtils.getActualSize(350.0));
        crdStoreRoom.setPrefHeight(ScreenUtils.getActualSize(210));
        crdWarehouse.setPrefWidth(ScreenUtils.getActualSize(350));
        crdWarehouse.setPrefHeight(ScreenUtils.getActualSize(210));
        masonry.setHgap(ScreenUtils.getActualSize(15));
        masonry.setVgap(ScreenUtils.getActualSize(15));
        masonry.setPadding(new Insets(ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(10)));
    }

}
