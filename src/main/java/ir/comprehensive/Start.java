package ir.comprehensive;

import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.ScreenUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
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
        Pane rootNode = fxmlLoader.load();
        applyFontStyle(rootNode);
        Scene scene = new Scene(rootNode, ScreenUtils.getActualSize(3200), ScreenUtils.getActualSize(1800));
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/image/icon2.png"));
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setTitle(MessageUtils.Message.APP_TITLE);
        primaryStage.show();

    }

    private void applyFontStyle(Pane rootNode) {
        for (Node n : rootNode.getChildren()) {
            n.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(32) + "px;-fx-font-family: 'shabnam';");
        }
    }

    @Override
    public void stop() {
        springContext.stop();
    }

}
