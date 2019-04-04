package ir.comprehensive;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        Font.loadFont(getClass().getResource("/fonts/shabnam.ttf").toExternalForm(), 16);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setResources(getMessageBundle());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        fxmlLoader.setLocation(getClass().getResource("/fxml/start.fxml"));
        Parent rootNode = fxmlLoader.load();


        Scene scene = new Scene(rootNode, 1024, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }
}
