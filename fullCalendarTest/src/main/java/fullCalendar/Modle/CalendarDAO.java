package fullCalendar.Modle;

import hibernate.util.HibernateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


public class CalendarDAO implements ICalendarDAO {

	
	private static final String GET_ALL_STMT = 
			"from CalendarDTO";
	
	@Override
	public void insert(CalendarDTO ctd) throws SQLException {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
		try {
			session.beginTransaction();
			session.saveOrUpdate(ctd);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}



	@Override
	public void update(CalendarDTO ctd) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(ctd);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}



	@Override
	public void delete(Integer id) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			System.out.println("--------------del");
			session.beginTransaction();
			CalendarDTO ctd = (CalendarDTO) session.get(CalendarDTO.class, id);
			System.out.println("ctd = " + ctd.id);
			session.delete(ctd);
			session.getTransaction().commit();	
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}



	@Override
	public CalendarDTO findByPrimaryKey(Integer id) throws SQLException {
		CalendarDTO ctd = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ctd = (CalendarDTO) session.get(CalendarDTO.class, id);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return ctd;
	}



	@Override
	public List<CalendarDTO> getAll() throws SQLException {
		List<CalendarDTO> list = null;
		System.out.println("-----------------------2");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
			System.out.println("-----------------------3");
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

}
