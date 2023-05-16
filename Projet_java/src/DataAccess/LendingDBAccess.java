package DataAccess;

import java.util.*;
import Model.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;


public class LendingDBAccess implements LendingDataAccess{

    public ArrayList<Borrower> getAllBorrowers() {
        try {
            Connection connection = SingletonConnexion.getUniqueConnexion();
            StringBuilder sql = new StringBuilder("select distinct firstName, lastName, phoneNumber, email from person where personType = 'Reader'");
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
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Lending> getAllLendings(String borrower, LocalDate date) {
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
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ResultResearch> getSearchLending(int lendingId) {
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
            throw new RuntimeException(e);
        }
    }
}
