package api.adapters;

/**
 * This adapter receives requests from controllers and fulfills them by communicating with the correct translator
 * @author Diego Rodriguez Updated: 4/18/2020
 */

import api.interfaces.DatabaseInterface;
import api.translators.ApacheDerbyTranslator;
import java.util.Map;

public class DatabaseAdapter implements DatabaseInterface {
    
    protected static final DatabaseInterface currentlyUsedDatabase = new ApacheDerbyTranslator();
    
    @Override
    public boolean insertIntoDatabase(Map<String, String> _userInformation) {
        return DatabaseAdapter.currentlyUsedDatabase.insertIntoDatabase(_userInformation);
    }

    @Override
    public String queryForAttribute(String _uuid, String _attributeNeeded) {
        return DatabaseAdapter.currentlyUsedDatabase.queryForAttribute(_uuid, _attributeNeeded);
    }

    @Override
    public String verifyLoginCredentials(String _email, String _password) {
        return DatabaseAdapter.currentlyUsedDatabase.verifyLoginCredentials(_email, _password);
    }

    @Override
    public boolean updateInformation(String _uuid, String _attribute, String _update) {
        return DatabaseAdapter.currentlyUsedDatabase.updateInformation(_uuid, _attribute, _update);
    }

    @Override
    public boolean deleteAccount(String _uuid) {
        return DatabaseAdapter.currentlyUsedDatabase.deleteAccount(_uuid);
    }

    @Override
    public boolean checkIfEmailExists(String _email) {
        return DatabaseAdapter.currentlyUsedDatabase.checkIfEmailExists(_email);
    }
    
}
