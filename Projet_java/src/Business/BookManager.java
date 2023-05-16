package Business;

import DataAccess.BookDBAccess;
import DataAccess.BookDataAccess;
import Model.*;
import Exception.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookManager {
    private BookDataAccess dao;

    public BookManager(){
        this.dao = new BookDBAccess();
    }
    public ArrayList<Book> getAllBook() throws ExceptionSQL {
        try {
            return dao.getAllBook();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public void addBook(Book book) throws ExceptionSQL{
        try {
            dao.addBook(book);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public void updateBook(Book book) throws ExceptionSQL{
        try {
            dao.updateBook(book);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public void deleteBook(Book book) throws ExceptionSQL{
        try {
            dao.deleteBook(book);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Language> showLanguage() throws ExceptionSQL{
        try {
            return dao.showLanguage();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }
    public ArrayList<Edition> showEdition() throws ExceptionSQL{
        try {
            return dao.showEdition();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }
    public ArrayList<Genre> showGenre() throws ExceptionSQL{
        try {
            return dao.showGenre();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Type> showType() throws ExceptionSQL{
        try {
            return dao.showType();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Serie> showSerie() throws ExceptionSQL{
        try {
            return dao.showSerie();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Contributor> showAuthor() throws ExceptionSQL{
        try {
            return dao.showAuthor();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Contributor> showDrawer() throws ExceptionSQL{
        try {
            return dao.showDrawer();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public Genre getGenre(String genderName) throws ExceptionSQL{
        try {
            return dao.getGenre(genderName);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public Serie getSerie(String serieName) throws ExceptionSQL{
        try {
            return dao.getSerie(serieName);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public Edition getEdition(String editionName) throws ExceptionSQL{
        try {
            return dao.getEdition(editionName);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public Edition getEdition(Integer editionId) throws ExceptionSQL{
        try {
            return dao.getEdition(editionId);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }
    public void deleteEdition(Edition edition) throws ExceptionSQL{
        try {
            dao.deleteEdition(edition);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }
    public void deleteSerie(Serie serie) throws ExceptionSQL{
        try {
            dao.deleteSerie(serie);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public Book getBook(String bookName) throws ExceptionSQL{
        try {
            return dao.getBook(bookName);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public Language getLanguage(String languageName) throws ExceptionSQL{
        try {
            return dao.getLanguage(languageName);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public Type getType(String typeName) throws ExceptionSQL{
        try {
            return dao.getType(typeName);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }
    public Contributor searchContributor(String firstName, String lastName,String personType) throws ExceptionSQL{
        try {
            return dao.searchContributor(firstName,lastName,personType);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }
}
