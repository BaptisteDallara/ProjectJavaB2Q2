package DataAccess;

import Model.Country;
import Model.Serie;

import java.util.ArrayList;

public interface UtilsDataAccess {
    public ArrayList<Country> getAllContries();
    public void addSerie(Serie serie);
    public boolean serieAlreadyExist(String name);
}
