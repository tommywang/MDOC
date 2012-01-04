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
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import util.HibernateUtil;

import domain.Contact;
import domain.ContactGroup;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			long id = Long.parseLong(request.getParameter("id"));
			DAOContact daoContact=new DAOContact();
			daoContact.deleteContact(id);
	        request.getRequestDispatcher("updateContactSuccess.jsp").forward(request, response);
		}
		catch (Exception e){
			response.setContentType( "text/html" );
			PrintWriter out = response.getWriter(); out.println( "<html><body>" );
			out.println( "<h1> Delete Failed </h1>" );
			out.println( "</body></html>" );
		}
	}*/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			this.daoContact = (DAOContact)context.getBean("beanDAOContact");
			this.daoContact.setHibernateTemplate(sessionFactory);
			long id = Long.parseLong(request.getParameter("id"));
			daoContact.deleteContact(id);
	        request.getRequestDispatcher("removeContactSuccess.jsp").forward(request, response);
		}
		catch (Exception e){
			response.setContentType( "text/html" );
			PrintWriter out = response.getWriter(); out.println( "<html><body>" );
			out.println( "<h1> Delete Failed </h1>" );
			out.println( "</body></html>" );
		}
	}

}
