package Controller;

import Business.PersonManager;
import DataAccess.BookDBAccess;
import DataAccess.BookDataAccess;
import Model.*;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class CreatePersonController {

    @FXML
    private DatePicker bithDatePicker;

    @FXML
    private TableColumn<Person, String> bithdayColumn;

    @FXML
    private TableColumn<Person, String> deathColumn;

    @FXML
    private DatePicker deathDatePicker;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputFName;

    @FXML
    private TextField inputLName;

    @FXML
    private TextField inputPhoneNumber;

    @FXML
    private ComboBox<String> nationalityCBox;

    @FXML
    private TableColumn<Person, String> personColumn;

    @FXML
    private ComboBox<String> personType;

    @FXML
    private TableColumn<Person, String> personTypeColumn;

    @FXML
    private TableView<Person> tableViewPerson;
    private PersonManager personManager;

    @FXML
    public void initialize(){
        personManager = new PersonManager();
        initNationalityCBox();
        initPersonTypeCBox();
        initTableViewPerson();
    }

    public void initTableViewPerson(){
        tableViewPerson.getItems().clear();
        personColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        personTypeColumn.setCellValueFactory(new PropertyValueFactory<>("personType"));
        bithdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        deathColumn.setCellValueFactory(new PropertyValueFactory<>("death"));
        tableViewPerson.getItems().addAll(personManager.getAllPerson());
    }

    @FXML
    public void initForms(){
        resetForm();
        String type = personType.getValue();
        switch (type){
            case "Author": initContributorForm();
                break;
            case "Drawer": initContributorForm();
                break;
            case "Reader": initReaderForm();
                break;
        }
    }

    public void resetForm(){
        nationalityCBox.setVisible(false);
        bithDatePicker.setVisible(false);
        deathDatePicker.setVisible(false);
        inputPhoneNumber.setVisible(false);
        inputEmail.setVisible(false);
    }

    public void initReaderForm(){
        bithDatePicker.setVisible(true);
        inputPhoneNumber.setVisible(true);
        inputEmail.setVisible(true);
    }

    public void initContributorForm(){
        nationalityCBox.setVisible(true);
        bithDatePicker.setVisible(true);
        deathDatePicker.setVisible(true);
    }

    public void initPersonTypeCBox(){
        personType.getItems().addAll("Author","Drawer","Reader");
    }

    public void initNationalityCBox(){
        ArrayList<Country> nationalities = personManager.getNationality();
        for (Country country : nationalities){
            nationalityCBox.getItems().add(country.getName());
        }
    }

}
