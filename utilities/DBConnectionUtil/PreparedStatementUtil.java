package utilities.DBConnectionUtil;

/**
 * Helper class for creating prepared statements.
 * @author Diego Rodriguez
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PreparedStatementUtil {

    protected PreparedStatement statementToReturn = null;

    /**
     * Creates a prepared statement for inserting a new tuple with 9 attributes
     * into the database. Those attributes come from the sign up page.
     *
     * @param _userInformation
     * @param _con
     * @return
     */
    public PreparedStatement statementForInsert(Map<String, String> _userInformation, Connection _con) {
        String insertStatement = "INSERT INTO users (UUID, FIRSTNAME, LASTNAME, STREET, CITY, STATE, ZIPCODE, EMAIL, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        try {
            this.statementToReturn = _con.prepareStatement(insertStatement);

            this.statementToReturn.setString(1, _userInformation.get("uuid"));
            this.statementToReturn.setString(2, _userInformation.get("firstName"));
            this.statementToReturn.setString(3, _userInformation.get("lastName"));
            this.statementToReturn.setString(4, _userInformation.get("street"));
            this.statementToReturn.setString(5, _userInformation.get("city"));
            this.statementToReturn.setString(6, _userInformation.get("state"));
            this.statementToReturn.setString(7, _userInformation.get("zipcode"));
            this.statementToReturn.setString(8, _userInformation.get("email"));
            this.statementToReturn.setString(9, _userInformation.get("password"));

        } catch (SQLException ex) {
            Logger.getLogger(PreparedStatementUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unable to construct prepared statement");
        }

        return this.statementToReturn;
    }

    /**
     * Creates a prepared statement for selecting a UUID from the database that corresponds with the email and password.
     *
     * @param _email
     * @param _password
     * @param _con
     * @return
     */
    public PreparedStatement statementForVerification(String _email, String _password, Connection _con) {
        String selectStatement = "SELECT uuid FROM users WHERE email = ? and password = ?";
        try {
            this.statementToReturn = _con.prepareStatement(selectStatement);
            this.statementToReturn.setString(1, _email);
            this.statementToReturn.setString(2, _password);
        } catch (SQLException ex) {
            Logger.getLogger(PreparedStatementUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unable to construct prepared statement");

        }
        return this.statementToReturn;
    }
    
    /**
     * Creates prepared statement for performing a query to check if email exists.
     * @param _email
     * @param _con
     * @return 
     */
    public PreparedStatement statementToCheckEmail(String _email, Connection _con) {
        String selectStatement = "SELECT uuid FROM users WHERE email = ?";
        try {
            this.statementToReturn = _con.prepareStatement(selectStatement);
            this.statementToReturn.setString(1, _email);
        } catch (SQLException ex) {
            Logger.getLogger(PreparedStatementUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unable to construct prepared statement");

        }
        return this.statementToReturn;
    }

    /**
     * Returns a prepared statement that queries for any attribute in the database based on UUID.
     * Checks to make sure passed attribute is valid using helper method.
     * Also note, SQL does not allow prepared statements to use ? after select clause.
     * @param _uuid
     * @param _attribute
     * @param _con
     * @return 
     */
    public PreparedStatement statementForQuery(String _uuid, String _attribute, Connection _con) {
        if (!checkForValidAttribute(_attribute)) {
            return null;
        } else {
            String selectStatement = "SELECT " + _attribute + " FROM users where uuid = ?";

            try {
                this.statementToReturn = _con.prepareStatement(selectStatement);
                this.statementToReturn.setString(1, _uuid);
            } catch (SQLException ex) {
                Logger.getLogger(PreparedStatementUtil.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Unable to construct prepared statement");
            }
            return this.statementToReturn;
        }
    }

    /**
     * Creates prepared statement that updates one specified attribute from in the database.
     * Uses helper method to check to make sure the attribute being updated is in the database. 
     *
     * @param _uuid
     * @param _attribute
     * @param _update
     * @param _con
     * @return
     */
    public PreparedStatement statementForUpdate(String _uuid, String _attribute, String _update, Connection _con) {
        if (!checkForValidAttribute(_attribute)) {
            return null;
        } else {
            String selectStatement = "UPDATE users SET " + _attribute + " = ? WHERE uuid = ?";

            try {
                this.statementToReturn = _con.prepareStatement(selectStatement);
                this.statementToReturn.setString(1, _update);
                this.statementToReturn.setString(2, _uuid);
            } catch (SQLException ex) {
                Logger.getLogger(PreparedStatementUtil.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Unable to construct prepared statement");
            }
            return this.statementToReturn;
        }
    }
    
    /**
     * Creates prepared statement that deletes user info from database based on UUID
     * @param _uuid
     * @param _con
     * @return 
     */
    
    public PreparedStatement statementForDelete(String _uuid, Connection _con) {
        String deleteStatement = "DELETE FROM users where uuid = ?";
        
        try {
                this.statementToReturn = _con.prepareStatement(deleteStatement);
                this.statementToReturn.setString(1, _uuid);
            } catch (SQLException ex) {
                Logger.getLogger(PreparedStatementUtil.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Unable to construct prepared statement");
            }
            return this.statementToReturn;
    }

    /**
     * Helper method for checking if an attribute matches one of the columns on
     * the table. If not then return false.
     *
     * @param _attribute
     * @return
     */
    public boolean checkForValidAttribute(String _attribute) {
        if (_attribute.equalsIgnoreCase("uuid") || _attribute.equalsIgnoreCase("firstname") || _attribute.equalsIgnoreCase("lastname")
                || _attribute.equalsIgnoreCase("street") || _attribute.equalsIgnoreCase("city") || _attribute.equalsIgnoreCase("state")
                || _attribute.equalsIgnoreCase("zipcode") || _attribute.equalsIgnoreCase("email") || _attribute.equalsIgnoreCase("password")) {

            return true;
        } else {
            System.out.println("INVALID ATTRIBUTE");
            return false;
        }
    }

}
