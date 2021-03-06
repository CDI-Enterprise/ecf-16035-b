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
import fr.cdiEnterprise.model.Region;
import fr.cdiEnterprise.service.Companies;

/**
 * Servlet implementation class RechListe
 * Servlet servant � g�rer un recherche multicrit�res
 */
@WebServlet(name="RechListe", urlPatterns = {"/RechListe", "/RechListe/*"})
public class RechListe extends HttpServlet {
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
		RequetesRecherche req = new RequetesRecherche();
			
		String nom = request.getParameter("nom_comp").trim().toUpperCase();
		String ville = request.getParameter("ville_comp").trim().toUpperCase();
		String secteur = request.getParameter("secteur_comp").trim().toLowerCase();
		String regionNom = request.getParameter("region_comp");
		Region regionRech = req.regionByName(regionNom);
		String idUser = (String) request.getAttribute("idUser");
		String nomFav = request.getParameter("nom_rech");
		
		Recherche recherche = new Recherche(0, idUser, nomFav, nom, secteur, regionRech, ville);
		request.setAttribute("RechFavSelect", recherche);
		
		Companies listeEntreprises = req.listCompanies(recherche);
		request.setAttribute("listeEntreprises", listeEntreprises);
	
		RequestDispatcher disp= request.getRequestDispatcher("/Recherche/RechAffichage");
		disp.forward(request, response);
	
	}

}
