package DataAccess;

import java.util.*;
import Model.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;


public class LendingDBAccess implements LendingDataAccess{

    private ExemplarDataAccess exemplarDataAccess = new ExemplarDBAccess();
    private BookDataAccess bookDataAccess = new BookDBAccess();
    private ExemplarDataAccess exemplarDBAccess = new ExemplarDBAccess();

    public ArrayList<Borrower> getAllBorrowers() throws SQLException {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            StringBuilder sql = new StringBuilder("select distinct firstName, lastName, phoneNumber, email, personId from person where personType = 'Reader'");
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            ResultSet data = statement.executeQuery();
            ArrayList<Borrower> readers = new ArrayList<>();
            while (data.next()) {
                Borrower reader = new Borrower(data.getString("firstName"), data.getString("lastName"), data.getInt("phoneNumber"), data.getString("email"));
                reader.setPersonId(data.getInt("personId"));
                readers.add(reader);
            }
            return readers;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void returned(Exemplar exemplar) throws SQLException {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "update exemplar set lending = null where exemplarId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,exemplar.getExemplarId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public ArrayList<Exemplar> getAllLendedExemplar() throws SQLException {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            StringBuilder sql = new StringBuilder("select * from exemplar where lending is not null");
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            ResultSet data = statement.executeQuery();
            ArrayList<Exemplar> exemplars = new ArrayList<>();
            while (data.next()) {
                Storage position = exemplarDataAccess.getStorage(data.getInt("place"));
                Book book = bookDataAccess.getBookById(data.getInt("book"));
                Exemplar exemplar = new Exemplar(book,bookDataAccess.getLanguage(data.getString("language")),data.getInt("nbPages")
                        ,data.getDouble("price"),data.getDouble("lendingPrice"),new Status(data.getString("state")),position);
                exemplar.setExemplarId(data.getInt("exemplarId"));
                Lending lending = getLendingById(data.getInt("lending"));
                exemplar.setLending(lending);
                exemplars.add(exemplar);
            }
            return exemplars;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Lending getLendingById(Integer lendingId) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from lending where lendingId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, lendingId);
            ResultSet data = statement.executeQuery();
            if(data.next()){
                Borrower borrower = getBorrowerById(data.getInt("reader"));
                Lending lending = new Lending(borrower,data.getDate("beginDate").toLocalDate(),data.getDate("endDate").toLocalDate());
                lending.setReturned(data.getBoolean("isReturned"));
                lending.setLendingId(data.getInt("lendingId"));
                return lending;
            } else {
                throw new RuntimeException("Lending not found");
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Borrower getBorrowerById(Integer borrowerId) throws SQLException{
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from person where personId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, borrowerId);
            ResultSet data = statement.executeQuery();
            if(data.next()){
                Borrower borrower = new Borrower(data.getString("firstName"),data.getString("lastName"),data.getInt("phoneNumber"),data.getString("email"));
                borrower.setPersonId(data.getInt("personId"));
                return borrower;
            } else {
                throw new RuntimeException("Borrower not found");
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Boolean getDelay(Borrower selectedBorrower,LocalDate date) throws SQLException {
        try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select * from lending where reader = ? and isReturned = false and endDate < ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, selectedBorrower.getPersonId());
            statement.setDate(2, Date.valueOf(date));
            ResultSet data = statement.executeQuery();
            return data.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void addLending(Exemplar exemplar, Borrower borrower) throws SQLException {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "insert into lending (reader, exemplar, beginDate, endDate, isReturned) values (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, borrower.getPersonId());
            statement.setInt(2, exemplar.getExemplarId());
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setDate(4, Date.valueOf(LocalDate.now().plusDays(15)));
            statement.setBoolean(5, false);
            statement.executeUpdate();
            editExemplarLending(exemplar,borrower);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void editExemplarLending(Exemplar exemplar,Borrower borrower) throws SQLException{
        try{
            Integer lendingId = getLendingId(exemplar,borrower);
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "update exemplar set lending = ? where exemplarId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, lendingId);
            statement.setInt(2, exemplar.getExemplarId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Integer getLendingId(Exemplar exemplar,Borrower borrower) throws SQLException{
            try{
            Connection connection = SingletonConnexion.getUniqueConnexion();
            String sql = "select lendingId from lending where reader = ? and exemplar = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, borrower.getPersonId());
            statement.setInt(2, exemplar.getExemplarId());
            ResultSet data = statement.executeQuery();
            data.next();
            return data.getInt("lendingId");
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public ArrayList<Lending> getAllLendings(String borrower, LocalDate date) throws SQLException {
        try {
            String[] borrowerSplit = borrower.split(" ");
            String borrowerFirstName = borrowerSplit[0];
            String borrowerLastName = borrowerSplit[1];
            if(borrowerSplit.length > 2 && borrowerSplit[2] != null){
                borrowerLastName += " " + borrowerSplit[2];
            }
            Connection connection = SingletonConnexion.getUniqueConnexion();
            StringBuilder sql = new StringBuilder("select distinct phoneNumber, email, beginDate, endDate, lendingId from lending ");
            sql.append("inner join person on person.personId = lending.reader ");
            sql.append("where person.firstName = ? and person.lastName = ? and lending.beginDate >= ?");
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            statement.setString(1, borrowerFirstName);
            statement.setString(2, borrowerLastName);
            statement.setDate(3, Date.valueOf(date));
            ResultSet data = statement.executeQuery();
            ArrayList<Lending> lendings = new ArrayList<>();
            data.next();
            Borrower reader = new Borrower(borrowerFirstName, borrowerLastName, data.getInt("phoneNumber"), data.getString("email"));
            Lending lending = new Lending(reader, data.getDate("beginDate").toLocalDate(), data.getDate("endDate").toLocalDate());
            lending.setLendingId(data.getInt("lendingId"));
            lendings.add(lending);
            while (data.next()) {
                lending = new Lending(reader, data.getDate("beginDate").toLocalDate(), data.getDate("endDate").toLocalDate());
                lending.setLendingId(data.getInt("lendingId"));
                lendings.add(lending);
            }
            return lendings;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public ArrayList<Exemplar> getAllAvailableExemplar() throws SQLException {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            StringBuilder sql = new StringBuilder("select * from exemplar where lending is null");
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            ResultSet data = statement.executeQuery();
            ArrayList<Exemplar> exemplars = new ArrayList<>();
            while (data.next()) {
                Book book = bookDataAccess.getBookById(data.getInt("book"));
                Storage position = exemplarDataAccess.getStorage(data.getInt("place"));
                Status status = new Status(data.getString("state"));
                Language language = bookDataAccess.getLanguage(data.getString("language"));
                Exemplar exemplar = new Exemplar(book,language,data.getInt("nbPages"),data.getDouble("price"),
                        data.getDouble("lendingPrice"),status,position);
                exemplar.setExemplarId(data.getInt("exemplarId"));
                exemplars.add(exemplar);
            }
            return exemplars;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public ArrayList<ResultResearch> getSearchLending(int lendingId) throws SQLException {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            StringBuilder sql = new StringBuilder("select exemplarId, title, beginDate, endDate, isReturned from book ");
            sql.append("inner join exemplar on exemplar.book = book.bookId ");
            sql.append("inner join lending on lending.exemplar = exemplar.exemplarId ");
            sql.append("where lending.lendingId = ?");
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            statement.setInt(1, lendingId);
            ResultSet data = statement.executeQuery();
            data.next();
            ArrayList<ResultResearch> results = new ArrayList<>();
            ResultResearch result = new ResultResearch(Integer.toString(data.getInt("exemplarId")));
            results.add(result);
            result = new ResultResearch(data.getString("title"));
            results.add(result);
            result = new ResultResearch(data.getDate("beginDate").toString());
            results.add(result);
            result = new ResultResearch(data.getDate("endDate").toString());
            results.add(result);
            if (data.getBoolean("isReturned")) {
                result = new ResultResearch("Returned");
            } else {
                result = new ResultResearch("Not returned");
            }
            results.add(result);
            return results;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
