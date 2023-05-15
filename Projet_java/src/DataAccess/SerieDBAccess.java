package DataAccess;

import Model.*;

import java.sql.*;
import java.util.ArrayList;


public class SerieDBAccess implements SerieDataAccess {
    
    public ArrayList<Serie> getAllSeries(){
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            StringBuilder sql = new StringBuilder("select distinct * from serie ");
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            ResultSet data = statement.executeQuery();
            ArrayList<Serie> series = new ArrayList<>();
            while (data.next()) {
                Serie serie = new Serie(data.getString("name"));
                series.add(serie);
            }
            return series;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ResultResearch> getSearchSerie (String serie){
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            StringBuilder sql = new StringBuilder("select book.title, person.firstName, person.lastName, edition.name, person.personType from book ");
            sql.append("inner join contribution on book.bookId = contribution.book ");
            sql.append("inner join person on person.personId = contribution.person ");
            sql.append("inner join serie on serie.serieId = book.serie ");
            sql.append("inner join edition on edition.editionId = book.edition ");
            sql.append("where serie.name = ?");
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            statement.setString(1, serie);
            ResultSet data = statement.executeQuery();
            data.next();
            String author = data.getString(2) + " " + data.getString(3);
            SearchSerie searchSeries = new SearchSerie(data.getString(1), data.getString(4));
            if (data.getString(5).equals("Author")) {
                if (!containAuthor(searchSeries, author)) {
                    searchSeries.setAuthor(author);
                }
            } else {
                if (!containDrawer(searchSeries, author)) {
                    searchSeries.setDrawer(author);
                }
            }
            while (data.next()) {
                if (!containTitle(searchSeries, data.getString(1))) {
                    searchSeries.setTitle(data.getString(1));
                }
                author = data.getString(2) + " " + data.getString(3);
                if (data.getString(5).equals("Author")) {
                    if (!containAuthor(searchSeries, author)) {
                        searchSeries.setAuthor(author);
                    }
                } else {
                    if (!containDrawer(searchSeries, author)) {
                        searchSeries.setDrawer(author);
                    }
                }
                if (!containEdition(searchSeries, data.getString(4))) {
                    searchSeries.setEdition(data.getString(4));
                }
            }
            ArrayList<ResultResearch> resultResearchs = new ArrayList<>();
            ResultResearch resultTitle = new ResultResearch(searchSeries.getTitle());
            resultResearchs.add(resultTitle);
            ResultResearch resultAuthor = new ResultResearch(searchSeries.getAuthor());
            resultResearchs.add(resultAuthor);
            System.out.println(resultResearchs.get(0).getResult());
            ResultResearch resultDrawer = new ResultResearch(searchSeries.getDrawer());
            resultResearchs.add(resultDrawer);
            ResultResearch resultEdition = new ResultResearch(searchSeries.getEdition());
            resultResearchs.add(resultEdition);
            return resultResearchs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean containTitle(SearchSerie searchSerie, String title) {
        ArrayList<String> titles = searchSerie.getTitleList();
        for (String title1 : titles) {
            if (title1.equals(title)) {
                return true;
            }
        }
        return false;
    }

    public boolean containEdition(SearchSerie searchSerie, String editor) {
        ArrayList<String> editors = searchSerie.getEditionList();
        for (String editor1 : editors) {
            if (editor1.equals(editor)) {
                return true;
            }
        }
        return false;
    }

    public boolean containAuthor(SearchSerie searchSerie, String author) {
        ArrayList<String> authors = searchSerie.getAuthorList();
        for (String author1 : authors) {
            if (author1.equals(author)) {
                return true;
            }
        }
        return false;
    }

    public boolean containDrawer(SearchSerie searchSerie, String drawer) {
        ArrayList<String> drawers = searchSerie.getDrawerList();
        for (String drawer1 : drawers) {
            if (drawer1.equals(drawer)) {
                return true;
            }
        }
        return false;
    }
}
