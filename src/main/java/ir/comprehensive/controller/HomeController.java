package ir.comprehensive.controller;

import com.jfoenix.controls.JFXMasonryPane;
import ir.comprehensive.component.Card;
import ir.comprehensive.utils.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class HomeController implements Initializable {
    @FXML
    public Card crdStoreRoom;
    public Card crdProject;
    public Card crdHumanResource;
    public JFXMasonryPane masonry;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        crdStoreRoom.setPrefWidth(ScreenUtils.getActualSize(350.0));
        crdStoreRoom.setPrefHeight(ScreenUtils.getActualSize(160));
        crdProject.setPrefWidth(ScreenUtils.getActualSize(350));
        crdProject.setPrefHeight(ScreenUtils.getActualSize(160));
        crdHumanResource.setPrefWidth(ScreenUtils.getActualSize(350));
        crdHumanResource.setPrefHeight(ScreenUtils.getActualSize(160));
        masonry.setHSpacing(ScreenUtils.getActualSize(10));
        masonry.setVSpacing(ScreenUtils.getActualSize(5));
        masonry.setPadding(new Insets(ScreenUtils.getActualSize(20),ScreenUtils.getActualSize(10),ScreenUtils.getActualSize(20),ScreenUtils.getActualSize(10)));
    }
}
