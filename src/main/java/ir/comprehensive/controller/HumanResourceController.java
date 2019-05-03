package ir.comprehensive.controller;

import com.jfoenix.controls.JFXDialog;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class HumanResourceController implements Initializable {
    public JFXDialog createDialog;
    private StartController startController;

    public HumanResourceController(StartController startController) {
        this.startController = startController;
    }

    public void closeCreateDialog() {
        createDialog.close();
    }

    public void save() {
        createDialog.close();
    }

    public void openCreateDialog() {
        createDialog.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // bind create dialog
        createDialog.setDialogContainer(startController.mainStack);
//        createDialog.setOnDialogOpened();
    }
}
