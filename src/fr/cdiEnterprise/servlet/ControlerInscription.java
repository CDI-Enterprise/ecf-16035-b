package fr.cdiEnterprise.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiEnterprise.dao.AccesBaseInscrit;
import fr.cdiEnterprise.model.Inscription;

/**
 * Servlet implementation class ControlerInscription
 */
@WebServlet(name="ControlerInscription", urlPatterns={"/inscription"})
public class ControlerInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlerInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		
		int reference = 0;
		String statut = request.getParameter("radio");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");
		String confirmation = request.getParameter("confirmation");
		
		Inscription exemple = new Inscription(reference, statut, nom, prenom, email, motDePasse, confirmation);
		
		AccesBaseInscrit base = new AccesBaseInscrit();
		
		base.inscription(exemple);
		
		request.setAttribute("inscription", exemple);
		RequestDispatcher dispatch = request.getRequestDispatcher("../la_page_recapitulatif.jsp");
		
		response.sendRedirect("../la_page_reussite.html");
		
	}

}
