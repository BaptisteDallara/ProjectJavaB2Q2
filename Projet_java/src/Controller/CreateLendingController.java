package Controller;

import Business.LendingManager;
import Model.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class CreateLendingController {

    @FXML
    private TableColumn<Exemplar, String> bookColumn;

    @FXML
    private TableColumn<Exemplar, String> bookColumnList;

    @FXML
    private TableColumn<Borrower, String> borrowerColumn;

    @FXML
    private TableColumn<Exemplar, String> lendingColumnList;

    @FXML
    private TableColumn<Exemplar, Double> lendingPriceColumn;

    @FXML
    private Label ouputMessage;

    @FXML
    private TableColumn<Exemplar, Double> priceColumn;

    @FXML
    private TableColumn<Exemplar, String> priceColumnList;

    @FXML
    private Label retardOutput;

    @FXML
    private TableColumn<Exemplar, String> stateColumn;

    @FXML
    private TableColumn<Exemplar, String> stateColumnList;

    @FXML
    private TableView<Exemplar> tabViewAvailableEx;

    @FXML
    private TableView<Exemplar> tabViewCurrentList;

    @FXML
    private TableView<Borrower> tableViewBorrower;

    private ArrayList<Exemplar> currentList;

    private LendingManager lendingManager;

    public void initialize() {
        lendingManager = new LendingManager();
        initTableViewBorrower();
    }

    public void initTableViewBorrower() {
        tableViewBorrower.getItems().clear();
        borrowerColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tableViewBorrower.getItems().addAll(lendingManager.getAllBorrowers());
    }

    public void initTableViewAvailable() {
        tabViewAvailableEx.getItems().clear();
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        lendingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("lendingPrice"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("stateString"));
        //tabViewAvailableEx.setItems().addAll();
    }

    public void initTableViewCurrentList() {
        tabViewCurrentList.getItems().clear();
        bookColumnList.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        lendingColumnList.setCellValueFactory(new PropertyValueFactory<>("lendingPrice"));
        priceColumnList.setCellValueFactory(new PropertyValueFactory<>("price"));
        stateColumnList.setCellValueFactory(new PropertyValueFactory<>("stateString"));
        //tabViewCurrentList.getItems().addAll(currentList); }
    }
}
