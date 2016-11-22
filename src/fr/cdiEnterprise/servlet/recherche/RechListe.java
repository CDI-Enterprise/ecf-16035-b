package fr.cdiEnterprise.servlet.recherche;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiEnterprise.dao.RequetesRecherche;

/**
 * Servlet implementation class RechListe
 */
@WebServlet("/RechListe")
public class RechListe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechListe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet RechListe");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost RechListe");
		System.out.println(request.getParameter("nom_comp"));
		System.out.println(request.getParameter("secteur_comp"));
		System.out.println(request.getParameter("ville_comp"));
		System.out.println(request.getParameter("region_comp"));
	
	}

}