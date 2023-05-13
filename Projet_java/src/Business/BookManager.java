package Business;

import DataAccess.BookDBAccess;
import DataAccess.BookDataAccess;
import Model.*;

import java.util.ArrayList;

public class BookManager {
    private BookDataAccess dao;

    public BookManager(){
        this.dao = new BookDBAccess();
    }
    public ArrayList<Book> getAllBook(){
        return dao.getAllBook();
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
    public ArrayList<Contributor> showAuthor(){
        return dao.showAuthor();
    }
    public ArrayList<Contributor> showDrawer(){
        return dao.showDrawer();
    }
    public Genre getGenre(String genderName){
        return dao.getGenre(genderName);
    }
    public Serie getSerie(String serieName){
        return dao.getSerie(serieName);
    }
    public Edition getEdition(String editionName){
        return dao.getEdition(editionName);
    }
    public Language getLanguage(String languageName){
        return dao.getLanguage(languageName);
    }

    public Type getType(String typeName){
        return dao.getType(typeName);
    }
    public Contributor searchContributor(String firstName, String lastName,String personType){
        return dao.searchContributor(firstName, lastName,personType);
    }


}
