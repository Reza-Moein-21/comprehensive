package ir.comprehensive.jfxapp;

import com.jfoenix.controls.JFXProgressBar;
import ir.comprehensive.service.PersonService;
import ir.comprehensive.utils.MessageUtils;
import ir.comprehensive.utils.ScreenUtils;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.StringJoiner;

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
        StackPane stackPane = new StackPane();
        stackPane.setMaxHeight(ScreenUtils.getActualSize(50));
        stackPane.setMaxWidth(ScreenUtils.getActualSize(50));
        JFXProgressBar jfxProgressBar = new JFXProgressBar();
        jfxProgressBar.setPrefWidth(ScreenUtils.getActualSize(780));
        jfxProgressBar.setPrefHeight(ScreenUtils.getActualSize(6));
        Label t = new Label(MessageUtils.Message.APP_TITLE);
        t.setStyle("-fx-font-family: 'shabnam';-fx-font-size: " + ScreenUtils.getActualSize(56));

        ImageView logo = new ImageView("/image/logo.png");
        logo.setFitWidth(ScreenUtils.getActualSize(313));
        logo.setFitHeight(ScreenUtils.getActualSize(333));
        HBox logoWrapper = new HBox(logo);
        logoWrapper.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(logoWrapper,t, jfxProgressBar);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(ScreenUtils.getActualSize(10));
        stackPane.getChildren().addAll(vBox);
        applyFontStyle(stackPane);
        StringJoiner vboxStyle = new StringJoiner(" ; ");
        vboxStyle.add("-fx-background-color: #EFEBE9").add("-fx-background-radius: " + ScreenUtils.getActualSize(50) + " " + ScreenUtils.getActualSize(0))
                .add("-fx-border-radius: " + ScreenUtils.getActualSize(45) + " " + ScreenUtils.getActualSize(0))
                .add("-fx-border-width: " + ScreenUtils.getActualSize(5))
                .add("-fx-border-color: #9E9E9E");
        stackPane.setStyle(vboxStyle.toString());
        Scene startScene = new Scene(stackPane, ScreenUtils.getActualSize(1048), ScreenUtils.getActualSize(580));
        startScene.setFill(Color.TRANSPARENT);

        stage.setScene(startScene);
        stage.show();

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setAutoReverse(false);

        ParallelTransition pt = new ParallelTransition(stackPane, ft);
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
