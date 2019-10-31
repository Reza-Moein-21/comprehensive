package ir.comprehensive;

import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.ScreenUtils;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

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
        Font.loadFont(getClass().getResource("/fonts/shabnam.ttf").toExternalForm(), ScreenUtils.getActualSize(32));
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(getMessageBundle());
        fxmlLoader.setLocation(getClass().getResource("/fxml/start.fxml"));
    }

    private Stage getWelcomeStage() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        StackPane vBox = new StackPane();
        vBox.setMaxHeight(10);
        vBox.setMaxWidth(10);
        Label t = new Label("Welcome page");
        vBox.getChildren().addAll(t);
        applyFontStyle(vBox);
        t.setStyle("-fx-font-size: " + ScreenUtils.getActualSize(100));

        Scene startScene = new Scene(vBox, 2000, 399);
        startScene.setFill(Color.TRANSPARENT);

        stage.setScene(startScene);
        stage.show();

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setAutoReverse(false);

        ParallelTransition pt = new ParallelTransition(vBox, ft);
        pt.play();
        return stage;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = getWelcomeStage();
        stage.getIcons().add(new Image("/image/icon2.png"));

        final Task<ConfigurableApplicationContext> progressTask = new Task<ConfigurableApplicationContext>() {
            @Override
            public ConfigurableApplicationContext call() {
                springContext = SpringApplication.run(Start.class);
                return springContext;
            }
        };

        progressTask.setOnSucceeded(event -> {
            fxmlLoader.setControllerFactory(springContext::getBean);
            Pane rootNode = null;
            try {
                rootNode = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            applyFontStyle(rootNode);
            Scene scene = new Scene(rootNode, ScreenUtils.getActualSize(3200), ScreenUtils.getActualSize(1800));
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("/image/icon2.png"));
            primaryStage.setOnCloseRequest(e -> System.exit(0));
            primaryStage.setTitle(MessageUtils.Message.APP_TITLE);
            stage.close();
            primaryStage.show();

        });

        Thread thread = new Thread(progressTask);
        thread.setDaemon(true);
        thread.start();

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
