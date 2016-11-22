package fr.cdiEnterprise.servlet.recherche;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiEnterprise.dao.RequetesRecherche;
import fr.cdiEnterprise.model.Recherche;

/**
 * Servlet implementation class RechFavAfficher
 */

@WebServlet(name="RechAfficherFavoris", urlPatterns= {"/RechFavAfficher/*", "/RechFavAfficher"}) 
public class RechFavAfficher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet d'afficher recherche favorite");
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost d'afficher recherche favorite");
		RequetesRecherche req= new RequetesRecherche();
		
		String idUser="francois";
		String rechOpt = request.getParameter("radioRech");
		
			
		if (rechOpt != null){
			Recherche recherche = req.recupRechFav(rechOpt, idUser);
			request.setAttribute("RechFavSelect", recherche);
		} else {
			System.out.println("pas de radio bouton selectionné");
		}
		
		System.out.println(request.getPathInfo());
		RequestDispatcher disp= request.getRequestDispatcher("/jsp/page_rech.jsp");
		disp.forward(request, response);		
	}

}
