package controllers;

/**
 * This controller handles all database requests from the starting pages (login, signup, home, myaccount)
 * Communicates with adapter to fulfill the requests. 
 * @author Diego Rodriguez Updated: 4/18/20
 */
import api.adapters.DatabaseAdapter;
import java.util.Map;

public class AccountController {
    
    
    protected DatabaseAdapter dbAdapter = new DatabaseAdapter();
    public UUIDController uuidController = new UUIDController();

    /**
     * Returns true if the the data is able to be inserted into database.
     *
     * @param _credentials
     * @return
     */
    public boolean sendInsertRequest(Map<String, String> _credentials) {
        return this.dbAdapter.insertIntoDatabase(_credentials);
    }

    /**
     * Returns true if email is in database and false if not.
     *
     * @param _email
     * @return
     */
    public boolean checkEmailRequest(String _email) {
        return this.dbAdapter.checkIfEmailExists(_email);
    }

    /**
     * Returns UUID that correspond with the given email and password If the
     * email and password do match, then we return null
     *
     * @param _email
     * @param _password
     * @return
     */
    public String sendVerificationRequest(String _email, String _password) {
        String uuid = this.dbAdapter.verifyLoginCredentials(_email, _password);
        if (uuid != null) {
            return uuid;
        } else {
            return null;
        }
    }
    
     /**
     * Returns attribute value that correspond with the given UUID.
     * If a value returned is null print message and return null
     *
     * @param _attribute
     * @return
     */
    public String sendQueryRequest(String _attribute) {
        String attribute = this.dbAdapter.queryForAttribute(this.uuidController.getUUID(), _attribute);

        if (attribute != null) {
            return attribute;
        } else {
            System.out.println("Query request with uuid: " + this.uuidController.getUUID() + "in account controller returned null string");
            return null;
        }
    }
    
    public boolean sendUpdateRequest(String _attribute, String _update) {      
        return this.dbAdapter.updateInformation(this.uuidController.getUUID(), _attribute, _update);
    }
    
    public boolean sendDeleteRequest() {      
        return this.dbAdapter.deleteAccount(this.uuidController.getUUID());
    }
    
}
