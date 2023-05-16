package Business;

import DataAccess.PersonDBAccess;
import DataAccess.PersonDataAccess;
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

    public ArrayList<Country> getNationality(){
        return personDataAccess.getNationality();
    }

    public ArrayList<Person> getAllPerson(){
        return personDataAccess.getAllPerson();
    }

    public void addContributor(Contributor person){
        personDataAccess.addContributor(person);
    }

    public void deletePerson(Person person){
        personDataAccess.deletePerson(person);
    }

    public void addBorrower(Borrower borrower){
        personDataAccess.addBorrower(borrower);
    }
}
