package fr.cdiEnterprise.servlet.recherche;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name= "RechControl", urlPatterns= {"/Recherche/*", "/Recherche"})
public class RechControl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("methode doGet Controleur");
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String idUser="francois";
		request.setAttribute("idUser", idUser);
		RequestDispatcher disp;
		String path = request.getPathInfo();
		System.out.println("methode doPost Controleur, path = "+path);
		System.out.println("methode doPost Controleur, context = "+request.getContextPath());
		System.out.println("methode doPost Controleur, uri = "+request.getRequestURI());
		
		if (path.equals("/")){
			disp = request.getRequestDispatcher("/RechAffichageNew");
			disp.forward(request, response);	
		} 
		
		else if (path.equals("/RechListe")) {
			disp = request.getRequestDispatcher("/RechListe");
			disp.forward(request, response);
		}
		
		else if (path.equals("/EnregistrerRech")) {
			disp = request.getRequestDispatcher("/RechEnregistrer");
			disp.forward(request, response);
		}
		
		else if (path.equals("/RechFavAfficher")) {
			disp = request.getRequestDispatcher("/RechFavAfficher");
			disp.forward(request, response);
		}
		
		else if (path.equals("/RechFavSuppr")) {
			disp = request.getRequestDispatcher("/RechSupprAfficher");
			disp.forward(request, response);
		}
		
		else {
			disp = request.getRequestDispatcher("/RechAffichageNew");
			disp.forward(request, response);	
		}
	}

}
