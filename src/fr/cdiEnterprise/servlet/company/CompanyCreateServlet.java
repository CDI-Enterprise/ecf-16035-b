package fr.cdiEnterprise.servlet.company;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiEnterprise.control.MethodsForControl;
import fr.cdiEnterprise.dao.DBConnection;
import fr.cdiEnterprise.dao.DataBaseCompany;
import fr.cdiEnterprise.exceptions.CompanyCreationException;
import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.model.Contact;
import fr.cdiEnterprise.model.Department;
import fr.cdiEnterprise.model.Language;
import fr.cdiEnterprise.model.Region;

/**
 * Servlet implementation class CompanyCreationServlet
 */
@WebServlet(name = "CompanyCreate", urlPatterns = { "/action/CompanyCreate" })

public class CompanyCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Attributs r�cup�r�s du formulaire pour la cr�ation d'une fiche entreprise
	private String companyName;
	private String companyAdress;
	private String companyCity;
	private String companyPostalCode;
	private String departmentName;
	private String regionName;
	private String companySize;
	private String companySector;
	private String languageName;
	private String companyProjects;
	private String companyWebSite;
	private String contactName;
	private String contactPhone;
	private String contactMail;

	// Attributs pour la cr�ation d'une fiche entreprise
	private Contact contact;
	private Company company;
	private int idCompany;
	private int idContact;
	private Department companyDepartment;
	private Region companyRegion;
	private Language companyLanguage;

	// M�thode init pour initialiser la base de donn�es
	public void init() {
		DBConnection.getConnect();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---/CompanyCreate get method ----");
		response.sendRedirect(request.getContextPath() + "/jsp/accueil.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CompanyCreationException {
		try {
		// R�cup�ration des donn�es du formulaire
		companyName = request.getParameter("companyName");
		companyName = companyName.toUpperCase().trim();
		companyAdress = request.getParameter("companyAdress");
		companyCity = request.getParameter("companyCity");
		companyCity = companyCity.toUpperCase().trim();
		companyPostalCode = request.getParameter("companyPostalCode");
		departmentName = request.getParameter("companyDepartment");
		regionName = request.getParameter("companyRegion");
		companySize = request.getParameter("companySize");
		companySector = request.getParameter("companySector");
		companySector = companySector.toLowerCase().trim();
		languageName = request.getParameter("companyLanguages");
		 if (languageName == null) {
			 throw new CompanyCreationException("Veuillez renseigner un langage infomatique");
		 }
		companyProjects = request.getParameter("companyProjects");
		companyWebSite = request.getParameter("companyWebSite");
		contactName = request.getParameter("contactName");
		contactPhone = request.getParameter("contactPhone");
		contactMail = request.getParameter("contactMail");

		
			System.out.println("try");
			MethodsForControl.nullField(companyName);
			MethodsForControl.nullField(companyCity);
			MethodsForControl.controlPostalCode(companyPostalCode);
			MethodsForControl.nullField(languageName);
			try {
				companyDepartment = DataBaseCompany.getDepartmentId(departmentName);
				companyRegion = DataBaseCompany.getRegionId(regionName);
				companyLanguage = DataBaseCompany.getLanguageId(languageName);
				// R�cup�ration du dernier id de la base de donn�es
				idContact = DataBaseCompany.getIdMax("contact");
				// Cr�ation de l'objet Contact
				contact = new Contact(idContact, contactName, contactPhone, contactMail);
				// R�cup�ration du dernier id de la base de donn�es +1
				idCompany = DataBaseCompany.getIdMax("company") + 1;
				// Cr�ation objet et insertion BDD
				company = new Company(idCompany, companyName, companyAdress, companyPostalCode, companyCity,
						companyDepartment, companyRegion, companySize, companySector, companyLanguage, companyProjects,
						companyWebSite, contact);
				DataBaseCompany.insertCompanyData(company, contact);
				request.setAttribute("company", company);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/company/companyCreate.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e2) {
				System.out.println("-------PROBLEME--------");
				e2.printStackTrace();
			}
		} catch (CompanyCreationException ev) {
			System.out.println("Exception");
			String mess = ev.getMessage();
			System.out.println(mess);
			request.setAttribute("messageErreur", mess);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/company/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
