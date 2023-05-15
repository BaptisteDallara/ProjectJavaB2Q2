package DataAccess;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonDBAccess implements PersonDataAccess{
    @Override
    public ArrayList<Country> getNationality(){
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            ArrayList<Country> countries = new ArrayList<>();
            String sql = "select * from Country";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            while (data.next()){
                Country country = new Country(data.getString("name"));
                countries.add(country);
            }
            return countries;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Contributor> getContributor() {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            ArrayList<Contributor> persons = new ArrayList<>();
            String sql = "select * from person where personType = ? or personType = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,"Author");
            statement.setString(2,"Drawer");
            ResultSet data = statement.executeQuery();
            while (data.next()){
                    Country country = new Country(data.getString("nationality"));
                    Contributor person = new Contributor(data.getString("firstName"),data.getString("lastName"),
                            LocalDate.parse(data.getString("birthday")),LocalDate.parse(data.getString("death")),country);
                    System.out.println(person.getFullName());
                    person.setPersonId(data.getInt("personId"));
                    person.setPersonType(data.getString("personType"));
                    persons.add(person);
            }
            return persons;
            }
            catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
