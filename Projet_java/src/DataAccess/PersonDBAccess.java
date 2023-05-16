package DataAccess;

import Model.*;
import DataAccess.BookDataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonDBAccess implements PersonDataAccess{

    private BookDataAccess bookDBAccess = new BookDBAccess();
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
    public ArrayList<Person> getAllPerson() {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            ArrayList<Person> persons = new ArrayList<>();
            String sql = "select * from person where personType = ? or personType = ? or personType = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,"Author");
            statement.setString(2,"Drawer");
            statement.setString(3,"Reader");
            ResultSet data = statement.executeQuery();
            while (data.next()){
                if(data.getString("personType").equals("Reader")){

                }
                else {
                    Contributor person = bookDBAccess.getContributeur(data);
                    person.setPersonId(data.getInt("personId"));
                    person.setPersonType(data.getString("personType"));
                    persons.add(person);
                }

            }
            return persons;
            }
            catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw new RuntimeException(e);
        }
    }
}
