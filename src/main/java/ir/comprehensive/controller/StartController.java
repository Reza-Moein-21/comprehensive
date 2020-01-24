package ir.comprehensive.controller;

import com.jfoenix.controls.JFXButton;
import ir.comprehensive.utils.ScreenUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
    @FXML
    public GridPane grdTop;

    @FXML
    public Label lblPageTitle;

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
        // config start page and back button
        viewNames.push(ViewName.HOME);
        lblPageTitle.setText(ViewName.HOME.getTitle());
        btnBack.setVisible(false);

        AnchorPane.setTopAnchor(main, ScreenUtils.getActualSize(120));
        AnchorPane.setRightAnchor(main, ScreenUtils.getActualSize(10));
        AnchorPane.setBottomAnchor(main, ScreenUtils.getActualSize(10));
        AnchorPane.setLeftAnchor(main, ScreenUtils.getActualSize(10));

        btnBack.setPrefWidth(ScreenUtils.getActualSize(96));
        btnBack.setPrefHeight(ScreenUtils.getActualSize(64));
        btnHome.setPrefWidth(ScreenUtils.getActualSize(96));
        btnHome.setPrefHeight(ScreenUtils.getActualSize(64));
        btnSetting.setPrefWidth(ScreenUtils.getActualSize(96));
        btnSetting.setPrefHeight(ScreenUtils.getActualSize(64));
        configTopGrid();
    }

    private void configTopGrid() {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setMinHeight(ScreenUtils.getActualSize(50));
        grdTop.getRowConstraints().add(rowConstraints);
    }

    public void backToPrevious(ActionEvent actionEvent) {
        viewNames.pop();
        navigateToView(viewNames.pop());
    }

    public void navigateToView(ViewName viewName, String viewTitle) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(viewName.getViewUrl(), getMessageBundle(), null, context::getBean);
        } catch (IOException e) {
            e.printStackTrace();
        }

        main.getChildren().setAll(parent);
        viewNames.push(viewName);
        // change page title
        lblPageTitle.setText(viewTitle);
        // hide back button on home view
        btnBack.setVisible(!viewNames.peek().equals(ViewName.HOME));
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
        // change page title
        lblPageTitle.setText(viewName.getTitle());
        // hide back button on home view
        btnBack.setVisible(!viewNames.peek().equals(ViewName.HOME));
    }

    public void backToHome(ActionEvent actionEvent) {
        navigateToView(ViewName.HOME);
    }

    public void openHadis(ActionEvent actionEvent) {
        navigateToView(ViewName.ADD_HADIS);
    }
}
