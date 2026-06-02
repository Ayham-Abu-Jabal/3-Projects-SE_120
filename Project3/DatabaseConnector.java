package Project3;
import java.sql.*;

public class DatabaseConnector {
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASS");

    public static Connection connect(){// connects to the sql server
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to postgresql server succesfully");
        }
        catch(SQLException e){
            System.out.println("error Connecting: " + e.getMessage());
        }
        return connection;
    }
}