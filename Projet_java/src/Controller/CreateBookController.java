package Controller;

import Business.BookManager;
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
    private ComboBox<String> DrawerCBox;

    @FXML
    private ComboBox<String> authorCBox;

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
            initCBoxAuthor(bookManager);
            initCBoxDrawer(bookManager);
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void initCBoxAuthor(BookManager bookManager){
        ArrayList<Contributor> contributors = bookManager.showAuthor();
        for(Contributor contributor : contributors){
            authorCBox.getItems().add(contributor.getFirstName() + " " + contributor.getLastName());
        }
    }

    public void initCBoxDrawer(BookManager bookManager){
        ArrayList<Contributor> contributors = bookManager.showDrawer();
        for(Contributor contributor : contributors){
            DrawerCBox.getItems().add(contributor.getFirstName() + " " + contributor.getLastName());
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