/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(urlPatterns = {"/DatabaseOperations"})
public class DatabaseOperations extends HttpServlet {
   static Connection con=null;
   static Statement st=null;
static
{
    
    
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
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

    try {
        //step3 create the statement object
         st = con.createStatement();
    } catch (SQLException ex) {
        Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
    }




    




}
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        response.setContentType("text/html;charset=UTF-8");
         System.out.println("hiiiiii");
         Connection con = DatabaseConnection.getDatabaseConnection();
         System.out.println(con);
         if( request.getParameter("create")!=null)
         {
        String b1 = request.getParameter("create");
       
        if(b1.equals("create"))
                {
            try {
                String ctname = request.getParameter("ctname");
               st =  con.createStatement();
               int colcount = Integer.parseInt(request.getParameter("colc"));
               String pkey = request.getParameter("pkey");
               String query=null;
               query = "CREATE TABLE  "+ctname +" ( ";
               for(int i=1;i<=colcount;i++)
               {
               String colname = request.getParameter("col"+i);
               String coltype = request.getParameter("col2"+i);
               query += colname+" "+coltype+", ";
               
               }
               query+="PRIMARY KEY ( "+pkey+" ))";
               System.out.println(query);
          /*      String sql = "CREATE TABLE  "+ctname +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))"; */
DatabaseCrudOperations.createTable(ctname, colcount, pkey, st, query);
    //  st.executeUpdate(query);
     try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          out.println("Table Created Successfully");
        }
      System.out.println("created table ");
      
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
             
                }
         }
        if(request.getParameter("retrieve")!= null)
                {
        String b2 = request.getParameter("retrieve");
        if(b2.equals("retrieve"))
                {
            try {
                String rtname = request.getParameter("rtname");
                 String prt = request.getParameter("prt");
                 String query;
                 int pkey = Integer.parseInt(prt);
               st =  con.createStatement();
               query = "select * from "+rtname+" where id='"+pkey+"'";
            /*   ResultSet rs=st.executeQuery(query);  
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
}*/
//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 

DatabaseCrudOperations.retrieveTable(rtname, pkey, query, st);
                try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          out.println("Table Retrieved Successfully");
        }
      System.out.println("retrieved table ");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                }
        
        
        
          if(request.getParameter("delete")!= null)
                {
        String b3 = request.getParameter("delete");
        if(b3.equals("delete"))
                {
            try {
                String dtname = request.getParameter("dtname").trim();
                int pdt = Integer.parseInt(request.getParameter("pdt"));
               st =  con.createStatement();
               String query="delete from "+dtname+" where id='"+pdt+"'";
           //  st.executeUpdate(query);
DatabaseCrudOperations.deleteTable(dtname, pdt, query, st);
 try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          out.println("record deleted Successfully");
        }
                
      System.out.println("record deleted ");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                }
          if(request.getParameter("update")!= null)
                {
        String b3 = request.getParameter("update");
        if(b3.equals("update"))
                {
            try {
                String utname = request.getParameter("utname");
                int put = Integer.parseInt(request.getParameter("put"));
                 String fut = request.getParameter("fut");
                  String vut = request.getParameter("vut");
               st =  con.createStatement();
            // st.executeUpdate("update "+utname+" set "+fut+"="+vut+" where id="+put);
            DatabaseCrudOperations.updateTable(utname, put, fut, vut, st);
 try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          out.println("record updated Successfully");
        }
                
      System.out.println("updated table ");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                }
      
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
