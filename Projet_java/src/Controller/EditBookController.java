package Controller;

import Business.BookManager;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class EditBookController {

    @FXML
    private ComboBox<String> DrawerCBox;

    @FXML
    private ComboBox<String> authorCBox;

    @FXML
    private ComboBox<String> bookSelected;

    @FXML
    private Button btnAddAuthor;

    @FXML
    private Button btnAddDrawer;

    @FXML
    private Button btnEditBook;

    @FXML
    private TableColumn<Contributor, String> columnAuthorFName;
    @FXML
    private TableColumn<Contributor, String> columnAuthorLName;

    @FXML
    private TableColumn<Contributor, String> columnDrawerFName;
    @FXML
    private TableColumn<Contributor, String> columnDrawerLName;

    @FXML
    private ComboBox<String> editionCBox;

    @FXML
    private ComboBox<String> genreCBox;

    @FXML
    private DatePicker inputPubDate;

    @FXML
    private TextField inputRecAge;

    @FXML
    private TextField inputTitle;

    @FXML
    private CheckBox isDiscontinuedCheck;

    @FXML
    private ComboBox<String> languageCBox;

    @FXML
    private ComboBox<String> serieCBox;

    @FXML
    private TableView<Contributor> tableViewAuth;

    @FXML
    private TableView<Contributor> tableViewDraw;

    @FXML
    private ComboBox<String> typeCBox;

    private ArrayList<Contributor> authors = new ArrayList<>();
    private ArrayList<Contributor> drawers = new ArrayList<>();
    private BookManager bookManager;

    @FXML
    public void initialize(){
        bookManager = new BookManager();
        initCBoxBookSelected();
        bookSelected.setOnAction(event -> initForms());
    }

    @FXML
    public void initForms(){
        Book book = bookManager.getBook(bookSelected.getValue());
        inputTitle.setText(book.getTitle());
        inputPubDate.setValue(book.getPublicationDate());
        inputRecAge.setText(book.getRecommendedAge().toString());
        isDiscontinuedCheck.setSelected(book.getDiscontinued());
        languageCBox.setValue(book.getOriginalLanguage().getName());
        editionCBox.setValue(book.getEdition().getName());
        genreCBox.setValue(book.getGenre().getName());
        if(book.getSerie() != null){
            serieCBox.setValue(book.getSerie().getName());
        }
        typeCBox.setValue(book.getType().getName());
        authors = book.getAuthors();
        drawers = book.getDrawers();
        initTabViewAuth();
        initTabViewDrawer();
    }
    public void initTabViewAuth(){
        tableViewAuth.getItems().clear();
        columnAuthorFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnAuthorLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableViewAuth.getItems().setAll(authors);
    }

    public void initTabViewDrawer(){
        tableViewDraw.getItems().clear();
        columnDrawerFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnDrawerLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableViewDraw.getItems().setAll(drawers);
    }

    @FXML
    public void initCBoxBookSelected(){
        bookSelected.getItems().clear();
        for(Book book : bookManager.getAllBook()){
            bookSelected.getItems().add(book.getTitle());
        }
    }
    public void initCBoxAuthor(){
        ArrayList<Contributor> contributors = bookManager.showAuthor();
        for(Contributor contributor : contributors){
            authorCBox.getItems().add(contributor.getFirstName() + " " + contributor.getLastName());
        }
    }

    public void initCBoxDrawer(){
        ArrayList<Contributor> contributors = bookManager.showDrawer();
        for(Contributor contributor : contributors){
            DrawerCBox.getItems().add(contributor.getFirstName() + " " + contributor.getLastName());
        }
    }

    @FXML
    void addAuthor(ActionEvent event) {

    }

    @FXML
    void addDrawer(ActionEvent event) {

    }

}
