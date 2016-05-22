package fullCalendar.Controler;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.exception.ConstraintViolationException;

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

		
		String action = request.getParameter("action");
		System.out.println(action);
		
		if ("add".equals(action)) {
		try{	
			ctd=  new CalendarDAO();
			List<CalendarDTO> ctdallList = new ArrayList<CalendarDTO>() ;
			request.setCharacterEncoding("UTF-8");
			String title = request.getParameter("title");
			System.out.println(request.getParameter("startdate"));
			Date start = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startdate"));
			System.out.println(start);
			Date end = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("enddate"));
			String constraint ="rendering:'background'";
			
			CalendarDTO ctd1 = new CalendarDTO();
			ctd1.setTitle(title);
			ctd1.setStart(start);
			ctd1.setEnd(end);
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
	
	
	if ("del".equals(action)) {
		Integer id = null;
		Map<String, String> errorMsgs = null;
		try {
			// ============接收id資料====================
			id =Integer.parseInt(request.getParameter("id"));
			System.out.println("id=" + id);
			// ============呼叫方法刪除資料====================
			ctd = new  CalendarDAO();	
			ctd.delete(id);
			
			/*****************************************/
			List<CalendarDTO> calendarDTO = ctd.getAll();
			request.setAttribute("calendarDTO", calendarDTO);
			RequestDispatcher successMsg = request
					.getRequestDispatcher("/calendarView_insert.jsp");
			successMsg.forward(request, response);
			return;
		}
		catch (ConstraintViolationException e) {
			System.out.println("hibernate 因資料已被使用中，外部連結學生檔有資料");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
  }
}

