package Controller;

import Business.PersonManager;
import DataAccess.BookDBAccess;
import DataAccess.BookDataAccess;
import Model.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    @FXML
    private Label outputMessage;
    private PersonManager personManager;

    @FXML
    public void initialize(){
        personManager = new PersonManager();
        initNationalityCBox();
        initPersonTypeCBox();
        initTableViewPerson();
    }

    @FXML
    public void onDeleteButtonClick() {
        try {
            Person person = tableViewPerson.getSelectionModel().getSelectedItem();
            if (person != null) {
                personManager.deletePerson(person);
                outputMessage.setText("Delete");
                initTableViewPerson();
            }
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error when deleting the person");
            alert.showAndWait();
        }
    }

    @FXML
    public void addPerson(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        try{
            if(!personType.getValue().isEmpty()) {
                if(!inputFName.getText().isEmpty() && !inputLName.getText().isEmpty()) {
                    if (personType.getValue().equals("Author") || personType.getValue().equals("Drawer")) {
                        if(!nationalityCBox.getValue().isEmpty()) {
                            Contributor person = new Contributor(inputFName.getText(), inputLName.getText());
                            person.setNationality(new Country(nationalityCBox.getValue()));
                            if (bithDatePicker.getValue() != null) {
                                person.setBirthday(bithDatePicker.getValue());
                            }
                            if (deathDatePicker.getValue() != null) {
                                person.setDeath(deathDatePicker.getValue());
                            }
                            person.setPersonType(personType.getValue());
                            personManager.addContributor(person);
                        } else {
                            alert.setHeaderText("Incorrect nationality");
                            alert.showAndWait();
                            throw new Exception();
                        }
                    } else {
                        Borrower person = new Borrower(inputFName.getText(), inputLName.getText());
                            if (!inputEmail.getText().isEmpty()) {
                                if (!inputEmail.getText().contains("@")) {
                                    alert.setHeaderText("Incorrect email don't forget @");
                                    alert.showAndWait();
                                    throw new Exception();
                                } else {
                                    person.setEmail(inputEmail.getText());
                                }
                            }
                            if (!inputPhoneNumber.getText().isEmpty()) {
                                if (inputPhoneNumber.getText().length() != 10 || Integer.parseInt(inputPhoneNumber.getText()) < 0) {
                                    alert.setHeaderText("Incorrect phone number");
                                    alert.showAndWait();
                                    throw new Exception();
                                } else {
                                    person.setPhoneNumber(Integer.parseInt(inputPhoneNumber.getText()));
                                }
                            } else {
                                alert.setHeaderText("Please choose a phone number");
                                alert.showAndWait();
                                throw new Exception();
                            }
                            if (bithDatePicker.getValue() != null) {
                                person.setBirthday(bithDatePicker.getValue());
                            }
                            person.setPersonType(personType.getValue());
                            personManager.addBorrower(person);
                    }
                    outputMessage.setText("Success");
                    clearAllSelection();
                    initTableViewPerson();
                } else {
                    alert.setHeaderText("Incorrect name or first name");
                    alert.showAndWait();
                    throw new Exception();
                }
            } else {
                alert.setHeaderText("Incorrect person type");
                alert.showAndWait();
            }
        } catch (Exception exception){
            alert.setHeaderText("Error when adding the person");
            alert.showAndWait();
        }
    }


    public void clearAllSelection(){
        inputPhoneNumber.clear();
        inputEmail.clear();
        inputFName.clear();
        inputLName.clear();
        nationalityCBox.getSelectionModel().clearSelection();
        bithDatePicker.getEditor().clear();
        deathDatePicker.getEditor().clear();
    }

    public void initTableViewPerson(){
        try {
            tableViewPerson.getItems().clear();
            personColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            personTypeColumn.setCellValueFactory(new PropertyValueFactory<>("personType"));
            bithdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
            deathColumn.setCellValueFactory(new PropertyValueFactory<>("death"));
            tableViewPerson.getItems().addAll(personManager.getAllPerson());
        } catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error when initializing the table");
            alert.showAndWait();
        }
    }

    @FXML
    public void initForms(){
        clearAllSelection();
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
        try {
            ArrayList<Country> nationalities = personManager.getNationality();
            for (Country country : nationalities){
                nationalityCBox.getItems().add(country.getName());
            }
        } catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error while initializing the table");
            alert.showAndWait();
        }
    }
}
