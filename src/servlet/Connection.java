package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyFirstServlet
 */
@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//
		if (username=="" || password==""){
			request.setAttribute("erreur", "Vous devez remplir les deux champs!");
			request.getRequestDispatcher("pages/index.jsp").forward(request, response);
		}
		else if (username.equals(password)){
			response.sendRedirect("pages/accueil.jsp");
		}
		else{
			request.setAttribute("erreur", "Le nom doit être identique au mot de passe!");
			request.getRequestDispatcher("pages/index.jsp").forward(request, response);
		}
	}

}
