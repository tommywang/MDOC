package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
			System.out.println("yessssssssssssssss");
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			this.daoEntreprise = (DAOEntreprise)context.getBean("beanDAOEntreprise");
			this.daoContact = (DAOContact)context.getBean("beanDAOContact");
			this.daoContact.springSetterWay(context); //on présuppose la base vide à ce moment
			System.out.println("fffffffffffffffffffffff");
			this.daoContact.commit();
			System.out.println("fffffffffffffffffffffff");
			this.daoContact.springConstructorWay(context);
			this.daoContact.commitAddress();
			response.sendRedirect("pages/accueil.jsp");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
