package Controller;

import Buisness.BookManager;
import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class CreateBookController {

    @FXML
    private ComboBox<String> editionCBox;

    @FXML
    private ComboBox<String> genreCBox;

    @FXML
    private ComboBox<Contributor> DrawerCBox;

    @FXML
    private ComboBox<Contributor> authorCBox;

    @FXML
    private Button btnAddAuthor;

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnAddDrawer;

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
    private ComboBox<String> typeCBox;

    public void initialize(){
        try {
            BookManager bookManager = new BookManager();
            initCBoxLanguage(bookManager);
            initCBoxEdition(bookManager);
            initCBoxGenre(bookManager);
            initCBoxType(bookManager);
            initCBoxSerie(bookManager);
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void initCBoxSerie(BookManager bookManager){
        ArrayList<Serie> series = bookManager.showSerie();
        for(Serie serie : series){
            serieCBox.getItems().add(serie.getName());
        }
    }

    public void initCBoxEdition(BookManager bookManager){
        ArrayList<Edition> editions = bookManager.showEdition();
        for(Edition edition : editions){
            editionCBox.getItems().add(edition.getName());
        }
    }

    public void initCBoxLanguage(BookManager bookManager){
        ArrayList<Language> languages = bookManager.showLanguage();
        for(Language language : languages){
            languageCBox.getItems().add(language.getName());
        }
    }

    public void initCBoxGenre(BookManager bookManager){
        ArrayList<Genre> genres = bookManager.showGenre();
        for(Genre genre : genres){
            genreCBox.getItems().add(genre.getName());
        }
    }

    public void initCBoxType(BookManager bookManager){
        ArrayList<Type> types = bookManager.showType();
        for(Type type : types){
            typeCBox.getItems().add(type.getName());
        }
    }

}