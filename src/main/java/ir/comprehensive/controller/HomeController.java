package ir.comprehensive.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    @FXML
    VBox homePage;
    private StartController startController;

    public HomeController(StartController startController) {
        this.startController = startController;
    }

    @FXML
    public void goToStoreroom() {
        startController.navigateToView(ViewName.STORE_ROOM);
    }

    public void goToHumanResource() {
        startController.navigateToView(ViewName.HUMAN_RESOURCE);
    }
}
