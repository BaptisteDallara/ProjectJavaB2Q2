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

    public ArrayList<Book> getAllBooks(String author, String serie, BookManager bookDao){
        return dao.getAllBooks(author, serie, bookDao);
    }

    public ArrayList<ResultResearch> getSearchBookAuthor (String author, String serie, String book){
        return dao.getSearchBookAuthor(author, serie, book);
    }
}
