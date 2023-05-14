package DataAccess;

import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Business.BookManager;

public interface AuthorDataAccess {
    public ArrayList<Contributor> getAllAuthor();
    public void addAuthor(Contributor author);
    public void deleteAuthor(Contributor author);
    public ResultSet getData(String sql);
    public ArrayList<Serie> getAllSeries(String author);
    public ArrayList<Book> getAllBooks(String author, String serie, BookManager bookManager);
}
