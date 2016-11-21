package fr.cdiEnterprise.servlet;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.cdiEnterprise.dao.MessageDao;
import fr.cdiEnterprise.model.Item;
import fr.cdiEnterprise.service.Items;

/**
 * Servlet implementation class messagerie
 */
@WebServlet(
		
	name="Messagerie",
	urlPatterns = {
			
	"/messagerie",
	"/messagerie/affiche_id=*"
		
})

public class messagerie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public messagerie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Récupérer les requete get et renvoi l'utilisateur a la page demander avec les elements neccessaires a la construction de la page.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//Syso de controle
		System.out.println("get");
		System.out.println("getRequestURI" + request.getRequestURI());
		System.out.println("Info path : " + request.getPathInfo());
		System.out.println("" + request.getServletPath());
		
//Recuperation de l'uri demander
		String path = request.getRequestURI();
		System.out.println("path" + path);
		
//Declaration des constante a testé TODO a passé en enum
		final String MESSAGERIE = "/ecf-16035-b/messagerie";
		
//Declaration des constante TODO a passé en enum
		final String PATH_MESSAGERIE = "/jsp/messagerie.jsp";
		
//Recupération de la session TODO a modifier pour qu'il ne la creer par mais envoie a une page de connexion si elle hesiste pas
		HttpSession session = request.getSession(true);
		
//Declaration en dur du login TODO a recupéré grasse a la session
		String box = "oracle";
		
//Ajout du login a la session TODO a supprimer
		session.setAttribute("login",box);
		
//Test de l'url pour renvoyer au action desiré
		if(path.equalsIgnoreCase(MESSAGERIE)){
			
			/* Instanciation de Items pour recupération de tout les Item lié au login puis ajout au request 
			 * pour affichage sur la page de messagerie
			 */
			Items items = new Items();
			items = MessageDao.getAllItems(session.getAttribute("login").toString(),false);
			session.setAttribute("message", items);
			request.setAttribute("message", items);

			RequestDispatcher dispatch = request.getRequestDispatcher(PATH_MESSAGERIE);
			dispatch.forward(request, response);
		}
		
	}

	/**
	 * Récupere et renvoie l'utilisateur a la page demander avec une inscription dans la base de données des données entrer par l'utilisateur.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("post");
		
	}

}
