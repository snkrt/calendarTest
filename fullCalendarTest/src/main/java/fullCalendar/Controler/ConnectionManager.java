package fullCalendar.Controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class ConnectionManager {
	
	public static void getConnection(){
		
		String db_connect_string = "jdbc:sqlserver://localhost:1433;databaseName=Demo"; //jdbc:db2://75.126.155.153:50000/SQLDB
		String db_userid = "sa"; //user14422
		String db_password =  "P@ssw0rd"; //3hzL7wE3j2bx
		
      try {
    	  
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         Connection conn = DriverManager.getConnection(db_connect_string,db_userid, db_password);     
         System.out.println("connected");

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

 
}
