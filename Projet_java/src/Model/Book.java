package Model;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book {
    private Integer bookId;
    private String title;
    private LocalDate publicationDate;
    private Integer recommendedAge;
    private Boolean isDiscontinued;
    private ArrayList<Contributor> authors;
    private ArrayList<Contributor> drawers;
    private Genre genre;
    private Type type;
    private Language originalLanguage;

    private Serie serie;
    private Edition edition;

    public Book(String title,LocalDate publicationDate,Integer recommendedAge,Boolean isDiscontinued,Genre genre,
                Type type,Language originalLanguage,Serie serie,Edition edition){
        authors = new ArrayList<Contributor>();
        drawers = new ArrayList<Contributor>();
        this.title = title;
        this.publicationDate = publicationDate;
        this.recommendedAge = recommendedAge;
        this.isDiscontinued = isDiscontinued;
        this.genre = genre;
        this.type = type;
        this.originalLanguage = originalLanguage;
        this.serie = serie;
        this.edition = edition;
    }

    public Book(String title,LocalDate publicationDate,Integer recommendedAge,Boolean isDiscontinued,Genre genre,
                Type type,Language originalLanguage,Edition edition){
        this(title,publicationDate,recommendedAge,isDiscontinued,genre,type,originalLanguage,null,edition);
    }
    public void setBookId(Integer bookId){
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setRecommendedAge(Integer recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

    public void setDiscontinued(Boolean discontinued) {
        isDiscontinued = discontinued;
    }

    public void setAuthors(ArrayList<Contributor> authors) {
        this.authors = authors;
    }

    public void setDrawers(ArrayList<Contributor> drawers) {
        this.drawers = drawers;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setOriginalLanguage(Language originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public String getEditionName(){
        return edition.getName();
    }



    public String getAuthorList(){
        String authorList = "";
        if(authors.size() > 0) {
            for (Contributor author : authors) {
                authorList += author.toString() + ", ";
            }
            authorList.substring(authorList.length() - 2);
        }
        return authorList;
    }

    public String getDrawerList(){
        String drawerList = "";
        if(drawers.size() > 0) {
            for (Contributor drawer : drawers) {
                drawerList += drawer.toString() + ", ";
            }
            drawerList.substring(drawerList.length() - 2);
        }
        return drawerList;
    }

    public void setSerie(Serie serie){
        this.serie = serie;
    }

    public String getSerieName(){
        if(serie == null)
            return "";
        return serie.getName();
    }
    public Genre getGenre() {
        return genre;
    }

    public Type getType() {
        return type;
    }

    public Language getOriginalLanguage() {
        return originalLanguage;
    }

    public Serie getSerie() {
        return serie;
    }

    public Edition getEdition() {
        return edition;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Integer getRecommendedAge() {
        return recommendedAge;
    }

    public Boolean getDiscontinued() {
        return isDiscontinued;
    }

    public ArrayList<Contributor> getAuthors() {
        return authors;
    }

    public ArrayList<Contributor> getDrawers() {
        return drawers;
    }

    public void addAuthor(Contributor ... authorsList){
        authors.addAll(Arrays.asList(authorsList));
    }
    public void addDrawer(Contributor ... drawersList){
        drawers.addAll(Arrays.asList(drawersList));
    }


}
