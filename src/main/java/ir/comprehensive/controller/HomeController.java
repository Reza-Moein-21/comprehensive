package ir.comprehensive.controller;

import ir.comprehensive.component.Card;
import ir.comprehensive.utils.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class HomeController implements Initializable {
    @FXML
    public Card crdStoreRoom;
    @FXML
    public Card crdHumanResource;
    @FXML
    public Card crdMyNoteCategory;
    @FXML
    public FlowPane masonry;

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

    @FXML
    public void goToHumanResource() {
        startController.navigateToView(ViewName.HUMAN_RESOURCE);
    }

    @FXML
    public void goToMyNoteCategory() {
        startController.navigateToView(ViewName.MY_NOTE_CATEGORY);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        crdStoreRoom.setPrefWidth(ScreenUtils.getActualSize(350.0));
        crdStoreRoom.setPrefHeight(ScreenUtils.getActualSize(210));
        crdHumanResource.setPrefWidth(ScreenUtils.getActualSize(350));
        crdHumanResource.setPrefHeight(ScreenUtils.getActualSize(210));
        crdMyNoteCategory.setPrefWidth(ScreenUtils.getActualSize(350.0));
        crdMyNoteCategory.setPrefHeight(ScreenUtils.getActualSize(210));
        masonry.setHgap(ScreenUtils.getActualSize(15));
        masonry.setVgap(ScreenUtils.getActualSize(15));
        masonry.setPadding(new Insets(ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(10)));
    }

}
