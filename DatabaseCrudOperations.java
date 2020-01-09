
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
public class DatabaseCrudOperations {
    public static void createTable(String ctname,int colcount,String pkey ,Statement st,String query) throws SQLException
    {
    try
    {
     st.executeUpdate(query);
    System.out.println("table created in createTable method");
    
    }
    catch(Exception e)
    {
        
        System.out.println(e);
        
    }
    }
    public static void retrieveTable(String rtname,int pkey,String query,Statement st)
    {
    
        try {
            ResultSet rs=st.executeQuery(query);
            ResultSetMetaData rsmd=rs.getMetaData();
            int colc = rsmd.getColumnCount();
            System.out.println("Total columns: "+rsmd.getColumnCount());
            System.out.println("Column Name of 1st column: "+rsmd.getColumnName(1));
            System.out.println("Column Type Name of 1st column: "+rsmd.getColumnTypeName(1));
            System.out.println("Column Type Name of 1st column: "+rsmd.getColumnTypeName(2));
            String qq="";
            while(rs.next())
            {
                for(int i=1;i<=colc;i++)
                {
                    //System.out.println("hii");
                    if((rsmd.getColumnTypeName(i)).equals("NUMBER"))
                    {
//qq += "rs.getInt('"+i+"')"+" ";
                        System.out.println(rsmd.getColumnName(i)+"   "+rs.getInt(i));
                        
                    }
                    else if(rsmd.getColumnTypeName(i).equals("VARCHAR2"))
                    {
//qq+= "rs.getString('"+i+"')"+" ";
                        System.out.println(rsmd.getColumnName(i)+"   "+rs.getString(i));
                    }
                    
                    
                }
//System.out.println(qq);
            }      
        System.out.println("table retrieved in retrieve table method");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseCrudOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    public static void deleteTable(String dtname,int pdt,String query,Statement st)
    {
    
        try {
            st.executeUpdate(query);
             System.out.println("record deleted in delete table method");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseCrudOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    public static void updateTable(String utname,int put,String fut, String vut,Statement st)
    {
    
        try {
            System.out.println(vut);
            st.executeUpdate("update "+utname+" set "+fut+" = "+vut+" where id="+put);
             System.out.println("record updated in update table method");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseCrudOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    public static void main(String args[])
     {
     
     
     
     
     }
}
