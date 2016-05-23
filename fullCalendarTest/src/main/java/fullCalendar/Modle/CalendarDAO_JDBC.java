package fullCalendar.Modle;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;



public class CalendarDAO_JDBC {
	private static final String INSERT_STMT =
			"insert into  calendar (title,starttime,endtime,color) values (?, ?, ?, ?, ?)"; 
	
	  Connection conn = null;
	
	  public CalendarDAO_JDBC() throws SQLException{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=demo";
		String userId="sa";
		String userPwd="P@ssw0rd";
		conn = DriverManager.getConnection(connUrl, userId, userPwd);
	  }
	
	
	
	public  ArrayList<HashMap<String, String>> calValues() {
	    HashMap<String, String> hm;
	    ArrayList<HashMap<String, String>> calrecord=new ArrayList<HashMap<String, String>>();

	    CalendarDTO user=new CalendarDTO();
	  

	    String query="select * from calendar";

	    try{
	    	 
	       Statement stmt=conn.createStatement(); 
	       ResultSet rs = stmt.executeQuery(query); 
	       boolean more = rs.next();
	       
	        // if month and year not exist 
	        if (!more) {
	            System.out.println("Sorry, not valid");             
	        }

	        //if month and year exists 
	        else if (more) {                
	            do{
	            	
	                hm=new HashMap<String,String>();
	                String repName=rs.getString("title");
	                System.out.println("Report Name : "+repName);
	                Timestamp repFreq=rs.getTimestamp("starttime");
	                System.out.println("Report Frequency : "+repFreq);
	                Timestamp reportDate=rs.getTimestamp("endtime");
	                System.out.println("Report Date : "+reportDate);
	                String reportColor=rs.getString("color");
	                System.out.println("Report Date : "+reportColor);
	                String reportRed=rs.getString("constr");
	                System.out.println("Report Date : "+reportRed);

	                hm.put("0", rs.getString("title"));
	                hm.put("1", rs.getString("starttime"));
	                hm.put("2", rs.getString("endtime"));
	                hm.put("3", rs.getString("color"));
	                hm.put("4", rs.getString("constr"));
	                
	                calrecord.add(hm);                   
	            }while(rs.next());
	        }                       
	    }catch(Exception e){
	        System.out.println(e.getMessage());     
	    }
	    return calrecord;
	}


	public int insert(CalendarDTO cto) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);
		int updateCount = 0;
		pstmt.setString(1, cto.getTitle());
		pstmt.setDate(2,  (Date) cto.getStart());
		pstmt.setDate(3, (Date) cto.getEnd());
		pstmt.setString(4, cto.getColor());

		updateCount = pstmt.executeUpdate();
		return updateCount;
	}

}
