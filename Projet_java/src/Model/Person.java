package Model;

import java.time.LocalDate;

public abstract class Person {
    private Integer personId;
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

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
    @Override
    public String toString(){
        return firstName + " " + lastName;
    }

}
