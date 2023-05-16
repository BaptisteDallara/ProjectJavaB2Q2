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
                outputMessage.setText("Person deleted");
                initTableViewPerson();
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void addPerson(){
        try{
            if(!personType.getValue().isEmpty()) {
                if(!inputFName.getText().isEmpty() && !inputLName.getText().isEmpty()) {
                    if (personType.getValue().equals("Author") || personType.getValue().equals("Drawer")) {
                        Contributor person = new Contributor(inputFName.getText(),inputLName.getText());
                        person.setNationality(new Country(nationalityCBox.getValue()));
                        if(bithDatePicker.getValue() != null){
                            person.setBirthday(bithDatePicker.getValue());
                        }
                        if(deathDatePicker.getValue() != null){
                            person.setDeath(deathDatePicker.getValue());
                        }
                        person.setPersonType(personType.getValue());
                        personManager.addContributor(person);
                    } else {
                        Borrower person = new Borrower(inputFName.getText(),inputLName.getText(),Integer.parseInt(inputPhoneNumber.getText())
                                            ,inputEmail.getText());
                        if(bithDatePicker.getValue() != null){
                            person.setBirthday(bithDatePicker.getValue());
                        }
                        person.setPersonType(personType.getValue());
                        personManager.addBorrower(person);
                    }
                }
                outputMessage.setText("Success");
                clearAllSelection();
                initTableViewPerson();
            } else {
                throw new RuntimeException();
            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
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
        tableViewPerson.getItems().clear();
        personColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        personTypeColumn.setCellValueFactory(new PropertyValueFactory<>("personType"));
        bithdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        deathColumn.setCellValueFactory(new PropertyValueFactory<>("death"));
        tableViewPerson.getItems().addAll(personManager.getAllPerson());
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
        ArrayList<Country> nationalities = personManager.getNationality();
        for (Country country : nationalities){
            nationalityCBox.getItems().add(country.getName());
        }
    }

}
