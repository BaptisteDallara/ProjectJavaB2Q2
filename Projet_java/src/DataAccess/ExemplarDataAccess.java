package DataAccess;

import Model.Exemplar;
import Model.Status;

import java.util.ArrayList;

public interface ExemplarDataAccess {

    public ArrayList<Status> getAllStatus();

    public ArrayList<Exemplar> getAllExemplar();
    public void deleteExemplar(Exemplar exemplar);
}
