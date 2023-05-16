package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Lending {
    private Integer lendingId;
    private Borrower borrower;
    private ArrayList<Exemplar> exemplars;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Boolean isReturn;

    public Lending(Borrower borrower,LocalDate beginDate,LocalDate endDate){
        this.borrower = borrower;
        this.exemplars = new ArrayList<Exemplar>();
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.isReturn = false;
    }

    public void setLendingId(Integer lendingId) {
        this.lendingId = lendingId;
    }
    public void addExemplar(Exemplar ... exemplarsLend){
        exemplars.addAll(Arrays.asList(exemplarsLend));
    }

    public void isReturn(){
        this.isReturn = true;
    }

    public LocalDate getBeginDate(){
        return beginDate;
    }

    public int getLendingId(){
        return lendingId;
    }
}
