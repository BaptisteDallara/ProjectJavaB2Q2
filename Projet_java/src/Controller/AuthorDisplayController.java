package Controller;


import java.util.ArrayList;
import javafx.fxml.FXML;

import Business.*;
import javafx.scene.control.*;
import Model.*;

import javafx.event.*;
import javafx.scene.input.MouseEvent;

public class AuthorDisplayController {

    @FXML
    private TableColumn<Book, String> resultSearch;

    @FXML
    private ComboBox<String> searchAuthor;

    @FXML
    private ComboBox<String> searchSerie;


    @FXML
    private ComboBox<String> searchBook;

    @FXML
    private TableColumn<Book, String> tableName;

    private AuthorManager authorManager;
    private BookManager bookManager;
    private ArrayList<Serie> series;
    private ArrayList<Book> books;

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
        searchSerie.getItems().clear();
        series = authorManager.getAllSeries(searchAuthor.getValue());
        for(Serie serie : series){
            searchSerie.getItems().add(serie.getName());
        }
    }

    @FXML
    public void initBook(MouseEvent event){
        searchBook.getItems().clear();
        books = authorManager.getAllBooks(searchAuthor.getValue(), searchSerie.getValue(), bookManager);
        for(Book book : books){
            searchBook.getItems().add(book.getTitle());
        }
        books.clear();
    }


    @FXML
    public void onButtonSearchClicked(ActionEvent event) {
        /*try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "SELECT * FROM person WHERE personType = 'Author' AND lastName = ? AND firstName = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, authorLName.getText());
            statement.setString(2, authorFName.getText());
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                Contributor author = new Contributor( data.getString("firstName"), data.getString("lastName"));
                author.setPersonId(data.getInt("personId"));
                System.out.println(author.getFirstName());
                System.out.println(author.getLastName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
    }
}
