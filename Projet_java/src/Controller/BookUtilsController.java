package Controller;

import Business.UtilsManager;
import Model.*;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    private UtilsManager utilsManager;

    @FXML
    public void initialize(){
        utilsManager = new UtilsManager();
        initCBoxCountry();
    }

    @FXML
    public void addSerie(){
        if(!inputSerieName.getText().isEmpty()) {
            Serie serie = new Serie(inputSerieName.getText());
            utilsManager.addSerie(serie);
            outputSerieMessage.setText("Success");
        } else {
            outputSerieMessage.setText("Please enter a name");
        }
    }

    public void initCBoxCountry(){
        ArrayList<Country> countries = utilsManager.getAllContries();
        for(Country country : countries){
            countryCBox.getItems().add(country.getName());
        }
    }
}
