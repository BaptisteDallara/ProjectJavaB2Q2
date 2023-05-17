package Controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

public class MainController {
    @FXML
    private AnchorPane primaryPane;
    @FXML
    private AnchorPane secondaryPane;
    @FXML
    public void initialize(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/View/HomeView.fxml")));
            Parent root = fxmlLoader.load();
            secondaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onHomeClick() {
        changingSecondScene("/View/HomeView.fxml");
    }
    @FXML
    public void onCreateBookClick(){
        changingSecondScene("/View/CreateBook.fxml");
    }

    @FXML
    public void onListBookClick() {
        changingSecondScene("/View/BookListView.fxml");
    }

    @FXML
    public void onSearchAuthorClick() {
        changingSecondScene("/View/ResearchAuthor.fxml");
    }

    @FXML
    public void onSearchSerieClick() {
        changingSecondScene("/View/ResearchSerie.fxml");
    }

    @FXML
    public void onSearchLendingClick() {
        changingSecondScene("/View/ResearchLending.fxml");
    }

    @FXML
    public void onDeleteBookClick() {
        changingSecondScene("/View/DeleteBook.fxml");
    }

    @FXML
    public void onEditBookClick() {
        changingSecondScene("/View/EditBookView.fxml");
    }

    @FXML
    public void onBookUtilsClick() {
        changingSecondScene("/View/BookUtilsView.fxml");
    }
    @FXML
    public void onAddPersonClick() {
        changingSecondScene("/View/CreatePerson.fxml");
    }

    @FXML
    public void onAddExemplarClick() {
        changingSecondScene("/View/CreateExemplar.fxml");
    }

    @FXML
    public void onAddLendingClick() {
        changingSecondScene("/View/CreateLending.fxml");
    }
    @FXML
    public void onReturnClick() {
        changingSecondScene("/View/DeleteLending.fxml");
    }
    @FXML
    public void changingSecondScene(String scene){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scene)));
            Parent root = fxmlLoader.load();
            secondaryPane.getChildren().setAll(root);
            /*Thread thread = new LoadingThread(scene, secondaryPane);
            thread.start();*/
        }
        catch (IOException exception){
            System.out.println("Error : " + exception.getMessage());
        }
    }

    /*
    public Scene loadingScene(String scene) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scene)));
            Parent root = fxmlLoader.load();
            return new Scene(root);
        } catch (IOException exception) {
            System.out.println("Error : " + exception.getMessage());
            throw new IOException();
        }
    }

    @FXML void loadCreateBook(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/View/Loading.fxml")));
            Parent root = fxmlLoader.load();
            secondaryPane.getChildren().setAll(root);
            Task<Scene> loadSceneTask = createLoadSceneTask();
            loadSceneTask.setOnSucceeded(event -> {
                FXMLLoader fxmlLoader1 = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/View/CreateBook.fxml")));
                Parent realScene = null;
                try {
                    realScene = fxmlLoader1.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                secondaryPane.getChildren().setAll(realScene);
            });
            new Thread(loadSceneTask).start();

        } catch (IOException exception){
            System.out.println("Error : " + exception.getMessage());
        }
    }
    private Task<Scene> createLoadSceneTask(){
        return new Task<>() {
            @Override
            protected Scene call() throws Exception {
                return null;
            }
        };
    }
*/

}