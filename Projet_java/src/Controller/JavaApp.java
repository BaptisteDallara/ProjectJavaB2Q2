package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaApp.class.getResource("../View/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 550);
        stage.setTitle("My Library");
        Image logo = new Image("View/icon/livre.png");
        stage.getIcons().add(logo);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop() throws Exception {
        super.stop();
    }
    public static void main(String[] args) {
        launch();
    }
}