package DataAccess;

import Model.Exemplar;
import Model.Status;
import Model.Storage;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExemplarDataAccess {

    public void addExemplar(Exemplar exemplar) throws SQLException;

    public ArrayList<Status> getAllStatus() throws SQLException;

    public ArrayList<Exemplar> getAllExemplar() throws SQLException;
    public Storage getStorage(Integer positionId) throws SQLException;
    public Storage getPosition(Integer room, Integer rackNumber, Integer line) throws SQLException;
    public void deleteExemplar(Exemplar exemplar) throws SQLException;
}
