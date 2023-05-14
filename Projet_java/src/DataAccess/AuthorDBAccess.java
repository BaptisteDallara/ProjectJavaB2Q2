package DataAccess;

import Model.*;
import Business.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

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
            ResultSet data = getData("select * from person where personType = 'Author'");
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

            StringBuilder sql = new StringBuilder("select distinct serie.serieId, serie.name from serie ");
            sql.append("inner join book on serie.serieId = book.serie ");
            sql.append("inner join contribution on book.bookId = contribution.book ");
            sql.append("inner join person on person.personId = contribution.person ");
            sql.append("where person.firstName = '");
            sql.append(authorFirstName);
            sql.append("' and person.lastName = '");
            sql.append(authorLastName);
            sql.append("'");
            ResultSet data = getData(sql.toString());
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
                StringBuilder sql = new StringBuilder("select distinct * from book ");
                sql.append("inner join contribution on book.bookId = contribution.book ");
                sql.append("inner join person on person.personId = contribution.person ");
                sql.append("inner join serie on serie.serieId = book.serie ");
                sql.append("where person.firstName = '");
                sql.append(authorFirstName);
                sql.append("' and person.lastName = '");
                sql.append(authorLastName);
                sql.append("' and serie.name = '");
                sql.append(serie);
                sql.append("'");
                ResultSet data = getData(sql.toString());
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
                StringBuilder sql = new StringBuilder("select distinct * from book ");
                sql.append("inner join contribution on book.bookId = contribution.book ");
                sql.append("inner join person on person.personId = contribution.person ");
                sql.append("where person.firstName = '");
                sql.append(authorFirstName);
                sql.append("' and person.lastName = '");
                sql.append(authorLastName);
                sql.append("'");
                ResultSet data = getData(sql.toString());
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
}
