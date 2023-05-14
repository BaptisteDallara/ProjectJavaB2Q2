package Model;

import java.time.LocalDate;

public class Borrower extends Person{
    private Integer phoneNumber;
    private String email;

    public Borrower(Integer personId, String firstName, String lastName, LocalDate birthday,Integer phoneNumber,String email){
        super(firstName,lastName,birthday);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Borrower(Integer personId, String firstName, String lastName,Integer phoneNumber,String email){
        this(personId,firstName,lastName,null,phoneNumber,email);
    }

}
