package api.translators;

/**
 * Translator that defines how interface methods should work with ApacheDerby
 * Utilizes a helper class (PreparedStatementUtil) for constructing prepared statements. 
 * 
 * @author Diego Rodriguez Updated: 4/17/2020
 */
import api.interfaces.DatabaseInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DBConnectionUtil.PreparedStatementUtil;

public class ApacheDerbyTranslator implements DatabaseInterface {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private Connection con = null;
    PreparedStatementUtil createPreparedStatement = new PreparedStatementUtil();
    PreparedStatement pStatement;

    /**
     * This method stores user information from sign up page into database.
     *
     * @param _userInformation
     * @return
     */
    @Override
    public boolean insertIntoDatabase(Map<String, String> _userInformation) {
        //try connecting to database, if its not able to connect then return false
        try {
            this.con = connectToDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ApacheDerbyTranslator.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        //use helper class to create prepared statement
        this.pStatement = createPreparedStatement.statementForInsert(_userInformation, this.con);

        //try executing the preparedStatement, if its not able to execute it then return false
        try {
            pStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ApacheDerbyTranslator.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unable to exectue update");
            return false;
        }

        //If database is successfully connected to and prepared statement is exectued then return true;
        return true;
    }

    @Override
    public String queryForAttribute(String _uuid, String _attribute) {
        String attribute = null;
        ResultSet resultSet;

        //try connecting to database using helper method
        try {
            this.con = connectToDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ApacheDerbyTranslator.class.getName()).log(Level.SEVERE, null, ex);
        }

        //use helper class to create prepared statement
        this.pStatement = createPreparedStatement.statementForQuery(_uuid, _attribute, this.con);

        //try executing query. If result set is empty return null, else, set it equal to uuid then return it. 
        try {
            resultSet = this.pStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No database match found");
            } else {
                attribute = resultSet.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApacheDerbyTranslator.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unable to exectue query");
        }

        return attribute;

    }

    /**
     * This method verifies user credentials by running a query on the database
     * that searches for uuid based on the email and password typed in by the
     * user. Returns null if no uuid is found.
     *
     * @param _email
     * @param _password
     * @return
     */
    @Override
    public String verifyLoginCredentials(String _email, String _password) {
        String uuid = null;
        ResultSet resultSet;
        System.out.println(_email + " : " + _password);
        //try connecting to database using helper method
        try {
            this.con = connectToDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ApacheDerbyTranslator.class.getName()).log(Level.SEVERE, null, ex);
        }

        //use helper class to create prepared statement
        this.pStatement = createPreparedStatement.statementForVerification(_email, _password, this.con);

        //try executing query. If result set is empty return null, else, set it equal to uuid then return it. 
        try {
            resultSet = this.pStatement.executeQuery();
            if (!resultSet.next()) {           
                System.out.println("No database match found");
            } else {
                uuid = resultSet.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApacheDerbyTranslator.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unable to exectue query");
        }

        return uuid;
    }

    @Override
    public boolean updateInformation(String _uuid, String _attribute, String _update) {
        //try connecting to database using helper method
        try {
            this.con = connectToDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ApacheDerbyTranslator.class.getName()).log(Level.SEVERE, null, ex);
        }

        //use helper class to create prepared statement
        this.pStatement = createPreparedStatement.statementForUpdate(_uuid, _attribute, _update, this.con);

        //try executing the preparedStatement, if its not able to execute it then return false
        try {
            pStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ApacheDerbyTranslator.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unable to exectue update");
            return false;
        }
        
        //if update is successful return true
        return true;
    }

    @Override
    public boolean deleteAccount(String _uuid) {
        //try connecting to database using helper method
        try {
            this.con = connectToDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ApacheDerbyTranslator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //use helper class to create prepared statement
        this.pStatement = createPreparedStatement.statementForDelete(_uuid, this.con);
        
        //try executing the preparedStatement, if its not able to execute it then return false
        try {
            pStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ApacheDerbyTranslator.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unable to exectue update");
            return false;
        }
        
        //if update is successful return true
        return true;
        
    }

    /**
     * Helper Class for connecting to the database
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static Connection connectToDatabase() throws ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection("jdbc:derby:test;create=true");

            con.setSchema("APP");
            return con;
        } catch (SQLException ex) {
            System.out.println("Problem connecting to database");
            return null;
        }

    }

    /**
     * Checks if email is already associated with an account. Returns true if yes, and false if no. 
     * Returns true if query is unable to execute because I thought it might be a safer option to not
     * let an account be created with that email if the query cannot execute for whatever reason. 
     * @param _email
     * @return 
     */
    @Override
    public boolean checkIfEmailExists(String _email) {
        ResultSet resultSet;

        //try connecting to database using helper method
        try {
            this.con = connectToDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ApacheDerbyTranslator.class.getName()).log(Level.SEVERE, null, ex);
        }

        //use helper class to create prepared statement
        this.pStatement = createPreparedStatement.statementToCheckEmail(_email, this.con);

        //try executing query. If result set is empty, meaning the email is not in the database then return false. 
        try {
            resultSet = this.pStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Email is not in the database");
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApacheDerbyTranslator.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unable to exectue query");
            return true;
        }
    }

}
