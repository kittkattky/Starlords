package api.interfaces;

/**
 * Interface to define how translators should work
 * Author: Diego Rodriguez Updated: 4/17/20
 */
import java.util.Map;

public interface DatabaseInterface {
    
    public boolean insertIntoDatabase(Map<String, String> _userInformation);
    
    public String queryForAttribute(String _uuid, String _attributeNeeded);
    
    public boolean checkIfEmailExists(String _email);
    
    public String verifyLoginCredentials(String _email, String _password);
    
    public boolean updateInformation(String _uuid, String _attribute, String _update);
    
    public boolean deleteAccount(String _uuid);
    
}
