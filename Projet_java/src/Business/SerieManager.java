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

    public boolean containTitle(SearchSerie searchSerie, String title){
        return dao.containTitle(searchSerie, title);
    }

    public boolean containEdition(SearchSerie searchSerie, String editor){
        return dao.containEdition(searchSerie, editor);
    }

    public boolean containAuthor(SearchSerie searchSerie, String author){
        return dao.containAuthor(searchSerie, author);
    }

    public boolean containDrawer(SearchSerie searchSerie, String drawer){
        return dao.containDrawer(searchSerie, drawer);
    }
}
