package DataAccess;

import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Business.BookManager;

public interface AuthorDataAccess {
    public ArrayList<Contributor> getAllAuthor() throws SQLException;
    public ResultSet getData(String sql) throws SQLException;
    public ArrayList<Serie> getAllSeries(String author) throws SQLException;
    public ArrayList<Book> getAllBooks(String author, String serie, BookManager bookManager) throws SQLException;
    public ArrayList<ResultResearch> getSearchBookAuthor (String author, String serie, String book) throws SQLException;
}
