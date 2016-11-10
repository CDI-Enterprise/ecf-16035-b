package fr.cdiEnterprise.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import fr.cdiEnterprise.dao.DataBaseCompany;
import fr.cdiEnterprise.dao.FavoriteDao;
import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.model.Favorite;
import fr.cdiEnterprise.view.MainFrame;
import fr.cdiEnterprise.view.company.CompaniesSRPanel;


public class PanelSRCompaniesListeners implements ActionListener, MouseListener {

	/* Given attributes */
	private CompaniesSRPanel panCompaniesSR;

	// Attributes do define the selected
	private ButtonGroup btnGrp;
	private JRadioButton btnSelected;

	private Company company;
	private String companySelec;
	private Favorite favoriteCompany;

	private Company selecCompany;
	private static int selecIndSelec;


	//Attributed to add favorite
	private int idFavorite;
	private String companyName;
	private String companyCity;
	private String companySize;
	private String companySector;
	private String companyWebSite;
	private String companyContactMail;	
	private String noteUser;
	private FavoriteDao favoriteDao;


	public PanelSRCompaniesListeners(CompaniesSRPanel panCompaniesSR) {
		this.panCompaniesSR = panCompaniesSR;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == panCompaniesSR.getBtnCancel()) {
			MainFrame.getPanMain().removeAll();
			MainFrame.getPanMain().add(MainFrame.getPanHome());
			MainFrame.getPanMain().repaint();
			MainFrame.getPanMain().revalidate();
		}

		/**
		 * Insert a new favorite in database.
		 * 
		 * @author Ismael
		 * @param id
		 * @param companyName
		 * @param city
		 * @param size
		 * @param sector
		 * @param WebSite
		 * @param contactMail
		 * @version 24-10-2016
		 */

		if (e.getSource() == panCompaniesSR.getBtnFavoris())	
		{
			FavoriteDao favoriteDao = new FavoriteDao();
			try
			{

				idFavorite		= FavoriteDao.getIdMax("favorite") +1;
				companyName		= panCompaniesSR.getTxtCompanyCity().getText();
				System.out.println("test 0" + companyName);
				companyCity		= panCompaniesSR.getTxtCompanyCity().getText();
				companySize		= panCompaniesSR.getLblSize().getText();	
				companySector	= panCompaniesSR.getTxtSector().getText();
				companyWebSite	= panCompaniesSR.getTxtWebSite().getText();
				companyContactMail		= panCompaniesSR.getTxtContactMail().getText();

				//Create a favorite's object
				favoriteCompany = new Favorite(idFavorite, companyName, companyCity, companySize, companySector, companyWebSite, companyContactMail, noteUser);
				System.out.println(favoriteCompany);

				//Send the add
				//favoriteDao.addFavorite(favoriteCompany);
				FavoriteDao.createFavorite(favoriteCompany);
				//addFavorite(idFavorite, companyName, companyCity, companySize, companySector, companyWebSite, companyContactMail, noteUser);
				System.out.println("hey" + favoriteCompany);
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}		
		}

		if (e.getSource() == panCompaniesSR.getBtnValider()) {
			try {
				btnGrp = panCompaniesSR.getReadGrp();
				btnSelected = MethodsForListeners.getSelectedJRadioButton(btnGrp);
			} catch (NullPointerException excep) {
				btnSelected = panCompaniesSR.getOptAffiche();
			}

			try {
				if (btnSelected == panCompaniesSR.getOptAffiche()) {
					for (Company company : DataBaseCompany.getCompaniesData()) {
						panCompaniesSR.getDlmCompanies().addElement(company);
					}
				} else {
					companySelec = panCompaniesSR.getTxtSearchByName().getText();
					company = DataBaseCompany.getCompaniesId(companySelec);
					panCompaniesSR.getDlmCompanies().addElement(company);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// JList list = (JList)e.getSource();
		//
		// if (e.getClickCount() == 2) {
		// System.out.println("double clicked");
		// }

		if (e.getSource() == panCompaniesSR.getLstCompanies()) {
			selecCompany = (Company) panCompaniesSR.getLstCompanies().getSelectedValue();
			selecIndSelec = selecCompany.getCompanyId();
			System.out.println(selecIndSelec);
			panCompaniesSR.getTxtCompanyName().setText(selecCompany.getCompanyName());
			panCompaniesSR.getTxtCompanyAdress().setText(selecCompany.getAdress());
			panCompaniesSR.getTxtCompanyCity().setText(selecCompany.getCity());
			panCompaniesSR.getTxtPostalCode().setText(selecCompany.getPostalCode());
			panCompaniesSR.getTxtSector().setText(selecCompany.getSector());
			panCompaniesSR.getTxtProjets().setText(selecCompany.getProjets());
			panCompaniesSR.getTxtWebSite().setText(selecCompany.getWebSite());

			try {
				panCompaniesSR.getTxtContactName().setText(selecCompany.getContact().getName());
				panCompaniesSR.getTxtContactPhone().setText(selecCompany.getContact().getPhoneNumber());
				panCompaniesSR.getTxtContactMail().setText(selecCompany.getContact().getEmail());
			} catch (NullPointerException excpt) {

			}
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}