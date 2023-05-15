package Business;

import DataAccess.UtilsDBAccess;
import DataAccess.UtilsDataAccess;
import Model.Country;
import Model.Edition;
import Model.Serie;

import java.util.ArrayList;

public class UtilsManager {
    private UtilsDataAccess utilsDataAccess;

    public UtilsManager(){
        this.utilsDataAccess = new UtilsDBAccess();
    }

    public void addEdition(Edition edition){
        utilsDataAccess.addEdition(edition);
    }
    public void addSerie(Serie serie){
        utilsDataAccess.addSerie(serie);
    }
    public ArrayList<Country> getAllContries(){
        return utilsDataAccess.getAllContries();
    }
}
