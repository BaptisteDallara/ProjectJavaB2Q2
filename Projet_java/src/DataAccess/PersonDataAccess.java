package DataAccess;

import Model.Borrower;
import Model.Contributor;
import Model.Country;
import Model.Person;

import java.util.ArrayList;

public interface PersonDataAccess {
    public ArrayList<Country> getNationality();
    public ArrayList<Person> getAllPerson();
    public void addBorrower(Borrower person);
    public void addContributor(Contributor person);

    public void deletePerson(Person person);
}
