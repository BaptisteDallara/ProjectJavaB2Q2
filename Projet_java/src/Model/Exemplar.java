package Model;

public class Exemplar {
    private Integer exemplarId;
    private Integer nbPages;

    private Lending lending;
    private Status state;
    private Language language;
    private double price;
    private double lendingPrice;

    private Book book;

    private Storage storage;

    public Exemplar(Book book, Language language, Integer nbPages, double price, double lendingPrice, Storage storage){
        this.book = book;
        this.language = language;
        this.nbPages = nbPages;
        this.price = price;
        this.lendingPrice = lendingPrice;
        this.storage = storage;
    }

    public void setExemplarId(Integer exemplarId) {
        this.exemplarId = exemplarId;
    }

    public Integer getExemplarId() {
        return exemplarId;
    }

    public Integer getNbPages() {
        return nbPages;
    }

    public Lending getLending() {
        return lending;
    }

    public Status getState() {
        return state;
    }

    public Language getLanguage() {
        return language;
    }

    public String getLanguageName(){
        return language.getName();
    }

    public double getPrice() {
        return price;
    }

    public double getLendingPrice() {
        return lendingPrice;
    }

    public Book getBook() {
        return book;
    }

    public String getBookTitle(){
        return book.getTitle();
    }

    public Storage getStorage() {
        return storage;
    }

    public String getPosition(){
        StringBuilder position = new StringBuilder("Ro : ");
        position.append(storage.getRoom());
        position.append("Ra : ");
        position.append(storage.getRack());
        position.append("Li : ");
        position.append(storage.getLine());
        return position.toString();
    }
}
