package DataAccess;

import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookDataAccess {
    public ArrayList<Book> getAllBook() throws SQLException;
    public void addBook(Book book) throws SQLException;
    public void deleteBook(Book book) throws SQLException;
    public ResultSet getData(String sql) throws SQLException;
    public ArrayList<Language> showLanguage() throws SQLException;
    public ArrayList<Edition> showEdition() throws SQLException;
    public ArrayList<Genre> showGenre() throws SQLException;
    public ArrayList<Type> showType() throws SQLException;
    public ArrayList<Serie> showSerie() throws SQLException;
    public Contributor getContributeur(ResultSet data) throws SQLException;
    public Contributor searchContributor(String firstName, String lastName,String personType) throws SQLException;
    public Genre getGenre(String genderName) throws SQLException;
    public Type getType(String typeName) throws SQLException;
    public Serie getSerie(String serieName) throws SQLException;
    public Edition getEdition(String editionName) throws SQLException;
    public Edition getEdition(Integer editionId) throws SQLException;
    public Language getLanguage(String languageName) throws SQLException;
    public void addContribution(Integer bookId, Integer contributorId) throws SQLException;
    public Book getBook(String bookName) throws SQLException;
    public Book getBookById(Integer bookId) throws SQLException;
    public void deleteEdition(Edition edition) throws SQLException;
    public void deleteSerie(Serie serie) throws SQLException;
    public void updateBook(Book book) throws SQLException;
    public ArrayList<Contributor> showAuthor() throws SQLException;
    public ArrayList<Contributor> showDrawer() throws SQLException;

}
