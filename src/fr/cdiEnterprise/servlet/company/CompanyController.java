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
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperation de l'url (à partir du chemin relatif à la servlet
		String path = request.getPathInfo();
		System.out.println("================  dans Controleur path=" + path);
		System.out.println("================  dans Controleur path=" + request.getContextPath());

		switch (path) {
		case "/Creation":
			// Pour afficher formulaire creation
			goCreation(request, response);
			break;
		case "/CompanyCreate":
			// Pour afficher fiche modifiee
			goCreate(request, response);
			break;
		case "/ModifSuppr":
			// Pour afficher formulaire modifier supprimer
			goUpDeleteCompany(request, response);
			break;
		case "/AffRecher":
			// Pour afficher formulaire rechercher lister
			goSearchList(request, response);
			break;
		case "/Affichage":
			// Pour afficher la liste des fiches
			goList(request, response);
			break;
		case "/Modifier":
			// Pour afficher formulaire de modification
			goUpdateCompany(request, response);
			break;
		case "/Supprimer":
			// Pour supprimer une fiche entreprise
			goDeleteCompany(request, response);
			break;
		case "/FicheModifiee":
			// Pour afficher récapitulatif fiche modifiée
			goCompanyUpdated(request, response);
			break;
		case "/Rechercher":
			// Pour afficher la page de recherche
			goCompanySearch(request, response);
			break;
		default:
			// Renvoi vers l'accueil si l'url patterns est inconnue
			System.out.println("Adresse non valide");
			doAccueil(request, response);
			break;
		}

		System.out.println("** Fin Controleur**");
	}

	/**
	 * Redirige vers la page d'accueil du site
	 * @author Anaïs
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
	 * Redirige vers la servlet pour ouvrir la page de création d'une fiche
	 * entreprise
	 * 
	 * @author Anaïs
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goCreation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/action/Creation/Creer");
		dispatcher.forward(request, response);
		// System.out.println("--- in the goCreation method ---");
		// System.out.println(request.getPathInfo());
	}

	/**
	 * Redirige vers la servlet ouvrant la page concernant l'entreprise
	 * nouvellement créée
	 * @author Anaïs
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goCreate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/action/CompanyCreate");
		System.out.println(dispatcher);
		dispatcher.forward(request, response);
		// System.out.println("--- in the goCreate method ---");
		// System.out.println(request.getPathInfo());

	}

	/**
	 * Redirige vers la servlet de la page de sélection d'une fiche entreprise
	 * pour modification ou suppression
	 * @author Anaïs
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
	 * @author Anaïs
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
	 * Permet d'accéder à la servlet ouvrant la page pour la recherche ou
	 * l'affichage de fiche entreprise
	 * @author Anaïs
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

	private void goList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		disp = request.getRequestDispatcher("/action/Affichage");
		disp.forward(request, response);
	}

	/**
	 * Permet d'accéder à la servlet ouvrant la page d'information fiche
	 * modifiée
	 * @author Anaïs
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
	 * Permet d'accéder à la servlet ouvrant la page de suppression d'une fiche
	 * entreprise
	 * @author Anaïs
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

	/**
	 * Permet d'accéder à la servlet ouvrant la page de la recherche
	 * @author Anaïs
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goCompanySearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		disp = request.getRequestDispatcher("/action/Rechercher");
		disp.forward(request, response);

	}

}
