package fr.cdiEnterprise.servlet.company;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Company
 */
@WebServlet(name = "CompanyControl", urlPatterns = { "/Company/*" })

public class CompanyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RequestDispatcher disp;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recuperation de l'url (à partir du chemin relatif à la servlet
		// application cad après /bb)
		String path = request.getPathInfo();
		System.out.println("================  dans Controleur path=" + path);
		System.out.println("================  dans Controleur path=" + request.getContextPath());
		switch(path){
			case "/Creation":
				// affichage formulaire gestion des bonbons
				goCreation(request, response);
				break;
			case "/CompanyCreate":
				// affichage formulaire gestion des bonbons
				goCreate(request, response);
				break;
			case "/ModifSuppr":
				// affichage page pour modifier supprimer une fiche entreprise
				goUpDeleteCompany(request, response);
				break;
			case "/AffRecher":
				// affichage page pour afficher et rechercher des fiches entreprises
				goSearchList(request, response);
				break;
			case "/Affichage":
				// affichage page pour afficher et rechercher des fiches entreprises
				goList(request, response);
				break;
			case "/Modifier":
				goUpdateCompany(request, response);
				break;
			case "/Supprimer":
				goDeleteCompany(request, response);
				break;
			case "/FicheModifiee":
				goCompanyUpdated(request, response);
				break;
			default:
				System.out.println("Adresse non valide");
				doAccueil(request, response);
				break;
		}

		System.out.println("** Fin Controleur**");
	}

	/**
	 * Redirige vers la page d'accueil du site
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doAccueil(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		disp = request.getRequestDispatcher("/jsp/accueil.jsp");
		disp.forward(request, response);

	}

	/**
	 * Redirige vers la page de création d'une fiche entreprise
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goCreation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// System.out.println("--- in the goCreation method ---");
		// System.out.println(request.getPathInfo());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/action/Creation");
		// System.out.println(dispatcher);
		dispatcher.forward(request, response);

	}

	/**
	 * Redirige vers la page concernant l'entreprise nouvellement créée
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goCreate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// System.out.println("--- in the goCreate method ---");
		// System.out.println(request.getPathInfo());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/action/CompanyCreate");
		System.out.println(dispatcher);
		dispatcher.forward(request, response);
	}

	/**
	 * Redirige vers la page de sélection d'une fiche entreprise pour
	 * modification ou suppression
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goUpDeleteCompany(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		disp = request.getRequestDispatcher("/action/ModifSuppr");
		disp.forward(request, response);
	}

	/**
	 * Redirige vers la servlet permettant l'affichage de la page pour modifier
	 * une fiche entreprise
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goUpdateCompany(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		disp = request.getRequestDispatcher("/action/Modifier");
		disp.forward(request, response);
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goSearchList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		disp = request.getRequestDispatcher("/action/AffRecher");
		disp.forward(request, response);
	}

	private void goList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		disp = request.getRequestDispatcher("/action/Affichage");
		disp.forward(request, response);
	}
	
	/**
	 * Permet d'accéder à la page d'information fiche modifiée
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goCompanyUpdated(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		disp = request.getRequestDispatcher("/action/FicheModifiee");
		disp.forward(request, response);
	}

	/**
	 * Permet d'accéder à la page de suppression d'une fiche entreprise
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goDeleteCompany(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		disp = request.getRequestDispatcher("/action/Supprimer");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
