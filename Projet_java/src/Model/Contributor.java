package Model;

import java.time.LocalDate;

public class Contributor extends Person{
    private LocalDate death;
    private Country nationality;

    public Contributor(Integer personId,String firstName,String lastName,LocalDate birthday,LocalDate death,Country nationality){
        super(personId,firstName,lastName,birthday);
        setDeath(death);
        setNationality(nationality);
    }
    public Contributor(Integer personId,String firstName,String lastName,LocalDate birthday,Country nationality){
        this(personId,firstName,lastName,birthday,null,nationality);
    }
    public Contributor(Integer personId,String firstName,String lastName){
        this(personId,firstName,lastName,null,null,null);
    }

    public void setDeath(LocalDate death) {
        this.death = death;
    }

    public void setNationality(Country nationality){
        this.nationality = nationality;
    }

}
