package Controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class LoadingThread extends Thread{
    private Label welcomeText;
    private Integer nbCaractLoad;

    public LoadingThread(Label welcomeText) {
        this.welcomeText = welcomeText;
        nbCaractLoad = 0;
    }

    @Override
        public void run() {
            try {
                String text = " Welcome";
                for(int i = 0; i < text.length() -1;i++){
                    Thread.sleep(500);
                    Platform.runLater(() -> welcomeText.setText(welcomeText.getText() + text.charAt(nbCaractLoad)));
                    nbCaractLoad++;
                }
            } catch (InterruptedException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error while loading the welcome text");
                alert.showAndWait();
            }
    }
}
