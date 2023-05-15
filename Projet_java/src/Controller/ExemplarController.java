package Controller;

import Business.BookManager;
import Business.ExemplarManager;
import Model.Book;
import Model.Exemplar;
import Model.Language;
import Model.Status;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ExemplarController {

    @FXML
    private ComboBox<String> bookCBox;

    @FXML
    private TableColumn<Exemplar, String> bookColumn;

    @FXML
    private TextField inputLinePos;

    @FXML
    private TextField inputNbPages;

    @FXML
    private TextField inputPrice;

    @FXML
    private TextField inputPriceLending;

    @FXML
    private TextField inputRackPos;

    @FXML
    private TextField inputRoomPos;

    @FXML
    private ComboBox<String> languageCBox;

    @FXML
    private TableColumn<Exemplar, String> languageColumn;

    @FXML
    private TableColumn<Exemplar, String> pageColumn;

    @FXML
    private TableColumn<Exemplar, String> positionColumn;

    @FXML
    private TableColumn<Exemplar, String> priceColumn;

    @FXML
    private ComboBox<String> stateCBox;

    @FXML
    private TableView<Exemplar> tabViewExemplar;

    private ExemplarManager exemplarManager;

    private BookManager bookManager;

    @FXML
    public void initialize(){
        bookManager = new BookManager();
        exemplarManager = new ExemplarManager();
        initBookCBox();
        initStateCBox();
        initLanguageCBox();
        initTabViewExemplar();
    }

    public void initTabViewExemplar(){
        tabViewExemplar.getItems().clear();
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("languageName"));
        pageColumn.setCellValueFactory(new PropertyValueFactory<>("nbPages"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tabViewExemplar.getItems().addAll(exemplarManager.getAllExemplar()); }

    public void initLanguageCBox(){
        ArrayList<Language> languages = bookManager.showLanguage();
        for(Language language : languages){
            languageCBox.getItems().add(language.getName());
        }
    }

    public void initStateCBox(){
        ArrayList<Status> states = exemplarManager.getAllStatus();
        for(Status state : states){
            stateCBox.getItems().add(state.getName());
        }
    }

    public void initBookCBox(){
        ArrayList<Book> books = bookManager.getAllBook();
        for(Book book : books){
            bookCBox.getItems().add(book.getTitle());
        }
    }

    @FXML
    void onDeleteExemplarClick() {
        Exemplar exemplar = tabViewExemplar.getSelectionModel().getSelectedItem();
        exemplarManager.deleteExemplar(exemplar);
        initTabViewExemplar();
    }

}
