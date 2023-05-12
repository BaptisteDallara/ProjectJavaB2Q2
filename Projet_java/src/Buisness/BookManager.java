package Buisness;

import DataAccess.BookDBAccess;
import DataAccess.BookDataAccess;
import Model.*;

import java.util.ArrayList;

public class BookManager {
    private BookDataAccess dao;

    public BookManager(){
        this.dao = new BookDBAccess();
    }

    public void addBook(Book book){
        dao.addBook(book);
    }

    public ArrayList<Language> showLanguage(){
        return dao.showLanguage();
    }
    public ArrayList<Edition> showEdition(){
        return dao.showEdition();
    }
    public ArrayList<Genre> showGenre(){
        return dao.showGenre();
    }

    public ArrayList<Type> showType(){
        return dao.showType();
    }

    public ArrayList<Serie> showSerie(){
        return dao.showSerie();
    }


}
