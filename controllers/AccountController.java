package controllers;

/**
 * This controller handles all database requests from the LogInPage and SignUpPage Views.
 * Communicates with adapter to fulfill the requests. 
 * @author Diego Rodriguez Updated: 4/18/20
 */
import api.adapters.DatabaseAdapter;
import java.util.Map;

public class AccountController {
    
    
    protected DatabaseAdapter dbAdapter = new DatabaseAdapter();
    protected String uuid;

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
    
    //=================  SETTERS ===============
    public void setUUID(String _uuid) {
        this.uuid = _uuid;
    }
    
    //=================  GETTERS  ===============
    public String getUUID() {
        return this.uuid;
    }
}
