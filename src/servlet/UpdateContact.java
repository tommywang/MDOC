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
				DAOContact daoContact=new DAOContact();
				daoContact.update(id, firstName, lastName, email, street, city, zip, country, phoneKind, phoneNumber, groupName);
			}
			else{
				DAOEntreprise daoEntreprise=new DAOEntreprise();
				daoEntreprise.update(id, firstName, lastName, email, street, city, zip, country, phoneKind, phoneNumber, groupName, Integer.parseInt(numSiret));
	
			}
			request.getRequestDispatcher("updateContactSuccess.jsp").forward(request, response);
		}
		catch (Exception e){
			request.getRequestDispatcher("updateContactError.jsp").forward(request, response);
		}
	}

}
