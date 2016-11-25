package fr.cdiEnterprise.servlet.recherche;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
* Servlet implementation class RechControl
* 
* <b>Servlet RechControl</b>
* Cette Servlet sert de controleur pour la page de recherche d'entreprises.
* Elle redirige les flux reçus depuis les formulaires de la page rech_jsp
* 
*/


@WebServlet(name= "RechControl", urlPatterns= {"/Recherche/*", "/Recherche"})
public class RechControl extends HttpServlet{

	
	
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

		if (path.equals("/")){
			disp = request.getRequestDispatcher("/RechAffichageNew");
			disp.forward(request, response);	
		} 
		
		else if (path.equals("/RechAffichage")){
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
			disp = request.getRequestDispatcher("/RechFavSuppr");
			disp.forward(request, response);
		}
		
		else {
			disp = request.getRequestDispatcher("/RechAffichageNew");
			disp.forward(request, response);	
		}
	}

}
