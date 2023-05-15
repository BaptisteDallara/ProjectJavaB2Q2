package Business;

import DataAccess.ExemplarDBAccess;
import DataAccess.ExemplarDataAccess;
import Model.Exemplar;
import Model.Status;

import java.util.ArrayList;

public class ExemplarManager {
    private ExemplarDataAccess exemplarDataAccess;

    public ExemplarManager(){
        exemplarDataAccess = new ExemplarDBAccess();
    }

    public ArrayList<Status> getAllStatus(){
        return exemplarDataAccess.getAllStatus();
    }

    public ArrayList<Exemplar> getAllExemplar(){
        return exemplarDataAccess.getAllExemplar();
    }

    public void deleteExemplar(Exemplar exemplar){
        exemplarDataAccess.deleteExemplar(exemplar);
    }

}
