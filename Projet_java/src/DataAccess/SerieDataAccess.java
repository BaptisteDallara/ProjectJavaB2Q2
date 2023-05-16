package DataAccess;

import Model.*;
import java.util.ArrayList;

public interface SerieDataAccess {
    public ArrayList<Serie> getAllSeries();
    public ArrayList<ResultResearch> getSearchSerie (String serie);
}
