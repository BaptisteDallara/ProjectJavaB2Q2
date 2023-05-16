package Business;

import DataAccess.PersonDBAccess;
import DataAccess.PersonDataAccess;
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
}
