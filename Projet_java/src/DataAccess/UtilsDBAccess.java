package DataAccess;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtilsDBAccess implements UtilsDataAccess{

    public ArrayList<Country> getAllContries(){
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from country";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<Country> countries = new ArrayList<>();
            while(data.next()){
                countries.add(new Country(data.getString("name")));
            }
            return countries;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    public void addSerie(Serie serie) {
        try {
            if(!serieAlreadyExist(serie.getName())) {
                Connection connection = SingletonConnexion.getUniqueConnexion();
                String sql = "insert into serie (name) values (?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, serie.getName());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public boolean serieAlreadyExist(String name) {
    	try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from serie where name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet data = statement.executeQuery();
            data.next();
            return data.getRow() != 0;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

}
