package fullCalendar.Modle;

import java.sql.SQLException;
import java.util.List;

public interface ICalendarDAO {
	public void insert(CalendarDTO ctd) throws SQLException;
	public void update(CalendarDTO ctd) throws SQLException;
	public void delete(Integer id) throws SQLException;
	public CalendarDTO findByPrimaryKey(Integer id) throws SQLException;
	public List<CalendarDTO> getAll() throws SQLException;
}
