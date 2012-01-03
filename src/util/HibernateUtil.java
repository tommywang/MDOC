package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	
	static{
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	 public static Session currentSession()
			throws HibernateException {
	   Session s = (Session) session.get();
	   // Ouvre une nouvelle Session, si ce Thread n'en a aucune
	   if (s == null) {
	   s = sessionFactory.openSession();
	   session.set(s);
	   }
	   return s;
	   }

	 public static void closeSession()
			throws HibernateException {
	   Session s = (Session) session.get();
	   session.set(null);
	   if (s != null)
	   s.close();
	   }
	 
		
}