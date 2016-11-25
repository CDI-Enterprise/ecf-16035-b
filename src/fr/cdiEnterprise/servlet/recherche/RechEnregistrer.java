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

/**
 * Servlet implementation class RechEnregistrer
 * Servlet servant à gérer l'enregistrement d'une recherche favorite
 */
@WebServlet(name="RechEnregistrer", urlPatterns = {"/RechEnregistrer", "/RechEnregistrer/*"})
public class RechEnregistrer extends HttpServlet {
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
		
		int index = req.indexRechFav();
		String nomRegion = request.getParameter("region_comp");
		System.out.println(nomRegion);
		Region region = req.regionByName(nomRegion);
		String idUser = (String) request.getAttribute("idUser");
		String nomComp = request.getParameter("nom_comp");
		String sector = request.getParameter("secteur_comp");
		String city = request.getParameter("ville_comp");
		String nomRech = request.getParameter("nom_rech").trim();
		
		System.out.println("enregistrement des infos: "+idUser+"/"+nomComp+"/"+sector+"/"+city+"/"+region+"/"+nomRech);
		
		if (nomRech != ""){
			System.out.println("création de recherche");
			Recherche recherche = new Recherche(index, idUser, nomRech, nomComp, sector, region, city);
			req.enregistrerRech(recherche);
		}
		
		RequestDispatcher disp= request.getRequestDispatcher("/Recherche/RechAffichage");
		disp.forward(request, response);
	}

}
