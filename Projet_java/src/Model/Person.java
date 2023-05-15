package Model;

import java.time.LocalDate;

public abstract class Person {
    private Integer personId;

    private String personType;
    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public Person(String firstName,String lastName,LocalDate birthday){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public Person(String firstName,String lastName){
        this(firstName,lastName,null);
    }

    public void setPersonId(Integer personId){
        this.personId = personId;
    }
    public void setPersonType(String personType){
        this.personType = personType;
    }

    public String getPersonType(){
        return personType;
    }
    public Integer getPersonId(){
        return personId;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getBirthday(){
        if(birthday == null){
            return "";
        }
        return birthday.toString();
    }
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }
    @Override
    public String toString(){
        return firstName + " " + lastName;
    }

}
