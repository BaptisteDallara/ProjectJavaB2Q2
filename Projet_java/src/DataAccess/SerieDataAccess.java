package DataAccess;

import Model.*;
import java.util.ArrayList;

public interface SerieDataAccess {
    public ArrayList<Serie> getAllSeries();
    public ArrayList<ResultResearch> getSearchSerie (String serie);
    public boolean containTitle(SearchSerie searchSerie ,String title);
    public boolean containEdition(SearchSerie searchSerie ,String editor);
    public boolean containAuthor(SearchSerie searchSerie ,String author);
    public boolean containDrawer(SearchSerie searchSerie ,String drawer);
}
