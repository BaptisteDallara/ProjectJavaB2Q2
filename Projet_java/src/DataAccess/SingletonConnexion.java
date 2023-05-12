package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection uniqueConnexion;
    public static Connection getUniqueConnexion() throws SQLException {
        if(uniqueConnexion == null){
            uniqueConnexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylib",
                                                                                        "root",
                                                                                        "blackpepere");
        }
        return uniqueConnexion;
    }

}
