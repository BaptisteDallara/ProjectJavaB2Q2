package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Model.*;
import Business.*;
import java.util.ArrayList;

public class LendingResearch {

    @FXML
    private ComboBox<String> borrowerCb;

    @FXML
    private ComboBox<String> lendingCb;

    @FXML
    private Button buttonSearch;

    @FXML
    private Label labelLending;

    @FXML
    private DatePicker dateLendingBegin;

    @FXML
    private TableView<ResultResearch> tableName;

    @FXML
    private TableColumn<ResultResearch, String> nameColumn;

    @FXML
    private TableView<ResultResearch> tableResult;

    @FXML
    private TableColumn<ResultResearch, String> resultColumn;

    private LendingManager lendingManager;
    private ArrayList<ResultResearch> resultResearch;
    private ArrayList<ResultResearch> tableNames;
    private ArrayList<Lending> lendings;

    @FXML
    public void initialize(){
        lendingManager = new LendingManager();
        initCBoxBorrower();
    }

    @FXML
    public void initCBoxBorrower(){
        ArrayList<Borrower> borrowers = lendingManager.getAllBorrowers();
        for(Borrower borrower : borrowers){
            borrowerCb.getItems().add(borrower.getFirstName() + " " + borrower.getLastName());
        }
    }

    @FXML
    public void onBorrowerCLicked(MouseEvent event) {

    }

    @FXML
    public void onDateClicked(MouseEvent event) {
        lendingCb.getItems().clear();
    }

    @FXML
    public void onLendingClick(MouseEvent event) {
        if (borrowerCb.getValue() == null || dateLendingBegin.getValue() == null){
            labelLending.setText("Veuillez remplir tous les champs");
        } else {
            labelLending.setText(null);
            lendings = lendingManager.getAllLendings(borrowerCb.getValue(), dateLendingBegin.getValue());
            for(Lending lending : lendings){
                lendingCb.getItems().add(lending.getBeginDate().toString() + " " + lending.getLendingId());
            }
            lendings.clear();
        }
    }

    @FXML
    public void onSearchButtonClick(ActionEvent event) {
        try {
            String[] lendingSplit = lendingCb.getValue().split(" ");
            int id = Integer.parseInt(lendingSplit[1]);

            tableName.getItems().clear();
            tableNames = new ArrayList<>();
            tableNames.add(new ResultResearch("Exemplar"));
            tableNames.add(new ResultResearch("Book"));
            tableNames.add(new ResultResearch("Lending"));
            tableNames.add(new ResultResearch("Lending"));
            tableNames.add(new ResultResearch("Lending"));
            tableName.getItems().setAll(tableNames);
            nameColumn.setCellValueFactory(new PropertyValueFactory<ResultResearch, String>("result"));

            tableResult.getItems().clear();
            resultResearch = lendingManager.getSearchLending(id);
            resultColumn.setCellValueFactory(new PropertyValueFactory<ResultResearch, String>("result"));
            tableResult.getItems().setAll(resultResearch);
        } catch (Exception e) {
            labelLending.setText("Veuillez remplir tous les champs");
        }
    }
}