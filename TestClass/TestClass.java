package TestClass;
/**
 * The method being tested is used in the PreparedStatement utility class and is called "checkForValidAttribute".
 * The purpose of "checkForValidAttribute" is to to check for the validity of attribute types for attributes to be  
 * inserted into the database. The attribute type should match one of the columns in the table "users". 
 * 
 * We can test this method by passing different strings to it and seeing if it returns true or false, depending on 
 * the validity of the string. 
 * 
 * @author Diego Rodriguez
 * @date 5/2/20
 */
import utilities.DBConnectionUtil.PreparedStatementUtil;

public class TestClass {
    
PreparedStatementUtil testView = new PreparedStatementUtil();
    public boolean testMethod(String _attribute) {
        return testView.checkForValidAttribute(_attribute);
    }
    public static void main(String[] args) {
        TestClass test = new TestClass();
        PreparedStatementUtil testView = new PreparedStatementUtil();
        
        //error case
        System.out.println(test.testMethod("uuids"));
        
        //edge case, we are expecting it to return true even though the attribute is in all caps.
        System.out.println(test.testMethod("UUID"));
        
        //normal cases
        System.out.println(testView.checkForValidAttribute("uuid"));
        System.out.println(testView.checkForValidAttribute("firstname"));
        
    }
}
