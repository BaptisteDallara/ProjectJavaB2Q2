package DataAccess;

import Model.Borrower;
import Model.Contributor;
import Model.Country;
import Model.Person;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PersonDataAccess {
    public ArrayList<Country> getNationality() throws SQLException;
    public ArrayList<Person> getAllPerson() throws Exception;
    public void addBorrower(Borrower person) throws Exception;
    public void addContributor(Contributor person) throws Exception;

    public void deletePerson(Person person) throws Exception;
}
