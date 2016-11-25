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
 * Servlet implementation class ControlerDesinscription
 */
@WebServlet(
		name = "servletDesinscription", 
		description = "SeDesinscrire", 
		urlPatterns = {"/desinscription"}
		)
public class ControlerDesinscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlerDesinscription() {
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
		 *  R�cup�ration des variables depuis le formulaire Inscription.jsp
		 *  r�cup�ration possible car adresse indiqu�e dans le fichier "Inscription.jsp"		  
		 *  Pour la r�f�rence, valeur fixe ins�r�e en lieu et place comme telle dans la table INSCRIPTION
		 *  la m�thode permettant une inscription avec une auto-incr�mentation devra �tre d�finie
		 */

		int reference       = 4;
		String statut       = request.getParameter("radio");
		String nom          = request.getParameter("nom");
		String prenom       = request.getParameter("prenom");
		String email        = request.getParameter("email");
		String motDePasse   = request.getParameter("motDePasse");
		String confirmation = request.getParameter("confirmation");
		
		// Instanciation d' un objet de type Inscription
		Inscription exemple = new Inscription(reference, statut, nom, prenom, email, motDePasse, confirmation);
		System.out.println(" instance cr��e");
		System.out.println(exemple.toString());
		
		// Instanciation d' un objet de type AccesBaseInscrit
		AccesBaseInscrit base = new AccesBaseInscrit();
		System.out.println("instance AccesBaseInscrit cr��e");
		
		/*
		 *  Application de la m�thode desinscription de AccesBaseInscrit en lui donnant en param�tre l'objet exemple
		 *  Exception possible du type SQL. Si tout ne correspond pas entre les informations receuillies
		 *  et ceux d�j� pr�sentes dans la base de donn�es, l'op�ration ne sera pas effectu�e.
		 */
		try {
			base.desinscription(exemple);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//R�direction vers Recapitulatif.jsp 
		request.setAttribute("inscription", exemple);
		request.setAttribute("reussite", "OK");
		request.getRequestDispatcher("/jsp/Recapitulatif_desinscri.jsp").forward(request, response);
		System.out.println(" redirection vers recapitulatif");
				
			
	}
}

