package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import domain.DAOContact;
import domain.Entreprise;

/**
 * Servlet implementation class SearchContact
 */
@WebServlet("/SearchContactById")
public class SearchContactById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long cpt = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchContactById() {
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
		long id =Long.parseLong(request.getParameter("id"));
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		DAOContact daoContact = (DAOContact)context.getBean("beanDAOContact");
		daoContact.setHibernateTemplate(sessionFactory);
		//recuperer le contact de la base
		Contact contact=daoContact.searchContactById(id);
		if (contact==null){
			response.setContentType( "text/html" );
			PrintWriter out = response.getWriter(); out.println( "<html><body>" );
			out.println( "<h1> Contact Not Found </h1>" );
			out.println("<input type=\"submit\" value=\"retour ˆ l'accueil\" onclick=\"javascript:window.location ='accueil.jsp';\"/>");

			out.println( "</body></html>" );
		}
		else{
			//ajouter des attributes pour remplir dynamiquement la page 
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

			if (contact instanceof Entreprise){
				request.setAttribute("numSiret", ((Entreprise)contact).getNumSiret());
				HibernateUtil.closeSession();
				request.getRequestDispatcher("SearchResultEntreprise.jsp").forward(request, response);
			}

			else{
				request.setAttribute("numSiret", "");
				HibernateUtil.closeSession();
				request.getRequestDispatcher("SearchResultContact.jsp").forward(request, response);
			}
		}
	}

}
