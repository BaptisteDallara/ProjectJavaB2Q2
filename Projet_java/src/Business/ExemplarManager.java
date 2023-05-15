package Business;

import DataAccess.ExemplarDBAccess;
import DataAccess.ExemplarDataAccess;
import Model.Exemplar;
import Model.Status;
import Model.Storage;

import java.util.ArrayList;

public class ExemplarManager {
    private ExemplarDataAccess exemplarDataAccess;

    public ExemplarManager(){
        exemplarDataAccess = new ExemplarDBAccess();

    }

    public void addExemplar(Exemplar exemplar){
        exemplarDataAccess.addExemplar(exemplar);
    }

    public ArrayList<Status> getAllStatus(){
        return exemplarDataAccess.getAllStatus();
    }

    public ArrayList<Exemplar> getAllExemplar(){
        return exemplarDataAccess.getAllExemplar();
    }

    public Storage getPosition(Integer room, Integer rackNumber, Integer line){
        return exemplarDataAccess.getPosition(room,rackNumber,line);
    }

    public void deleteExemplar(Exemplar exemplar){
        exemplarDataAccess.deleteExemplar(exemplar);
    }

}
