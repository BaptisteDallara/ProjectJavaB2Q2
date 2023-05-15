package DataAccess;

import Model.Book;
import Model.Exemplar;
import Model.Status;
import Model.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExemplarDBAccess implements ExemplarDataAccess{

    private BookDataAccess bookDBAccess = new BookDBAccess();

    @Override
    public void deleteExemplar(Exemplar exemplar) {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "delete from exemplar where exemplarId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,exemplar.getExemplarId());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Exemplar> getAllExemplar() {
        ArrayList<Exemplar> exemplarList = new ArrayList<>();
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from exemplar";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            while (data.next()){
                Storage position = getStorage(data.getInt("place"));
                Book book = bookDBAccess.getBookById(data.getInt("book"));
                Exemplar exemplar = new Exemplar(book,bookDBAccess.getLanguage(data.getString("language")),data.getInt("nbPages")
                        ,data.getDouble("price"),data.getDouble("lendingPrice"),position);
                exemplar.setExemplarId(data.getInt("exemplarId"));
                exemplarList.add(exemplar);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return exemplarList;
    }

    public Storage getStorage(Integer positionId){
        Storage position = null;
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from storage where storageId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,positionId);
            ResultSet data = statement.executeQuery();
            while (data.next()){
                position = new Storage(data.getInt("room"),data.getInt("rackNumber"),data.getInt("lineNumber"));
                position.setStorageId(data.getInt("storageId"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return position;
    }

    @Override
    public ArrayList<Status> getAllStatus() {
        ArrayList<Status> statusList = new ArrayList<>();
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from status";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            while (data.next()){
                Status status = new Status(data.getString("name"));
                statusList.add(status);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return statusList;
    }
}
