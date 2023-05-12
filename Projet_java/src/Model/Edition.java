package Model;

public class Edition {
    private Integer editionId;
    private String name;
    private Country location;

    public Edition(Integer editionId,String name,Country location){
        this.editionId = editionId;
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Integer getEditionId() {
        return editionId;
    }
}
