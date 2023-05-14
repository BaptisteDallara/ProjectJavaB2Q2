package Business;

import DataAccess.*;
import Model.*;

import java.util.ArrayList;

public class AuthorManager {
    private AuthorDataAccess dao;
    private BookDataAccess bookDao;

    public AuthorManager(){
        this.dao = new AuthorDBAccess();
        this.bookDao = new BookDBAccess();
    }
    public ArrayList<Contributor> getAllAuthor(){
        return dao.getAllAuthor();
    }

    public ArrayList<Serie> getAllSeries(String author){
        return dao.getAllSeries(author);
    }

    public ArrayList<Book> getAllBooks(String author, String serie, BookDataAccess bookDao){
        return dao.getAllBooks(author, serie, bookDao);
    }

    public void addAuthor(Contributor author){
        dao.addAuthor(author);
    }

    public void deleteAuthor(Contributor author){
        dao.deleteAuthor(author);
    }
}
