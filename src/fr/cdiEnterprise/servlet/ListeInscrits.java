package fr.cdiEnterprise.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiEnterprise.dao.AccesBaseInscrit;
import fr.cdiEnterprise.service.Inscriptions;

/**
 * Servlet implementation class ListeInscrits
 */
@WebServlet(name="ListerInscription", urlPatterns={"/listeInscrits"})
public class ListeInscrits extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeInscrits() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Inscriptions liste = new Inscriptions(); 
		AccesBaseInscrit base = new AccesBaseInscrit();
		liste = base.afficherInscrits();
		
		System.out.println(liste);
		
		request.setAttribute("inscriptions", liste);
		request.setAttribute("reussite", "OK");
		request.getRequestDispatcher("/jsp/ListeInscrit.jsp").forward(request, response);
		System.out.println(" redirection vers la Liste des inscrits");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
