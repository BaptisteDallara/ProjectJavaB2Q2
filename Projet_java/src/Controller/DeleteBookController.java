package Controller;

import Business.BookManager;
import Model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
        bookManager = new BookManager();
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        editionColumn.setCellValueFactory(new PropertyValueFactory<>("editionName"));
        SerieColumn.setCellValueFactory(new PropertyValueFactory<>("serieName"));
        authorNameColumn.setCellValueFactory(new PropertyValueFactory<>("authorList"));
        DrawerNameColumn.setCellValueFactory(new PropertyValueFactory<>("drawerList"));
        tabViewBook.getItems().addAll(bookManager.getAllBook());
    }

    public void onDeleteClick(){
        Book book = tabViewBook.getSelectionModel().getSelectedItem();
        bookManager.deleteBook(book);
        tabViewBook.getItems().remove(book);
    }

}
