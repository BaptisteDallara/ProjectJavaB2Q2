package DataAccess;

import Model.Book;
import Model.Exemplar;
import Model.Status;
import Model.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExemplarDBAccess implements ExemplarDataAccess{

    private BookDataAccess bookDBAccess = new BookDBAccess();

    @Override
    public void deleteExemplar(Exemplar exemplar) throws SQLException {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "delete from exemplar where exemplarId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,exemplar.getExemplarId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    @Override
    public ArrayList<Exemplar> getAllExemplar() throws SQLException {
        try{
            ArrayList<Exemplar> exemplarList = new ArrayList<>();
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
            return exemplarList;
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

        @Override
        public Storage getStorage(Integer positionId) throws SQLException{
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
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    @Override
    public void addExemplar(Exemplar exemplar) throws SQLException {
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
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    @Override
    public Storage getPosition(Integer room, Integer rackNumber, Integer line) throws SQLException {
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
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    @Override
    public ArrayList<Status> getAllStatus() throws SQLException {
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
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }
}
