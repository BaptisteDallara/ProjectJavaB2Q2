package DataAccess;

import Model.Contributor;
import Model.Country;
import Model.Person;

import java.util.ArrayList;

public interface PersonDataAccess {
    public ArrayList<Country> getNationality();
    public ArrayList<Person> getAllPerson();
}
