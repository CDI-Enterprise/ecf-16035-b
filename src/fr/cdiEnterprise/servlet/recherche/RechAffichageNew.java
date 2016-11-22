package fr.cdiEnterprise.servlet.recherche;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiEnterprise.dao.RequetesRecherche;
import fr.cdiEnterprise.service.Companies;
import fr.cdiEnterprise.service.RecherchesFav;
import fr.cdiEnterprise.service.Regions;

/**
 * Servlet implementation class RechAffichageNew
 */

@WebServlet(name="RechAffichage", urlPatterns = {"/RechAffichageNew", "/RechAffichageNew/*"})
public class RechAffichageNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGetaffichagenew");
		
		RequetesRecherche req= new RequetesRecherche();
		
		String idUser= (String) request.getAttribute("idUser");
		
		Regions listeRegions = req.listerRegions();
		request.setAttribute("listeRegion", listeRegions);
				
		Companies listeEntreprises = req.listAllCompanies();
		request.setAttribute("listeEntreprises", listeEntreprises);
		
		RecherchesFav listeRechFav = req.listeRech(idUser);
		request.setAttribute("listeRechFav", listeRechFav);
		
		RequestDispatcher disp= request.getRequestDispatcher("/jsp/page_rech.jsp");
		disp.include(request, response);		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost affichagenew");
		
		RequetesRecherche req= new RequetesRecherche();
		
		String idUser= (String) request.getAttribute("idUser");
		
		Regions listeRegions = req.listerRegions();
		request.setAttribute("listeRegion", listeRegions);
				
		Companies listeEntreprises = req.listAllCompanies();
		request.setAttribute("listeEntreprises", listeEntreprises);
		
		RecherchesFav listeRechFav = req.listeRech(idUser);
		request.setAttribute("listeRechFav", listeRechFav);
		
		RequestDispatcher disp= request.getRequestDispatcher("/jsp/page_rech.jsp");
		disp.include(request, response);		
			
		
		
	}

}
