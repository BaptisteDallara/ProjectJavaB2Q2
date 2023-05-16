package DataAccess;

import java.util.*;
import Model.*;
import java.time.LocalDate;

public interface LendingDataAccess {
    public ArrayList<Borrower> getAllBorrowers();
    public ArrayList<Exemplar> getAllAvailableExemplar();
    public Boolean getDelay(Borrower selectedBorrower,LocalDate date);
    public void addLending(Exemplar exemplar,Borrower borrower);

    public ArrayList<Lending> getAllLendings(String borrower, LocalDate date);

    public ArrayList<ResultResearch> getSearchLending(int lendingId);
}
