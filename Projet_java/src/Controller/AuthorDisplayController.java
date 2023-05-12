package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.naming.spi.DirStateFactory.Result;

import DataAccess.SingletonConnexion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;

import Model.*;

public class AuthorDisplayController {

    @FXML
    private TableView<Contributor> authorTable;

    @FXML
    private Button btnSearchAuthor;

    @FXML
    private TextField inputAuthor;

    @FXML
    private TableColumn<Contributor, String> authorColumn;

    @FXML
    public void onSearchButtonClicked() throws SQLException {
        Connection connection = SingletonConnexion.getUniqueConnexion();
        String sql = "SELECT * FROM person WHERE personType = 'author' AND lastName = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, inputAuthor.getText());
        ResultSet data = statement.executeQuery();
        while (data.next()) {
            Contributor author = new Contributor(data.getInt("personId"), data.getString("firstName"), data.getString("lastName"));
        }
    }
}
