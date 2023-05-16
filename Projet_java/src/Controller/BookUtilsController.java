package Controller;

import Business.BookManager;
import Business.UtilsManager;
import Model.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class BookUtilsController {

    @FXML
    private ComboBox<String> countryCBox;

    @FXML
    private TextField inputEditionName;

    @FXML
    private TextField inputSerieName;

    @FXML
    private Label outputEditionMessage;

    @FXML
    private Label outputSerieMessage;

    @FXML
    private TableColumn<?, ?> editionColumn;
    @FXML
    private TableColumn<?, ?> serieColumn;

    @FXML
    private TableView<Edition> tableViewEdition;

    @FXML
    private TableView<Serie> tableViewSerie;

    private UtilsManager utilsManager;
    private BookManager bookManager;

    @FXML
    public void initialize(){
        utilsManager = new UtilsManager();
        bookManager = new BookManager();
        initCBoxCountry();
        initTableViewEdition();
        initTableViewSerie();
    }

    public void initTableViewEdition(){
        try {
            tableViewEdition.getItems().clear();
            editionColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableViewEdition.getItems().addAll(bookManager.showEdition());
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error when loading the editions");
            alert.showAndWait();
        }
    }

    public void initTableViewSerie(){
        try {
            tableViewSerie.getItems().clear();
            serieColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableViewSerie.getItems().addAll(bookManager.showSerie());
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error when loading the series");
            alert.showAndWait();
        }
    }

    public void onDeleteEditionClick(){
        try {
            Edition edition = tableViewEdition.getSelectionModel().getSelectedItem();
            bookManager.deleteEdition(edition);
            tableViewEdition.getItems().remove(edition);
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error when deleting the edition");
            alert.showAndWait();
        }
    }

    public void onDeleteSerieClick(){
        try {
            Serie serie = tableViewSerie.getSelectionModel().getSelectedItem();
            bookManager.deleteSerie(serie);
            tableViewSerie.getItems().remove(serie);
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error when deleting the serie");
            alert.showAndWait();
        }
    }

    @FXML
    public void addEdition(){
        try {
            Edition edition = new Edition(inputEditionName.getText(), new Country(countryCBox.getSelectionModel().getSelectedItem()));
            utilsManager.addEdition(edition);
            outputEditionMessage.setText("Success");
            inputEditionName.clear();
            countryCBox.getSelectionModel().clearSelection();
            initTableViewEdition();
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please enter a name and select a country");
            alert.showAndWait();
        }
    }

    @FXML
    public void addSerie(){
        if(!inputSerieName.getText().isEmpty()) {
            Serie serie = new Serie(inputSerieName.getText());
            utilsManager.addSerie(serie);
            outputSerieMessage.setText("Success");
            inputSerieName.clear();
            initTableViewSerie();
        } else {
            outputSerieMessage.setText("Please enter a name");
        }
    }

    public void initCBoxCountry(){
        ArrayList<Country> countries = utilsManager.getAllCountries();
        for(Country country : countries){
            countryCBox.getItems().add(country.getName());
        }
    }
}
