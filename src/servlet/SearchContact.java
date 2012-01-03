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

import org.hibernate.Session;

import util.HibernateUtil;

import domain.Address;
import domain.Contact;
import domain.DAOContact;
import domain.UnknownContactException;

/**
 * Servlet implementation class SearchContact
 */
@WebServlet("/SearchContact")
public class SearchContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long cpt = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchContact() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		Contact contact=searchContact(username);
		if (contact==null){
			response.setContentType( "text/html" );
			PrintWriter out = response.getWriter(); out.println( "<html><body>" );
			out.println( "<h1> Contact Not Found </h1>" );
			out.println( "</body></html>" );
		}
		else{
			//ystem.out.println(contact.getLastName());
			request.setAttribute("id", contact.getId_contact());
			request.setAttribute("firstName", contact.getFirstName());
			request.setAttribute("lastName", contact.getLastName());
			request.setAttribute("email", contact.getEmail());
			request.setAttribute("street", contact.getAddress().getStreet());
			request.setAttribute("zip", contact.getAddress().getZip());
			request.setAttribute("city", contact.getAddress().getCity());
			request.setAttribute("country", contact.getAddress().getCountry());
			request.setAttribute("phoneNumber", contact.getProfiles().iterator().next().getPhoneNumber());
			request.setAttribute("groupName", contact.getBooks().iterator().next().getGroupName());
			request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
		
		}
	}

	private synchronized Contact searchContact(String name){
		Session session = HibernateUtil.currentSession();
		String hqlSearch="select c from Contact as c left join fetch c.address a "
						+ "left join fetch c.profiles p "
						+ "left join fetch c.books b "
						+ "where firstName like :name";
		List list = session.createQuery(hqlSearch).setString("name", name).list();
		Iterator it = list.iterator();
		//while(it.hasNext())
		//{
		// Contact contact = (Contact)it.next();
	 
		//}
		HibernateUtil.closeSession();
		if (it.hasNext()){
			Contact contact = (Contact)it.next();
			System.out.println(contact.getLastName());	
			return contact;
		}
		else{
			return null;
		}
	}
}
