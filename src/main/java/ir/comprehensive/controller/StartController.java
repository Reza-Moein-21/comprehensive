package ir.comprehensive.controller;

import com.jfoenix.controls.JFXButton;
import ir.comprehensive.utils.ScreenUtils;
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
    @FXML
    public JFXButton btnBack;
    @FXML
    public JFXButton btnHome;
    @FXML
    public JFXButton btnSetting;

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
        btnBack.setPrefWidth(ScreenUtils.getActualSize(96));
        btnBack.setPrefHeight(ScreenUtils.getActualSize(64));
        btnHome.setPrefWidth(ScreenUtils.getActualSize(96));
        btnHome.setPrefHeight(ScreenUtils.getActualSize(64));
        btnSetting.setPrefWidth(ScreenUtils.getActualSize(96));
        btnSetting.setPrefHeight(ScreenUtils.getActualSize(64));

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
