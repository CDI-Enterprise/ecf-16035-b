package fr.cdiEnterprise.view.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import fr.cdiEnterprise.control.PanelSRCompaniesListeners;
import fr.cdiEnterprise.dao.DataBaseCompany;
import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.model.Department;
import fr.cdiEnterprise.model.Language;
import fr.cdiEnterprise.model.Region;
import net.miginfocom.swing.MigLayout;

public class CompaniesSRPanel extends JPanel {

	/**
	 * Panel pour l'affichage et la recherche de companies
	 */
	private static final long serialVersionUID = 1L;
	private Container panneau;
	private JPanel panNorth;
	private JPanel panSouth;
	private JPanel panCenter;
	private JPanel panWest;
	private JPanel panSearch;
	private JPanel panRead;
	private JPanel panCompany;
	private JPanel panContact;
	private JRadioButton optAffiche;
	private JRadioButton optRecherche;
	private ButtonGroup readGrp; 
	private JLabel lblTitle;
	private JLabel lblSearchByName;
	private JTextField txtSearchByName;
	private JLabel lblCompanyName;
	private JTextField txtCompanyName;
	private JLabel lblCompanyAdress;
	private JTextField txtCompanyAdress;
	private JLabel lblCompanyCity;
	private JTextField txtCompanyCity;
	private JLabel lblPostalCode;
	private JTextField txtPostalCode;
	private JLabel lblCompanyDepartment;
	private JComboBox<String> cboCompanyDepartment;
	private JLabel lblSelcDepartment;
	private JLabel lblCompanyRegion;
	private JComboBox<String> cboCompanyRegion;
	private JLabel lblSelcRegion;
	private JLabel lblSize;
	private JRadioButton optMicroEnt;
	private JRadioButton optPME;
	private JRadioButton optETI;
	private JRadioButton optGrdEnt;
	private JLabel lblSector;
	private JTextField txtSector;
	private JLabel lblLanguages;
	private DefaultListModel<Language> dlmLanguages;
	private JList<Language> lstLanguages;
	private JScrollPane languages;
	private JLabel lblSelcLanguages;
	private JLabel lblWebSite;
	private JTextField txtWebSite;
	private JLabel lblProjets;
	private JTextArea txtProjets;
	private JLabel lblContactName;
	private JTextField txtContactName;
	private JLabel lblContactPhone;
	private JTextField txtContactPhone;
	private JLabel lblContactMail;
	private JTextField txtContactMail;
	private DefaultListModel<Company> dlmCompanies; 
	private JList <Company> lstCompanies;
	private JScrollPane allCompanies;
	private JButton btnValidate;
	private JButton btnCancel;
	private JButton btnFavoris;
	private PanelSRCompaniesListeners clic;
	private Border border;
	
	
	public CompaniesSRPanel() throws SQLException {
		
		panneau = this;
		panneau.setLayout(new BorderLayout(5,5));
				
		panNorth = new JPanel();
		panWest = new JPanel();
		panCenter = new JPanel();
		panSouth = new JPanel();

		panneau.add(panNorth, BorderLayout.NORTH);
		panneau.add(panWest,BorderLayout.WEST);
		panneau.add(panCenter, BorderLayout.CENTER);
		panneau.add(panSouth, BorderLayout.SOUTH);
		
		border = BorderFactory.createLineBorder(Color.GRAY);
		
		panNorth.setLayout(new FlowLayout());
		lblTitle = new JLabel("Recherche fiches entreprise");
		lblTitle.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		panWest.setLayout(new MigLayout());
		panCenter.setLayout(new MigLayout());
		
		panSearch = new JPanel();
		panSearch.setLayout(new MigLayout());
		
		
		panRead = new JPanel();
		panRead.setLayout(new MigLayout());
		
		optAffiche = new JRadioButton("Afficher la liste des fiches entreprises");
		optRecherche = new JRadioButton("Rechercher par critères (uniquement par nom)");
		readGrp = new ButtonGroup();
		readGrp.add(optRecherche);
		readGrp.add(optAffiche);
			
		
		lblSearchByName = new JLabel("Recherche par nom d'entreprise");
		txtSearchByName = new JTextField();
		txtSearchByName.setColumns(30);
		
		dlmCompanies = new DefaultListModel<Company>();
		lstCompanies = new JList<Company>(dlmCompanies);
		lstCompanies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		allCompanies = new JScrollPane(lstCompanies, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		allCompanies.setPreferredSize(new Dimension(800, 400));
		
		panCompany = new JPanel();
		panCompany.setLayout(new MigLayout());
		panCompany.setBorder(BorderFactory.createTitledBorder("ENTREPRISE"));
		
		panContact = new JPanel();	
		panContact.setLayout(new MigLayout());
		panContact.setBorder(BorderFactory.createTitledBorder("CONTACT ENTREPRISE"));
	
		lblCompanyName = new JLabel("Nom de l'entreprise *");
		txtCompanyName = new JTextField();
		txtCompanyName.setColumns(30);
		txtCompanyName.setBorder(border);
		txtCompanyName.setEditable(false);

		lblCompanyAdress = new JLabel("Adresse (rue et numéro)");
		txtCompanyAdress = new JTextField();
		txtCompanyAdress.setColumns(30);
		txtCompanyAdress.setEditable(false);

		lblCompanyCity = new JLabel("Ville *");
		txtCompanyCity = new JTextField();
		txtCompanyCity.setColumns(30);
		txtCompanyCity.setBorder(border);
		txtCompanyCity.setEditable(false);

		lblPostalCode = new JLabel("Code postal *");
		txtPostalCode = new JTextField();
		txtPostalCode.setColumns(5);
		txtPostalCode.setEditable(false);
		
		lblCompanyDepartment = new JLabel("Departement *");
		cboCompanyDepartment = new JComboBox<String>();
		
		for (Department department : DataBaseCompany.getDepartmentListData()){	
			cboCompanyDepartment.addItem(department.getDepartmentName());
				}
		cboCompanyDepartment.setEditable(false);
		cboCompanyDepartment.setMaximumRowCount(5);
		
		lblSelcDepartment = new JLabel();

		lblCompanyRegion = new JLabel("Région *");
		cboCompanyRegion = new JComboBox<String>();
		for (Region region : DataBaseCompany.getRegionsListData()) {
			cboCompanyRegion.addItem(region.getRegionName());
		}
		cboCompanyRegion.setEditable(false);
		cboCompanyRegion.setMaximumRowCount(5);
		
		lblSelcRegion = new JLabel();
		lblSize= new JLabel("Taille entreprise");
		optMicroEnt = new JRadioButton("Microentreprise (<10)");
		optPME = new JRadioButton("PME (<250)");
		optETI = new JRadioButton("ETI (>250 et <5000)"); 
		optGrdEnt= new JRadioButton("Grande Entreprise");
		
		lblSector = new JLabel("Secteur");
		txtSector = new JTextField();
		txtSector.setColumns(20);
		txtSector.setEditable(false);
		
		lblLanguages = new JLabel ("Langages principalement utilisés *");	
		dlmLanguages = new DefaultListModel<Language>();
		lstLanguages = new JList<Language>(dlmLanguages);
		for (Language language : DataBaseCompany.getLanguagesListData()) {
		dlmLanguages.addElement(language);
		}
		languages = new JScrollPane(lstLanguages, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		languages.setPreferredSize(new Dimension(150, 60));
		lblSelcLanguages = new JLabel();

		lblProjets = new JLabel("Principaux projets de l'entreprise");
		txtProjets= new JTextArea();
		txtProjets.setEditable(true);
		txtProjets.setColumns(30);
		txtProjets.setRows(3);
		txtProjets.setBorder(border);
		txtProjets.setEditable(false);
		
		lblWebSite = new JLabel("Site Web *");
		txtWebSite = new JTextField();
		txtWebSite.setColumns(20);
		txtWebSite.setEditable(false);
		
		lblContactName = new JLabel("Nom du contact ");
		txtContactName = new JTextField();
		txtContactName.setColumns(20);
		txtContactName.setEditable(false);
		
		lblContactPhone = new JLabel("Téléphone");
		txtContactPhone = new JTextField();
		txtContactPhone.setColumns(20);
		txtContactPhone.setEditable(false);
		
		lblContactMail = new JLabel("Adresse mail");
		txtContactMail = new JTextField();
		txtContactMail.setColumns(20);
		txtContactMail.setEditable(false);
		
		btnValidate = new JButton("Valider");
		btnCancel = new JButton("Annuler");
		btnFavoris = new JButton("Ajouter aux favoris");
		
		panNorth.add(lblTitle);
		panWest.add(panSearch, "wrap");
		panWest.add(panRead, "wrap");
		panSearch.add(optAffiche, "wrap");
		panSearch.add(optRecherche, "wrap");
		panSearch.add(lblSearchByName);
		panSearch.add(txtSearchByName, "wrap");
		panRead.add(allCompanies, "wrap");
		panCenter.add(panCompany, "wrap 20");
		panCompany.add(lblCompanyName);
		panCompany.add(txtCompanyName, "wrap 20");
		panCompany.add(lblCompanyAdress);
		panCompany.add(txtCompanyAdress, "wrap 20");
		panCompany.add(lblCompanyCity);
		panCompany.add(txtCompanyCity, "wrap 20");
		panCompany.add(lblPostalCode);
		panCompany.add(txtPostalCode, "wrap 20");
		panCompany.add(lblCompanyDepartment);
		panCompany.add(cboCompanyDepartment);
		panCompany.add(lblSelcDepartment, "wrap 20");
		panCompany.add(lblCompanyRegion);
		panCompany.add(cboCompanyRegion);
		panCompany.add(lblSelcRegion, "wrap 20");
		panCompany.add(lblSize);
		panCompany.add(optMicroEnt);
		panCompany.add(optPME);
		panCompany.add(optETI);
		panCompany.add(optGrdEnt, "wrap 20");
		panCompany.add(lblSector);
		panCompany.add(txtSector, "wrap 20");
		panCompany.add(lblLanguages);
		panCompany.add(languages);
		panCompany.add(lblSelcLanguages, "wrap 20");
		panCompany.add(lblProjets);
		panCompany.add(txtProjets, "wrap 20");
		panCompany.add(lblWebSite);
		panCompany.add(txtWebSite, "wrap 30");
		panCenter.add(panContact);
		panContact.add(lblContactName);
		panContact.add(txtContactName, "wrap 20");
		panContact.add(lblContactPhone);
		panContact.add(txtContactPhone, "wrap 20");
		panContact.add(lblContactMail);
		panContact.add(txtContactMail, "wrap 20");
		panSouth.add(btnValidate);
		panSouth.add(btnFavoris);
		panSouth.add(btnCancel);
		
	
		clic = new PanelSRCompaniesListeners(this);
		btnValidate.addActionListener(clic);
		btnCancel.addActionListener(clic);
		btnFavoris.addActionListener(clic);
		
		MouseListener cliq = (MouseListener) new PanelSRCompaniesListeners(this);			
//		lstCompanies.addListSelectionListener(clic);
		lstCompanies.addMouseListener(cliq);
	}


	/**
	 * @return the panSearch
	 */
	public JPanel getPanSearch() {
		return panSearch;
	}


	/**
	 * @param panSearch the panSearch to set
	 */
	public void setPanSearch(JPanel panSearch) {
		this.panSearch = panSearch;
	}


	/**
	 * @return the panRead
	 */
	public JPanel getPanRead() {
		return panRead;
	}


	/**
	 * @param panRead the panRead to set
	 */
	public void setPanRead(JPanel panRead) {
		this.panRead = panRead;
	}


	/**
	 * @return the panCompany
	 */
	public JPanel getPanCompany() {
		return panCompany;
	}


	/**
	 * @param panCompany the panCompany to set
	 */
	public void setPanCompany(JPanel panCompany) {
		this.panCompany = panCompany;
	}


	/**
	 * @return the panContact
	 */
	public JPanel getPanContact() {
		return panContact;
	}



	/**
	 * @param panContact the panContact to set
	 */
	public void setPanContact(JPanel panContact) {
		this.panContact = panContact;
	}


	/**
	 * @return the lblTitle
	 */
	public JLabel getLblTitle() {
		return lblTitle;
	}


	/**
	 * @param lblTitle the lblTitle to set
	 */
	public void setLblTitle(JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}

	/**
	 * @return the lblCompanyName
	 */
	public JLabel getLblCompanyName() {
		return lblCompanyName;
	}


	/**
	 * @param lblCompanyName the lblCompanyName to set
	 */
	public void setLblCompanyName(JLabel lblCompanyName) {
		this.lblCompanyName = lblCompanyName;
	}

	/**
	 * @return the txtCompanyName
	 */
	public JTextField getTxtCompanyName() {
		return txtCompanyName;
	}

	/**
	 * @param txtCompanyName the txtCompanyName to set
	 */
	public void setTxtCompanyName(JTextField txtCompanyName) {
		this.txtCompanyName = txtCompanyName;
	}

	/**
	 * @return the lblCompanyAdress
	 */
	public JLabel getLblCompanyAdress() {
		return lblCompanyAdress;
	}


	/**
	 * @param lblCompanyAdress the lblCompanyAdress to set
	 */
	public void setLblCompanyAdress(JLabel lblCompanyAdress) {
		this.lblCompanyAdress = lblCompanyAdress;
	}


	/**
	 * @return the txtCompanyAdress
	 */
	public JTextField getTxtCompanyAdress() {
		return txtCompanyAdress;
	}

	/**
	 * @param txtCompanyAdress the txtCompanyAdress to set
	 */
	public void setTxtCompanyAdress(JTextField txtCompanyAdress) {
		this.txtCompanyAdress = txtCompanyAdress;
	}

	/**
	 * @return the lblCompanyCity
	 */
	public JLabel getLblCompanyCity() {
		return lblCompanyCity;
	}


	/**
	 * @param lblCompanyCity the lblCompanyCity to set
	 */
	public void setLblCompanyCity(JLabel lblCompanyCity) {
		this.lblCompanyCity = lblCompanyCity;
	}


	/**
	 * @return the txtCompanyCity
	 */
	public JTextField getTxtCompanyCity() {
		return txtCompanyCity;
	}

	/**
	 * @param txtCompanyCity the txtCompanyCity to set
	 */
	public void setTxtCompanyCity(JTextField txtCompanyCity) {
		this.txtCompanyCity = txtCompanyCity;
	}

	/**
	 * @return the lblPostalCode
	 */
	public JLabel getLblPostalCode() {
		return lblPostalCode;
	}

	/**
	 * @param lblPostalCode the lblPostalCode to set
	 */
	public void setLblPostalCode(JLabel lblPostalCode) {
		this.lblPostalCode = lblPostalCode;
	}

	/**
	 * @return the txtPostalCode
	 */
	public JTextField getTxtPostalCode() {
		return txtPostalCode;
	}

	/**
	 * @param txtPostalCode the txtPostalCode to set
	 */
	public void setTxtPostalCode(JTextField txtPostalCode) {
		this.txtPostalCode = txtPostalCode;
	}

	/**
	 * @return the lblCompanyDepartment
	 */
	public JLabel getLblCompanyDepartment() {
		return lblCompanyDepartment;
	}


	/**
	 * @param lblCompanyDepartment the lblCompanyDepartment to set
	 */
	public void setLblCompanyDepartment(JLabel lblCompanyDepartment) {
		this.lblCompanyDepartment = lblCompanyDepartment;
	}

	/**
	 * @return the cboCompanyDepartment
	 */
	public JComboBox<String> getCboCompanyDepartment() {
		return cboCompanyDepartment;
	}

	/**
	 * @param cboCompanyDepartment the cboCompanyDepartment to set
	 */
	public void setCboCompanyDepartment(JComboBox<String> cboCompanyDepartment) {
		this.cboCompanyDepartment = cboCompanyDepartment;
	}

	/**
	 * @return the lblSelcDepartment
	 */
	public JLabel getLblSelcDepartment() {
		return lblSelcDepartment;
	}

	/**
	 * @param lblSelcDepartment the lblSelcDepartment to set
	 */
	public void setLblSelcDepartment(JLabel lblSelcDepartment) {
		this.lblSelcDepartment = lblSelcDepartment;
	}

	/**
	 * @return the lblCompanyRegion
	 */
	public JLabel getLblCompanyRegion() {
		return lblCompanyRegion;
	}

	/**
	 * @param lblCompanyRegion the lblCompanyRegion to set
	 */
	public void setLblCompanyRegion(JLabel lblCompanyRegion) {
		this.lblCompanyRegion = lblCompanyRegion;
	}

	/**
	 * @return the cboCompanyRegion
	 */
	public JComboBox<String> getCboCompanyRegion() {
		return cboCompanyRegion;
	}

















	/**
	 * @param cboCompanyRegion the cboCompanyRegion to set
	 */
	public void setCboCompanyRegion(JComboBox<String> cboCompanyRegion) {
		this.cboCompanyRegion = cboCompanyRegion;
	}

















	/**
	 * @return the lblSelcRegion
	 */
	public JLabel getLblSelcRegion() {
		return lblSelcRegion;
	}

















	/**
	 * @param lblSelcRegion the lblSelcRegion to set
	 */
	public void setLblSelcRegion(JLabel lblSelcRegion) {
		this.lblSelcRegion = lblSelcRegion;
	}

















	/**
	 * @return the lblSize
	 */
	public JLabel getLblSize() {
		return lblSize;
	}

















	/**
	 * @param lblSize the lblSize to set
	 */
	public void setLblSize(JLabel lblSize) {
		this.lblSize = lblSize;
	}

















	/**
	 * @return the optMicroEnt
	 */
	public JRadioButton getOptMicroEnt() {
		return optMicroEnt;
	}

















	/**
	 * @param optMicroEnt the optMicroEnt to set
	 */
	public void setOptMicroEnt(JRadioButton optMicroEnt) {
		this.optMicroEnt = optMicroEnt;
	}

















	/**
	 * @return the optPME
	 */
	public JRadioButton getOptPME() {
		return optPME;
	}

















	/**
	 * @param optPME the optPME to set
	 */
	public void setOptPME(JRadioButton optPME) {
		this.optPME = optPME;
	}

















	/**
	 * @return the optETI
	 */
	public JRadioButton getOptETI() {
		return optETI;
	}

















	/**
	 * @param optETI the optETI to set
	 */
	public void setOptETI(JRadioButton optETI) {
		this.optETI = optETI;
	}

















	/**
	 * @return the optGrdEnt
	 */
	public JRadioButton getOptGrdEnt() {
		return optGrdEnt;
	}

















	/**
	 * @param optGrdEnt the optGrdEnt to set
	 */
	public void setOptGrdEnt(JRadioButton optGrdEnt) {
		this.optGrdEnt = optGrdEnt;
	}

















	/**
	 * @return the lblSector
	 */
	public JLabel getLblSector() {
		return lblSector;
	}

















	/**
	 * @param lblSector the lblSector to set
	 */
	public void setLblSector(JLabel lblSector) {
		this.lblSector = lblSector;
	}

















	/**
	 * @return the txtSector
	 */
	public JTextField getTxtSector() {
		return txtSector;
	}

















	/**
	 * @param txtSector the txtSector to set
	 */
	public void setTxtSector(JTextField txtSector) {
		this.txtSector = txtSector;
	}

















	/**
	 * @return the lblLanguages
	 */
	public JLabel getLblLanguages() {
		return lblLanguages;
	}

















	/**
	 * @param lblLanguages the lblLanguages to set
	 */
	public void setLblLanguages(JLabel lblLanguages) {
		this.lblLanguages = lblLanguages;
	}

















	/**
	 * @return the dlmLanguages
	 */
	public DefaultListModel<Language> getDlmLanguages() {
		return dlmLanguages;
	}

















	/**
	 * @param dlmLanguages the dlmLanguages to set
	 */
	public void setDlmLanguages(DefaultListModel<Language> dlmLanguages) {
		this.dlmLanguages = dlmLanguages;
	}

















	/**
	 * @return the lstLanguages
	 */
	public JList<Language> getLstLanguages() {
		return lstLanguages;
	}

















	/**
	 * @param lstLanguages the lstLanguages to set
	 */
	public void setLstLanguages(JList<Language> lstLanguages) {
		this.lstLanguages = lstLanguages;
	}

















	/**
	 * @return the languages
	 */
	public JScrollPane getLanguages() {
		return languages;
	}

















	/**
	 * @param languages the languages to set
	 */
	public void setLanguages(JScrollPane languages) {
		this.languages = languages;
	}

















	/**
	 * @return the lblSelcLanguages
	 */
	public JLabel getLblSelcLanguages() {
		return lblSelcLanguages;
	}

















	/**
	 * @param lblSelcLanguages the lblSelcLanguages to set
	 */
	public void setLblSelcLanguages(JLabel lblSelcLanguages) {
		this.lblSelcLanguages = lblSelcLanguages;
	}

















	/**
	 * @return the lblWebSite
	 */
	public JLabel getLblWebSite() {
		return lblWebSite;
	}

















	/**
	 * @param lblWebSite the lblWebSite to set
	 */
	public void setLblWebSite(JLabel lblWebSite) {
		this.lblWebSite = lblWebSite;
	}

















	/**
	 * @return the txtWebSite
	 */
	public JTextField getTxtWebSite() {
		return txtWebSite;
	}

















	/**
	 * @param txtWebSite the txtWebSite to set
	 */
	public void setTxtWebSite(JTextField txtWebSite) {
		this.txtWebSite = txtWebSite;
	}

















	/**
	 * @return the lblProjets
	 */
	public JLabel getLblProjets() {
		return lblProjets;
	}

















	/**
	 * @param lblProjets the lblProjets to set
	 */
	public void setLblProjets(JLabel lblProjets) {
		this.lblProjets = lblProjets;
	}

















	/**
	 * @return the txtProjets
	 */
	public JTextArea getTxtProjets() {
		return txtProjets;
	}

















	/**
	 * @param txtProjets the txtProjets to set
	 */
	public void setTxtProjets(JTextArea txtProjets) {
		this.txtProjets = txtProjets;
	}

















	/**
	 * @return the lblContactName
	 */
	public JLabel getLblContactName() {
		return lblContactName;
	}

















	/**
	 * @param lblContactName the lblContactName to set
	 */
	public void setLblContactName(JLabel lblContactName) {
		this.lblContactName = lblContactName;
	}

















	/**
	 * @return the txtContactName
	 */
	public JTextField getTxtContactName() {
		return txtContactName;
	}

















	/**
	 * @param txtContactName the txtContactName to set
	 */
	public void setTxtContactName(JTextField txtContactName) {
		this.txtContactName = txtContactName;
	}

















	/**
	 * @return the lblContactPhone
	 */
	public JLabel getLblContactPhone() {
		return lblContactPhone;
	}

















	/**
	 * @param lblContactPhone the lblContactPhone to set
	 */
	public void setLblContactPhone(JLabel lblContactPhone) {
		this.lblContactPhone = lblContactPhone;
	}

















	/**
	 * @return the txtContactPhone
	 */
	public JTextField getTxtContactPhone() {
		return txtContactPhone;
	}

















	/**
	 * @param txtContactPhone the txtContactPhone to set
	 */
	public void setTxtContactPhone(JTextField txtContactPhone) {
		this.txtContactPhone = txtContactPhone;
	}

















	/**
	 * @return the lblContactMail
	 */
	public JLabel getLblContactMail() {
		return lblContactMail;
	}

















	/**
	 * @param lblContactMail the lblContactMail to set
	 */
	public void setLblContactMail(JLabel lblContactMail) {
		this.lblContactMail = lblContactMail;
	}

	/**
	 * @return the txtContactMail
	 */
	public JTextField getTxtContactMail() {
		return txtContactMail;
	}


	/**
	 * @param txtContactMail the txtContactMail to set
	 */
	public void setTxtContactMail(JTextField txtContactMail) {
		this.txtContactMail = txtContactMail;
	}


	/**
	 * @return the allCompanies
	 */
	public JScrollPane getAllCompanies() {
		return allCompanies;
	}

	/**
	 * @param allCompanies the allCompanies to set
	 */
	public void setAllCompanies(JScrollPane allCompanies) {
		this.allCompanies = allCompanies;
	}

	/**
	 * @return the clic
	 */
	public PanelSRCompaniesListeners getClic() {
		return clic;
	}

	/**
	 * @param clic the clic to set
	 */
	public void setClic(PanelSRCompaniesListeners clic) {
		this.clic = clic;
	}

	/**
	 * @return the border
	 */
	public Border getBorder() {
		return border;
	}

	/**
	 * @param border the border to set
	 */
	public void setBorder(Border border) {
		this.border = border;
	}

	public Container getPanneau() {
		return panneau;
	}



	public void setPanneau(Container panneau) {
		this.panneau = panneau;
	}



	public JPanel getPanNorth() {
		return panNorth;
	}



	public void setPanNorth(JPanel panNorth) {
		this.panNorth = panNorth;
	}



	public JPanel getPanSouth() {
		return panSouth;
	}



	public void setPanSouth(JPanel panSouth) {
		this.panSouth = panSouth;
	}



	public JPanel getPanCenter() {
		return panCenter;
	}



	public void setPanCenter(JPanel panCenter) {
		this.panCenter = panCenter;
	}



	public JPanel getPanWest() {
		return panWest;
	}



	public void setPanWest(JPanel panWest) {
		this.panWest = panWest;
	}



	public JRadioButton getOptAffiche() {
		return optAffiche;
	}



	public void setOptAffiche(JRadioButton optAffiche) {
		this.optAffiche = optAffiche;
	}



	public JRadioButton getOptRecherche() {
		return optRecherche;
	}



	public void setOptRecherche(JRadioButton optRecherche) {
		this.optRecherche = optRecherche;
	}



	public JLabel getLblSearchByName() {
		return lblSearchByName;
	}



	public void setLblSearchByName(JLabel lblSearchByName) {
		this.lblSearchByName = lblSearchByName;
	}



	public JTextField getTxtSearchByName() {
		return txtSearchByName;
	}



	public void setTxtSearchByName(JTextField txtSearchByName) {
		this.txtSearchByName = txtSearchByName;
	}



	public DefaultListModel<Company> getDlmCompanies() {
		return dlmCompanies;
	}



	public void setDlmCompanies(DefaultListModel<Company> dlmCompanies) {
		this.dlmCompanies = dlmCompanies;
	}



	public JList<Company> getLstCompanies() {
		return lstCompanies;
	}



	public void setLstCompanies(JList<Company> lstCompanies) {
		this.lstCompanies = lstCompanies;
	}



	public JScrollPane getCompanies() {
		return allCompanies;
	}



	public void setCompanies(JScrollPane companies) {
		this.allCompanies = companies;
	}



	public JButton getBtnValider() {
		return btnValidate;
	}



	public void setBtnValider(JButton btnValider) {
		this.btnValidate = btnValider;
	}



	public JButton getBtnCancel() {
		return btnCancel;
	}



	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}



	public ButtonGroup getReadGrp() {
		return readGrp;
	}



	public void setReadGrp(ButtonGroup readGrp) {
		this.readGrp = readGrp;
	}


	/**
	 * @return the btnFavoris
	 */
	public JButton getBtnFavoris() {
		return btnFavoris;
	}


	/**
	 * @param btnFavoris the btnFavoris to set
	 */
	public void setBtnFavoris(JButton btnFavoris) {
		this.btnFavoris = btnFavoris;
	}

}
