package DataAccess;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookDBAccess implements BookDataAccess{

    @Override
    public ResultSet getData(String sql) throws SQLException {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            return data;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }
    @Override
    public Book getBook(String bookName) throws SQLException{
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from book where title = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,bookName);
            ResultSet data = statement.executeQuery();
            data.next();
            Book book = new Book(data.getString("title"), LocalDate.parse(data.getString("publicationDate")),
                    data.getInt("recommendedAge"),data.getBoolean("isDiscontinued"),getGenre(data.getString("genre")),
                    getType(data.getString("type")),getLanguage(data.getString("originalLanguage")),
                    getEdition(data.getInt("edition")));
            if(data.getInt("serie") != 0) {
                book.setSerie(getSerie(data.getInt("serie")));
            }
            book.setBookId(data.getInt("bookId"));
            String sqlContribution = "select * from contribution where book = ?";
            PreparedStatement statementContribution = connection.prepareStatement(sqlContribution);
            statementContribution.setInt(1,book.getBookId());
            ResultSet dataContribution = statementContribution.executeQuery();
            while(dataContribution.next()){
                String sqlContributor = "select * from person where personId = ?";
                PreparedStatement statementContributor = connection.prepareStatement(sqlContributor);
                statementContributor.setInt(1,dataContribution.getInt("person"));
                ResultSet dataContributor = statementContributor.executeQuery();
                dataContributor.next();
                if(dataContributor.getString("personType").equals("Author")){
                    book.addAuthor(getContributeur(dataContributor));
                }
                if(dataContributor.getString("personType").equals("Drawer")){
                    book.addDrawer(getContributeur(dataContributor));
                }
            }
            return book;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }
    @Override
    public ArrayList<Book> getAllBook() throws SQLException{
        try {
            ResultSet data = getData("select * from book");
            ArrayList<Book> books = new ArrayList<>();
            while (data.next()) {
                Book book = getBook(data.getString("title"));
                books.add(book);
                }
            return books;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    @Override
    public void addBook(Book book) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "insert into book (title,originalLanguage,edition,genre,type,publicationDate," +
                    "recommendedAge,isDiscontinued,serie) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            statement.setString(2,book.getOriginalLanguage().getName());
            statement.setInt(3,book.getEdition().getEditionId());
            statement.setString(4,book.getGenre().getName());
            statement.setString(5,book.getType().getName());
            java.sql.Date sqlPubDate = java.sql.Date.valueOf(book.getPublicationDate());
            statement.setDate(6,sqlPubDate);
            statement.setInt(7,book.getRecommendedAge());
            statement.setBoolean(8, book.getDiscontinued());
            if(book.getSerie() != null){
                statement.setInt(9,book.getSerie().getSerieId());
            }
            else {
                statement.setNull(9,java.sql.Types.INTEGER);
            }
            statement.executeUpdate();
            String sql2 = "select * from book where title = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setString(1, book.getTitle());
            ResultSet data = statement2.executeQuery();
            data.next();
            int bookId = data.getInt("bookId");
            for(Contributor author : book.getAuthors()) {
                addContribution(bookId,author.getPersonId());
            }
            for(Contributor drawer : book.getDrawers()) {
                addContribution(bookId,drawer.getPersonId());
            }
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }

    }

    @Override
    public void addContribution(Integer bookId, Integer contributorId) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "insert into contribution (book,person) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bookId);
            ResultSet data = getData("select * from person where personId = '"+ contributorId + "'");
            data.next();
            Contributor contributor = getContributeur(data);
            statement.setInt(2,contributor.getPersonId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }

    }
    
    @Override
    public void deleteBook(Book book) throws SQLException {
        try{
            deleteContributionFromBook(book);
            deleteLendingFromBook(book);
            deleteExemplarFromBook(book);
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "delete from book where bookId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, book.getBookId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public void deleteSerie(Serie serie) throws SQLException {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from book where serie = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, serie.getSerieId());
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                Book book = new Book(data.getString("title"), LocalDate.parse(data.getString("publicationDate")),
                        data.getInt("recommendedAge"), data.getBoolean("isDiscontinued"), getGenre(data.getString("genre")),
                        getType(data.getString("type")), getLanguage(data.getString("originalLanguage")),
                        getEdition(data.getInt("edition")));
                book.setBookId(data.getInt("bookId"));
                deleteBook(book);
            }
            sql = "delete from serie where serieId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, serie.getSerieId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }
    public void deleteEdition(Edition edition) throws SQLException{
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from book where edition = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, edition.getEditionId());
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                Book book = new Book(data.getString("title"), LocalDate.parse(data.getString("publicationDate")),
                        data.getInt("recommendedAge"),data.getBoolean("isDiscontinued"),getGenre(data.getString("genre")),
                        getType(data.getString("type")),getLanguage(data.getString("originalLanguage")),
                        getEdition(data.getInt("edition")));
                book.setBookId(data.getInt("bookId"));
                deleteBook(book);
            }
            sql = "delete from edition where editionId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, edition.getEditionId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public void updateBook (Book book) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "update book set title = ?, originalLanguage = ?, edition = ?, genre = ?, type = ?, " +
                    "publicationDate = ?, recommendedAge = ?, isDiscontinued = ?, serie = ? where bookId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            statement.setString(2,book.getOriginalLanguage().getName());
            statement.setInt(3,book.getEdition().getEditionId());
            statement.setString(4,book.getGenre().getName());
            statement.setString(5,book.getType().getName());
            java.sql.Date sqlPubDate = java.sql.Date.valueOf(book.getPublicationDate());
            statement.setDate(6,sqlPubDate);
            statement.setInt(7,book.getRecommendedAge());
            statement.setBoolean(8, book.getDiscontinued());
            if(book.getSerie() != null){
                statement.setInt(9,book.getSerie().getSerieId());
            }
            else {
                statement.setNull(9,java.sql.Types.INTEGER);
            }
            statement.setInt(10,book.getBookId());
            statement.executeUpdate();
            statement.close();
            deleteContributionFromBook(book);
            for(Contributor author : book.getAuthors()) {
                addContribution(book.getBookId(),author.getPersonId());
            }
            for(Contributor drawer : book.getDrawers()) {
                addContribution(book.getBookId(),drawer.getPersonId());
            }
            statement.close();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public void deleteExemplarFromBook(Book book) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "delete from exemplar where book = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, book.getBookId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public void deleteLendingFromBook(Book book) throws SQLException{
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select exemplarId from exemplar where book = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, book.getBookId());
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                sql = "update exemplar set lending = null where exemplarId = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, data.getInt("exemplarId"));
                statement.executeUpdate();
                sql = "delete from lending where exemplar = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, data.getInt("exemplarId"));
                statement.executeUpdate();
            }
            statement.close();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public void deleteContributionFromBook(Book book) throws SQLException {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "delete from contribution where book = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, book.getBookId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }
    @Override
    public ArrayList<Language> showLanguage() throws SQLException{
        try{
            ResultSet data = getData("select * from language");
            ArrayList<Language> languages = new ArrayList<>();
            while (data.next()){
                Language language = new Language(data.getString("name"));
                languages.add(language);
            }
            return languages;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }


    @Override
    public ArrayList<Edition> showEdition() throws SQLException{
        try{
            ResultSet data = getData("select * from edition");
            ArrayList<Edition> editions = new ArrayList<>();
            while (data.next()){
                Country country = new Country(data.getString("country"));
                Edition edition = new Edition(data.getString("name"),country);
                edition.setEditionId(data.getInt("editionId"));
                editions.add(edition);
            }
            return editions;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }
    @Override
    public ArrayList<Genre> showGenre() throws SQLException{
        try{
            ResultSet data = getData("select * from genre");
            ArrayList<Genre> genres = new ArrayList<>();
            while (data.next()){
                Genre genre = new Genre(data.getString("name"));
                genres.add(genre);
            }
            return genres;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }
    @Override
    public ArrayList<Type> showType() throws SQLException{
        try{
            ResultSet data = getData("select * from type");
            ArrayList<Type> types = new ArrayList<>();
            while (data.next()){
                Type type = new Type(data.getString("name"));
                types.add(type);
            }
            return types;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }
    @Override
    public ArrayList<Serie> showSerie() throws SQLException{
        try{
            ResultSet data = getData("select * from serie");
            ArrayList<Serie> series = new ArrayList<>();
            while (data.next()){
                Serie serie = new Serie(data.getString("name"));
                serie.setSerieId(data.getInt("serieId"));
                series.add(serie);
            }
            return series;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public Contributor searchContributor(String firstName, String lastName, String personType) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from person where firstName = ? and lastName = ? and personType = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, personType);
            ResultSet data = statement.executeQuery();
            Contributor contributor = null;
            while (data.next()){
                contributor = getContributeur(data);
            }
            statement.close();
            return contributor;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public ArrayList<Contributor> showAuthor() throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement("select * from person where personType = ?");
            statement.setString(1, "Author");
            ResultSet data = statement.executeQuery();
            ArrayList<Contributor> authors = new ArrayList<>();
            while (data.next()){
                Contributor author = getContributeur(data);
                authors.add(author);
            }
            statement.close();
            return authors;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public ArrayList<Contributor> showDrawer() throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement("select * from person where personType = ?");
            statement.setString(1, "Drawer");
            ResultSet data = statement.executeQuery();
            ArrayList<Contributor> drawers = new ArrayList<>();
            while (data.next()){
                Contributor drawer = getContributeur(data);
                drawers.add(drawer);
            }
            statement.close();
            return drawers;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public Genre getGenre(String genderName) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement("select * from genre where name = ?");
            statement.setString(1, genderName);
            ResultSet data = statement.executeQuery();
            Genre genre = null;
            while (data.next()){
                genre = new Genre(data.getString("name"));
            }
            statement.close();
            return genre;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public Type getType(String typeName) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement("select * from type where name = ?");
            statement.setString(1, typeName);
            ResultSet data = statement.executeQuery();
            Type type = null;
            while (data.next()){
                type = new Type(data.getString("name"));
            }
            statement.close();
            return type;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public Edition getEdition(Integer editionId) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement("select * from edition where editionId = ?");
            statement.setInt(1, editionId);
            ResultSet data = statement.executeQuery();
            Edition edition = null;
            while (data.next()){
                Country country = new Country(data.getString("country"));
                edition = new Edition(data.getString("name"),country);
                edition.setEditionId(data.getInt("editionId"));
            }
            statement.close();
            return edition;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public Edition getEdition(String editionName) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement("select * from edition where name = ?");
            statement.setString(1, editionName);
            ResultSet data = statement.executeQuery();
            Edition edition = null;
            while (data.next()){
                Country country = new Country(data.getString("country"));
                edition = new Edition(data.getString("name"),country);
                edition.setEditionId(data.getInt("editionId"));
            }
            statement.close();
            return edition;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    @Override
    public Book getBookById(Integer bookId) throws SQLException {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement("select * from book where bookId = ?");
            statement.setInt(1, bookId);
            ResultSet data = statement.executeQuery();
            data.next();
            Book book = new Book(data.getString("title"), LocalDate.parse(data.getString("publicationDate")),
                    data.getInt("recommendedAge"),data.getBoolean("isDiscontinued"),getGenre(data.getString("genre")),
                    getType(data.getString("type")),getLanguage(data.getString("originalLanguage")),
                    getEdition(data.getInt("edition")));
            book.setBookId(data.getInt("bookId"));
            statement.close();
            return book;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public Language getLanguage(String languageName) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement("select * from language where name = ?");
            statement.setString(1, languageName);
            ResultSet data = statement.executeQuery();
            Language language = null;
            while (data.next()){
                language = new Language(data.getString("name"));
            }
            statement.close();
            return language;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public Serie getSerie(String serieName) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement("select * from serie where name = ?");
            statement.setString(1, serieName);
            ResultSet data = statement.executeQuery();
            Serie serie = null;
            while (data.next()){
                serie = new Serie(data.getString("name"));
                serie.setSerieId(data.getInt("serieId"));
            }
            statement.close();
            return serie;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }
    public Serie getSerie(Integer serieId) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement("select * from serie where serieId = ?");
            statement.setInt(1, serieId);
            ResultSet data = statement.executeQuery();
            Serie serie = null;
            while (data.next()){
                serie = new Serie(data.getString("name"));
                serie.setSerieId(data.getInt("serieId"));
            }
            statement.close();
            return serie;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public Contributor getContributeur(ResultSet data) throws SQLException{
        Contributor contributor = new Contributor(data.getString("firstName"),data.getString("lastName"));
        contributor.setPersonId(data.getInt("personId"));
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
        contributor.setPersonType(data.getString("personType"));
        return contributor;
    }
}