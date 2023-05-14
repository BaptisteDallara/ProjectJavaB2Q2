package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.Objects;

public class MainController {
    @FXML
    private AnchorPane primaryPane;
    @FXML
    private AnchorPane secondaryPane;

    @FXML
    public void onHomeClick() {
        changingSecondScene("../View/HomeView.fxml");
    }
    @FXML

    public void onCreateBookClick(){
        changingSecondScene("../View/CreateBook.fxml");
    }

    @FXML
    public void onListBookClick() {
        changingSecondScene("../View/BookListView.fxml");
    }

    @FXML
    public void onSearchAuthorClick() {
        changingSecondScene("../View/ResearchAuthor.fxml");
    }

    @FXML
    public void onDeleteBookClick() {
        changingSecondScene("../View/DeleteBook.fxml");
    }

    @FXML
    public void changingSecondScene(String scene){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(scene)));
            Parent root = fxmlLoader.load();
            secondaryPane.getChildren().setAll(root);

        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

}