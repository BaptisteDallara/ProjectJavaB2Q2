package DataAccess;

import Model.*;
import DataAccess.BookDataAccess;

import java.sql.*;
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
    public void addContributor(Contributor person) {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "insert into person (firstName,lastName,phoneNumber,email,personType,birthday,death) values (?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,person.getFirstName());
            statement.setString(2,person.getLastName());
            statement.setNull(3, java.sql.Types.INTEGER);
            statement.setNull(4, java.sql.Types.VARCHAR);
            statement.setString(5,person.getPersonType());
            if(person.getBirthday() == null){
                statement.setNull(6, java.sql.Types.DATE);
            }
            else{
                java.sql.Date sqlPubDate = java.sql.Date.valueOf(person.getBirthday());
                statement.setDate(6,sqlPubDate);
            }
            if(person.getDeath() == null){
                statement.setNull(7, java.sql.Types.DATE);
            }
            else{
                java.sql.Date sqlPubDate = java.sql.Date.valueOf(person.getDeath());
                statement.setDate(7,sqlPubDate);
            }
            statement.executeUpdate();

        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void addBorrower(Borrower person) {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "insert into person (firstName,lastName,phoneNumber,email,personType,birthday,death) values (?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,person.getFirstName());
            statement.setString(2,person.getLastName());
            statement.setInt(3,person.getPhoneNumber());
            statement.setString(4,person.getEmail());
            statement.setString(5,"Reader");
            if(person.getBirthday() == null){
                statement.setNull(6, java.sql.Types.DATE);
            }
            else{
                java.sql.Date sqlPubDate = java.sql.Date.valueOf(person.getBirthday());
                statement.setDate(6,sqlPubDate);
            }
            statement.setNull(7, java.sql.Types.DATE);
            statement.executeUpdate();
        } catch (Exception exception){
            throw new RuntimeException(exception);
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
                Person person = null;
                if(data.getString("personType").equals("Reader")){
                    person = new Borrower(data.getString("firstName"), data.getString("lastName"),data.getInt("phoneNumber")
                        ,data.getString("email"));
                    if(data.getString("birthday") != null){
                        person.setBirthday(java.time.LocalDate.parse(data.getString("birthday")));
                    }
                    person.setPersonId(data.getInt("personId"));
                }
                else {
                    person = bookDBAccess.getContributeur(data);
                }
                person.setPersonId(data.getInt("personId"));
                person.setPersonType(data.getString("personType"));
                persons.add(person);
            }
            return persons;
            }
            catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePerson(Person person) {
        try{
            if(person.getPersonType().equals("Reader")) {
                deleteLending((Borrower) person);
            } else {
                deleteContribution((Contributor) person);
            }
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "delete from person where personId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,person.getPersonId());
            statement.executeUpdate();
        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    public void deleteLending(Borrower borrower){
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "delete from lending where reader = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,borrower.getPersonId());
            statement.executeUpdate();
        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    public void deleteContribution(Contributor contributor) {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "delete from contribution where person = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,contributor.getPersonId());
            statement.executeUpdate();
        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }
}
