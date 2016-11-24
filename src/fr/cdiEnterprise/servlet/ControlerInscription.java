package fr.cdiEnterprise.servlet;

import java.io.IOException;
import java.sql.SQLException;

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
		
		System.out.println("get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("post");
		
		/*
		 *  Récupération des variables depuis le formulaire Inscription.jsp
		 *  récupération possible car adresse indiquée dans le fichier "Inscription.jsp"		  
		 *  Pour la référence, valeur fixe insérée en lieu et place comme telle dans la table INSCRIPTION
		 *  la méthode permettant une inscription avec une auto-incrémentation devra être définie
		 */
		
<<<<<<< HEAD
		int reference       = 4;
		String statut       = request.getParameter("radio");
		String nom          = request.getParameter("nom");
		String prenom       = request.getParameter("prenom");
		String email        = request.getParameter("email");
		String motDePasse   = request.getParameter("motDePasse");
=======
		// Récupération des variables depuis le formulaire Inscription.jsp
		// récupération possible car adresse indiquée dans le formulaire Inscription.jsp

		int reference = 4;

		String statut = request.getParameter("radio");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		String confirmation = request.getParameter("confirmation");
		
<<<<<<< HEAD
		// Instanciation d'un objet de type Inscription
=======
		// Instanciation d' un objet de type Inscription

>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		Inscription exemple = new Inscription(reference, statut, nom, prenom, email, motDePasse, confirmation);
<<<<<<< HEAD
		System.out.println(" instance créée");
		System.out.println(exemple.toString());
		
=======
		//Inscription ex = new Inscription(reference, statut, nom, prenom, email, motDePasse, confirmation);

<<<<<<< HEAD
		//Inscription exemple = new Inscription(statut, nom, prenom, email, motDePasse, confirmation);
=======
		
		
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b

>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		// Instanciation d' un objet de type AccesBaseInscrit
		AccesBaseInscrit base = new AccesBaseInscrit();
<<<<<<< HEAD
		System.out.println("instance AccesBaseInscrit créée");
				
		// Application de la méthode inscription de AccesBaseInscrit en lui donnant en paramètre l'objet exemple
		try {
			base.inscription(exemple);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("envoie à la base");
=======
		//AccesBaseInscrit con = new AccesBaseInscrit();
		//con.inscription(ex);
		// Application de la méthode inscription de AccesBaseInscrit en lui donnant en paramètre l' objet exemple
//		base.desinscription(exemple);
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		
<<<<<<< HEAD
		//Rédirection vers Recapitulatif.jsp 
		request.setAttribute("inscription", exemple);
		request.setAttribute("reussite", "OK");
		request.getRequestDispatcher("/jsp/Recapitulatif.jsp").forward(request, response);
		System.out.println(" redirection vers recapitulatif");
=======

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
		
		
		
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		
=======
				

>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		
	}

}
