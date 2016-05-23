package fullCalendar.Modle;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


public class CalendarDTO implements Serializable {
	
	public Integer id;
	public String title;
	public java.util.Date start;
	public java.util.Date end;
	public String color;
	public String constraint;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public java.util.Date getStart() {
		return start;
	}
	public void setStart(java.util.Date start) {
		this.start = start;
	}
	public java.util.Date getEnd() {
		return end;
	}
	public void setEnd(java.util.Date end) {
		this.end = end;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getConstraint() {
		return constraint;
	}
	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public CalendarDTO(Integer id, String title, java.util.Date start, java.util.Date end,
			String color,String constraint) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.color = color;
		this.constraint = constraint;
	}
	public CalendarDTO() {
		super();
	}
	
}
