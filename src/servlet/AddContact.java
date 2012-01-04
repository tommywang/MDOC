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

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
			//String groupName1 = request.getParameter("groupName1");
			//if (!groupName.equals(""))
				groupNameSet.add(groupName);
			//if (!groupName1.equals(""))
			//	groupNameSet.add(groupName1);
			
			if (request.getParameter("numSiret")!=""){
				System.out.println("passe entyrepreise");
				int numSiret=Integer.parseInt(request.getParameter("numSiret"));
				daoEntreprise.createEntreprise(firstName, lastName, email, numSiret);
				daoEntreprise.createAdress(street, city, zip, country);
				daoEntreprise.createPhoneNumberSet(phoneNumberSet, phoneKindSet);
				daoEntreprise.createContactGroupSet(groupNameSet);
				daoEntreprise.commit();
			}
			else{
				daoContact.createContact(firstName, lastName, email);
				daoContact.createAdress(street, city, zip, country);
				daoContact.createPhoneNumberSet(phoneNumberSet, phoneKindSet);
				daoContact.createContactGroupSet(groupNameSet);
				daoContact.commit();
			}
			request.getRequestDispatcher("addContactSuccess.jsp").forward(request, response);
		}
		catch (Exception e){
			request.getRequestDispatcher("addContactError.jsp").forward(request, response);
		}
	}
	
	
	/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			
			
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
			//groupNameSet.add("Travail");
			
			if (request.getParameter("numSiret")!=""){
				//SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				this.daoEntreprise = (DAOEntreprise)context.getBean("beanDAOEntreprise");
				this.daoEntreprise.setHibernateTemplate(sessionFactory);
				int numSiret=Integer.parseInt(request.getParameter("numSiret"));
				daoEntreprise.createEntreprise(firstName, lastName, email, numSiret);
				daoEntreprise.createAdress(street, city, zip, country);
				daoEntreprise.createPhoneNumberSet(phoneNumberSet, phoneKindSet);
				daoEntreprise.createContactGroupSet(groupNameSet);
				daoEntreprise.commit();
			}
			else{
				//SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				this.daoContact = (DAOContact)context.getBean("beanDAOContact");
				this.daoContact.setHibernateTemplate(sessionFactory);
				daoContact.createContact(firstName, lastName, email);
				daoContact.createAdress(street, city, zip, country);
				daoContact.createPhoneNumberSet(phoneNumberSet, phoneKindSet);
				daoContact.createContactGroupSet(groupNameSet);
				daoContact.commit();
			}
			request.getRequestDispatcher("addContactSuccess.jsp").forward(request, response);
		}
		catch (Exception e){
			e.printStackTrace();
			request.getRequestDispatcher("addContactError.jsp").forward(request, response);
		}
	}
	*/
}

