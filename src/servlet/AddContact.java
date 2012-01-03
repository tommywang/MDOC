package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
	private DAOAddress daoAddress;
	private DAOPhoneNumber daoPhoneNumber;
	private DAOContactGroup daoContactGroup;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddContact() {
		super();
		this.daoContact = null;
		this.daoAddress = null;
		this.daoPhoneNumber = null;
		this.daoContactGroup = null;
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
		
		this.daoAddress = new DAOAddress();
//		this.daoAddress = (DAOAddress)context.getBean("beanDAOAddress");		
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
//		Address address = new Address();
//		address.setUpAddress(street, city, zip, country);
		this.daoAddress.createAdress(street, city, zip, country);
		this.daoContact.addAdress(this.daoAddress.getAddress());
//		this.daoAddress.createAddress(address);
		
		this.daoPhoneNumber = new DAOPhoneNumber();
//		this.daoPhoneNumber = (DAOPhoneNumber)context.getBean("beanDAOPhoneNumber");	
		String phoneKind = request.getParameter("phoneKind");
		String phoneNumber = request.getParameter("phoneNumber");
//		PhoneNumber phoneNumberObject1 = new PhoneNumber();
//		phoneNumberObject1.setUpPhoneNumber(phoneNumber, phoneKind);
		this.daoPhoneNumber.createPhoneNumber(phoneKind, phoneNumber);
		this.daoContact.addPhoneNumber(this.daoPhoneNumber.getPhoneNumber());
		this.daoPhoneNumber.addContact(this.daoContact.getContact());
//		this.DAOPhoneNumber.createPhoneNumber(phoneNumberObject1);
		
		this.daoContactGroup = new DAOContactGroup();
//		this.daoContactGroup = (DAOContactGroup)context.getBean("beanDAOContactGroup");
		String groupName = request.getParameter("groupName");
//		ContactGroup contactGroup1 = new ContactGroup();
//		contactGroup1.setUpContactGroup(groupName);
		this.daoContactGroup.createContactGroup(groupName);
		this.daoContact.addContactGroup(this.daoContactGroup.getContactGroup());
		this.daoContactGroup.addContact(this.daoContact.getContact());
//		this.daoContactGroup.createContactGroup(contactGroup1);
		
		this.daoAddress.validateAddress();
		this.daoContact.validateContact();
		this.daoPhoneNumber.validatePhoneNumber();
		this.daoContactGroup.validateContactGroup();
		this.daoAddress.commitAddress();
		this.daoContact.commitContact();
		this.daoContactGroup.commitContactGroup();
		this.daoPhoneNumber.commitPhoneNumber();
		
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

