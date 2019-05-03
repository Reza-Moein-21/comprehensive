package ir.comprehensive.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ir.comprehensive.utils.MessageUtils.getMessageBundle;

@Controller
public class StartController implements Initializable {

    ConfigurableApplicationContext context;

    public StartController(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @FXML
    AnchorPane main;
    @FXML
    public StackPane mainStack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backToPrevious(ActionEvent actionEvent) {
        Parent homePage = null;
        try {
            homePage = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"), getMessageBundle(), null, context::getBean);
        } catch (IOException e) {
            e.printStackTrace();
        }

        main.getChildren().setAll(homePage);
    }
}
