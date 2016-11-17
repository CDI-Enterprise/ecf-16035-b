package fr.cdiEnterprise.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiEnterprise.dao.RequetesRecherche;
import fr.cdiEnterprise.service.Regions;

@WebServlet(name= "RechControl", urlPatterns="/Recherche/*")
public class RechercheServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = request.getPathInfo();
		System.out.println("methode doPost, path = "+path);
		
		RequetesRecherche req= new RequetesRecherche();
		Regions listeRegions = req.listerRegions();
		request.setAttribute("listeRegion", listeRegions);
		RequestDispatcher disp= request.getRequestDispatcher("/jsp/page_rech.jsp");
		disp.forward(request, response);
		
	}

}
