package fr.cdiEnterprise.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.cdiEnterprise.view.company.CompaniesSRPanel;
import fr.cdiEnterprise.view.company.CompanyCreationPanel;
import fr.cdiEnterprise.view.company.CompanyDeletUpdatPanel;
import fr.cdiEnterprise.view.message.MessagingMainPanel;
import fr.cdiEnterprise.view.profile.PanelUserCRUD;
import fr.cdiEnterprise.view.profile.PanelUserSR;

/**
 * MainFrame for the CDI Enterprise program with a JMenuBar.
 * @author Claire, Anais, Nicolas, Ismaël
 * @version 22-10-2016
 */

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static JPanel panMain;
	private static HomePanel panHome;

	// Panels for profile management
	private static PanelUserCRUD panelUserCRUD;
	private static PanelUserSR panelUserSR;
	private static JScrollPane scrollPanelUserCRUD;
	private static JScrollPane scrollPanelUserSR;

	private static CompanyCreationPanel panelCreatCompany;
	private static JScrollPane scrollCreateCompany;
	private static JScrollPane scrollUpdateDeleteCompany;
	private static CompanyDeletUpdatPanel panelUpdateDeleteCompany;
	private static CompaniesSRPanel	panelSRCompanies;
	private static JScrollPane scrollSRCompanies;
	private static RechercheAvanceePanel panelRecherche;

	private static JPanel panelMessaging;

	private static BookMarkPanel panelBookMark;
	private static JScrollPane scrollBookMark;

	/**
	 * MainFrame constructor.
	 * Constructs a Main Frame with a default panel of 1280*800 resizable
	 * With a personalized icon for system menu and main title
	 * With a main menu @see fr.cdiEnterprise.view.Menu
	 * It opens where the OS windows usually do
	 * @throws SQLException 
	 */
	public MainFrame() throws SQLException  {

		// Algorithm from stackoverflow.com, set the font by default
		for (Entry<Object, Object> entry : javax.swing.UIManager.getDefaults().entrySet()) {
			Object key = entry.getKey();
			Object value = javax.swing.UIManager.get(key);
			if (value != null && value instanceof javax.swing.plaf.FontUIResource) {
				//				javax.swing.plaf.FontUIResource fr=(javax.swing.plaf.FontUIResource)value;
				// (String for font name, integer for style, integer for size)
				javax.swing.plaf.FontUIResource f = new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, 14);
				javax.swing.UIManager.put(key, f);
			}
		}

		// Get the JFrame's default panel
		panMain = (JPanel) this.getContentPane();

		// Main frame properties
		// Icon for MainFrame
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("iconMainFrame.png")));
		// Title for MainFrame
		this.setTitle("CDI Enterprise - Recherche de stages et suivi des stagiaires");
		// Is it resizable?
		this.setResizable(true);
		// Set the opening location
		this.setLocationByPlatform(true);
		// MMC Close properties
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Main content properties
		panMain.setPreferredSize(new Dimension (1000,600));
		panMain.setLayout(new BorderLayout());

		// Main menu @see fr.cdiEnterprise.view.Menu
		this.setJMenuBar(new Menu());

		// TODO (Groupe) Home panel for welcoming screen
		panHome = new HomePanel();
		panHome.setBackground(Color.WHITE);
//		panHome.setLayout(new FlowLayout());
		panMain.add(panHome);


		// Panel for user CRUD
		panelUserCRUD = new PanelUserCRUD();
		panelUserCRUD.setPreferredSize(new Dimension (1260,800));
		scrollPanelUserCRUD = new JScrollPane(panelUserCRUD);

		// Panel for users search and display (SR)
		panelUserSR = new PanelUserSR();
		panelUserSR.setPreferredSize(new Dimension(1260, 800));
		scrollPanelUserSR = new JScrollPane(panelUserSR);

		// Panel Company
		panelCreatCompany = new CompanyCreationPanel();
		scrollCreateCompany = new JScrollPane(panelCreatCompany);
		panelUpdateDeleteCompany = new CompanyDeletUpdatPanel();
		scrollUpdateDeleteCompany = new JScrollPane(panelUpdateDeleteCompany);
		panelSRCompanies = new CompaniesSRPanel();
		scrollSRCompanies = new JScrollPane(panelSRCompanies);

		// Panel Search
		panelRecherche = new RechercheAvanceePanel();


		panelMessaging = new MessagingMainPanel();

		//Panel for BookMark
		panelBookMark = new BookMarkPanel();
		scrollBookMark = new JScrollPane(panelBookMark);

	}

	/**
	 * @return the panMain
	 */
	public static JPanel getPanMain() {
		return panMain;
	}

	/**
	 * @return the panelSRCompanies
	 */
	public static CompaniesSRPanel getPanelSRCompanies() {
		return panelSRCompanies;
	}

	/**
	 * @param panelSRCompanies the panelSRCompanies to set
	 */
	public static void setPanelSRCompanies(CompaniesSRPanel panelSRCompanies) {
		MainFrame.panelSRCompanies = panelSRCompanies;
	}

	/**
	 * @return the scrollSRCompanies
	 */
	public static JScrollPane getScrollSRCompanies() {
		return scrollSRCompanies;
	}

	/**
	 * @param scrollSRCompanies the scrollSRCompanies to set
	 */
	public static void setScrollSRCompanies(JScrollPane scrollSRCompanies) {
		MainFrame.scrollSRCompanies = scrollSRCompanies;
	}

	/**
	 * Displays the welcome panel.
	 * @return the panHome
	 */
	public static JPanel getPanHome() {
		return panHome;
	}

	/**
	 * @return the scrollPanelUserCRUD
	 */
	public static JScrollPane getScrollPanelUserCRUD() {
		return scrollPanelUserCRUD;
	}

	/**
	 * @return the scrollPanelUserSR
	 */
	public static JScrollPane getScrollPanelUserSR() {
		return scrollPanelUserSR;
	}	

	public static CompanyCreationPanel getPanelCreatCompany() 
	{
		return panelCreatCompany;
	}

	/**
	 * @return the scrollCreateCompany
	 */
	public static JScrollPane getScrollCreateCompany() {
		return scrollCreateCompany;

	}

	/**
	 * @return the panelRecherche
	 */
	public static RechercheAvanceePanel getPanelRecherche() {
		return panelRecherche;
	}

	/**This method will provide the main BookMark Panel
	 * @return the panelBookMark
	 */
	public static BookMarkPanel getPanelBookMark() 
	{
		return panelBookMark;
	}


	/**
	 * @return the scrollBookMark
	 */
	public static JScrollPane getScrollBookMark() 
	{
		return scrollBookMark;
	}


	public static CompanyDeletUpdatPanel getPanelDeletUpdatCompany()
	{
		return panelUpdateDeleteCompany;
	}
	public static JScrollPane getScrollUpdateDeleteCompany()
	{
		return scrollUpdateDeleteCompany;

	}
	/**
	 * This method will provide the main messaging panel.  
	 * @return an object JPanel representing the main messaging.
	 */
	public static JPanel getPanelMessaging() {
		return panelMessaging;
	}

	/**
	 * Cette methode permet le changement de panel sur la JFrame
	 * @param panel correspond au JPanel que nous desirons afficher.
	 */
	public static void SwithPanel(JPanel panel) {

		System.out.println("in the mainframe " + panel.getName());
		panMain.removeAll();
		panMain.add(panel);
		panMain.validate();
		panMain.repaint();
	}
}