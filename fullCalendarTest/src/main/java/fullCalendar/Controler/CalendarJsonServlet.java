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
import fullCalendar.Modle.ICalendarDAO;


/*@WebServlet("/CalendarJsonServlet")*/
public class CalendarJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICalendarDAO ctd;
	
    public CalendarJsonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			ctd=  new CalendarDAO();
			List<CalendarDTO> ctdallList = new ArrayList<CalendarDTO>() ;
			ctdallList =  ctd.getAll();
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("title", ctdallList);
			
			response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter outJSON = response.getWriter();
	        outJSON.write(new Gson().toJson(ctdallList));
	
    	} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
    }