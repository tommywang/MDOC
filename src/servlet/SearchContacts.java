package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import util.HibernateUtil;

import domain.Contact;
import domain.ContactGroup;
import domain.DAOContact;
import domain.Entreprise;

/**
 * Servlet implementation class SearchContact
 */
@WebServlet("/SearchContacts")
public class SearchContacts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long cpt = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchContacts() {
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
		int numResult =Integer.parseInt(request.getParameter("numResult"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		DAOContact daoContact = (DAOContact)context.getBean("beanDAOContact");
		daoContact.setHibernateTemplate(sessionFactory);
		//DAOContact daoContact=new DAOContact();
		Set<Contact> contacts=daoContact.searchContacts(firstName,lastName,numResult);
		if (contacts.isEmpty()){
			response.setContentType( "text/html" );
			PrintWriter out = response.getWriter(); out.println( "<html><body>" );
			out.println( "<h1> Contact Not Found </h1>" );
			out.println("<input type=\"submit\" value=\"retour ˆ l'accueil\" onclick=\"javascript:window.location ='accueil.jsp';\"/>");

			out.println( "</body></html>" );
		}
		else{
			response.setContentType( "text/html" );
			PrintWriter out = response.getWriter(); out.println( "<html><body>" );
			out.println("<table border=\"1\"><tr><td>Id</td><td>First Name</td><td>Last Name</td><td>Email Name</td>");
			out.println("<td>Street</td><td>ZIP</td><td>City</td><td>Country</td><td>Phone Kind</td><td>Phone Number</td>");
			out.println("<td>Group Name</td><td>Siret Number</td>");
			out.println("</tr>");
			for (Contact contact :contacts){
				out.println("<tr>");
				out.println("<td>"+contact.getId_contact()+"</td>"+"");
				out.println("<td>"+contact.getFirstName()+"</td>"+"");
				out.println("<td>"+contact.getLastName()+"</td>"+"");
				out.println("<td>"+contact.getEmail()+"</td>"+"");
				out.println("<td>"+contact.getAddress().getStreet()+"</td>"+"");
				out.println("<td>"+contact.getAddress().getZip()+"</td>"+"");
				out.println("<td>"+contact.getAddress().getCity()+"</td>"+"");
				out.println("<td>"+contact.getAddress().getCountry()+"</td>"+"");
				out.println("<td>"+contact.getProfiles().iterator().next().getPhoneKind()+"</td>"+"");
				out.println("<td>"+contact.getProfiles().iterator().next().getPhoneNumber()+"</td>"+"");
				String groups="";
				Iterator it=contact.getBooks().iterator();
				if (it.hasNext())
					groups=((ContactGroup) it.next()).getGroupName();
				while(it.hasNext()){
					groups+=", "+((ContactGroup) it.next()).getGroupName();
				}
				out.println("<td>"+groups+"</td>"+"");
				if (contact instanceof Entreprise)
				out.println("<td>"+((Entreprise)contact).getNumSiret()+"</td>"+"");
				out.println("</tr>");
			}
			
			out.println("<input type=\"submit\" value=\"retour ˆ l'accueil\" onclick=\"javascript:window.location ='accueil.jsp';\"/>");
			out.println( "</body></html>" );
		}
	}

	
}
