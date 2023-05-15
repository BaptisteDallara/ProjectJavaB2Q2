package Model;

import java.util.ArrayList;

public class SearchSerie {
    private ArrayList<String> title;
    private ArrayList<String> author;
    private ArrayList<String> drawer;
    private ArrayList<String> edition;

    public SearchSerie(String title, String edition) {
        this.title = new ArrayList<String>();
        this.title.add(title);
        this.author = new ArrayList<String>();
        this.author.add("Auteur : ");
        this.drawer = new ArrayList<String>();
        this.drawer.add("Dessinateur : ");
        this.edition = new ArrayList<String>();
        this.edition.add(edition);
    }

    public String getTitle() {
        StringBuilder title = new StringBuilder();
        for (String str : this.title) {
            title.append(str);
            title.append(", ");
        }
        return title.toString();
    }

    public ArrayList<String> getTitleList() {
        return this.title;
    }

    public String getAuthor() {
        StringBuilder author = new StringBuilder();
        author.append(this.author.get(0));
        if (this.author.size() > 1) {
            author.append(this.author.get(1));
        }
        for (int i = 2; i < this.author.size(); i++) {
            author.append(", ");
            author.append(this.author.get(i));
        }
        return author.toString();
    }

    public ArrayList<String> getAuthorList() {
        return this.author;
    }

    public String getDrawer() {
        StringBuilder drawer = new StringBuilder();
        drawer.append(this.drawer.get(0));
        if (this.drawer.size() > 1) {
            drawer.append(this.drawer.get(1));
        }
        for (int i = 2; i < this.drawer.size(); i++) {
            drawer.append(", ");
            drawer.append(this.drawer.get(i));
        }
        return drawer.toString();
    }

    public ArrayList<String> getDrawerList() {
        return this.drawer;
    }

    public String getEdition() {
        StringBuilder edition = new StringBuilder();
        for (String str : this.edition) {
            edition.append(str);
            edition.append(", ");
        }
        return edition.toString();
    }

    public ArrayList<String> getEditionList() {
        return this.edition;
    }

    public void setTitle(String title) {
        this.title.add(title);
    }

    public void setAuthor(String author) {
        this.author.add(author);
    }

    public void setDrawer(String drawer) {
        this.drawer.add(drawer);
    }

    public void setEdition(String edition) {
        this.edition.add(edition);
    }
}
