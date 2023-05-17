package DataAccess;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtilsDBAccess implements UtilsDataAccess{

    public ArrayList<Country> getAllCountries() throws SQLException{
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
            throw new SQLException(exception);
        }
    }

    public void addSerie(Serie serie) throws SQLException {
        try {
            if(!serieAlreadyExist(serie.getName())) {
                Connection connection = SingletonConnexion.getUniqueConnexion();
                String sql = "insert into serie (name) values (?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, serie.getName());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public void addEdition(Edition edition) throws SQLException{
        try {
            if(!editionAlreadyExist(edition)){
                Connection connection = SingletonConnexion.getUniqueConnexion();
                String sql = "insert into edition (name, country) values (?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, edition.getName());
                statement.setString(2, edition.getCountry().getName());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public Boolean editionAlreadyExist(Edition edition) throws SQLException {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from edition where name = ? and country = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, edition.getName());
            statement.setString(2, edition.getCountry().getName());
            ResultSet data = statement.executeQuery();
            return data.next();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

    public Boolean serieAlreadyExist(String name) throws SQLException {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from serie where name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet data = statement.executeQuery();
            return data.next();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
    }

}
