package ir.comprehensive.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class StartController implements Initializable {

    @FXML
    JFXHamburger appHamburger;

    @FXML
    JFXDrawer appDrawer;

    HamburgerSlideCloseTransition slideCloseTransition;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slideCloseTransition = new HamburgerSlideCloseTransition(appHamburger);
        slideCloseTransition.setRate(-1);
        appHamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, this::appHamburgerMousePress);
    }

    private void appHamburgerMousePress(MouseEvent mouseEvent) {
        slideCloseTransition.setRate(slideCloseTransition.getRate() * -1);
        slideCloseTransition.play();

        if (appDrawer.isOpened()) {
            appDrawer.close();
        } else {
            appDrawer.open();
        }
    }
}
