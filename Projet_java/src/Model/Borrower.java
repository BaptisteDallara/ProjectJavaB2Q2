package Model;

import java.time.LocalDate;

public class Borrower extends Person{
    private Integer phoneNumber;
    private String email;

    public Borrower(String firstName, String lastName, LocalDate birthday,Integer phoneNumber,String email){
        super(firstName,lastName,birthday);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Borrower(String firstName, String lastName,Integer phoneNumber,String email){
        this(firstName,lastName,null,phoneNumber,email);
    }

    public Borrower(String firstName, String lastName){
        this(firstName,lastName,null,null,null);
    }

    public String getDeath(){
        return "";
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
