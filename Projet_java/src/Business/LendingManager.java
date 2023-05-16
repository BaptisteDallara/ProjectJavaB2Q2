package Business;

import java.sql.Date;
import java.util.ArrayList;
import java.time.LocalDate;

import DataAccess.*;
import Model.*;

public class LendingManager {
    private LendingDataAccess dao;

    public LendingManager(){
        this.dao = new LendingDBAccess();
    }

    public ArrayList<Exemplar> getAllAvailableExemplar(){
        return dao.getAllAvailableExemplar();
    }

    public void addLending(Exemplar exemplar,Borrower borrower){
        dao.addLending(exemplar,borrower);
    }

    public ArrayList<Borrower> getAllBorrowers(){
        return dao.getAllBorrowers();
    }

    public ArrayList<Lending> getAllLendings(String borrower, LocalDate date){
        return dao.getAllLendings(borrower, date);
    }

    public ArrayList<ResultResearch> getSearchLending(int lendingId){
        return dao.getSearchLending(lendingId);
    }
}
