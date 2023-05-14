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
    public Contributor getContributeur(ResultSet data) throws SQLException;
    public Contributor searchContributor(String firstName, String lastName,String personType);
    public Genre getGenre(String genderName);
    public Type getType(String typeName);
    public Serie getSerie(String serieName);
    public Edition getEdition(String editionName);
    public Edition getEdition(Integer editionId);
    public Language getLanguage(String languageName);
    public void addContribution(Integer bookId, Integer contributorId);
    public ArrayList<Contributor> showAuthor();
    public ArrayList<Contributor> showDrawer();

}
