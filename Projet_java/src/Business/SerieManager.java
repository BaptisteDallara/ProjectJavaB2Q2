package Business;

import DataAccess.*;
import Model.*;

import java.util.ArrayList;

public class SerieManager {
    private SerieDataAccess dao;

    public SerieManager(){
        this.dao = new SerieDBAccess();
    }

    public ArrayList<Serie> getAllSeries(){
        return dao.getAllSeries();
    }

    public ArrayList<ResultResearch> getSearchSerie (String serie){
        return dao.getSearchSerie(serie);
    }
}
