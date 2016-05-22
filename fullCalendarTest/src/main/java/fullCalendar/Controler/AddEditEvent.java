package fullCalendar.Controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import com.google.gson.Gson;




import fullCalendar.Modle.CalendarDAO;
import fullCalendar.Modle.CalendarDTO;
import fullCalendar.Modle.ICalendarDAO;



@WebServlet("/AddEditEvent")
public class AddEditEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICalendarDAO ctd;
    public AddEditEvent() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try{	
	
		ctd=  new CalendarDAO();
		List<CalendarDTO> ctdallList = new ArrayList<CalendarDTO>() ;
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		System.out.println(request.getParameter("startdate"));
		java.util.Date start = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startdate"));
//		java.util.Date start = new java.util.Date(new Long(request.getParameter("startdate")));
		System.out.println(start);
		java.util.Date end = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("enddate"));
//		java.util.Date end = new java.util.Date(new Long(request.getParameter("enddate")));
		//String color = request.getParameter("color");
		String constraint ="rendering:'background'";
		
		CalendarDTO ctd1 = new CalendarDTO();
		ctd1.setTitle(title);
		ctd1.setStart(start);
		ctd1.setEnd(end);
//		ctd1.setColor(color);
		ctd1.setConstraint(constraint);
		ctd.insert(ctd1);
	

		
		RequestDispatcher successMsg = request.getRequestDispatcher("/calendarView_insert.jsp");
		successMsg.forward(request, response);
		
		return;
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 	
  }
}