package servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.HibernateUtil;

import domain.*;

/**
 * Servlet implementation class AddContact
 */
@WebServlet("/AddContact")
public class AddContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOContact daoContact;
	private DAOEntreprise daoEntreprise;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddContact() {
		super();
		this.daoContact = null;
		this.daoEntreprise=null;
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
			daoEntreprise=new DAOEntreprise();
			daoContact = new DAOContact();
			//recuperer toutes les données
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");

			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String zip = request.getParameter("zip");
			String country = request.getParameter("country");

			Set<String> phoneKindSet = new HashSet<String>();
			Set<String> phoneNumberSet = new HashSet<String>();
			String phoneKind = request.getParameter("phoneKind");
			String phoneNumber = request.getParameter("phoneNumber");
			phoneKindSet.add(phoneKind);
			phoneNumberSet.add(phoneNumber);

			Set<String> groupNameSet = new HashSet<String>();
			String groupName = request.getParameter("groupName");
			groupNameSet.add(groupName);

			if (request.getParameter("numSiret")!=""){//entreprise
				int numSiret=Integer.parseInt(request.getParameter("numSiret"));
				daoEntreprise.createEntreprise(firstName, lastName, email, numSiret);
				daoEntreprise.createAdress(street, city, zip, country);
				daoEntreprise.createPhoneNumberSet(phoneNumberSet, phoneKindSet);
				daoEntreprise.createContactGroupSet(groupNameSet);
				daoEntreprise.commit();
			}
			else{//contact 
				daoContact.createContact(firstName, lastName, email);
				daoContact.createAdress(street, city, zip, country);
				daoContact.createPhoneNumberSet(phoneNumberSet, phoneKindSet);
				daoContact.createContactGroupSet(groupNameSet);
				daoContact.commit();
			}
			HibernateUtil.closeSession();
			request.getRequestDispatcher("addContactSuccess.jsp").forward(request, response);
		}
		catch (Exception e){
			request.getRequestDispatcher("addContactError.jsp").forward(request, response);
		}
	}

}

