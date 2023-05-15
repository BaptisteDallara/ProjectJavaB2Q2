package DataAccess;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Business.BookManager;


public class AuthorDBAccess implements AuthorDataAccess{
    
    public ResultSet getData(String sql){
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            return data;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public ArrayList<Contributor> getAllAuthor(){
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            PreparedStatement statement = connection.prepareStatement("select * from person where personType = ?");
            statement.setString(1, "Author");
            ResultSet data = statement.executeQuery();
            ArrayList<Contributor> auteurs = new ArrayList<>();
            while (data.next()) {
                Contributor author = new Contributor(data.getString("firstName"), data.getString("lastName"));
                author.setPersonId(data.getInt("personId"));
                auteurs.add(author);
            }
            return auteurs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addAuthor(Contributor author){

    }
    public void deleteAuthor(Contributor author){

    }
    
    public ArrayList<Serie> getAllSeries(String author){
        try {
            String[] authorNameSplit = author.split(" ");
            String authorFirstName = authorNameSplit[0];
            String authorLastName = authorNameSplit[1];
            if(authorNameSplit.length > 2 && authorNameSplit[2] != null){
                authorLastName += " " + authorNameSplit[2];
            }
            Connection connection = SingletonConnexion.getUniqueConnexion();
            StringBuilder sql = new StringBuilder("select distinct serie.serieId, serie.name from serie ");
            sql.append("inner join book on serie.serieId = book.serie ");
            sql.append("inner join contribution on book.bookId = contribution.book ");
            sql.append("inner join person on person.personId = contribution.person ");
            sql.append("where person.firstName = ? and person.lastName = ?");
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            statement.setString(1, authorFirstName);
            statement.setString(2, authorLastName);
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

    public ArrayList<Book> getAllBooks(String author, String serie, BookManager bookManager){
        try {
            String[] authorNameSplit = author.split(" ");
            String authorFirstName = authorNameSplit[0];
            String authorLastName = authorNameSplit[1];
            if(authorNameSplit.length > 2 && authorNameSplit[2] != null){
                authorLastName += " " + authorNameSplit[2];
            }

            if (serie != null) {
                Connection connection = SingletonConnexion.getUniqueConnexion();
                StringBuilder sql = new StringBuilder("select distinct * from book ");
                sql.append("inner join contribution on book.bookId = contribution.book ");
                sql.append("inner join person on person.personId = contribution.person ");
                sql.append("inner join serie on serie.serieId = book.serie ");
                sql.append("where person.firstName = ? and person.lastName = ? and serie.name = ?");
                PreparedStatement statement = connection.prepareStatement(sql.toString());
                statement.setString(1, authorFirstName);
                statement.setString(2, authorLastName);
                statement.setString(3, serie);
                ResultSet data = statement.executeQuery();
                ArrayList<Book> books = new ArrayList<>();
                while (data.next()) {
                    Book book = new Book(data.getString("title"), LocalDate.parse(data.getString("publicationDate")),
                    data.getInt("recommendedAge"),data.getBoolean("isDiscontinued"),bookManager.getGenre(data.getString("genre")),
                    bookManager.getType(data.getString("type")), bookManager.getLanguage(data.getString("originalLanguage")),
                    bookManager.getEdition(data.getInt("edition")));
                    book.setBookId(data.getInt("bookId"));
                    if (!listContains(books, book.getTitle())) {
                        books.add(book);
                    }
                }
            return books;
            } else {
                Connection connection = SingletonConnexion.getUniqueConnexion();
                StringBuilder sql = new StringBuilder("select distinct * from book ");
                sql.append("inner join contribution on book.bookId = contribution.book ");
                sql.append("inner join person on person.personId = contribution.person ");
                sql.append("where person.firstName = ? and person.lastName = ?");
                PreparedStatement statement = connection.prepareStatement(sql.toString());
                statement.setString(1, authorFirstName);
                statement.setString(2, authorLastName);
                ResultSet data = statement.executeQuery();
                ArrayList<Book> books = new ArrayList<>();
                while (data.next()) {
                    Book book = new Book(data.getString("title"), LocalDate.parse(data.getString("publicationDate")),
                    data.getInt("recommendedAge"),data.getBoolean("isDiscontinued"),bookManager.getGenre(data.getString("genre")),
                    bookManager.getType(data.getString("type")), bookManager.getLanguage(data.getString("originalLanguage")),
                    bookManager.getEdition(data.getInt("edition")));
                    book.setBookId(data.getInt("bookId"));
                    if (!listContains(books, book.getTitle())) {
                        books.add(book);
                    }
                }
            return books;
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean listContains(ArrayList<Book> books,String title){
        for(Book book : books){
            if(book.getTitle().equals(title)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<ResultResearch> getSearchBookAuthor(String author, String serie, String book) {
        try {
            String[] authorNameSplit = author.split(" ");
            String authorFirstName = authorNameSplit[0];
            String authorLastName = authorNameSplit[1];
            if(authorNameSplit.length > 2 && authorNameSplit[2] != null){
                authorLastName += " " + authorNameSplit[2];
            }

            if (serie != null) {
                Connection connection = SingletonConnexion.getUniqueConnexion();
                StringBuilder sql = new StringBuilder("select distinct title, edition.name, exemplar.language from book ");
                sql.append("inner join contribution on book.bookId = contribution.book ");
                sql.append("inner join person on person.personId = contribution.person ");
                sql.append("inner join serie on serie.serieId = book.serie ");
                sql.append("inner join edition on edition.editionId = book.edition ");
                sql.append("inner join exemplar on exemplar.book = book.bookId ");
                sql.append("where person.firstName = ? and person.lastName = ? and serie.name = ? and book.title = ?");
                PreparedStatement statement = connection.prepareStatement(sql.toString());
                statement.setString(1, authorFirstName);
                statement.setString(2, authorLastName);
                statement.setString(3, serie);
                statement.setString(4, book);
                ResultSet data = statement.executeQuery();
                data.next();
                SearchBookAuthor searchBookAuthor = new SearchBookAuthor(data.getString(1), data.getString(2), data.getString(3));
                while (data.next()) {
                    searchBookAuthor.setLanguage(data.getString(3));
                }
                ArrayList<ResultResearch> resultResearchs = new ArrayList<>();
                ResultResearch resultTitle = new ResultResearch(searchBookAuthor.getTitle());
                resultResearchs.add(resultTitle);
                ResultResearch resultEdition = new ResultResearch(searchBookAuthor.getEdition());
                resultResearchs.add(resultEdition);
                ResultResearch resultLanguage = new ResultResearch(searchBookAuthor.getLanguage());
                resultResearchs.add(resultLanguage);
                return resultResearchs;
            } else {
                Connection connection = SingletonConnexion.getUniqueConnexion();
                StringBuilder sql = new StringBuilder("select distinct title, edition.name, exemplar.language from book ");
                sql.append("inner join contribution on book.bookId = contribution.book ");
                sql.append("inner join person on person.personId = contribution.person ");
                sql.append("inner join edition on edition.editionId = book.edition ");
                sql.append("inner join exemplar on exemplar.book = book.bookId ");
                sql.append("where person.firstName = ? and person.lastName = ? and book.title = ?");
                PreparedStatement statement = connection.prepareStatement(sql.toString());
                statement.setString(1, authorFirstName);
                statement.setString(2, authorLastName);
                statement.setString(3, book);
                ResultSet data = statement.executeQuery();
                data.next();
                SearchBookAuthor searchBookAuthor = new SearchBookAuthor(data.getString(1), data.getString(2), data.getString(3));
                while (data.next()) {
                    searchBookAuthor.setLanguage(data.getString("language.name"));
                }
                ArrayList<ResultResearch> resultResearchs = new ArrayList<>();
                ResultResearch resultTitle = new ResultResearch(searchBookAuthor.getTitle());
                resultResearchs.add(resultTitle);
                ResultResearch resultEdition = new ResultResearch(searchBookAuthor.getEdition());
                resultResearchs.add(resultEdition);
                ResultResearch resultLanguage = new ResultResearch(searchBookAuthor.getLanguage());
                resultResearchs.add(resultLanguage);
                return resultResearchs;
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
