package fr.cdiEnterprise.servlet.recherche;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiEnterprise.dao.RequetesRecherche;

/**
 * Servlet implementation class RechSupprAfficher
 */
@WebServlet(name="RechSupprFavori", urlPatterns= {"/RechSupprAfficher/*", "/RechSupprAfficher"})
public class RechSupprAfficher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechSupprAfficher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("doGet de suppr rech fav");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost de suppr rech fav");
		RequetesRecherche req= new RequetesRecherche();
		
		String idUser= (String) request.getAttribute("idUser");
		String rechOpt = request.getParameter("radioRech");
		
		System.out.println(idUser+rechOpt);
		
		if (rechOpt != null){
			req.SupprRechFav(rechOpt, idUser);
		
		} else {
			System.out.println("pas de radio bouton selectionné");
		}
		
		System.out.println("Path dans le do post de suppr rech fav: "+request.getPathInfo());
		
		RequestDispatcher disp= request.getRequestDispatcher("/jsp/page_rech.jsp");
		disp.forward(request, response);		
	}
}
