package Model;

public class SearchBookAuthor {
    private String edition;
    private String language;
    private String genre;

    public SearchBookAuthor(String edition, String language, String genre) {
        this.edition = edition;
        this.language = language;
        this.genre = genre;
    }

    public String getEdition() {
        return edition;
    }

    public String getLanguage() {
        return language;
    }

    public String getGenre() {
        return genre;
    }

    public void setLanguage(String language) {
        StringBuilder addLanguage = new StringBuilder(this.language);
        addLanguage.append(", ");
        addLanguage.append(language);
        this.language = addLanguage.toString();
    }
}
