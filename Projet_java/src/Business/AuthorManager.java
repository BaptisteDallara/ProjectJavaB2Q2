package Business;

import DataAccess.*;
import Model.*;

import java.util.ArrayList;

public class AuthorManager {
    private AuthorDataAccess dao;

    public AuthorManager(){
        this.dao = new AuthorDBAccess();
    }
    public ArrayList<Contributor> getAllAuthor(){
        return dao.getAllAuthor();
    }

    public ArrayList<Serie> getAllSeries(String author){
        return dao.getAllSeries(author);
    }

    public ArrayList<Book> getAllBooks(String author, String serie, BookManager bookManager){
        return dao.getAllBooks(author, serie, bookManager);
    }

    public void addAuthor(Contributor author){
        dao.addAuthor(author);
    }

    public void deleteAuthor(Contributor author){
        dao.deleteAuthor(author);
    }
}
