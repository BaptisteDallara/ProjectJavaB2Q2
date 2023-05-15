package DataAccess;

import Model.Country;
import Model.Edition;
import Model.Serie;

import java.util.ArrayList;

public interface UtilsDataAccess {
    public ArrayList<Country> getAllContries();
    public void addSerie(Serie serie);
    public void addEdition(Edition edition);


}