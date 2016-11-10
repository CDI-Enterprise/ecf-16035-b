package fr.cdiEnterprise.view;

import javax.swing.JMenuBar;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.cdiEnterprise.control.MainMenuListener;


/**
 * Main menu for the CDI Enterprise program, visible on every frame.
 * @author Claire, Anais, Nicolas, Ismael, Olivier
 * @version 22-10-2016
 */

public class Menu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	// Main menu creation
	private JMenu menuProfile;
	private JMenu menuCompany;
	private JMenu menuSearch;
	private JMenu menuBookmark;
	private JMenu menuMessaging;
	private JMenu menuHelp;
	private JMenu menuQuit;

	// menuProfile : sub item
	private JMenuItem subProfileCRUD;
	private JMenuItem subProfileSR;	

	// menuEntreprise : sub item
	private JMenuItem subCompanyCreate;
	private JMenuItem subCompanyUpdateDelete;
	private JMenuItem subCompanyRead;
	
	// menuSearch : sub item
	private JMenuItem subSearchRechercher;
	private JMenuItem subSearchDeleteRecherche;
	
	private JMenuItem subMessageDisplay;

	//menuBokkMark : sub item
	private JMenuItem subBookMarkRead;

	// menuHelp : sub item
	private JMenuItem subHelpDoc;
	private JMenuItem subHelpShortcut;
	private JMenuItem subHelpUpdate;
	private JMenuItem subHelpAbout;

	/**
	 * Constructor
	 */
	public Menu() {

		// PROFILE
		menuProfile = new JMenu("Profil");
		this.add(menuProfile);
		
		subProfileCRUD = new JMenuItem("Administrer les profils");
		menuProfile.add(subProfileCRUD);
		subProfileCRUD.setMnemonic(KeyEvent.VK_P);
		subProfileCRUD.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
	
		subProfileSR = new JMenuItem("Liste des membres");
		menuProfile.add(subProfileSR);

		// COMPANY
		menuCompany = new JMenu("Entreprise");
		this.add(menuCompany);
		// Shortcut for Company
		//menuCompany.setMnemonic('E');
		// Sub menu for Enterprise
		subCompanyCreate = new JMenuItem("Créer une nouvelle fiche");
		menuCompany.add(subCompanyCreate);
		subCompanyUpdateDelete = new JMenuItem("Modifier / Supprimer une fiche");
		menuCompany.add(subCompanyUpdateDelete);
		subCompanyRead = new JMenuItem("Afficher et rechercher des fiches entreprises");
		menuCompany.add(subCompanyRead);

		// SEARCH
		menuSearch = new JMenu("Recherche");
		this.add(menuSearch);
		subSearchRechercher = new JMenuItem("Rechercher une fiche entreprise");
		menuSearch.add(subSearchRechercher);
		subSearchDeleteRecherche = new JMenuItem("Supprimer une recherche");
		menuSearch.add(subSearchDeleteRecherche);

		

		// BOOKMARK
		menuBookmark = new JMenu("Favoris");
		this.add(menuBookmark);
		subBookMarkRead = new JMenuItem("Mes Favoris");
		menuBookmark.add(subBookMarkRead);

		// MESSAGING
		menuMessaging = new JMenu("Messagerie");
		subMessageDisplay = new JMenuItem("Gerer les Messages");
		menuMessaging.add(subMessageDisplay);
		subMessageDisplay.setMnemonic(KeyEvent.VK_M);
		subMessageDisplay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		this.add(menuMessaging);

		// HELP
		menuHelp = new JMenu("Aide");
		this.add(menuHelp);
	
		// Sub menu for Help
		subHelpDoc = new JMenuItem("Documentation");
		menuHelp.add(subHelpDoc);
		subHelpShortcut = new JMenuItem("Raccourcis clavier");
		menuHelp.add(subHelpShortcut);
		subHelpUpdate = new JMenuItem("Vérifier les mises à  jour");
		menuHelp.add(subHelpUpdate);
		subHelpAbout = new JMenuItem("A propos");
		menuHelp.add(subHelpAbout);

		// QUIT
		menuQuit = new JMenu("Déconnexion");
		this.add(menuQuit);


		//LISTENER
		MainMenuListener listener = new MainMenuListener(this);
		subProfileCRUD.addActionListener(listener);
		subProfileSR.addActionListener(listener);

		subMessageDisplay.addActionListener(listener);

		subCompanyCreate.addActionListener(listener);
		subCompanyUpdateDelete.addActionListener(listener);
		subCompanyRead.addActionListener(listener);
		
		subSearchRechercher.addActionListener(listener);
		subSearchDeleteRecherche.addActionListener(listener);
		menuBookmark.addActionListener(listener);
		subBookMarkRead.addActionListener(listener);
		
		subHelpUpdate.addActionListener(listener);
		subHelpAbout.addActionListener(listener);

	}

	/**
	 * @return the subProfileCRUD
	 */
	public JMenuItem getSubProfileCRUD() {
		return subProfileCRUD;
	}
	
	/**
	 * @return the subProfileSR
	 */
	public JMenuItem getSubProfileSR() {
		return subProfileSR;
	}

	/**
	 * @return the subCompanyCreate
	 */
	public JMenuItem getSubCompanyCreate() {
		return subCompanyCreate;
	}

	public JMenuItem getSubCompanyUpdateDelete(){
		return subCompanyUpdateDelete;
	}
	
	/**
	 * @return the subSearchRechercher
	 */
	public JMenuItem getSubSearchRechercher() {
		return subSearchRechercher;
	}
	
	/**
	 * @return the subSearchDeleteRecherche
	 */
	public JMenuItem getSubSearchDeleteRecherche() {
		return subSearchDeleteRecherche;
	}

	/**
	 * @return the subBookMarkRead
	 */
	public JMenuItem getSubBookMarkRead() {
		return subBookMarkRead;
	}


	public JMenuItem getSubMessageDisplay() {
		return subMessageDisplay;
	}

	/**
	 * @return the subCompanyRead
	 */
	public JMenuItem getSubCompanyRead() {
		return subCompanyRead;
	}

	/**
	 * @param subCompanyRead the subCompanyRead to set
	 */
	public void setSubCompanyRead(JMenuItem subCompanyRead) {
		this.subCompanyRead = subCompanyRead;
	}
	
	/**
	 * @return the subHelpUpdate
	 */
	public JMenuItem getSubHelpUpdate() {
		return subHelpUpdate;
	}

	/**
	 * @return the subHelpAbout
	 */
	public JMenuItem getSubHelpAbout() {
		return subHelpAbout;
	}
}