package Business;

import DataAccess.UtilsDBAccess;
import DataAccess.UtilsDataAccess;
import Model.Country;
import Model.Serie;

import java.util.ArrayList;

public class UtilsManager {
    private UtilsDataAccess utilsDataAccess;

    public UtilsManager(){
        this.utilsDataAccess = new UtilsDBAccess();
    }

    public void addSerie(Serie serie){
        utilsDataAccess.addSerie(serie);
    }
    public ArrayList<Country> getAllContries(){
        return utilsDataAccess.getAllContries();
    }
}
