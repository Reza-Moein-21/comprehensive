package ir.comprehensive.jfxapp.controller.humanresource;

import ir.comprehensive.jfxapp.utils.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class HumanResourceController implements Initializable {
    @FXML
    public Tab tabHrCategories;
    @FXML
    public Tab tabHrPerson;

    HumanResourcePersonController personController;
    HumanResourceCategoryController categoryController;

    @Autowired
    public HumanResourceController(HumanResourceCategoryController categoryController, HumanResourcePersonController personController) {
        this.categoryController = categoryController;
        this.personController = personController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabHrPerson.setStyle(String.format("-fx-padding: %s %s", ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
        tabHrCategories.setStyle(String.format("-fx-padding: %s %s", ScreenUtils.getActualSize(10), ScreenUtils.getActualSize(50)));
    }
}
