package Business;

import java.sql.Date;
import java.util.ArrayList;
import java.time.LocalDate;

import DataAccess.*;
import Exception.ExceptionSQL;
import Model.*;

public class LendingManager {
    private LendingDataAccess dao;

    public LendingManager(){
        this.dao = new LendingDBAccess();
    }

    public ArrayList<Exemplar> getAllAvailableExemplar() throws ExceptionSQL{
        try {
            return dao.getAllAvailableExemplar();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Exemplar> getAllLendedExemplar() throws ExceptionSQL{
        try {
            return dao.getAllLendedExemplar();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public void returned(Exemplar exemplar) throws ExceptionSQL{
        try {
            dao.returned(exemplar);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public Boolean getDelay(Borrower selectedBorrower,LocalDate date) throws ExceptionSQL {
        try {
            return dao.getDelay(selectedBorrower,date);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public void addLending(Exemplar exemplar,Borrower borrower) throws ExceptionSQL{
        try {
            dao.addLending(exemplar,borrower);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Borrower> getAllBorrowers() throws ExceptionSQL{
        try {
            return dao.getAllBorrowers();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Lending> getAllLendings(String borrower, LocalDate date) throws ExceptionSQL{
        try {
            return dao.getAllLendings(borrower, date);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<ResultResearch> getSearchLending(int lendingId) throws ExceptionSQL{
        try {
            return dao.getSearchLending(lendingId);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }
}
