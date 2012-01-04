package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import util.HibernateUtil;

import domain.DAOContact;
import domain.DAOEntreprise;

/**
 * Servlet implementation class DefaultDatabases
 */
public class DefaultDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOContact daoContact;
	DAOEntreprise daoEntreprise;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DefaultDatabase() {
		super();
		this.daoContact = null;
		this.daoEntreprise = null;
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
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			DAOContact daoContact = (DAOContact)context.getBean("beanDAOContact");
			daoContact.springSetterWay(context);
			HibernateUtil.closeSession();
			response.setContentType( "text/html" );
			PrintWriter out = response.getWriter(); out.println( "<html><body>" );
			out.println( "<h1> Contact Added </h1>" );
			out.println("<input type=\"submit\" value=\"retour ˆ l'accueil\" onclick=\"javascript:window.location ='pages/accueil.jsp';\"/>");

			out.println( "</body></html>" );
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
