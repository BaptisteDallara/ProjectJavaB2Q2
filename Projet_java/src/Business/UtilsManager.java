package Business;

import DataAccess.UtilsDBAccess;
import DataAccess.UtilsDataAccess;
import Exception.ExceptionSQL;
import Model.Country;
import Model.Edition;
import Model.Serie;

import java.util.ArrayList;

public class UtilsManager {
    private UtilsDataAccess utilsDataAccess;

    public UtilsManager(){
        this.utilsDataAccess = new UtilsDBAccess();
    }

    public void addEdition(Edition edition) throws ExceptionSQL{
        try {
            utilsDataAccess.addEdition(edition);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public void addSerie(Serie serie) throws ExceptionSQL{
        try {
            utilsDataAccess.addSerie(serie);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Country> getAllCountries() throws ExceptionSQL{
        try {
            return utilsDataAccess.getAllCountries();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }
}
