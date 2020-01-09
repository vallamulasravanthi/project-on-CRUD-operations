/*This java project dynamically handles DDL and DML operations.
It allows you to dynamically create a table of arbitary size(variable no of columns)
and datatype.
Based on the datatype,an appropriate table is created which is manipulated 
via CRUD operations*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class DatabaseConnection {
   
    public static Connection getDatabaseConnection()
    {
        Connection con=null;
     try {
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//step2 create  the connection object

        try {
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
            System.out.println("Connection established");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    return con;
    
    }
     public static void main(String args[])
     {
     
     
     
     
     }
}
