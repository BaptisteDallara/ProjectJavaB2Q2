package Controller;


import java.util.ArrayList;

import Business.AuthorManager;
import Business.BookManager;
import Exception.ExceptionSQL;
import javafx.fxml.FXML;


import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.*;

import javafx.event.*;
import javafx.scene.input.MouseEvent;

public class AuthorDisplayController {

    @FXML
    private TableView<ResultResearch> resultTable;

    @FXML
    private TableView<ResultResearch> tableName;

    @FXML
    private TableColumn<ResultResearch, String> resultSearch;

    @FXML
    private TableColumn<ResultResearch, String> columnName;

    @FXML
    private ComboBox<String> searchAuthor;

    @FXML
    private ComboBox<String> searchSerie;

    @FXML
    private ComboBox<String> searchBook;

    private AuthorManager authorManager;
    private BookManager bookManager;
    private ArrayList<Serie> series;
    private ArrayList<Book> books;
    private ArrayList<ResultResearch> resultResearch;
    private ArrayList<ResultResearch> tableNames;

    @FXML
    public void initialize(){
        authorManager = new AuthorManager();
        bookManager = new BookManager();
        initCBoxAuthor();
    }

    @FXML
    public void initCBoxAuthor(){
        ArrayList<Contributor> authors;
        try {
            authors = authorManager.getAllAuthor();
            for(Contributor author : authors){
                searchAuthor.getItems().add(author.getFirstName() + " " + author.getLastName());
            }
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while loading authors");
            alert.showAndWait();
        }
    }

    @FXML
    public void initSerie(MouseEvent event) {
        try {
            searchSerie.getItems().clear();
            series = authorManager.getAllSeries(searchAuthor.getValue());
            for(Serie serie : series){
                searchSerie.getItems().add(serie.getName());
            }
            series.clear();
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Please select an author");
            alert.showAndWait();
        }
    }

    @FXML
    public void initBook(MouseEvent event){
        try {
            searchBook.getItems().clear();
            books = authorManager.getAllBooks(searchAuthor.getValue(), searchSerie.getValue(), bookManager);
            for(Book book : books){
                searchBook.getItems().add(book.getTitle());
            }
            books.clear();
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Please select at least an author");
            alert.showAndWait();
        }
    }


    @FXML
    public void onAuthorClicked(MouseEvent event) {
        try {
            searchSerie.getItems().clear();
            searchBook.getItems().clear();
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("There was a problem while clearing the serie and book comboboxes");
            alert.showAndWait();
        }
    }

    @FXML
    public void onButtonSearchClicked(ActionEvent event) {
        try {
            resultTable.getItems().clear();
            tableName.getItems().clear();

            tableNames = new ArrayList<>();
            tableNames.add(new ResultResearch("Edition"));
            tableNames.add(new ResultResearch("Exemplar"));
            tableNames.add(new ResultResearch("Book"));
            resultResearch = authorManager.getSearchBookAuthor(searchAuthor.getValue(), searchSerie.getValue(), searchBook.getValue());
            
            resultSearch.setCellValueFactory(new PropertyValueFactory<>("result"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("result"));
            resultTable.getItems().addAll(resultResearch);
            tableName.getItems().addAll(tableNames);
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Please select at least an author and a book");
            alert.showAndWait();
        }
    }
}
