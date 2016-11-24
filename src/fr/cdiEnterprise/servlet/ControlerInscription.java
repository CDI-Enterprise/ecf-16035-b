package fr.cdiEnterprise.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiEnterprise.dao.AccesBaseInscrit;
import fr.cdiEnterprise.model.Inscription;

/**
 * Servlet implementation class ControlerInscriptions
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

		int reference = 4;

		String statut = request.getParameter("radio");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");
		String confirmation = request.getParameter("confirmation");
		
		// Instanciation d' un objet de type Inscription

		Inscription exemple = new Inscription(reference, statut, nom, prenom, email, motDePasse, confirmation);
		//Inscription ex = new Inscription(reference, statut, nom, prenom, email, motDePasse, confirmation);

<<<<<<< HEAD
		//Inscription exemple = new Inscription(statut, nom, prenom, email, motDePasse, confirmation);
=======
		
		
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b

		// Instanciation d' un objet de type AccesBaseInscrit
		AccesBaseInscrit base = new AccesBaseInscrit();
		//AccesBaseInscrit con = new AccesBaseInscrit();
		//con.inscription(ex);
		// Application de la méthode inscription de AccesBaseInscrit en lui donnant en paramètre l' objet exemple
//		base.desinscription(exemple);
		

		// TODO essai d' envoi de la réponse vers une page html.
	//	response.sendRedirect("../InscriptionReussie.html");

		// essai d' envoi de la réponse vers une page html.
		// response.sendRedirect("../Recapitulatif.jsp");

		// TODO essai pour générer un récapitulatif de l' inscription.
	//	request.setAttribute("inscription", exemple);
	//	RequestDispatcher dispatcher = request.getRequestDispatcher("../Recapitulatif.jsp");

		// essai pour générer un récapitulatif de l'inscription.
		request.setAttribute("inscription", exemple);
		request.setAttribute("reussite", "OK");
		request.getRequestDispatcher("/jsp/Recapitulatif.jsp").forward(request, response);

<<<<<<< HEAD
		
		
		
		
=======
				

>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		
	}

}
