package Controller;

import Business.LendingManager;
import Model.Exemplar;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeleteLendingController {

    @FXML
    private TableColumn<Exemplar, String> bookColumn;
    @FXML
    private TableColumn<Exemplar, String> languageColumn;

    @FXML
    private TableColumn<Exemplar, String> pageColumn;

    @FXML
    private TableColumn<Exemplar, String> positionColumn;

    @FXML
    private TableColumn<Exemplar, String> priceColumn;

    @FXML
    private TableView<Exemplar> tabViewExemplar;
    private LendingManager lendingManager;

    public void initialize(){
        lendingManager = new LendingManager();
        initTableViewExemplar();
    }

    public void initTableViewExemplar(){
        tabViewExemplar.getItems().clear();
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("languageName"));
        pageColumn.setCellValueFactory(new PropertyValueFactory<>("nbPages"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tabViewExemplar.getItems().addAll(lendingManager.getAllLendedExemplar());
    }

    public void returned(){
        try{
            Exemplar exemplar = tabViewExemplar.getSelectionModel().getSelectedItem();
            lendingManager.returned(exemplar);
            tabViewExemplar.getItems().remove(exemplar);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
