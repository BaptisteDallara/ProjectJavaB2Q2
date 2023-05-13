package Controller;

import Business.BookManager;
import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreateBookController {

    @FXML
    private ComboBox<String> editionCBox;

    @FXML
    private TableView<Contributor> tableViewAuth;
    @FXML
    private TableView<Contributor> tableViewDraw;
    @FXML
    private ComboBox<String> genreCBox;

    @FXML
    private TableColumn<Contributor, String> columnAuthorFName;
    @FXML
    private TableColumn<Contributor, String> columnAuthorLName;

    @FXML
    private TableColumn<Contributor, String> columnDrawerFName;
    @FXML
    private TableColumn<Contributor, String> columnDrawerLName;
    @FXML
    private ComboBox<String> DrawerCBox;

    @FXML
    private ComboBox<String> authorCBox;

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

    private ArrayList<Contributor> authors = new ArrayList<>();
    private ArrayList<Contributor> drawers = new ArrayList<>();
    private BookManager bookManager;

    public void initialize(){
        try {
            bookManager = new BookManager();
            initCBoxLanguage();
            initCBoxEdition();
            initCBoxGenre();
            initCBoxType();
            initCBoxSerie();
            initCBoxAuthor();
            initCBoxDrawer();
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addBook(){
        String title = inputTitle.getText();
        String recAge = inputRecAge.getText();
        String pubDate = inputPubDate.getValue().toString();
        String language = languageCBox.getValue();
        String edition = editionCBox.getValue();
        String genre = genreCBox.getValue();
        String type = typeCBox.getValue();
        String serie = serieCBox.getValue();
        Boolean isDiscontinued = isDiscontinuedCheck.isSelected();
        if(title != null && recAge != null && pubDate != null && language != null && edition != null && genre != null && type != null){
            Genre genre1 = bookManager.getGenre(genre);
            Type type1 = bookManager.getType(type);
            Serie serie1 = bookManager.getSerie(serie);
            Language language1 = bookManager.getLanguage(language);
            Edition edition1 = bookManager.getEdition(edition);
            Book book = new Book(title, LocalDate.parse(pubDate),Integer.parseInt(recAge),isDiscontinued, genre1, type1,language1,serie1,edition1);
            for(Contributor author : authors){
                book.addAuthor(author);
            }
            for(Contributor drawer : drawers){
                book.addDrawer(drawer);
            }
            bookManager.addBook(book);
            System.out.println("livre ajouté avec succès");
        }
    }


    public void initTabViewAuth(){
        columnAuthorFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnAuthorLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableViewAuth.getItems().setAll(authors);
    }

    public void initTabViewDrawer(){
        columnDrawerFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnDrawerLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableViewDraw.getItems().setAll(drawers);
    }
    public void addAuthor(){
        String authorName = authorCBox.getValue();
        if(authorName != null) {
            String[] authorNameSplit = authorName.split(" ");
            String firstName = authorNameSplit[0];
            String lastName = authorNameSplit[1];
            if(authorNameSplit.length > 2 && authorNameSplit[2] != null){
                lastName += " " + authorNameSplit[2];
            }
            Contributor author = bookManager.searchContributor(firstName, lastName, "Author");
            if(!authors.contains(author)) {
                authors.add(author);
                initTabViewAuth();
            }
        }
    }

    public void addDrawer(){
        String drawerName = DrawerCBox.getValue();
        if(drawerName != null) {
            String[] drawerNameSplit = drawerName.split(" ");
            String firstName = drawerNameSplit[0];
            String lastName = drawerNameSplit[1];
            if(drawerNameSplit.length > 2 &&drawerNameSplit[2] != null){
                lastName += " " + drawerNameSplit[2];
            }
            Contributor drawer = bookManager.searchContributor(firstName, lastName, "Drawer");
            if(!drawers.contains(drawer)) {
                drawers.add(drawer);
                initTabViewDrawer();
            }
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

    public void initCBoxSerie(){
        ArrayList<Serie> series = bookManager.showSerie();
        for(Serie serie : series){
            serieCBox.getItems().add(serie.getName());
        }
    }

    public void initCBoxEdition(){
        ArrayList<Edition> editions = bookManager.showEdition();
        for(Edition edition : editions){
            editionCBox.getItems().add(edition.getName());
        }
    }

    public void initCBoxLanguage(){
        ArrayList<Language> languages = bookManager.showLanguage();
        for(Language language : languages){
            languageCBox.getItems().add(language.getName());
        }
    }

    public void initCBoxGenre(){
        ArrayList<Genre> genres = bookManager.showGenre();
        for(Genre genre : genres){
            genreCBox.getItems().add(genre.getName());
        }
    }

    public void initCBoxType(){
        ArrayList<Type> types = bookManager.showType();
        for(Type type : types){
            typeCBox.getItems().add(type.getName());
        }
    }

}