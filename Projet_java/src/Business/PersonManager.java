package Business;

import DataAccess.PersonDBAccess;
import DataAccess.PersonDataAccess;
import Exception.ExceptionSQL;
import Model.Borrower;
import Model.Contributor;
import Model.Country;
import Model.Person;

import java.util.ArrayList;

public class PersonManager {

    private PersonDataAccess personDataAccess;

    public PersonManager(){
        personDataAccess = new PersonDBAccess() {
        };
    }

    public ArrayList<Country> getNationality() throws ExceptionSQL{
        try {
            return personDataAccess.getNationality();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Person> getAllPerson() throws ExceptionSQL{
        try {
            return personDataAccess.getAllPerson();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public void addContributor(Contributor person) throws ExceptionSQL{
        try {
            personDataAccess.addContributor(person);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public void deletePerson(Person person) throws ExceptionSQL{
        try {
            personDataAccess.deletePerson(person);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public void addBorrower(Borrower borrower) throws ExceptionSQL {
        try {
            personDataAccess.addBorrower(borrower);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }
}
