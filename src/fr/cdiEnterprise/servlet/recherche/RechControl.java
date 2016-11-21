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
import fr.cdiEnterprise.service.Regions;

@WebServlet(name= "RechControl", urlPatterns= {"/Recherche/*", "/Recherche"})
public class RechControl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		RequestDispatcher disp;
//		String path = request.getPathInfo();
//		System.out.println("methode doGet, path = "+path);
//		
//		if (path.equals("/")){
//			disp = request.getRequestDispatcher("/RechAffichageNew");
//			disp.forward(request, response);	
//		} else {System.out.println("erreur ecriture path");}
		
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher disp;
		String path = request.getPathInfo();
		System.out.println("methode doPost Controleur, path = "+path);
		
		if (path.equals("/")){
			disp = request.getRequestDispatcher("/RechAffichageNew");
			disp.forward(request, response);	
		} 
		
		if (path.equals("/RechControl/RechListe")) {
			disp = request.getRequestDispatcher("/RechListe");
			disp.forward(request, response);
		}
		
		if (path.equals("/RechControl/EnregistrerRech")) {
			disp = request.getRequestDispatcher("/RechEnregistrer");
			disp.forward(request, response);
		}
		
		if (path.equals("/RechControl/VoirRechFav")) {
			disp = request.getRequestDispatcher("/RechFavAfficher");
			disp.forward(request, response);
		}
		
		if (path.equals("/RechControl/SupprRechFav")) {
			disp = request.getRequestDispatcher("/RechSupprAfficher");
			disp.forward(request, response);
		}

	}

}
