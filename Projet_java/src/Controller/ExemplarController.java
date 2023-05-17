package Controller;

import Business.BookManager;
import Business.ExemplarManager;
import Model.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

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
    private Label outputMessage;

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

    @FXML
    public void addExemplar() {
        try {
            if(!bookCBox.getValue().isEmpty() && !languageCBox.getValue().isEmpty() && !stateCBox.getValue().isEmpty() &&
                    !inputNbPages.getText().isEmpty() && !inputPrice.getText().isEmpty() && !inputPriceLending.getText().isEmpty() &&
                    !inputRoomPos.getText().isEmpty() && !inputRackPos.getText().isEmpty() && !inputLinePos.getText().isEmpty()
                    && !(Integer.parseInt(inputNbPages.getText()) < 0) && !(Double.parseDouble(inputPrice.getText()) < 0) &&
                    !(Double.parseDouble(inputPriceLending.getText()) < 0) && !(Integer.parseInt(inputRoomPos.getText()) < 0) &&
                    !(Integer.parseInt(inputRackPos.getText()) < 0) && !(Integer.parseInt(inputLinePos.getText()) < 0)){
                Book book = bookManager.getBook(bookCBox.getValue());
                Language language = bookManager.getLanguage(languageCBox.getValue());
                Status status = new Status(stateCBox.getValue());
                Storage position = exemplarManager.getPosition(parseInt(inputRoomPos.getText()), parseInt(inputRackPos.getText()),
                        parseInt(inputLinePos.getText()));
                Exemplar exemplar = new Exemplar(book, language, parseInt(inputNbPages.getText()), parseDouble(inputPrice.getText()),
                        parseDouble(inputPriceLending.getText()), status, position);
                exemplarManager.addExemplar(exemplar);
                outputMessage.setText("Success");
                initTabViewExemplar();
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Input incorrect");
                alert.showAndWait();
            }
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while adding exemplar");
            alert.showAndWait();
        }
    }

    public void initTabViewExemplar() {
        try {
            tabViewExemplar.getItems().clear();
            bookColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
            languageColumn.setCellValueFactory(new PropertyValueFactory<>("languageName"));
            pageColumn.setCellValueFactory(new PropertyValueFactory<>("nbPages"));
            positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            tabViewExemplar.getItems().addAll(exemplarManager.getAllExemplar()); 
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while initializing exemplar table");
            alert.showAndWait();
        }
        
    }

    public void initLanguageCBox(){
        try {
            ArrayList<Language> languages = bookManager.showLanguage();
            for (Language language : languages) {
                languageCBox.getItems().add(language.getName());
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while initializing language combobox");
            alert.showAndWait();
        }
    }

    public void initStateCBox(){
        try {
            ArrayList<Status> states = exemplarManager.getAllStatus();
            for (Status state : states) {
                stateCBox.getItems().add(state.getName());
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while initializing state combobox");
            alert.showAndWait();
        }
    }

    public void initBookCBox(){
        try {
            ArrayList<Book> books = bookManager.getAllBook();
            for (Book book : books) {
                bookCBox.getItems().add(book.getTitle());
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while initializing book combobox");
            alert.showAndWait();
        }
    }

    @FXML
    public void onDeleteExemplarClick() {
        try {
            Exemplar exemplar = tabViewExemplar.getSelectionModel().getSelectedItem();
            exemplarManager.deleteExemplar(exemplar);
            initTabViewExemplar();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while deleting exemplar");
            alert.showAndWait();
        }
    }
}
