package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.sql.ResultSet;

import DataAccess.SingletonConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.*;


public class AuthorDisplayController {

    @FXML
    private TableView<Contributor> authorTable;

    @FXML
    private Button btnSearchAuthor;

    @FXML
    private TextField inputAuthor;

    @FXML
    private TableColumn<Contributor, String> columnAuthorFName;

    @FXML
    private TableColumn<Contributor, String> columnAuthorLName;

    public ArrayList<Contributor> authors = new ArrayList<>();

    @FXML
    public void onSearchButtonClicked() throws SQLException {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "SELECT * FROM person WHERE personType = 'Author' AND lastName = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, inputAuthor.getText());
            System.out.println("hello");
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                Contributor author = new Contributor( data.getString("firstName"), data.getString("lastName"));
                author.setPersonId(data.getInt("personId"));
                System.out.println(author.getFirstName());
                authors.add(author);
            }
            columnAuthorFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            columnAuthorLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            authorTable.getItems().setAll(authors);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
