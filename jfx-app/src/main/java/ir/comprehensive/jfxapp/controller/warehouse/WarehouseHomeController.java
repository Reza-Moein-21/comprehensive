package ir.comprehensive.jfxapp.controller.warehouse;

import ir.comprehensive.jfxapp.component.Card;
import ir.comprehensive.jfxapp.controller.StartController;
import ir.comprehensive.jfxapp.controller.ViewName;
import ir.comprehensive.jfxapp.utils.ScreenUtils;
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

    private void configCard(Card card) {
        card.setPrefWidth(ScreenUtils.getActualSize(820));
        card.setPrefHeight(ScreenUtils.getActualSize(340));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configCard(crdStoreRoom);
        configCard(crdWarehouse);

        masonry.setHgap(ScreenUtils.getActualSize(15));
        masonry.setVgap(ScreenUtils.getActualSize(15));
        masonry.setPadding(new Insets(ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(10)));
    }

}
