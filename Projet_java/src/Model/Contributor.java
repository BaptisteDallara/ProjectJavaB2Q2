package Model;

import java.time.LocalDate;

public class Contributor extends Person{
    private LocalDate death;
    private Country nationality;

    public Contributor(String firstName,String lastName,LocalDate birthday,LocalDate death,Country nationality){
        super(firstName,lastName,birthday);
        setDeath(death);
        setNationality(nationality);
    }
    public Contributor(String firstName,String lastName,LocalDate birthday,Country nationality){
        this(firstName,lastName,birthday,null,nationality);
    }
    public Contributor(String firstName,String lastName){
        this(firstName,lastName,null,null,null);
    }

    public void setDeath(LocalDate death) {
        this.death = death;
    }

    public void setNationality(Country nationality){
        this.nationality = nationality;
    }

    @Override
    public String getFullName(){
        return super.getFullName();
    }

    @Override
    public String getPersonType(){
        return super.getPersonType();
    }

    @Override
    public String getBirthday(){
        return super.getBirthday();
    }

    public String getDeath(){
        if(death == null){
            return "";
        }
        return death.toString();
    }
}
