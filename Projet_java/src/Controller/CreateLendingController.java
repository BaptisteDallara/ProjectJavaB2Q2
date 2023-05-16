package Controller;

import Business.LendingManager;
import Model.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
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
    private Label delayOutput;

    @FXML
    private Label totalOutput;

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

    private Borrower selectedBorrower;

    public void initialize() {
        lendingManager = new LendingManager();
        initTableViewBorrower();
        initTableViewAvailable();
    }

    public void reinitalize() {
        ouputMessage.setText("");
        currentList = new ArrayList<>();
        if(tableViewBorrower.getSelectionModel().getSelectedItem() != null){
            selectedBorrower = tableViewBorrower.getSelectionModel().getSelectedItem();
            if(lendingManager.getDelay(selectedBorrower, LocalDate.now())){
                delayOutput.setText("Delay : yes");
            } else {
                delayOutput.setText("Delay : none");
            }
        }
        initTableViewBorrower();
        initTableViewAvailable();
        initTableViewCurrentList();
    }


    public void initTableViewBorrower() {
        tableViewBorrower.getItems().clear();
        borrowerColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tableViewBorrower.getItems().addAll(lendingManager.getAllBorrowers());
    }

    public void addCurrentList(){
        try {
            if(currentList != null && selectedBorrower != null) {
                Exemplar exemplar = tabViewAvailableEx.getSelectionModel().getSelectedItem();
                if (exemplar != null) {
                    currentList.add(exemplar);
                    tabViewAvailableEx.getItems().remove(exemplar);
                    initTableViewCurrentList();
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            ouputMessage.setText("Select a borrower");
        }
    }

    public void addLending(){
        try {
            if (selectedBorrower != null) {
                Double TotalPrice = 0.0;
                for (Exemplar exemplar : currentList) {
                    TotalPrice += exemplar.getLendingPrice();
                    lendingManager.addLending(exemplar, selectedBorrower);
                }
                reinitalize();
                tableViewBorrower.getSelectionModel().clearSelection();
                StringBuilder totalText = new StringBuilder("Total : ").append(TotalPrice).append(" €\n");
                totalText.append("Return date : ");
                totalText.append(LocalDate.now().plusDays(15));
                totalOutput.setText(totalText.toString());
                ouputMessage.setText("Lending added");
                selectedBorrower = null;
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            ouputMessage.setText("Select a borrower");
        }
    }

    public void initTableViewAvailable() {
        tabViewAvailableEx.getItems().clear();
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        lendingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("lendingPrice"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("stateString"));
        tabViewAvailableEx.getItems().addAll(lendingManager.getAllAvailableExemplar());
    }

    public void initTableViewCurrentList() {
        tabViewCurrentList.getItems().clear();
        bookColumnList.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        lendingColumnList.setCellValueFactory(new PropertyValueFactory<>("lendingPrice"));
        priceColumnList.setCellValueFactory(new PropertyValueFactory<>("price"));
        stateColumnList.setCellValueFactory(new PropertyValueFactory<>("stateString"));
        tabViewCurrentList.getItems().addAll(currentList);
    }
}
