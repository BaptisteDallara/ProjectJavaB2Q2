package Model;

import java.time.LocalDate;

public abstract class Person {
    private Integer personId;
    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public Person(Integer personId,String firstName,String lastName,LocalDate birthday){
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public Person(Integer personId,String firstName,String lastName){
        this(personId,firstName,lastName,null);
    }


}
