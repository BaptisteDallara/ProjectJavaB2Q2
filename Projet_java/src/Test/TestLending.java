package Test;

import Business.LendingManager;
import Model.Borrower;
import Model.Exemplar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestLending {
    private LendingManager lendingManager;

    @BeforeEach
    public void setUp(){
        this.lendingManager = new LendingManager();
    }

    // need to show all the borrowers available in the database to make a lending
    @Test
    public void testGetAllBorrowers() throws Exception{
        ArrayList<Borrower> borrowers = lendingManager.getAllBorrowers();
        assertNotNull(borrowers);
    }

    // need to show all the available exemplars in the database to make a lending
    @Test
    public void testGetAllAvailableExemplar() throws Exception{
        ArrayList<Exemplar> exemplars = lendingManager.getAllAvailableExemplar();
        for(Exemplar exemplar : exemplars){
            assertNull(exemplar.getLending());
        }
        assertNotNull(exemplars);
    }


    @Test
    public void testGetAllLendedExemplar() throws Exception{
        ArrayList<Exemplar> exemplars = lendingManager.getAllLendedExemplar();
        for(Exemplar exemplar : exemplars){
            assertNotNull(exemplar.getLending());
        }
        assertNotNull(exemplars);
    }

    // need to be able to return an exemplar
    @Test
    public void testReturned() throws Exception{
        ArrayList<Exemplar> exemplars = lendingManager.getAllLendedExemplar();
        for(Exemplar exemplar : exemplars){
            lendingManager.returned(exemplar);
        }
        assertNotNull(exemplars);
    }

    //main function

    @Test
    public void addLending() throws Exception{
        ArrayList<Exemplar> exemplars = lendingManager.getAllAvailableExemplar();
        ArrayList<Borrower> borrowers = lendingManager.getAllBorrowers();
        Borrower borrower = borrowers.get(0);
        for(Exemplar exemplar : exemplars){
            lendingManager.addLending(exemplar,borrower);
        }
        assertNotNull(exemplars);
    }
}
