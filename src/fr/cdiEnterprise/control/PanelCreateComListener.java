package fr.cdiEnterprise.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import fr.cdiEnterprise.dao.DataBaseCompany;
import fr.cdiEnterprise.exceptions.CompanyCreationException;
import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.model.Contact;
import fr.cdiEnterprise.model.Department;
import fr.cdiEnterprise.model.Language;
import fr.cdiEnterprise.model.Region;
//import fr.cdiEnterprise.service.Languages;
import fr.cdiEnterprise.view.MainFrame;
import fr.cdiEnterprise.view.company.CompanyCreationPanel;

/**
 * Listeners for panel "Company Creation"
 *
 * @version 21-10-2016
 * @author Anaïs
 * 
 *
 */

public class PanelCreateComListener implements ActionListener {

	// Given attribute
	private CompanyCreationPanel panCompCreat;

	// Attributes for creation company
	private int idCompany;
	private String companyName;
	private String companyAdress;
	private String companyCity;
	private String companyPostalCode;
	private Department companyDepartment;
	private String nomDepartment;
	private Region companyRegion;
	private String nomRegion;
	private String companySize;
	private String companySector;
	// private Languages companyLanguages; Sera utilisé dans la deuxième version
	// => sélection multiple de langages informatiques
	private Language companyLanguage;
	private String companyProjets;
	private String companyWebSite;
	private Contact contact;
	private String contactName;
	private String contactPhone;
	private String contactMail;

	// Attributes to give select language
	private String languageSelect;

	// Attributes do define the selected size
	private ButtonGroup btnGrp;
	private JRadioButton btnSelected;

	// Attribute to create a company
	private Company company;

	// Attribute to create a new language
	private String newLanguage;

	// Atribute for Error
	private JFrame popupError;
	private String messError;

	public PanelCreateComListener(CompanyCreationPanel panCompCreat) {
		this.panCompCreat = panCompCreat;
	}

	@Override
	public void actionPerformed(ActionEvent e) throws CompanyCreationException {

		if (e.getSource() == panCompCreat.getBtnCreate()) {
			try {
				btnGrp = panCompCreat.getSizeGrp();
				btnSelected = MethodsForListeners.getSelectedJRadioButton(btnGrp);
				companySize = btnSelected.getText();
			} catch (NullPointerException excep) {
				JOptionPane.showMessageDialog(popupError, "La taille de l'entreprise ne sera pas renseignée");
			}

			try {
				System.out.println("Création d'une nouvelle entreprise");

				companyName = MethodsForListeners.nullField(panCompCreat.getTxtCompanyName().getText());
				companyAdress = panCompCreat.getTxtCompanyAdress().getText();
				companyCity = MethodsForListeners.nullField(panCompCreat.getTxtCompanyCity().getText().toUpperCase());
				companyPostalCode = MethodsForListeners.nullField(panCompCreat.getTxtPostalCode().getText());
				MethodsForListeners.controlPostalCode(companyPostalCode);
				nomDepartment = panCompCreat.getCboCompanyDepartment().getSelectedItem().toString();
				companyDepartment = DataBaseCompany.getDepartmentId(nomDepartment);
				nomRegion = panCompCreat.getCboCompanyRegion().getSelectedItem().toString();
				companyRegion = DataBaseCompany.getRegionId(nomRegion);
				companySector = panCompCreat.getTxtSector().getText();

				try {
					languageSelect = panCompCreat.getLstLanguages().getSelectedValue().toString();
				} catch (NullPointerException except) {
					languageSelect = "JAVA";
				}
				companyLanguage = DataBaseCompany.getLanguageId(languageSelect);

				companyProjets = panCompCreat.getTxtProjets().getText();
				companyWebSite = panCompCreat.getTxtWebSite().getText();
				contactName = panCompCreat.getTxtContactName().getText();
				contactPhone = panCompCreat.getTxtContactPhone().getText();
				contactMail = panCompCreat.getTxtContactMail().getText();

				contact = new Contact(contactName, contactPhone, contactMail);

				idCompany = DataBaseCompany.getIdMax("company") + 1;
				// System.out.println(idCompany);
				company = new Company(idCompany, companyName, companyAdress, companyPostalCode, companyCity,
						companyDepartment, companyRegion, companySize, companySector, companyLanguage, companyProjets,
						companyWebSite, contact);
				// System.out.println(company);

				DataBaseCompany.insertCompanyData(company, contact);
				CompanyCreationPanel.getDlmCompanies().addElement(company);
				MethodsForListeners.resetJTextField(panCompCreat.getAllJTextFields());

			} catch (NullPointerException | SQLException ev) {
				messError = ev.getMessage();
				JOptionPane.showMessageDialog(popupError, messError);
			}

		}

		if (e.getSource() == panCompCreat.getBtnLanguageCreate()) {
			newLanguage = panCompCreat.getTxtNewLanguage().getText();
			try {
				DataBaseCompany.insertLanguageData(newLanguage);
				panCompCreat.getDlmLanguages().clear();
				for (Language language : DataBaseCompany.getLanguagesListData()) {
					panCompCreat.getDlmLanguages().addElement(language);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == panCompCreat.getBtnCancel()) {
			MainFrame.getPanMain().removeAll();
			MainFrame.getPanMain().add(MainFrame.getPanHome());
			MainFrame.getPanMain().repaint();
			MainFrame.getPanMain().revalidate();
		}

	}

}
