package fr.cdiEnterprise.servlet.company;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.cdiEnterprise.control.MethodsForListeners;
import fr.cdiEnterprise.dao.DBConnection;
import fr.cdiEnterprise.dao.DataBaseCompany;
import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.model.Contact;
import fr.cdiEnterprise.model.Department;
import fr.cdiEnterprise.model.Language;
import fr.cdiEnterprise.model.Region;

/**
 * Servlet implementation class CompanyCreationServlet
 */
@WebServlet("/action/CompanyCreate")
public class CompanyCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Attributs récupérés du formulaire pour la création d'une fiche entreprise
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

	// Attributs pour la création d'une fiche entreprise
	private Contact contact;
	private Company company;
	private int idCompany;
	private int idContact;
	private Department companyDepartment;
	private Region companyRegion;
	private Language companyLanguage;

	// Méthode init pour initialiser la base de données
	public void init() {
		 DBConnection.getConnect();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération des données du formulaire
		companyName = request.getParameter("companyName");
		companyName = companyName.toUpperCase().trim();
		MethodsForListeners.nullField(companyName);
		companyAdress = request.getParameter("companyAdress");
		companyCity = request.getParameter("companyCity");
		MethodsForListeners.nullField(companyCity);
		companyCity = companyCity.toUpperCase().trim();
		companyPostalCode = request.getParameter("companyPostalCode");
		MethodsForListeners.controlPostalCode(companyPostalCode);
		departmentName = request.getParameter("companyDepartment");
		regionName = request.getParameter("companyRegion");
		companySize = request.getParameter("companySize");
		companySector = request.getParameter("companySector");
		companySector = companySector.toLowerCase().trim();
		languageName = request.getParameter("companyLanguages");
			if (languageName == null) {
			languageName = "JAVA";
			}
		companyProjects = request.getParameter("companyProjects");
		companyWebSite = request.getParameter("companyWebSite");
		contactName = request.getParameter("contactName");
		contactPhone = request.getParameter("contactPhone");
		contactMail = request.getParameter("contactMail");

		try {
			companyDepartment = DataBaseCompany.getDepartmentId(departmentName);
			companyRegion = DataBaseCompany.getRegionId(regionName);
			companyLanguage = DataBaseCompany.getLanguageId(languageName);
			// Récupération du dernier id de la base de données
			idContact = DataBaseCompany.getIdMax("contact");
			// Création de l'objet Contact
			contact = new Contact(idContact, contactName, contactPhone, contactMail);
			// Récupération du dernier id de la base de données +1
			idCompany = DataBaseCompany.getIdMax("company") + 1;
			// Création objet et insertion BDD
			company = new Company(idCompany, companyName, companyAdress, companyPostalCode, companyCity,
					companyDepartment, companyRegion, companySize, companySector, companyLanguage, companyProjects,
					companyWebSite, contact);
			DataBaseCompany.insertCompanyData(company, contact);
		} catch (SQLException e2) {
			System.out.println("-------PROBLEME--------");
			e2.printStackTrace();
		}
		request.setAttribute("company", company);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/company/companyCreate.jsp");
		dispatcher.forward(request, response);

	}

}
