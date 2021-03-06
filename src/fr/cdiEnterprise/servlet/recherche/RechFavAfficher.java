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
import fr.cdiEnterprise.service.Companies;
import fr.cdiEnterprise.service.RecherchesFav;
import fr.cdiEnterprise.service.Regions;

/**
 * Servlet implementation class RechFavAfficher
 * Servlet servant � g�rer laffichage d'une recherche favorite
 */

@WebServlet(name="RechAfficherFavoris", urlPatterns= {"/RechFavAfficher/*", "/RechFavAfficher"}) 
public class RechFavAfficher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/Recherche/RechAffichageNew");
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequetesRecherche req= new RequetesRecherche();
		
		String idUser= (String) request.getAttribute("idUser");
		String rechOpt = request.getParameter("radioRech");
		
		Regions listeRegions = req.listerRegions();
		request.setAttribute("listeRegion", listeRegions);
		
		RecherchesFav listeRechFav = req.listeRech(idUser);
		request.setAttribute("listeRechFav", listeRechFav);
		
		if (rechOpt != null){
			Recherche recherche = req.recupRechFav(rechOpt, idUser);
			request.setAttribute("RechFavSelect", recherche);
			Companies listeEntreprises = req.listCompanies(recherche);
			request.setAttribute("listeEntreprises", listeEntreprises);
		} else {
			System.out.println("pas de radio bouton selectionn�");
		}
				
		System.out.println("Path dans le do post d'aff rech fav: "+request.getPathInfo());
		
		RequestDispatcher disp= request.getRequestDispatcher("/Recherche/RechAffichage");
		disp.forward(request, response);
	}

}
