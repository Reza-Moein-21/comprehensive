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
import java.util.Stack;

import static ir.comprehensive.utils.MessageUtils.getMessageBundle;

@Controller
public class StartController implements Initializable {
    private Stack<ViewName> viewNames = new Stack<>();
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
        viewNames.push(ViewName.HOME);
    }

    public void backToPrevious(ActionEvent actionEvent) {
        viewNames.pop();
        navigateToView(viewNames.pop());
    }

    public void navigateToView(ViewName viewName) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(viewName.getViewUrl(), getMessageBundle(), null, context::getBean);
        } catch (IOException e) {
            e.printStackTrace();
        }

        main.getChildren().setAll(parent);
        viewNames.push(viewName);
    }

    public void backToHome(ActionEvent actionEvent) {
        navigateToView(ViewName.HOME);
    }
}
