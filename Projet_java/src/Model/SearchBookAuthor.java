package Model;

public class SearchBookAuthor {
    private String title;
    private String edition;
    private String language;

    public SearchBookAuthor(String title, String edition, String language) {
        this.title = title;
        this.edition = edition;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public String getEdition() {
        return edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        StringBuilder addLanguage = new StringBuilder(this.language);
        addLanguage.append(", ");
        addLanguage.append(language);
        this.language = addLanguage.toString();
    }
}
