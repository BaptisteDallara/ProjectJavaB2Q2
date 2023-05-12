package Model;

public class Serie {
    private Integer serieId;
    private String name;
    public Serie(Integer serieId,String name){
        this.serieId = serieId;
        this.name = name;
    }

    public Integer getSerieId() {
        return serieId;
    }

    public String getName() {
        return name;
    }
}
