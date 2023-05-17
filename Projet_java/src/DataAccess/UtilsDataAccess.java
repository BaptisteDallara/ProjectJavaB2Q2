package DataAccess;

import Model.Country;
import Model.Edition;
import Model.Serie;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UtilsDataAccess {
    public ArrayList<Country> getAllCountries() throws SQLException;
    public void addSerie(Serie serie) throws SQLException;
    public void addEdition(Edition edition) throws SQLException;


}
