package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class LoadingThread extends Thread{
    private String scene;
    private AnchorPane secondaryPane;

    public LoadingThread(String scene, AnchorPane secondaryPane) {
        this.scene = scene;
        this.secondaryPane = secondaryPane;
    }

    @Override
    public void run() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scene)));
            Parent root = fxmlLoader.load();
            secondaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
