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
                        ,data.getDouble("price"),data.getDouble("lendingPrice"),new Status(data.getString("state")),position);
                exemplar.setExemplarId(data.getInt("exemplarId"));
                exemplarList.add(exemplar);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return exemplarList;
    }

        @Override
        public Storage getStorage(Integer positionId){
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from storage where storageId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,positionId);
            ResultSet data = statement.executeQuery();
            Storage position = null;
            while (data.next()){
                position = new Storage(data.getInt("room"),data.getInt("rackNumber"),data.getInt("lineNumber"));
                position.setStorageId(data.getInt("storageId"));
            }
            return position;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addExemplar(Exemplar exemplar) {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "insert into exemplar (book,language,nbPages,price,lendingPrice,state,place) values (?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,exemplar.getBook().getBookId());
            statement.setString(2,exemplar.getLanguage().getName());
            statement.setInt(3,exemplar.getNbPages());
            statement.setDouble(4,exemplar.getPrice());
            statement.setDouble(5,exemplar.getLendingPrice());
            statement.setString(6,exemplar.getState().getName());
            statement.setInt(7,exemplar.getStorage().getStorageId());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Storage getPosition(Integer room, Integer rackNumber, Integer line) {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from storage where room = ? and rackNumber = ? and lineNumber = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,room);
            statement.setInt(2,rackNumber);
            statement.setInt(3,line);
            ResultSet data = statement.executeQuery();
            Storage position = null;
            while (data.next()){
                position = new Storage(data.getInt("room"),data.getInt("rackNumber"),data.getInt("lineNumber"));
                position.setStorageId(data.getInt("storageId"));
            }
            if(position == null){
                 sql = "insert into storage (room,rackNumber,lineNumber) values (?,?,?)";
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1,room);
                    statement.setInt(2,rackNumber);
                    statement.setInt(3,line);
                    statement.executeUpdate();
                    sql = "select * from storage where room = ? and rackNumber = ? and lineNumber = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1,room);
                    statement.setInt(2,rackNumber);
                    statement.setInt(3,line);
                    data = statement.executeQuery();
                    while (data.next()){
                        position = new Storage(data.getInt("room"),data.getInt("rackNumber"),data.getInt("lineNumber"));
                        position.setStorageId(data.getInt("storageId"));
                }
            }
            return position;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Status> getAllStatus() {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from status";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<Status> statusList = new ArrayList<>();
            while (data.next()){
                Status status = new Status(data.getString("name"));
                statusList.add(status);

            }
            return statusList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
