package DataAccess;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AuthorDBAccess implements AuthorDataAccess{
    public ArrayList<Contributor> getAllAuthor(){
        try {
            ResultSet data = getData("select * from person where personType = 'Author'");
            ArrayList<Contributor> auteurs = new ArrayList<>();
            while (data.next()) {
                Contributor author = new Contributor(data.getString("firstName"), data.getString("lastName"));
                author.setPersonId(data.getInt("personId"));
                auteurs.add(author);
            }
            return auteurs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addAuthor(Contributor author){

    }
    public void deleteAuthor(Contributor author){

    }
    public ResultSet getData(String sql){
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            return data;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
