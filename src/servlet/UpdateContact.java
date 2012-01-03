package servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Address;
import domain.Contact;
import domain.DAOContact;
import domain.DAOEntreprise;
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
			
			if (numSiret==""){
				/*
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
				*/
				/*
				Set<String> phoneKindSet = new HashSet<String>();
				Set<String> phoneNumberSet = new HashSet<String>();
				phoneKindSet.add(phoneKind);
				phoneNumberSet.add(phoneNumber);
				Set<String> groupNameSet = new HashSet<String>();
				groupNameSet.add(groupName);*/
				DAOContact daoContact=new DAOContact();
				daoContact.update(id, firstName, lastName, email, street, city, zip, country, phoneKind, phoneNumber, groupName);
				/*
				daoContact.createContact(firstName, lastName, email);
				daoContact.createAdress(street, city, zip, country);
				daoContact.createPhoneNumberSet(phoneKindSet, phoneNumberSet);
				daoContact.createContactGroupSet(groupNameSet);
				daoContact.commit();
				*/
			}
			else{
				/*
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
				*/
				DAOEntreprise daoEntreprise=new DAOEntreprise();
				daoEntreprise.update(id, firstName, lastName, email, street, city, zip, country, phoneKind, phoneNumber, groupName, Integer.parseInt(numSiret));
				/*
				Set<String> phoneKindSet = new HashSet<String>();
				Set<String> phoneNumberSet = new HashSet<String>();
				phoneKindSet.add(phoneKind);
				phoneNumberSet.add(phoneNumber);
				Set<String> groupNameSet = new HashSet<String>();
				groupNameSet.add(groupName);
				DAOEntreprise daoEntreprise=new DAOEntreprise();
				daoEntreprise.createEntreprise(firstName, lastName, email, Integer.parseInt(numSiret));
				daoEntreprise.createAdress(street, city, zip, country);
				daoEntreprise.createPhoneNumberSet(phoneKindSet, phoneNumberSet);
				daoEntreprise.createContactGroupSet(groupNameSet);
				daoEntreprise.commit();
				*/
			}
			request.getRequestDispatcher("updateContactSuccess.jsp").forward(request, response);
			/*
			transaction.commit();
			request.getRequestDispatcher("updateContactSuccess.jsp").forward(request, response);
			HibernateUtil.closeSession();*/
		}
		catch (Exception e){
			request.getRequestDispatcher("updateContactError.jsp").forward(request, response);
		}
	}

}
