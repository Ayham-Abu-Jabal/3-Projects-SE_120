package Project3;
import java.sql.*;
public class DatabaseConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/StudentDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "LETSROCK2025";

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
