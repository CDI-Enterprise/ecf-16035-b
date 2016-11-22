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
@WebServlet(name= "CompanyControl", urlPatterns={"/Company/*"})

public class CompanyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private RequestDispatcher 	disp;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperation de l'url (à partir du chemin relatif à la servlet application cad après /bb)
		String path = request.getPathInfo();
		System.out.println("================  dans Controleur path=" + path );
		System.out.println("================  dans Controleur path=" + request.getContextPath() );
				
		if (path.equals("/Creation")) {
			// affichage formulaire gestion des bonbons
			goCreation(request, response);
		}
		else if (path.equals("/CompanyCreate")) {
			// affichage formulaire gestion des bonbons
			goCreate(request, response);
		}
		else if (path.equals("/ModifSuppr")) {
			// affichage page pour modifier supprimer une fiche entreprise
			goUpDeleteCompany(request, response);
		}
		else if (path.equals("/AffRecher")) {
			// affichage page pour afficher et rechercher des fiches entreprises
			goSearchList(request, response);
		}
		else if (path.equals("/Modifier")){
			goUpdateCompany(request, response);
		}
		else{
			System.out.println("BETISE");
			request.setAttribute("msgErreur", "Vous avez tripatouill&eacute; l'url!!! ");
			doAccueil(request, response);
		}
		
		System.out.println("** Fin Controleur**");
	}

	/**
	 * Affichage de la page d'accueil du site
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doAccueil(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		disp = request.getRequestDispatcher("/jsp/accueil.jsp");
		disp.forward(request,response);	
		
		//on demande au navigateur de réémettre cette requete
//		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));
	}

//	
//	/**
//	 * Affichage de la page pour créer une fiche entreprise
//	 * @param request
//	 * @param response
//	 * @throws ServletException
//	 * @throws IOException
//	 */
	private void goCreation(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("--- in the goCreation method ---");
		System.out.println(request.getPathInfo());
//		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/action/Creation");
		System.out.println(dispatcher);
		dispatcher.forward(request, response);

	}
	
	private void goCreate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("--- in the goCreate method ---");
		System.out.println(request.getPathInfo());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/action/CompanyCreate");
		System.out.println(dispatcher);
		dispatcher.forward(request, response);
	}
	
	/**
	 * Modification - Suppression d'une fiche entreprise
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goUpDeleteCompany(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		disp = request.getRequestDispatcher("/action/ModifSuppr"); 
		disp.forward(request,response);
	}
	
	/**
	 * Redirige vers la servlet permettant l'affichage de la page pour modifier une fiche entreprise
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goUpdateCompany(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		disp = request.getRequestDispatcher("/action/Modifier"); 
		disp.forward(request,response);
	}
	
	/**
	 * Affichage de la liste des bonbons
	 * Redirige vers ControleurCatalogue.class
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goSearchList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		disp = request.getRequestDispatcher("AfficheRecherche"); 
		disp.forward(request,response);		
	}
//
//





	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}


