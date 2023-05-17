package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {

    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize(){
        LoadingThread loadingThread = new LoadingThread(welcomeLabel);
        loadingThread.start();
    }

}
