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

/**
 * Servlet implementation class RechAffichageNew
 */

@WebServlet("/RechAffichageNew")
public class RechAffichageNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechAffichageNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequetesRecherche req= new RequetesRecherche();
		Regions listeRegions = req.listerRegions();
		request.setAttribute("listeRegion", listeRegions);
				
		Companies listeEntreprises = req.listAllCompanies();
		request.setAttribute("listeEntreprise", listeEntreprises);
		
		RequestDispatcher disp= request.getRequestDispatcher("/jsp/page_rech.jsp");
		disp.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
