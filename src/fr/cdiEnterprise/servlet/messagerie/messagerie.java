package fr.cdiEnterprise.servlet.messagerie;

import java.io.IOException;

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
 * 
 * @author Aur�lien
 * @version 1
 * @since 21/11/2016
 */
@WebServlet(
		
	name="Messagerie",
	urlPatterns = {
			
	"/messagerie",
	"/messagerie/*",
	
})

public class messagerie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default constructor
     * @see HttpServlet#HttpServlet()
     */
    public messagerie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * R�cup�rer les requete get et renvoi l'utilisateur a la page demander avec les elements neccessaires a la construction de la page.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//Syso de controle
		System.out.println("get");
		System.out.println("getRequestURI :" + request.getRequestURI());
		System.out.println("Info path : " + request.getPathInfo());
		System.out.println("servlet path :" + request.getServletPath());
		System.out.println("Trasnlated " + request.getPathTranslated());
		System.out.println("URL " + request.getRequestURL());
		
//Recuperation de l'uri demander
		String path = request.getRequestURI();
		System.out.println("path" + path);
		
//Declaration des constante a test� TODO a pass� en enum
		final String MESSAGERIE = "/ecf-16035-b/messagerie";
		final String AFFICHAGE = "/ecf-16035-b/messagerie/affichage";
		final String NOUVEAU = "/ecf-16035-b/messagerie/nouveau";
		
//Declaration des constante TODO a pass� en enum
		
		if(path.equalsIgnoreCase(MESSAGERIE)){
			
			constructMail(request,response);
			
		}else if(path.equalsIgnoreCase(AFFICHAGE)){

			afficheMail(request,response);
			
		}else if(path.equalsIgnoreCase(NOUVEAU)){

			nouveauMail(request,response);
			
		}

	}

	/**
	 * R�cupere et renvoie l'utilisateur a la page demander avec une inscription dans la base de donn�es des donn�es entrer par l'utilisateur.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("post");
		
	}
	
	

	private void nouveauMail(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * Recupere l'id du mail qui a etait selectionn� par le mail et affiche son contennu grace {@link MessageDao}	
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void afficheMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String PATH_AFFICHAGE = "/jsp/messagerie/messagerie_affichage.jsp";
		
		String ref = request.getParameter("ref");
		MessageDao dao = new MessageDao();
		
		Item item = dao.getItemByRef(ref);
		
		System.out.println(item.getBody());
		System.out.println(item.getSender());
		System.out.println(item.getObject());
		request.setAttribute("message", item);
		RequestDispatcher dispatch = request.getRequestDispatcher(PATH_AFFICHAGE);
		dispatch.forward(request, response);
		
	}

	/**
	 * Demande la liste d'{@link Items} a la base de donn�es grace a la classe {@link MessageDao} et la renvoie a la page messagerie.jsp
	 * 
	 * @param response 
	 * @param request 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void constructMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String PATH_MESSAGERIE = "/jsp/messagerie/messagerie.jsp";
		
		//TODO supression de la SIMULATION SESSION
		
		//Recup�ration de la session
		
		HttpSession session = request.getSession(true);
				
		//Declaration en dur du login
		
		String box = "oracle";
				
		//Ajout du login a la session
		
		session.setAttribute("login",box);
							
		
		/* Instanciation de Items pour recup�ration de tout les Item li� au login puis ajout au request 
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
