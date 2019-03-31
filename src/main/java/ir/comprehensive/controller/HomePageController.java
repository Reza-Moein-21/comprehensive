package ir.comprehensive.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Controller;

import java.io.IOException;

import static ir.comprehensive.utils.MessageUtils.getMessageBundle;

@Controller
public class HomePageController {

    @FXML
    GridPane homePage;

    @FXML
    public void goToStoreroom(ActionEvent actionEvent) {
        AnchorPane main = (AnchorPane) homePage.getParent();
        Parent storeroom = null;
        try {
            storeroom = FXMLLoader.load(getClass().getResource("/fxml/storeRoom.fxml"), getMessageBundle());
        } catch (IOException e) {
            e.printStackTrace();
        }
        main.getChildren().setAll(storeroom);
    }
}
