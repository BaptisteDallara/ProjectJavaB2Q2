package Business;

import DataAccess.ExemplarDBAccess;
import DataAccess.ExemplarDataAccess;
import Exception.ExceptionSQL;
import Model.Exemplar;
import Model.Status;
import Model.Storage;

import java.util.ArrayList;

public class ExemplarManager {
    private ExemplarDataAccess exemplarDataAccess;

    public ExemplarManager(){
        exemplarDataAccess = new ExemplarDBAccess();

    }

    public void addExemplar(Exemplar exemplar) throws ExceptionSQL{
        try {
            exemplarDataAccess.addExemplar(exemplar);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Status> getAllStatus() throws ExceptionSQL{
        try {
            return exemplarDataAccess.getAllStatus();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public ArrayList<Exemplar> getAllExemplar() throws ExceptionSQL{
        try {
            return exemplarDataAccess.getAllExemplar();
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public Storage getPosition(Integer room, Integer rackNumber, Integer line) throws ExceptionSQL{
        try {
            return exemplarDataAccess.getPosition(room,rackNumber,line);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

    public void deleteExemplar(Exemplar exemplar) throws ExceptionSQL{
        try {
            exemplarDataAccess.deleteExemplar(exemplar);
        } catch (Exception exception) {
            throw new ExceptionSQL(exception);
        }
    }

}
