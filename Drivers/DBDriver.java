/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import utilities.DBConnectionUtil.ConnectionUtil;
import api.adapters.DatabaseAdapter;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DBConnectionUtil.PreparedStatementUtil;

/**
 *
 * @author darod
 */
public class DBDriver {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DatabaseAdapter adapter = new DatabaseAdapter();
        Connection con = ConnectionUtil.conDB();
        //PreparedStatement preparedStatement = con.prepareStatement("CREATE TABLE users (uuid VARCHAR(36), firstName VARCHAR(50), lastName VARCHAR(50), street VARCHAR(20), city VARCHAR(20), state VARCHAR(20), zipcode VARCHAR(5), email VARCHAR(50), password VARCHAR(50))");
        PreparedStatement preparedStatement2 = con.prepareStatement("INSERT INTO users (UUID, firstName, lastName, STREET, CITY, STATE, ZIPCODE, EMAIL, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        PreparedStatement preparedStatement3 = con.prepareStatement("select * from users");
        PreparedStatement delete = con.prepareStatement("delete from users where uuid = '12797caf-5c2b-49d9-ba02-2fdc0c74c419'");
        //UUID uuid = UUID.randomUUID();
        String uuid = "5ac1d0ac-7999-4951-aee0-3d69d1b10308";
        Map<String, String> userInformation = new HashMap<>();
        
        DatabaseAdapter dbAdapter = new DatabaseAdapter();
        System.out.println(dbAdapter.queryForAttribute("879189c2-e2b5-48a3-b849-47e3a84f4b59", "uuid"));
        //System.out.println(adapter.queryForAttribute(, "zipcode"));
        //System.out.println(adapter.updateInformation(uuid, "city", "Charlotte"));
        //System.out.println(adapter.queryForAttribute(uuid, "city"));
//        preparedStatement2.setString(1, "e62330fa-9072-44ab-9cc2-ae9283c98233");
//        preparedStatement2.setString(2, "Diego");
//        preparedStatement2.setString(3, "Rodriguez");
//        preparedStatement2.setString(4, "street");
//        preparedStatement2.setString(5, "state");
//        preparedStatement2.setString(6, "city");
//        preparedStatement2.setString(7, "23456");
//        preparedStatement2.setString(8, "test@test.com");
//        preparedStatement2.setString(9, "password");
        //delete.executeUpdate();
        
        ResultSet rs = preparedStatement3.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (rs.next()) {        
            for (int i = 1; i <= columnsNumber; i++) {

                System.out.print(rs.getString(i) + " "); //Print one element of a row

            }

            System.out.println();//Move to the next line to print the next row.           

        }
        
        
        
        
        
            
        }
    }


