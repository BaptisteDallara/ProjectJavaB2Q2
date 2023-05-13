package Model;

public class Edition {
    private Integer editionId;
    private String name;
    private Country location;

    public Edition(String name,Country location){
        this.name = name;
        this.location = location;
    }
    public Integer setEditionId(Integer editionId){
        return this.editionId = editionId;
    }

    public String getName() {
        return name;
    }

    public Integer getEditionId() {
        return editionId;
    }
}
