package fr.cdiEnterprise.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@WebServlet("/Company/Creation/ok")
public class CompanyCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Attributs récupérés du formulaire pour la création d'une fiche entreprise
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
    
    //Attributs pour la création d'une fiche entreprise
    private Contact contact;
    private Company company;
    private int idCompany;
    private int idContact;
    private Department companyDepartment;
    private Region companyRegion;
    private Language companyLanguage;
    
    
    
    //Méthode init pour initialiser la base de données
    public void init() {
		DBConnection.getConnect();
	}

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Récupération des données du formulaire
		companyName = request.getParameter("companyName");
		companyAdress = request.getParameter("companyAdress");
		companyCity = request.getParameter("companyCity");
		companyPostalCode = request.getParameter("companyPostalCode");
		departmentName = request.getParameter("companyDepartment");
		regionName = request.getParameter("companyRegion");
		companySize = request.getParameter("companySize");
		companySector = request.getParameter("companySector");
		languageName = request.getParameter("companyLanguages");
		companyProjects = request.getParameter("companyProjects");
		companyWebSite = request.getParameter("companyWebSite");
		contactName = request.getParameter("contactName");
		contactPhone = request.getParameter("contactPhone");
		contactMail = request.getParameter("contactMail");
		
		//Récupération objet department
		try {
			companyDepartment = DataBaseCompany.getDepartmentId(departmentName);
			System.out.println(companyDepartment);
			companyRegion = DataBaseCompany.getRegionId(regionName);
			companyLanguage = DataBaseCompany.getLanguageId(languageName);
			System.out.println(languageName);
		//Création d'un objet contact
			//Récupération du dernier id de la base de données
				idContact = DataBaseCompany.getIdMax("contact");
			//Création de l'objet 
				contact = new Contact(idContact, contactName, contactPhone, contactMail);
				
		//Création d'un objet company
			//Récupération du dernier id de la base de données +1
			idCompany = DataBaseCompany.getIdMax("company") + 1;
			//Création objet et insertion BDD
			company = new Company(idCompany, companyName, companyAdress, companyPostalCode, companyCity,
				companyDepartment, companyRegion, companySize, companySector, companyLanguage, companyProjects,
				companyWebSite, contact);
		System.out.println(company);
		DataBaseCompany.insertCompanyData(company, contact);
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			System.out.println("-------PROBLEME--------");
			e2.printStackTrace();
		}
		
		response.sendRedirect( request.getContextPath() + "/jsp/accueil.jsp" );
	}

}
