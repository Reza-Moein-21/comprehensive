package ir.comprehensive.controller;

import ir.comprehensive.utils.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MyNoteBaseController implements Initializable {
    @FXML
    public Tab tabMyNoteTemp;
    @FXML
    public Tab tabMyNoteCategory;

    MyNoteTempController myNoteTempController;
    MyNoteCategoryController myNoteCategoryController;

    @Autowired
    public MyNoteBaseController(MyNoteTempController myNoteTempController, MyNoteCategoryController myNoteCategoryController) {
        this.myNoteTempController = myNoteTempController;
        this.myNoteCategoryController = myNoteCategoryController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabMyNoteTemp.setStyle(String.format("-fx-padding: %s %s", ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
        tabMyNoteCategory.setStyle(String.format("-fx-padding: %s %s", ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
    }
}
