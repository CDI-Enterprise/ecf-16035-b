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
@WebServlet(
		name="ControlerInscription", urlPatterns = {"/inscription", "/desinscription"}
)
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
		
		// Récupération des variables depuis le formulaire Inscription.jsp
		// récupération possible car adresse indiquée dans le formulaire Inscription.jsp
		
		
		String statut = request.getParameter("radio");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");
		String confirmation = request.getParameter("confirmation");
		
		// Instanciation d' un objet de type Inscription
		Inscription exemple = new Inscription(statut, nom, prenom, email, motDePasse, confirmation);
		
		// Instanciation d' un objet de type AccesBaseInscrit
		AccesBaseInscrit base = new AccesBaseInscrit();
		
		// Application de la méthode inscription de AccesBaseInscrit en lui donnant en paramètre l' objet exemple
//		base.desinscription(exemple);
		
		// essai d' envoi de la réponse vers une page html.
		// response.sendRedirect("../Recapitulatif.jsp");
		
		// essai pour générer un récapitulatif de l'inscription.
		request.setAttribute("inscription", exemple);
		request.setAttribute("reussite", "OK");
		request.getRequestDispatcher("/jsp/Recapitulatif.jsp").forward(request, response);
				
		
	}

}
