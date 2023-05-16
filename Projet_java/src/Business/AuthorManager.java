package Business;

import DataAccess.*;
import Model.*;
import Exception.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorManager {
    private AuthorDataAccess dao;

    public AuthorManager(){
        this.dao = new AuthorDBAccess();
    }

    public ArrayList<Contributor> getAllAuthor() throws ExceptionSQL{
        try {
            return dao.getAllAuthor();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Serie> getAllSeries(String author) throws ExceptionSQL{
        try {
            return dao.getAllSeries(author);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Book> getAllBooks(String author, String serie, BookManager bookDao) throws ExceptionSQL{
        try {
            return dao.getAllBooks(author, serie, bookDao);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<ResultResearch> getSearchBookAuthor (String author, String serie, String book) throws ExceptionSQL{
        try {
            return dao.getSearchBookAuthor(author, serie, book);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }
}
