package DataAccess;

import java.util.*;
import Model.*;

import java.sql.SQLException;
import java.time.LocalDate;

public interface LendingDataAccess {
    public ArrayList<Borrower> getAllBorrowers() throws SQLException;
    public ArrayList<Exemplar> getAllAvailableExemplar() throws SQLException;
    public Boolean getDelay(Borrower selectedBorrower,LocalDate date) throws SQLException;
    public ArrayList<Exemplar> getAllLendedExemplar() throws SQLException;
    public void returned(Exemplar exemplar) throws SQLException;
    public void addLending(Exemplar exemplar,Borrower borrower) throws SQLException;

    public ArrayList<Lending> getAllLendings(String borrower, LocalDate date) throws SQLException;

    public ArrayList<ResultResearch> getSearchLending(int lendingId) throws SQLException;
}
