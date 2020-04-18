/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.interfaces;

import java.util.Map;

/**
 *
 * @author darod
 */
public interface DatabaseInterface {
    
    public boolean insertIntoDatabase(Map<String, String> _userInformation);
    
    public String queryForAttribute(String _uuid, String attributeNeeded);
    
    public boolean checkIfEmailExists(String _email);
    
    public String verifyLoginCredentials(String _email, String _password);
    
    public boolean updateInformation(String _uuid, String _attribute, String _update);
    
    public boolean deleteAccount(String _uuid);
    
}
