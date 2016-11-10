package fr.cdiEnterprise.view.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
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

import fr.cdiEnterprise.control.PanelDeletUpdatCompListeners;
import fr.cdiEnterprise.control.PanelSRCompaniesListeners;
import fr.cdiEnterprise.dao.DataBaseCompany;
import fr.cdiEnterprise.dao.OldDatas;
import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.model.Department;
import fr.cdiEnterprise.model.Language;
import fr.cdiEnterprise.model.Region;
import net.miginfocom.swing.MigLayout;



public class CompanyDeletUpdatPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Container panneau;
	private JPanel panNorth;
	private JPanel panSouth;
	private JPanel panCenter;
	private JPanel panWest;
	private JLabel lblTitle;
	private JPanel panCompany;
	private JPanel panContact;
	private Border border;

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
	private JScrollPane companies;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnCancel;
	private PanelDeletUpdatCompListeners clic;
	
	public CompanyDeletUpdatPanel() throws SQLException {
		panneau = this;
		panneau.setLayout(new BorderLayout(5,5));
		
		border = BorderFactory.createLineBorder(Color.GRAY);
		
		panNorth = new JPanel();
		panWest = new JPanel();
		panCenter = new JPanel();
		panSouth = new JPanel();

		panneau.add(panNorth, BorderLayout.NORTH);
		panneau.add(panWest,BorderLayout.WEST);
		panneau.add(panCenter, BorderLayout.CENTER);
		panneau.add(panSouth, BorderLayout.SOUTH);
	
		/* Header */
		panNorth.setLayout(new FlowLayout());
		lblTitle = new JLabel("Modification / Suppression d'une fiche entreprise");
		lblTitle.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		lblTitle.setVisible(true);
		panNorth.setBorder(BorderFactory.createLineBorder(Color.GRAY));	
		panNorth.add(lblTitle);
		
		/* Body */
		panWest.setLayout(new MigLayout());
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
		
		dlmCompanies = new DefaultListModel<Company>();
		lstCompanies = new JList<Company>(CompanyCreationPanel.dlmCompanies);
		for(Company company: OldDatas.getCompaniesList()){
		//for (Company company: DataBaseCompany.getCompaniesData()){
			dlmCompanies.addElement(company);
		}
		lstCompanies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		companies = new JScrollPane(lstCompanies, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		companies.setPreferredSize(new Dimension(800, 800));
		
		
		btnDelete = new JButton("Supprimer");
		btnUpdate = new JButton("Modifier");
		btnCancel = new JButton("Annuler");
		
////		dateEdit = new JDateChooser();
////
		panWest.add(panCompany, "wrap 20");
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
		panWest.add(panContact);
		panContact.add(lblContactName);
		panContact.add(txtContactName, "wrap 20");
		panContact.add(lblContactPhone);
		panContact.add(txtContactPhone, "wrap 20");
		panContact.add(lblContactMail);
		panContact.add(txtContactMail, "wrap 20");
		panCenter.add(companies);
				
		panSouth.add(btnUpdate);
		panSouth.add(btnDelete);
		panSouth.add(btnCancel);
		
		clic = new PanelDeletUpdatCompListeners(this);
		
		MouseListener cliq = (MouseListener) new PanelDeletUpdatCompListeners(this);
		
		btnDelete.addActionListener(clic);
		btnCancel.addActionListener(clic);
		btnUpdate.addActionListener(clic);
		
		lstCompanies.addListSelectionListener(clic);
		lstCompanies.addMouseListener(cliq);
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * @return the panneau
	 */
	public Container getPanneau() {
		return panneau;
	}


	/**
	 * @return the panNorth
	 */
	public JPanel getPanNorth() {
		return panNorth;
	}


	/**
	 * @return the panSouth
	 */
	public JPanel getPanSouth() {
		return panSouth;
	}


	/**
	 * @return the panCenter
	 */
	public JPanel getPanCenter() {
		return panCenter;
	}


	/**
	 * @return the panWest
	 */
	public JPanel getPanWest() {
		return panWest;
	}


	/**
	 * @return the lblTitle
	 */
	public JLabel getLblTitle() {
		return lblTitle;
	}


	/**
	 * @return the panCompany
	 */
	public JPanel getPanCompany() {
		return panCompany;
	}


	/**
	 * @return the panContact
	 */
	public JPanel getPanContact() {
		return panContact;
	}


	/**
	 * @return the border
	 */
	public Border getBorder() {
		return border;
	}


	/**
	 * @return the lblCompanyName
	 */
	public JLabel getLblCompanyName() {
		return lblCompanyName;
	}


	/**
	 * @return the txtCompanyName
	 */
	public JTextField getTxtCompanyName() {
		return txtCompanyName;
	}


	/**
	 * @return the lblCompanyAdress
	 */
	public JLabel getLblCompanyAdress() {
		return lblCompanyAdress;
	}


	/**
	 * @return the txtCompanyAdress
	 */
	public JTextField getTxtCompanyAdress() {
		return txtCompanyAdress;
	}


	/**
	 * @return the lblCompanyCity
	 */
	public JLabel getLblCompanyCity() {
		return lblCompanyCity;
	}


	/**
	 * @return the txtCompanyCity
	 */
	public JTextField getTxtCompanyCity() {
		return txtCompanyCity;
	}


	/**
	 * @return the lblPostalCode
	 */
	public JLabel getLblPostalCode() {
		return lblPostalCode;
	}


	/**
	 * @return the txtPostalCode
	 */
	public JTextField getTxtPostalCode() {
		return txtPostalCode;
	}


	/**
	 * @return the lblCompanyDepartment
	 */
	public JLabel getLblCompanyDepartment() {
		return lblCompanyDepartment;
	}


	/**
	 * @return the cboCompanyDepartment
	 */
	public JComboBox<String> getCboCompanyDepartment() {
		return cboCompanyDepartment;
	}


	/**
	 * @return the lblSelcDepartment
	 */
	public JLabel getLblSelcDepartment() {
		return lblSelcDepartment;
	}


	/**
	 * @return the lblCompanyRegion
	 */
	public JLabel getLblCompanyRegion() {
		return lblCompanyRegion;
	}


	/**
	 * @return the cboCompanyRegion
	 */
	public JComboBox<String> getCboCompanyRegion() {
		return cboCompanyRegion;
	}


	/**
	 * @return the lblSelcRegion
	 */
	public JLabel getLblSelcRegion() {
		return lblSelcRegion;
	}


	/**
	 * @return the lblSize
	 */
	public JLabel getLblSize() {
		return lblSize;
	}


	/**
	 * @return the optMicroEnt
	 */
	public JRadioButton getOptMicroEnt() {
		return optMicroEnt;
	}


	/**
	 * @return the optPME
	 */
	public JRadioButton getOptPME() {
		return optPME;
	}


	/**
	 * @return the optETI
	 */
	public JRadioButton getOptETI() {
		return optETI;
	}


	/**
	 * @return the optGrdEnt
	 */
	public JRadioButton getOptGrdEnt() {
		return optGrdEnt;
	}


	/**
	 * @return the lblSector
	 */
	public JLabel getLblSector() {
		return lblSector;
	}


	/**
	 * @return the txtSector
	 */
	public JTextField getTxtSector() {
		return txtSector;
	}


	/**
	 * @return the lblLanguages
	 */
	public JLabel getLblLanguages() {
		return lblLanguages;
	}


	/**
	 * @return the dlmLanguages
	 */
	public DefaultListModel<Language> getDlmLanguages() {
		return dlmLanguages;
	}


	/**
	 * @return the lstLanguages
	 */
	public JList<Language> getLstLanguages() {
		return lstLanguages;
	}


	/**
	 * @return the languages
	 */
	public JScrollPane getLanguages() {
		return languages;
	}


	/**
	 * @return the lblSelcLanguages
	 */
	public JLabel getLblSelcLanguages() {
		return lblSelcLanguages;
	}


	/**
	 * @return the lblWebSite
	 */
	public JLabel getLblWebSite() {
		return lblWebSite;
	}


	/**
	 * @return the txtWebSite
	 */
	public JTextField getTxtWebSite() {
		return txtWebSite;
	}


	/**
	 * @return the lblProjets
	 */
	public JLabel getLblProjets() {
		return lblProjets;
	}


	/**
	 * @return the txtProjets
	 */
	public JTextArea getTxtProjets() {
		return txtProjets;
	}


	/**
	 * @return the lblContactName
	 */
	public JLabel getLblContactName() {
		return lblContactName;
	}


	/**
	 * @return the txtContactName
	 */
	public JTextField getTxtContactName() {
		return txtContactName;
	}


	/**
	 * @return the lblContactPhone
	 */
	public JLabel getLblContactPhone() {
		return lblContactPhone;
	}


	/**
	 * @return the txtContactPhone
	 */
	public JTextField getTxtContactPhone() {
		return txtContactPhone;
	}


	/**
	 * @return the lblContactMail
	 */
	public JLabel getLblContactMail() {
		return lblContactMail;
	}


	/**
	 * @return the txtContactMail
	 */
	public JTextField getTxtContactMail() {
		return txtContactMail;
	}


	/**
	 * @return the dlmCompanies
	 */
	public DefaultListModel<Company> getDlmCompanies() {
		return dlmCompanies;
	}


	/**
	 * @return the lstCompanies
	 */
	public JList<Company> getLstCompanies() {
		return lstCompanies;
	}


	/**
	 * @return the companies
	 */
	public JScrollPane getCompanies() {
		return companies;
	}


	/**
	 * @return the btnDelete
	 */
	public JButton getBtnDelete() {
		return btnDelete;
	}


	/**
	 * @return the btnUpdate
	 */
	public JButton getBtnUpdate() {
		return btnUpdate;
	}


	/**
	 * @return the btnCancel
	 */
	public JButton getBtnCancel() {
		return btnCancel;
	}
}
