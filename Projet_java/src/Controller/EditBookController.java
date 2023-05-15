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

    public void resetAuthor(){
        authors.clear();
        initTabViewAuth();
    }

    public void resetDrawer(){
        drawers.clear();
        initTabViewDrawer();
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
                outputMessage.setText("Error : Invalid input");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            outputMessage.setText("Error : Invalid input");
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
        DrawerCBox.getSelectionModel().clearSelection();
        resetAuthor();
        resetDrawer();
    }

    @FXML
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
            if(!compareContributorIn(authors,author)) {
                authors.add(author);
                initTabViewAuth();
            }
        }
    }

    @FXML
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
            if(!compareContributorIn(drawers,drawer)) {
                drawers.add(drawer);
                initTabViewDrawer();
            }
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
