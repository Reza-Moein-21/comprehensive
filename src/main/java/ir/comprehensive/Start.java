package ir.comprehensive;

import ir.comprehensive.utils.ScreenUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static ir.comprehensive.utils.MessageUtils.getMessageBundle;

@SpringBootApplication
public class Start extends Application {

    private ConfigurableApplicationContext springContext;
    private FXMLLoader fxmlLoader;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        springContext = SpringApplication.run(Start.class);
        Font.loadFont(getClass().getResource("/fonts/shabnam.ttf").toExternalForm(), ScreenUtils.getActualSize(32));
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setResources(getMessageBundle());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        fxmlLoader.setLocation(getClass().getResource("/fxml/start.fxml"));
        Parent rootNode = fxmlLoader.load();

        Scene scene = new Scene(rootNode, ScreenUtils.getActualSize(2048), ScreenUtils.getActualSize(1024));
        setGlobalCss(scene);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/image/red-hat.png"));
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.show();

    }

    @Override
    public void stop() {
        springContext.stop();
    }

    private void setGlobalCss(Scene scene) {
        double scale = ScreenUtils.getScale();
        if (scale <= ScreenUtils.LOW) {
            scene.getStylesheets().add(getClass().getResource("/css/lowFont.css").toExternalForm());
        } else if (ScreenUtils.LOW < scale && scale <= ScreenUtils.MID) {
            scene.getStylesheets().add(getClass().getResource("/css/midFont.css").toExternalForm());
        } else if (ScreenUtils.MID < scale && scale <= ScreenUtils.HI) {
            scene.getStylesheets().add(getClass().getResource("/css/hiFont.css").toExternalForm());
        } else if (scale >= ScreenUtils.EXTRA) {
            scene.getStylesheets().add(getClass().getResource("/css/extraFont.css").toExternalForm());
        }
    }
}
