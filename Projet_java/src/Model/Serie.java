package Model;

public class Serie {
    private Integer serieId;
    private String name;
    public Serie(String name){
        this.name = name;
    }

    public Integer setSerieId(Integer serieId){
        return this.serieId = serieId;
    }
    public Integer getSerieId() {
        return serieId;
    }

    public String getName() {
        return name;
    }
}
