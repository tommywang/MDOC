package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
		//response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher rd = request.getRequestDispatcher("pages/accueil.jsp");
		if (username=="" || password==""){
			request.setAttribute("erreur", "Vous devez remplir les deux champs!");
			request.getRequestDispatcher("pages/index.jsp").forward(request, response);
			//response.sendRedirect("pages/index.jsp");
		}
		else if (username.equals(password)){
			//rd.forward(request, response);
			response.sendRedirect("pages/accueil.jsp");
		}
		else{
			request.setAttribute("erreur", "Le nom doit �tre identique au mot de passe!");
			request.getRequestDispatcher("pages/index.jsp").forward(request, response);
			//response.sendRedirect("pages/index.jsp");
		}
	}

}
