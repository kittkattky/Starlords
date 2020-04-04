package utils;

/**
 * This class connects to the database
 *
 * @author Kahlie Last Updated: 2/25/2020
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    private Connection con = null;
    
    public static Connection conDB() throws ClassNotFoundException{
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection("jdbc:derby:test;create=true");

            con.setSchema("APP");
            return con;
        } catch (SQLException ex) {
            System.err.println("ConnectionUtil : "+ex.getMessage());
           return null;
        }
        
    }
    
}