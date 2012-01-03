package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;

import domain.Contact;
import domain.DAOContact;
import domain.UnknownContactException;

/**
 * Servlet implementation class RemoveContact
 */
@WebServlet("/RemoveContact")
public class RemoveContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DAOContact daoContact;
    long cpt=0;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveContact() {
        super();
        // TODO Auto-generated constructor stub
    }

    private synchronized void test(){
        //Session session = HibernateUtil.getSessionFactory().openSession();
        //session.beginTransaction();
        Session session = HibernateUtil.currentSession();/*
        Contact contact =
        		(Contact) session.load(Contact.class, new Long(1));
        
        		contact.setFirstName("Jacques");
        		session.save(contact);
        		*/
        		List list = session.createQuery("from Contact where firstName like 'Hello'").list();
        		Iterator it = list.iterator();
        		while(it.hasNext())
        		{
        		  Contact contact = (Contact)it.next();
        		  System.out.println(contact.getLastName());
        		}
        		HibernateUtil.closeSession();
        		/*
        String hqlDelete = "delete Contact where id_contact=:tt";
        Query query=session.createQuery( hqlDelete ).setInteger( "tt",2 );
        query.executeUpdate();
        List email = session.createQuery("from Contact"
                ).list();*/
        //List courseResult = session.createQuery("select c.courseName from " +
          //      "Course as c").list();        
        //session.getTransaction().commit();
        //System.out.println("la taille est "+email.size());
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		daoContact = new DAOContact();
		test();
		response.setContentType( "text/html" );
		PrintWriter out = response.getWriter(); out.println( "<html><body>" );
		out.println( "<h1> Contact Supprimé </h1>" );
		out.println( "</body></html>" );
	}

}
