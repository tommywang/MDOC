package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DAOContact;
import domain.UnknownContactException;

/**
 * Servlet implementation class SearchContact
 */
@WebServlet("/SearchContact")
public class SearchContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DAOContact daoContact;
    long cpt = 0;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchContact() {
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
		String username = request.getParameter("username");
		String newUsername = request.getParameter("newUsername");
		String newPassword = request.getParameter("newPassword");
		String newEmail = request.getParameter("newEmail");
		daoContact = new DAOContact();
		response.setContentType( "text/html" );
		PrintWriter out = response.getWriter(); out.println( "<html><body>" );
		out.println( "<h1> Contact Supprimé </h1>" );
		out.println( "</body></html>" );
	}

}
