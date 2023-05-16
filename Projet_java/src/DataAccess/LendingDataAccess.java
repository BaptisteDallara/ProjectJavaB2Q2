package DataAccess;

import java.util.*;
import Model.*;
import java.time.LocalDate;

public interface LendingDataAccess {
    public ArrayList<Borrower> getAllBorrowers();
    public ArrayList<Exemplar> getAllAvailableExemplar();
    public void addLending(Exemplar exemplar,Borrower borrower);

    public ArrayList<Lending> getAllLendings(String borrower, LocalDate date);

    public ArrayList<ResultResearch> getSearchLending(int lendingId);
}
