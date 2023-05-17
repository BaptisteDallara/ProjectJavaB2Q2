package Controller;

import Business.BookManager;
import Model.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class EditBookController {

    @FXML
    private ComboBox<String> drawerCBox;

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
    @FXML
    private Label outputMessage;

    private ArrayList<Contributor> authors = new ArrayList<>();
    private ArrayList<Contributor> drawers = new ArrayList<>();
    private BookManager bookManager;

    @FXML
    public void initialize(){
        bookManager = new BookManager();
        initCBoxBookSelected();
        bookSelected.setOnAction(event -> initForms());
        initCBoxAuthor();
        initCBoxDrawer();
        initCBoxEdition();
        initCBoxSerie();
        initCBoxLanguage();
        initCBoxGenre();
        initCBoxType();
    }

    @FXML
    public void initForms(){
        try {
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
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error while loading book");
            alert.showAndWait();
        }
    }

    public void resetAuthor(){
        authors.clear();
        initTabViewAuth();
    }

    public void resetDrawer(){
        drawers.clear();
        initTabViewDrawer();
    }
    public void initCBoxSerie(){
        try {
            ArrayList<Serie> series = bookManager.showSerie();
            for (Serie serie : series) {
                serieCBox.getItems().add(serie.getName());
            }
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error while loading series");
            alert.showAndWait();
        }
    }

    public void initCBoxEdition(){
        try {
            ArrayList<Edition> editions = bookManager.showEdition();
            for (Edition edition : editions) {
                editionCBox.getItems().add(edition.getName());
            }
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error while loading editions");
            alert.showAndWait();
        }
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
        try {
            bookSelected.getItems().clear();
            for (Book book : bookManager.getAllBook()) {
                bookSelected.getItems().add(book.getTitle());
            }
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error while loading books");
            alert.showAndWait();
        }
    }

    public void initCBoxAuthor(){
        try {
            ArrayList<Contributor> contributors = bookManager.showAuthor();
            for (Contributor contributor : contributors) {
                authorCBox.getItems().add(contributor.getFirstName() + " " + contributor.getLastName());
            }
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error while loading authors");
            alert.showAndWait();
        }
    }

    public void initCBoxDrawer(){
        try {
            ArrayList<Contributor> contributors = bookManager.showDrawer();
            System.out.println(contributors.get(0).getLastName());
            for (Contributor contributor : contributors) {
                drawerCBox.getItems().add(contributor.getFirstName() + " " + contributor.getLastName());
            }
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error while loading drawers");
            alert.showAndWait();
        }
    }

    public void initCBoxLanguage(){
        try {
            ArrayList<Language> languages = bookManager.showLanguage();
            for (Language language : languages) {
                languageCBox.getItems().add(language.getName());
            }
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error while loading languages");
            alert.showAndWait();
        }
    }

    public void initCBoxGenre(){
        try {
            ArrayList<Genre> genres = bookManager.showGenre();
            for (Genre genre : genres) {
                genreCBox.getItems().add(genre.getName());
            }
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error while loading genres");
            alert.showAndWait();
        }
    }

    public void initCBoxType(){
        try {
            ArrayList<Type> types = bookManager.showType();
            for (Type type : types) {
                typeCBox.getItems().add(type.getName());
            }
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error while loading types");
            alert.showAndWait();
        }
    }
    @FXML
    public void onEditBtnClick(){
        try {
            Book book = bookManager.getBook(bookSelected.getValue());
            if (!inputTitle.getText().isEmpty() && !inputRecAge.getText().isEmpty() && !(parseInt(inputRecAge.getText()) > 99) && !(parseInt(inputRecAge.getText()) < 1) && !languageCBox.getValue().isEmpty()
                    && !editionCBox.getValue().isEmpty() && !genreCBox.getValue().isEmpty() && !typeCBox.getValue().isEmpty()) {
                book.setTitle(inputTitle.getText());
                book.setPublicationDate(inputPubDate.getValue());
                book.setRecommendedAge(parseInt(inputRecAge.getText()));
                book.setDiscontinued(isDiscontinuedCheck.isSelected());
                book.setOriginalLanguage(bookManager.getLanguage(languageCBox.getValue()));
                book.setEdition(bookManager.getEdition(editionCBox.getValue()));
                book.setGenre(bookManager.getGenre(genreCBox.getValue()));
                if (serieCBox.getValue() != null) {
                    book.setSerie(bookManager.getSerie(serieCBox.getValue()));
                }
                book.setType(bookManager.getType(typeCBox.getValue()));
                book.setAuthors(authors);
                book.setDrawers(drawers);
                bookManager.updateBook(book);
                outputMessage.setText("Success");
                clearAllSelection();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Invalid input");
                alert.showAndWait();
            }
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error while editing book");
            alert.showAndWait();
        }
    }

    public void clearAllSelection(){
        bookSelected.getSelectionModel().clearSelection();
        inputTitle.clear();
        inputRecAge.clear();
        inputPubDate.setValue(null);
        isDiscontinuedCheck.setSelected(false);
        languageCBox.getSelectionModel().clearSelection();
        editionCBox.getSelectionModel().clearSelection();
        genreCBox.getSelectionModel().clearSelection();
        serieCBox.getSelectionModel().clearSelection();
        typeCBox.getSelectionModel().clearSelection();
        authorCBox.getSelectionModel().clearSelection();
        drawerCBox.getSelectionModel().clearSelection();
        resetAuthor();
        resetDrawer();
    }

    @FXML
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
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error while adding author");
            alert.showAndWait();
        }
    }

    @FXML
    public void addDrawer(){
        try {
            String drawerName = drawerCBox.getValue();
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
            alert.setTitle("Error Dialog");
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

}
