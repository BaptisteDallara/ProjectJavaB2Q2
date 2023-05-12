package DataAccess;

import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookDataAccess {
    public ArrayList<Book> getAllBook();
    public void addBook(Book book);
    public void deleteBook(Book book);
    public ResultSet getData(String sql);
    public ArrayList<Language> showLanguage();
    public ArrayList<Edition> showEdition();
    public ArrayList<Genre> showGenre();
    public ArrayList<Type> showType();
    public ArrayList<Serie> showSerie();

}
