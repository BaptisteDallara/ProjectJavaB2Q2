package Controller;


import java.util.ArrayList;

import Business.AuthorManager;
import Business.BookManager;
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

    @FXML
    private Label labelBook;

    @FXML
    private Label labelSerie;

    @FXML
    private Label labelTable;


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
        ArrayList<Contributor> authors = authorManager.getAllAuthor();
        for(Contributor author : authors){
            searchAuthor.getItems().add(author.getFirstName() + " " + author.getLastName());
        }
    }

    @FXML
    public void initSerie(MouseEvent event) {
        if (searchAuthor.getValue() != null){
            labelSerie.setText(null);
            searchSerie.getItems().clear();
            series = authorManager.getAllSeries(searchAuthor.getValue());
            for(Serie serie : series){
                searchSerie.getItems().add(serie.getName());
            }
            series.clear();
        } else {
            labelSerie.setText("Please select an author");
        }
    }

    @FXML
    public void initBook(MouseEvent event){
        if (searchAuthor.getValue() != null){
            labelBook.setText(null);
            searchBook.getItems().clear();
            books = authorManager.getAllBooks(searchAuthor.getValue(), searchSerie.getValue(), bookManager);
            for(Book book : books){
                searchBook.getItems().add(book.getTitle());
            }
            books.clear();
        } else {
            labelBook.setText("Please select an author");
        }
    }


    @FXML
    public void onButtonSearchClicked(ActionEvent event) {
        try {
            if (searchAuthor.getValue() == null || searchBook.getValue() == null){
                labelTable.setText("Please select an author and a book and perhaps a serie"); 
            } else {
                labelTable.setText(null);
                resultTable.getItems().clear();
                tableName.getItems().clear();

                tableNames = new ArrayList<>();
                tableNames.add(new ResultResearch("Book"));
                tableNames.add(new ResultResearch("Edition"));
                tableNames.add(new ResultResearch("Exemplar"));
                resultResearch = authorManager.getSearchBookAuthor(searchAuthor.getValue(), searchSerie.getValue(), searchBook.getValue());
            
                resultSearch.setCellValueFactory(new PropertyValueFactory<>("result"));
                columnName.setCellValueFactory(new PropertyValueFactory<>("result"));
                resultTable.getItems().addAll(resultResearch);
                tableName.getItems().addAll(tableNames);
            }
        } catch (Exception e) {
            labelTable.setText("This book don't have any exemplar");
        }
    }
}
