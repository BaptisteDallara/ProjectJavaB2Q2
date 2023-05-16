package Controller;

import Business.BookManager;
import Model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class DeleteBookController {
    @FXML
    private TableColumn<Book, String> DrawerNameColumn;

    @FXML
    private TableColumn<Book, String> SerieColumn;

    @FXML
    private TableColumn<Book, String> authorNameColumn;

    @FXML
    private TableColumn<Book, String> editionColumn;

    @FXML
    private TableView<Book> tabViewBook;

    @FXML
    private TableColumn<?, ?> titleColumn;
    @FXML
    private Button btnDeleteBook;

    private BookManager bookManager;

    public void initialize(){
        try {
            bookManager = new BookManager();
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            editionColumn.setCellValueFactory(new PropertyValueFactory<>("editionName"));
            SerieColumn.setCellValueFactory(new PropertyValueFactory<>("serieName"));
            authorNameColumn.setCellValueFactory(new PropertyValueFactory<>("authorList"));
            DrawerNameColumn.setCellValueFactory(new PropertyValueFactory<>("drawerList"));
            tabViewBook.getItems().addAll(bookManager.getAllBook());
        } catch (Exception exception) {
            Alert  alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error when loading the books");
            alert.showAndWait();
        }
    }

    public void onDeleteClick(){
        try {
            Book book = tabViewBook.getSelectionModel().getSelectedItem();
            bookManager.deleteBook(book);
            tabViewBook.getItems().remove(book);
        } catch (Exception exception) {
            Alert  alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error when deleting the book");
            alert.showAndWait();
        }
    }
}
