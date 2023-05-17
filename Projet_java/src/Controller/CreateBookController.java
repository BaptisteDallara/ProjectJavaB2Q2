package Controller;

import Business.BookManager;
import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

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

    @FXML
    private Label outputMessage;

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while initializing the page");
            alert.showAndWait();
        }
    }

    public void addBook(){
        try {
            if (!inputTitle.getText().isEmpty() && !inputRecAge.getText().isEmpty() && !(Integer.parseInt(inputRecAge.getText()) > 99) && !(Integer.parseInt(inputRecAge.getText()) < 1)
                    && !languageCBox.getValue().isEmpty()
                    && !editionCBox.getValue().isEmpty() && !genreCBox.getValue().isEmpty() && !typeCBox.getValue().isEmpty()) {
                    String title = inputTitle.getText();
                    String recAge = inputRecAge.getText();
                    String pubDate = inputPubDate.getValue().toString();
                    Boolean isDiscontinued = isDiscontinuedCheck.isSelected();
                    Genre genre1 = bookManager.getGenre(genreCBox.getValue());
                    Type type1 = bookManager.getType(typeCBox.getValue());
                    Serie serie1 = bookManager.getSerie(serieCBox.getValue());
                    Language language1 = bookManager.getLanguage(languageCBox.getValue());
                    Edition edition1 = bookManager.getEdition(editionCBox.getValue());
                    Book book = new Book(title, LocalDate.parse(pubDate), parseInt(recAge), isDiscontinued, genre1, type1, language1, serie1, edition1);
                    for (Contributor author : authors) {
                        book.addAuthor(author);
                    }
                    for (Contributor drawer : drawers) {
                        book.addDrawer(drawer);
                    }
                    bookManager.addBook(book);
                    outputMessage.setText("Success !");
                    clearAllSelection();
                }
                else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.showAndWait();
            }
        }
        catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while adding book");
            alert.showAndWait();
        }
    }

    public void clearAllSelection(){
        inputTitle.clear();
        inputRecAge.clear();
        inputPubDate.setValue(null);
        isDiscontinuedCheck.setSelected(false);
        authors.clear();
        drawers.clear();
        initTabViewAuth();
        initTabViewDrawer();
        languageCBox.getSelectionModel().clearSelection();
        editionCBox.getSelectionModel().clearSelection();
        genreCBox.getSelectionModel().clearSelection();
        typeCBox.getSelectionModel().clearSelection();
        serieCBox.getSelectionModel().clearSelection();
        authorCBox.getSelectionModel().clearSelection();
        DrawerCBox.getSelectionModel().clearSelection();
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
        try {
            String authorName = authorCBox.getValue();
            String[] authorNameSplit = authorName.split(" ");
            String firstName = authorNameSplit[0];
            String lastName = authorNameSplit[1];
            if(authorNameSplit.length > 2 && authorNameSplit[2] != null){
                lastName += " " + authorNameSplit[2];
            }
            Contributor author = bookManager.searchContributor(firstName, lastName, "Author");
            if(!compareContributorIn(authors,author)) {
                authors.add(author);
                initTabViewAuth();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while adding author");
            alert.showAndWait();
        }
    }

    public void addDrawer(){
        try {
            String drawerName = DrawerCBox.getValue();
            String[] drawerNameSplit = drawerName.split(" ");
            String firstName = drawerNameSplit[0];
            String lastName = drawerNameSplit[1];
            if(drawerNameSplit.length > 2 &&drawerNameSplit[2] != null){
                lastName += " " + drawerNameSplit[2];
            }
            Contributor drawer = bookManager.searchContributor(firstName, lastName, "Drawer");
            if(!compareContributorIn(drawers,drawer)) {
                drawers.add(drawer);
                initTabViewDrawer();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while adding drawer");
            alert.showAndWait();
        }
    }

    public Boolean compareContributorIn(ArrayList<Contributor> contributors, Contributor contributor){
        for(Contributor contributor1 : contributors){
            if(contributor1.getPersonId() == contributor.getPersonId()){
                return true;
            }
        }
        return false;
    }

    public void resetAuthor(){
        authors.clear();
        initTabViewAuth();
    }

    public void resetDrawer(){
        drawers.clear();
        initTabViewDrawer();
    }

    public void initCBoxAuthor(){
        try {
            ArrayList<Contributor> contributors = bookManager.showAuthor();
            for(Contributor contributor : contributors){
                authorCBox.getItems().add(contributor.getFirstName() + " " + contributor.getLastName());
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while initializing author");
            alert.showAndWait();
        }
    }

    public void initCBoxDrawer(){
        try {
            ArrayList<Contributor> contributors = bookManager.showDrawer();
            for(Contributor contributor : contributors){
                DrawerCBox.getItems().add(contributor.getFirstName() + " " + contributor.getLastName());
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while initializing drawer");
            alert.showAndWait();
        }
    }

    public void initCBoxSerie(){
        try {
            ArrayList<Serie> series = bookManager.showSerie();
            for(Serie serie : series){
                serieCBox.getItems().add(serie.getName());
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while initializing serie");
            alert.showAndWait();
        }
    }

    public void initCBoxEdition(){
        try {
            ArrayList<Edition> editions = bookManager.showEdition();
            for(Edition edition : editions){
                editionCBox.getItems().add(edition.getName());
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while initializing edition");
            alert.showAndWait();
        }
    }

    public void initCBoxLanguage(){
        try {
            ArrayList<Language> languages = bookManager.showLanguage();
            for(Language language : languages){
                languageCBox.getItems().add(language.getName());
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while initializing language");
            alert.showAndWait();
        }
    }

    public void initCBoxGenre(){
        try {
            ArrayList<Genre> genres = bookManager.showGenre();
            for(Genre genre : genres){
                genreCBox.getItems().add(genre.getName());
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while initializing genre");
            alert.showAndWait();
        }
    }

    public void initCBoxType(){
        try {
            ArrayList<Type> types = bookManager.showType();
            for(Type type : types){
                typeCBox.getItems().add(type.getName());
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while initializing type");
            alert.showAndWait();
        }
    }
}