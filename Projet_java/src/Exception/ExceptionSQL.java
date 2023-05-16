package Exception;

import java.sql.SQLException;

public class ExceptionSQL extends Exception{

    public ExceptionSQL(SQLException exception){
        super("There is an error with your request : " + exception.getMessage());
    }
}
