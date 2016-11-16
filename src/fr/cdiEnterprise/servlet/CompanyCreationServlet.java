package fr.cdiEnterprise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CompanyCreationServlet
 */
@WebServlet("/Company/Creation/ok")
public class CompanyCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Attributs 
	private String companyName;
    private String companyAdress;
    private String companyCity;
    private String companyPostalCode;
    private String companyDepartment;
    private String companyRegion;
    private String companySize;
    private String companySector;
    private String companyLanguages;
    private String companyProjects;
    private String companyWebSite;
    private String contactName;
    private String contactPhone;
    private String contactMail;
     
     

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
		companyDepartment = request.getParameter("companyDepartment");
		companyRegion = request.getParameter("companyRegion");
		companySize = request.getParameter("companySize");
		companySector = request.getParameter("companySector");
		companyLanguages = request.getParameter("companyLanguages");
		companyProjects = request.getParameter("companyProjects");
		companyWebSite = request.getParameter("companyWebSite");
		contactName = request.getParameter("contactName");
		contactPhone = request.getParameter("contactPhone");
		contactMail = request.getParameter("contactMail");
		

	}

}
