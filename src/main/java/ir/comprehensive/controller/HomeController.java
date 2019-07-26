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
    @FXML
    public Card crdProject;
    @FXML
    public Card crdHumanResource;
    @FXML
    public Card crdMyNote;
    @FXML
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

    @FXML
    public void goToHumanResource() {
        startController.navigateToView(ViewName.HUMAN_RESOURCE);
    }

    @FXML
    public void goToMyNote() {
        startController.navigateToView(ViewName.MY_NOTEBOOK);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        crdStoreRoom.setPrefWidth(ScreenUtils.getActualSize(350.0));
        crdStoreRoom.setPrefHeight(ScreenUtils.getActualSize(160));
//        crdProject.setPrefWidth(ScreenUtils.getActualSize(350));
//        crdProject.setPrefHeight(ScreenUtils.getActualSize(160));
        crdHumanResource.setPrefWidth(ScreenUtils.getActualSize(350));
        crdHumanResource.setPrefHeight(ScreenUtils.getActualSize(160));
        crdMyNote.setPrefWidth(ScreenUtils.getActualSize(450));
        crdMyNote.setPrefHeight(ScreenUtils.getActualSize(160));
        masonry.setHSpacing(ScreenUtils.getActualSize(10));
        masonry.setVSpacing(ScreenUtils.getActualSize(5));
        masonry.setPadding(new Insets(ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(20), ScreenUtils.getActualSize(10)));
    }

}
