package fullCalendar.Controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;




import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fullCalendar.Modle.CalendarDAO_JDBC;
import fullCalendar.Modle.CalendarDTO;
import fullCalendar.Modle.CalendarDAO;


/*@WebServlet("/CalendarJsonServlet")*/
public class CalendarJsonServlet_SQLserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarJsonServlet_SQLserve() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	
    	PrintWriter out = response.getWriter();
        //List calRecord = new ArrayList();
        ArrayList<HashMap<String, String>> checkCalData = null;
		try {
				checkCalData = new CalendarDAO_JDBC().calValues();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
        System.out.println("Sample display of data which is stored in arraylist in servlet");

        for (int i=0;i<checkCalData.size();i++){
            System.out.println(checkCalData.get(i).get("0"));
            System.out.println(checkCalData.get(i).get("1"));
            String repdate=checkCalData.get(i).get("2");//report date
            System.out.println(repdate);//.subSequence(0,9));
           // System.out.println(currentData);
        }

	        List eventList = new ArrayList();   
	        System.out.println("Display data in calendar"); 
	        CalendarDTO d;
	        
		    for (int i=0;i<checkCalData.size();i++){         
		    d=new CalendarDTO();
		    try {
		
		    String reportname=checkCalData.get(i).get("0");//report name
		 
		 //convert date value and pick only date part and store it to bean part and try to display.....
		    String inputStart=checkCalData.get(i).get("1");
		    DateFormat  sdfSta = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    java.util.Date calStartdate =  (java.util.Date) sdfSta.parse(inputStart);
	//	    java.util.Date calStartdate =  new java.util.Date(new java.util.Date().getTime());
	//	    java.sql.Date calStartdate =  new java.sql.Date(utilDateSta.getTime()) ;
		    
		    String inputEnd=checkCalData.get(i).get("2");
		    java.util.Date calEnddate =  (java.util.Date) sdfSta.parse(inputEnd);
		   
		    String reportColor=checkCalData.get(i).get("3");//report name
 
		  //set EVENT is background
		    String reportRed=checkCalData.get(i).get("4");//report name

		  //print detail   
		    System.out.println(calStartdate);
		    System.out.println(calEnddate); 
		    System.out.println(reportColor);
		    System.out.println(reportname);
		    System.out.println(reportRed);
		
	     //setting data into calendar object
		     d.setId(i);
		     d.setStart(calStartdate);
		     d.setEnd(calEnddate);
		     d.setTitle(reportname);
		     d.setColor(reportColor);
		     d.setConstraint(reportRed);
			
		  //setting data into list object
		     eventList.add(d);  
		     
		    } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
	        
	    /*Converting to json values*/
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter outJSON = response.getWriter();
	        outJSON.write(new Gson().toJson(eventList));
    
	     }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
    }