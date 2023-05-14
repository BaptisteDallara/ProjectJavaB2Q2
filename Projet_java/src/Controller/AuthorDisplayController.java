package Controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

import DataAccess.SingletonConnexion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.*;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class AuthorDisplayController {

    @FXML
    private TextField authorFName;

    @FXML
    private TextField authorLName;

    @FXML
    private TableColumn<?, ?> restultSearch;

    @FXML
    private TableColumn<?, ?> tableName;

    @FXML
    void onButtonSearchClicked(ActionEvent event) {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "SELECT * FROM person WHERE personType = 'Author' AND lastName = ? AND firstName = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, authorLName.getText());
            statement.setString(2, authorFName.getText());
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                Contributor author = new Contributor( data.getString("firstName"), data.getString("lastName"));
                author.setPersonId(data.getInt("personId"));
                System.out.println(author.getFirstName());
                authors.add(author);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onComboBoxClicked(ActionEvent event) {

    }

}