package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.*;

/**
 * Servlet implementation class AddContact
 */
@WebServlet("/AddContact")
public class AddContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOContact daoContact;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddContact() {
		super();
		this.daoContact = null;;
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

		try{
			//		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

			this.daoContact = new DAOContact();
			//		this.daoContact = (DAOContact)context.getBean("beanDAOContact");		
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			//		Contact contact1 = (Contact)context.getBean("beanContact");
			//		Contact contact1 = new Contact();
			//		contact1.setUpContact(firstName, lastName, email);	
			this.daoContact.createContact(firstName, lastName, email);
			//		this.daoContact.createContact(contact1);

			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String zip = request.getParameter("zip");
			String country = request.getParameter("country");
			//		Address address = new Address();
			//		address.setUpAddress(street, city, zip, country);
			this.daoContact.createAdress(street, city, zip, country);

			Set<String> phoneKindSet = new HashSet<String>();
			Set<String> phoneNumberSet = new HashSet<String>();
			String phoneKind = request.getParameter("phoneKind");
			System.out.println("PHONEKIND = " + phoneKind);
			String phoneNumber = request.getParameter("phoneNumber");
			phoneKindSet.add(phoneKind);
			phoneNumberSet.add(phoneNumber);
			//		PhoneNumber phoneNumberObject1 = new PhoneNumber();
			//		phoneNumberObject1.setUpPhoneNumber(phoneNumber, phoneKind);
			this.daoContact.createPhoneNumberSet(phoneNumberSet, phoneKindSet);

			Set<String> groupNameSet = new HashSet<String>();
			String groupName = request.getParameter("groupName");
			groupNameSet.add(groupName);
			//		ContactGroup contactGroup1 = new ContactGroup();
			//		contactGroup1.setUpContactGroup(groupName);
			this.daoContact.createContactGroupSet(groupNameSet);

			this.daoContact.commit();

			request.getRequestDispatcher("addContactSuccess.jsp").forward(request, response);
		}
		catch (Exception e){
			request.getRequestDispatcher("addContactError.jsp").forward(request, response);
		}

		response.setContentType( "text/html" );
		PrintWriter out = response.getWriter();
		out.println( "<html><body>" );
		out.println( "<h1> Contact Ajouté </h1>" );
		out.println( "<h1> Adresse Ajoutée </h1>" );
		out.println( "<h1> Numéro de téléphone n°1 ajouté </h1>" );
		out.println("" +
				"<input type=\"submit\" value=\"retour à l'accueil\" onclick=\"javascript:window.location =\'accueil.jsp\';\"/>" +
				"<p></p>");
		out.println( "</body></html>" );
	}
}

