package Business;

import DataAccess.*;
import Exception.ExceptionSQL;
import Model.*;

import java.util.ArrayList;

public class SerieManager {
    private SerieDataAccess dao;

    public SerieManager(){
        this.dao = new SerieDBAccess();
    }

    public ArrayList<Serie> getAllSeries() throws ExceptionSQL{
        try {
            return dao.getAllSeries();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<ResultResearch> getSearchSerie (String serie) throws ExceptionSQL{
        try {
            return dao.getSearchSerie(serie);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }
}
