package fr.cdiEnterprise.servlet.messagerie;

import java.io.IOException;
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
import fr.cdiEnterprise.util.MpClientV2;

/**
 * Servlet implementation class messagerie
 * 
 * @author Aurélien
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
	 * Récupérer les requete get et renvoi l'utilisateur a la page demander avec les elements neccessaires a la construction de la page.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//Syso de controle
		System.out.println("get");
		System.out.println("getRequestURI :" + request.getRequestURI());
		
//Recuperation de l'uri demander
		String path = request.getRequestURI();
		System.out.println("path " + path);
		
//Declaration des constante a testé TODO a passé en enum
		final String MESSAGERIE = "/ecf-16035-b/messagerie";
		final String AFFICHAGE = "/ecf-16035-b/messagerie/affichage";
		final String NOUVEAU = "/ecf-16035-b/messagerie/nouveau";
		
		
		
//Declaration des constante TODO a passé en enum

		if(path.equalsIgnoreCase(MESSAGERIE)){
			
			constructMail(request,response);
			
		}else if(path.equalsIgnoreCase(AFFICHAGE)){

			afficheMail(request,response);
			
		}else if(path.equalsIgnoreCase(NOUVEAU)){

			try {
				nouveauMail(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	/**
	 * Récupere et renvoie l'utilisateur a la page demander avec une inscription dans la base de données des données entrer par l'utilisateur.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("post");
		
	}
	
	
	/**
	 * Envoie sur la fenetre de nouveau message en generant un ID/ref/identity au mail.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 */
	private void nouveauMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		final String PATH_MESSAGERIE = "/jsp/messagerie/messagerie.jsp";
		final String PATH_NOUVEAU = "../../WEB-INF/messagerie/nouveau.jsp";
		
		//TODO a supprimer SIMULATION SESSION UTILISATEUR
		HttpSession session = request.getSession(true);
		String box = "oracle";
		session.setAttribute("login",box);	
		
		MpClientV2 mpclient = new MpClientV2(session.getAttribute("login").toString());
		System.out.println(mpclient.getNewID());
		
		request.setAttribute("url", PATH_NOUVEAU);
		request.setAttribute("id", mpclient.getNewID());
		
		RequestDispatcher dispatch = request.getRequestDispatcher(PATH_MESSAGERIE);
		dispatch.forward(request, response);
	}
	
	
	/**
	 * Recupere l'id du mail qui a etait selectionné par le mail et affiche son contennu grace {@link MessageDao}	
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void afficheMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String PATH_MESSAGERIE =  "/jsp/messagerie/messagerie.jsp";
		final String PATH_AFFICHAGE = "../../WEB-INF/messagerie/un_message.jsp";
		
		String ref = request.getParameter("ref");
		MessageDao dao = new MessageDao();
		
		Item item = dao.getItemByRef(ref);
		
		request.setAttribute("url", PATH_AFFICHAGE);
		request.setAttribute("message", item);
		
		RequestDispatcher dispatch = request.getRequestDispatcher(PATH_MESSAGERIE);
		dispatch.forward(request, response);
		
	}

	/**
	 * Demande la liste d'{@link Items} a la base de données grace a la classe {@link MessageDao} et la renvoie a la page messagerie.jsp
	 * 
	 * @param response 
	 * @param request 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void constructMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String PATH_MESSAGERIE = "/jsp/messagerie/messagerie.jsp";
		final String PATH_BOITE_RECEPTION = "../../WEB-INF/messagerie/boite_reception.jsp";

		//TODO supression de la SIMULATION SESSION
		HttpSession session = request.getSession(true);
		String box = "oracle";
		session.setAttribute("login",box);
							
		Items items = new Items();
		items = MessageDao.getAllItems(session.getAttribute("login").toString(),false);
		
		request.setAttribute("message", items);
		request.setAttribute("url", PATH_BOITE_RECEPTION);
	
		RequestDispatcher disp = request.getRequestDispatcher(PATH_MESSAGERIE);
		disp.forward(request,response);
		
	}

}
