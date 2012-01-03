package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Address;
import domain.Contact;
import domain.Entreprise;

import util.HibernateUtil;

/**
 * Servlet implementation class UpdateContact
 */
@WebServlet("/UpdateContact")
public class UpdateContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateContact() {
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
		try {
			long id = Long.parseLong(request.getParameter("id"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String zip = request.getParameter("zip");
			String country = request.getParameter("country");
			String phoneKind = request.getParameter("phoneKind");
			String phoneNumber = request.getParameter("phoneNumber");
			String groupName = request.getParameter("groupName");
			String numSiret = request.getParameter("numSiret");

			Session session = HibernateUtil.currentSession();
			Transaction transaction = session.beginTransaction();
			if (numSiret==""){
				Contact contact = (Contact) session.load(Contact.class, id);
				//System.out.println(firstName);
				contact.setFirstName(firstName);
				contact.setLastName(lastName);
				contact.setEmail(email);
				Address address=contact.getAddress();
				address.setStreet(street);
				address.setCity(city);
				address.setZip(zip);
				address.setCountry(country);
				contact.getProfiles().iterator().next().setPhoneKind(phoneKind);
				contact.getProfiles().iterator().next().setPhoneNumber(phoneNumber);
				contact.getBooks().iterator().next().setGroupName(groupName);

				session.save(contact);
			}
			else{
				Entreprise entreprise = (Entreprise) session.load(Entreprise.class, id);
				//System.out.println(firstName);
				entreprise.setFirstName(firstName);
				entreprise.setLastName(lastName);
				entreprise.setEmail(email);
				Address address=entreprise.getAddress();
				address.setStreet(street);
				address.setCity(city);
				address.setZip(zip);
				address.setCountry(country);
				entreprise.getProfiles().iterator().next().setPhoneKind(phoneKind);
				entreprise.getProfiles().iterator().next().setPhoneNumber(phoneNumber);
				entreprise.getBooks().iterator().next().setGroupName(groupName);
				entreprise.setNumSiret(Integer.parseInt(numSiret));
				session.save(entreprise);;
			}
			transaction.commit();
			request.getRequestDispatcher("updateContactSuccess.jsp").forward(request, response);
			HibernateUtil.closeSession();
		}
		catch (Exception e){
			request.getRequestDispatcher("updateContactError.jsp").forward(request, response);
		}
	}

}
