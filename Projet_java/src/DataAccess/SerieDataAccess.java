package DataAccess;

import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SerieDataAccess {
    public ArrayList<Serie> getAllSeries() throws SQLException;
    public ArrayList<ResultResearch> getSearchSerie (String serie) throws SQLException;
}
