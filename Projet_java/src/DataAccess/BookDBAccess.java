package DataAccess;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDBAccess implements BookDataAccess{

    @Override
    public ResultSet getData(String sql) {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            return data;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
    @Override
    public ArrayList<Book> getAllBook(){
        // don't forget try catch and exceptions
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addBook(Book book){
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "insert into book (bookId,title,originalLanguage,edition,genre," +
                        "type,publicationDate,recommendedAge,isDiscontinued)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,book.getBookId());
            statement.setString(2, book.getTitle());
            statement.setString(3,book.getOriginalLanguage().getName());
            statement.setInt(4,book.getEdition().getEditionId());
            statement.setString(5,book.getGenre().getName());
            statement.setString(6,book.getType().getName());
            java.sql.Date sqlPubDate = java.sql.Date.valueOf(book.getPublicationDate());
            statement.setDate(7,sqlPubDate);
            statement.setInt(8,book.getRecommendedAge());
            statement.setBoolean(9, book.getDiscontinued());
            if(book.getSerie() != null){
                sql = "update book set serie = ? where bookId = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1,book.getSerie().getSerieId());
                statement.setInt(2,book.getBookId());
                statement.executeUpdate();
            }
            //connection.close();
        }
        catch (SQLException exception){
            System.out.println(exception.getMessage());
        }

    }
    @Override
    public void deleteBook(Book book) {
    }
    @Override
    public ArrayList<Language> showLanguage(){
        try{
            ResultSet data = getData("select * from language");
            ArrayList<Language> languages = new ArrayList<>();
            while (data.next()){
                Language language = new Language(data.getString("name"));
                languages.add(language);
            }
            return languages;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    @Override
    public ArrayList<Edition> showEdition(){
        try{
            ResultSet data = getData("select * from edition");
            ArrayList<Edition> editions = new ArrayList<>();
            while (data.next()){
                Country country = new Country(data.getString("country"));
                Edition edition = new Edition(data.getInt("editionId"),data.getString("name"),country);
                editions.add(edition);
            }
            return editions;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
    @Override
    public ArrayList<Genre> showGenre(){
        try{
            ResultSet data = getData("select * from genre");
            ArrayList<Genre> genres = new ArrayList<>();
            while (data.next()){
                Genre genre = new Genre(data.getString("name"));
                genres.add(genre);
            }
            return genres;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
    @Override
    public ArrayList<Type> showType(){
        try{
            ResultSet data = getData("select * from type");
            ArrayList<Type> types = new ArrayList<>();
            while (data.next()){
                Type type = new Type(data.getString("name"));
                types.add(type);
            }
            return types;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
    @Override
    public ArrayList<Serie> showSerie(){
        try{
            ResultSet data = getData("select * from serie");
            ArrayList<Serie> series = new ArrayList<>();
            while (data.next()){
                Serie serie = new Serie(data.getInt("serieId"),data.getString("name"));
                series.add(serie);
            }
            return series;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Contributor searchContributor(String firstName, String lastName, String personType){
        try{
            ResultSet data = getData("select * from person where firstName = '" + firstName + "' and lastName = '" + lastName + "' and personType = '" + personType + "'");
            Contributor contributor = null;
            while (data.next()){
                contributor = getContributeur(data);
            }
            return contributor;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public ArrayList<Contributor> showAuthor(){
        try{
            ResultSet data = getData("select * from person where personType = 'Author'");
            ArrayList<Contributor> authors = new ArrayList<>();
            while (data.next()){
                Contributor author = getContributeur(data);
                authors.add(author);
            }
            return authors;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public ArrayList<Contributor> showDrawer(){
        try{
            ResultSet data = getData("select * from person where personType = 'Drawer'");
            ArrayList<Contributor> drawers = new ArrayList<>();
            while (data.next()){
                Contributor drawer = getContributeur(data);
                drawers.add(drawer);
            }
            return drawers;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Genre getGenre(String genderName){
            try{
            ResultSet data = getData("select * from genre where name = '" + genderName + "'");
            Genre genre = null;
            while (data.next()){
                genre = new Genre(data.getString("name"));
            }
            return genre;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Type getType(String typeName){
        try{
            ResultSet data = getData("select * from type where name = '" + typeName + "'");
            Type type = null;
            while (data.next()){
                type = new Type(data.getString("name"));
            }
            return type;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
    public Edition getEdition(String editionName){
        try{
            ResultSet data = getData("select * from edition where name = '" + editionName + "'");
            Edition edition = null;
            while (data.next()){
                Country country = new Country(data.getString("country"));
                edition = new Edition(data.getInt("editionId"),data.getString("name"),country);
            }
            return edition;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
    public Language getLanguage(String languageName){
        try{
            ResultSet data = getData("select * from language where name = '" + languageName + "'");
            Language language = null;
            while (data.next()){
                language = new Language(data.getString("name"));
            }
            return language;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Serie getSerie(String serieName){
        try{
            ResultSet data = getData("select * from serie where name = '" + serieName + "'");
            Serie serie = null;
            while (data.next()){
                serie = new Serie(data.getInt("serieId"),data.getString("name"));
            }
            return serie;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Contributor getContributeur(ResultSet data) throws SQLException{
        Contributor contributor = new Contributor(data.getInt("personId"),data.getString("firstName"),data.getString("lastName"));
        if(data.getString("birthday") != null){
            contributor.setBirthday(java.time.LocalDate.parse(data.getString("birthday")));
        }
        if(data.getString("death") != null){
            contributor.setDeath(java.time.LocalDate.parse(data.getString("death")));
        }
        if(data.getString("nationality") != null){
            Country nationality = new Country(data.getString("nationality"));
            contributor.setNationality(nationality);
        }
        return contributor;
    }
}
