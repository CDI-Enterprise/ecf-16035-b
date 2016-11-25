package fr.cdiEnterprise.servlet.messagerie;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.cdiEnterprise.dao.MessageDao;
import fr.cdiEnterprise.exceptions.MailException;
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
	"/brouillon",
	"/supprimer",
	"/messagerie/*",
	"/envoyer",
	"/archive"
	
})

public class messagerie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default constructor
     * @see HttpServlet#HttpServlet()
     */
    public messagerie() {
        super();
    }

	/**
	 * Récupérer les requete get et renvoi l'utilisateur a la page demander avec les elements neccessaires a la construction de la page.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * @author Aurélien
	 * @version 1
 	 * @since 21/11/2016
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//Declaration des constante a testé TODO a passé en enum
		//Dossier
		final String MESSAGERIE = "/ecf-16035-b/messagerie";
		final String BROUILLON = "/ecf-16035-b/brouillon";
		final String ENVOYER = "/ecf-16035-b/envoyer";
		final String SUPPRIMER = "/ecf-16035-b/supprimer";
		final String ARCHIVE = "/ecf-16035-b/archive";
		
		//Action
		final String AFFICHAGE = "/ecf-16035-b/messagerie/affichage";
		final String NOUVEAU = "/ecf-16035-b/messagerie/nouveau";
		final String SUPPRESSION = "/ecf-16035-b/messagerie/supprimer";
		final String MODIFICATION_BROUILLON = "/ecf-16035-b/messagerie/brouillon";
		
//Recuperation de l'uri demander
		
		String path = request.getRequestURI();	
		
//Aiguillage

		if(path.equalsIgnoreCase(MESSAGERIE)){
			
			constructMail(request,response);
			
		}else if(path.equalsIgnoreCase(BROUILLON)){
			
			constructBrouillon(request,response);
			
		}else if(path.equalsIgnoreCase(AFFICHAGE)){

			afficheMail(request,response);
			
		}else if(path.equalsIgnoreCase(NOUVEAU)){
			
			nouveauMail(request,response);
			
		}else if(path.equalsIgnoreCase(SUPPRESSION)){
			
			supprimerMail(request,response);
			
		}else if(path.equalsIgnoreCase(SUPPRIMER)){
			
			constructSupprimer(request, response);
		
		}else if(path.equalsIgnoreCase(MODIFICATION_BROUILLON)){
		
			affichageBrouillon(request, response);
		
		}else if(path.equalsIgnoreCase(ENVOYER)){
		
		constructEnvoyer(request, response);
		
		}else if(path.equalsIgnoreCase(ARCHIVE)){
		
		constructArchive(request, response);
		
		}else{
			
			final String MESSAGE_ERREUR = "La ressource demander n'existe pas ";
			final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
			request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
			
		}

	}

	

	/**
	 * Récupere et renvoie l'utilisateur a la page demander avec une inscription dans la base de données des données entrer par l'utilisateur.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * @author Aurélien
	 * @version 1
	 * @throws IOException 
	 * @throws ServletException 
	 * @since 21/11/2016
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		final String NOUVEAU = "/ecf-16035-b/messagerie/nouveau";
		
		String path = request.getRequestURI();
		
		if(path.equalsIgnoreCase(NOUVEAU)){
			sendMail(request,response);
		}else{
			final String MESSAGE_ERREUR = "La ressource demander n'existe pas ";
			final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
			request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
		}
	}
	
	/**
	 * Recupere l'id du Mail brouillon et remplie les informations avec les informations deja existante pour ce message
	 * 
	 * @param request
	 * @param response
	 * 
	 * @author Aurélien
	 * @version 1
	 * @throws IOException 
	 * @throws ServletException 
 	 * @since 24/11/2016
	 */
	private void affichageBrouillon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String PATH_MESSAGERIE =  "/jsp/messagerie/messagerie.jsp";
		final String PATH_UN_BROUILLON = "../../WEB-INF/messagerie/un_brouillon.jsp";
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		MessageDao dao = new MessageDao();
		Item item = dao.getItemByRef(id, true, false);
		
		request.setAttribute("message", item);
		request.setAttribute("url", PATH_UN_BROUILLON);
		
		RequestDispatcher disp = request.getRequestDispatcher(PATH_MESSAGERIE);
		try {
			disp.forward(request, response);
		} catch (ServletException | IOException e) {
			
			final String MESSAGE_ERREUR = "Un singe a du codé cette partie donc il y a un bug";
			final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
			request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
			disp = request.getRequestDispatcher(ERREUR);
			disp.forward(request, response);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * recupere l'id des mail selectionnée par l'utilisateur et les supprime de la base de donnée
	 * @param response 
	 * @param request 
	 * 
	 * @author Aurélien
	 * @version 1
	 * @throws IOException 
	 * @throws ServletException 
 	 * @since 24/11/2016
	 */
	private void supprimerMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String PATH_MESSAGERIE = "/jsp/messagerie/messagerie.jsp";
		final String PATH_SUPPRESSION_REUSSI = "../../WEB-INF/messagerie/suppression_reussie.jsp";
		final String PATH_SUPPRESSION_ERREUR = "../../WEB-INF/messagerie/suppression_erreur.jsp";
		
		@SuppressWarnings("unused") //utiliser comme un incrementeur
		int incrementeur = 0;
		Enumeration<String> parameterName = request.getParameterNames();
		ArrayList<Integer> id = new ArrayList<Integer>();
		Items items = new Items();
		Item item = new Item();
		
			while(parameterName.hasMoreElements()){
				
				id.add(Integer.parseInt(request.getParameter(parameterName.nextElement())));
				incrementeur++;

			}
			
			MessageDao dao = new MessageDao();
			
			for(int x = 0;x < id.size();x++){
				
				item = dao.getItemByRef(id.get(x), false, false);
				items.add(item);		
			}
		
			dao.updateDelete(items);
			
			RequestDispatcher disp = request.getRequestDispatcher(PATH_MESSAGERIE);
			
			try {
				request.setAttribute("url", PATH_SUPPRESSION_REUSSI);
				disp.forward(request,response);
				
			} catch (ServletException | IOException e) {
				
				final String MESSAGE_ERREUR = "Un singe a du codé cette partie donc il y a un bug";
				final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
				request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
				disp = request.getRequestDispatcher(ERREUR);
				disp.forward(request, response);
				e.printStackTrace();
			} 
			
		
	}
	
	/**
	 * Envoie le mail en l'inserant dans la base de donnée grace a {@link MessageDao}
	 * 
	 * @param request
	 * @param response
	 * 
	 * @author Aurélien
	 * @version 1
	 * @throws IOException 
	 * @throws ServletException 
 	 * @since 24/11/2016
	 */
	private void sendMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String PATH_MESSAGERIE = "/jsp/messagerie/messagerie.jsp";
		final String PATH_ENVOIE_REUSSI = "../../WEB-INF/messagerie/envoie_reussie.jsp";
		final String PATH_ENVOIE_ERREUR = "../../WEB-INF/messagerie/envoie_erreur.jsp";	
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String sender = "oracle"; //TODO a supprimer SIMULATION DE SESSION
		String receiver = request.getParameter("receiver");
		String object = request.getParameter("objet");
		String body = request.getParameter("body");
		LocalDateTime date = LocalDateTime.now();
		
		MessageDao dao = new MessageDao();
			
			try {
				
				if(dao.idExist(id)){
					

					boolean draft = true; //false car non un draft
					boolean deleted = false; //false car non deleted
					
					Item item = new Item(id,sender,receiver,object,body,date,draft,deleted);
					
					if(MailException.senderNotEqualsReceiver(item) && MailException.notNull(item) && MailException.notMaxChar(item)){
						dao.updateDraft(item);
					}
					
				}else{
					
					boolean draft = false; //false car non un draft
					boolean deleted = false; //false car non deleted
					
					Item item = new Item(id,sender,receiver,object,body,date,draft,deleted);
					
					if(MailException.senderNotEqualsReceiver(item) && MailException.notNull(item) && MailException.notMaxChar(item)){
						dao.insertItem(item);
					}
				}
				
				request.setAttribute("url", PATH_ENVOIE_REUSSI);
				
				
			} catch (SQLException e) {
				
				final String MESSAGE_ERREUR = "Un singe a du codé cette partie donc il y a un bug";
				final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
				request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
				RequestDispatcher disp = request.getRequestDispatcher(ERREUR);
				disp.forward(request, response);
				e.printStackTrace();
				request.setAttribute("url", PATH_ENVOIE_ERREUR);
				
			}
			
			RequestDispatcher disp = request.getRequestDispatcher(PATH_MESSAGERIE);
			
			try {
				disp.forward(request,response);
			} catch (ServletException | IOException e) {
				
				final String MESSAGE_ERREUR = "Un singe a du codé cette partie donc il y a un bug";
				final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
				request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
				disp = request.getRequestDispatcher(ERREUR);
				disp.forward(request, response);
				e.printStackTrace();
				
			}
	}

	/**
	 * Envoie sur la fenetre de nouveau message en generant un ID/id/identity au mail.
	 * 
	 * @param request
	 * @param response
	 * 
	 * @author Aurélien
	 * @version 1
	 * @throws IOException 
	 * @throws ServletException 
 	 * @since 24/11/2016
	 */
	private void nouveauMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		final String PATH_MESSAGERIE = "/jsp/messagerie/messagerie.jsp";
		final String PATH_NOUVEAU = "../../WEB-INF/messagerie/nouveau.jsp";
		
		//TODO a supprimer SIMULATION SESSION UTILISATEUR
		HttpSession session = request.getSession(true);
		String box = "oracle";
		session.setAttribute("login",box);	
		
		MpClientV2 mpclient = null;
		
		try {
			mpclient = new MpClientV2(session.getAttribute("login").toString());
		} catch (SQLException e1) {
			final String MESSAGE_ERREUR = "Un singe a du codé cette partie donc il y a un bug";
			final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
			request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
			RequestDispatcher disp = request.getRequestDispatcher(ERREUR);
			disp.forward(request, response);
			e1.printStackTrace();
		}
		
		
		request.setAttribute("url", PATH_NOUVEAU);
		request.setAttribute("id", mpclient.getNewID());
	
		RequestDispatcher disp = request.getRequestDispatcher(PATH_MESSAGERIE);
		try {
			disp.forward(request,response);
		} catch (ServletException | IOException e) {
			final String MESSAGE_ERREUR = "Un singe a du codé cette partie donc il y a un bug";
			final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
			request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
			disp = request.getRequestDispatcher(ERREUR);
			disp.forward(request, response);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Recupere l'id du mail qui a etait selectionné par le mail et affiche son contennu grace {@link MessageDao}	
	 * @param request
	 * @param response 
	 * 
	 * @author Aurélien
	 * @version 1
	 * @throws IOException 
	 * @throws ServletException 
 	 * @since 24/11/2016
	 */
	private void afficheMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		final String PATH_MESSAGERIE =  "/jsp/messagerie/messagerie.jsp";
		final String PATH_AFFICHAGE = "../../WEB-INF/messagerie/un_message.jsp";
		
		int id = Integer.parseInt(request.getParameter("id"));
		MessageDao dao = new MessageDao();
		
		//1 false = draft | 2 false = deleted les deux a false car on est dans la boite de recepetion
		Item item = dao.getItemByRef(id,false,false);
		
		request.setAttribute("url", PATH_AFFICHAGE);
		request.setAttribute("message", item);
		
		RequestDispatcher disp = request.getRequestDispatcher(PATH_MESSAGERIE);
		try {
			disp.forward(request,response);
		} catch (ServletException | IOException e) {
			final String MESSAGE_ERREUR = "Un singe a du codé cette partie donc il y a un bug";
			final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
			request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
			disp = request.getRequestDispatcher(ERREUR);
			disp.forward(request, response);
			e.printStackTrace();
		}
		
	}

	/**
	 * Demande la liste d'{@link Items} a la base de données grace a la classe 
	 * {@link MessageDao} et la renvoie a la page messagerie.jsp
	 * 
	 * @param response 
	 * @param request 
	 * 
	 * @author Aurélien
	 * @version 1
	 * @throws IOException 
	 * @throws ServletException 
 	 * @since 24/11/2016
	 */
	private void constructMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String PATH_MESSAGERIE = "/jsp/messagerie/messagerie.jsp";
		final String PATH_BOITE_RECEPTION = "../../WEB-INF/messagerie/boite_reception.jsp";

		//TODO supression de la SIMULATION SESSION
		HttpSession session = request.getSession(true);
		String box = "oracle";
		session.setAttribute("login",box);
							
		Items items = new Items();
		items = MessageDao.getAllItems(session.getAttribute("login").toString(),false,false);
		
		request.setAttribute("message", items);
		request.setAttribute("url", PATH_BOITE_RECEPTION);
	
		RequestDispatcher disp = request.getRequestDispatcher(PATH_MESSAGERIE);
		
		try {
			
			disp.forward(request,response);
			
		} catch (ServletException | IOException e) {
			
			final String MESSAGE_ERREUR = "Un singe a du codé cette partie donc il y a un bug";
			final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
			request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
			disp = request.getRequestDispatcher(ERREUR);
			disp.forward(request, response);
			e.printStackTrace();
			
		}
		
	}
	
	/**
	 * Demande la liste d'{@link Items} brouillon a la base de données grace a la classe 
	 * {@link MessageDao} et la renvoie a la page messagerie.jsp
	 * 
	 * @param response 
	 * @param request 
	 * 
	 * @author Aurélien
	 * @version 1
	 * @throws IOException 
	 * @throws ServletException 
 	 * @since 24/11/2016 
	 */
	private void constructBrouillon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String PATH_MESSAGERIE = "/jsp/messagerie/messagerie.jsp";
		final String PATH_BOITE_BROUILLON = "../../WEB-INF/messagerie/brouillon.jsp";

		//TODO supression de la SIMULATION SESSION
		HttpSession session = request.getSession(true);
		String box = "oracle";
		session.setAttribute("login",box);
							
		Items items = new Items();
		items = MessageDao.getAllItems(session.getAttribute("login").toString(),true,false);
		
		request.setAttribute("message", items);
		request.setAttribute("url", PATH_BOITE_BROUILLON);
	
		RequestDispatcher disp = request.getRequestDispatcher(PATH_MESSAGERIE);
		try {
			disp.forward(request,response);
		} catch (ServletException | IOException e) {
			final String MESSAGE_ERREUR = "Un singe a du codé cette partie donc il y a un bug";
			final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
			request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
			disp = request.getRequestDispatcher(ERREUR);
			disp.forward(request, response);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Demande la liste d'{@link Items} supprimer a la base de données grace a la classe 
	 * {@link MessageDao} et la renvoie a la page messagerie.jsp
	 * 
	 * @param response 
	 * @param request 
	 * 
	 * @author Aurélien
	 * @version 1
	 * @throws IOException 
	 * @throws ServletException 
 	 * @since 24/11/2016
	 */
	private void constructSupprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		final String PATH_MESSAGERIE = "/jsp/messagerie/messagerie.jsp";
		final String PATH_BOITE_BROUILLON = "../../WEB-INF/messagerie/supprimer.jsp";

		//TODO supression de la SIMULATION SESSION
		HttpSession session = request.getSession(true);
		String box = "oracle";
		session.setAttribute("login",box);
							
		Items items = new Items();
		items = MessageDao.getAllItems(session.getAttribute("login").toString(),false,true);
		
		request.setAttribute("message", items);
		request.setAttribute("url", PATH_BOITE_BROUILLON);
	
		RequestDispatcher disp = request.getRequestDispatcher(PATH_MESSAGERIE);
		try {
			disp.forward(request,response);
		} catch (ServletException | IOException e) {
			final String MESSAGE_ERREUR = "Un singe a du codé cette partie donc il y a un bug";
			final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
			request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
			disp = request.getRequestDispatcher(ERREUR);
			disp.forward(request, response);
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  Demande la liste d'{@link Items} envoyer a la base de données grace a la classe 
	 *  {@link MessageDao} et la renvoie a la page messagerie.jsp
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void constructEnvoyer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String PATH_MESSAGERIE = "/jsp/messagerie/messagerie.jsp";
		final String PATH_BOITE_ENVOIE = "../../WEB-INF/messagerie/element_envoyer.jsp";
		
		HttpSession session = request.getSession(true);
		String box = "oracle";
		session.setAttribute("login",box);
							
		Items items = new Items();
		items = MessageDao.getSendedItems(session.getAttribute("login").toString(),false,false);
		
		request.setAttribute("message", items);
		request.setAttribute("url", PATH_BOITE_ENVOIE);
		
		RequestDispatcher disp = request.getRequestDispatcher(PATH_MESSAGERIE);
		
		try {
			disp.forward(request,response);
		} catch (ServletException | IOException e) {
			final String MESSAGE_ERREUR = "Un singe a du codé cette partie donc il y a un bug";
			final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
			request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
			disp = request.getRequestDispatcher(ERREUR);
			disp.forward(request, response);
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  Demande la liste d'{@link Items} Archive a la base de données grace a la classe 
	 *  {@link MessageDao} et la renvoie a la page messagerie.jsp
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void constructArchive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String PATH_MESSAGERIE = "/jsp/messagerie/messagerie.jsp";
		final String PATH_BOITE_ARCHIVE = "../../WEB-INF/messagerie/archive.jsp";
		request.setAttribute("url", PATH_BOITE_ARCHIVE);
		try {
			
			RequestDispatcher disp = request.getRequestDispatcher(PATH_MESSAGERIE);
			disp.forward(request, response);
			
		} catch (IOException e) {
			
			final String MESSAGE_ERREUR = "Un singe a du codé cette partie donc il y a un bug";
			final String ERREUR = "../../WEB-INF/messagerie/erreur.jsp";
			request.setAttribute("Message_Erreur", MESSAGE_ERREUR);
			RequestDispatcher disp = request.getRequestDispatcher(ERREUR);
			disp.forward(request, response);
			e.printStackTrace();
		}
		
	}

}
