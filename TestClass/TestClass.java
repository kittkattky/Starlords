package TestClass;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        System.out.println(test.testMethod("uuids"));
        System.out.println(test.testMethod("UUID"));
        System.out.println(testView.checkForValidAttribute("uuid"));
        System.out.println(testView.checkForValidAttribute("firstname"));
        
    }
}
