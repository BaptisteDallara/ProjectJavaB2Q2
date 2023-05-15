package DataAccess;

import Model.Exemplar;
import Model.Status;
import Model.Storage;

import java.util.ArrayList;

public interface ExemplarDataAccess {

    public void addExemplar(Exemplar exemplar);

    public ArrayList<Status> getAllStatus();

    public ArrayList<Exemplar> getAllExemplar();
    public Storage getStorage(Integer positionId);
    public Storage getPosition(Integer room, Integer rackNumber, Integer line);
    public void deleteExemplar(Exemplar exemplar);
}
