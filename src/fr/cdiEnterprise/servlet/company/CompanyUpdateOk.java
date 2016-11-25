package fr.cdiEnterprise.servlet.company;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cdiEnterprise.dao.DataBaseCompany;
import fr.cdiEnterprise.model.Company;

/**
 * Servlet implementation class CompanyUpdateOk
 */
@WebServlet(name = "FicheModifiee", urlPatterns = { "/action/FicheModifiee" })

public class CompanyUpdateOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String companyName;
	private String companyAdress; 
    private Company company;
    private int idCompany;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("----- in the get method----");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("----in the post method ----");
		companyName = request.getParameter("companyName");
		System.out.println(companyName);
		try {
			company = DataBaseCompany.getCompaniesId(companyName);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		idCompany = company.getCompanyId();
		companyAdress = request.getParameter("companyAdress");
		try {
			DataBaseCompany.updateCompanyData(idCompany, companyAdress);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("company", company);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/company/companyUpdateOk.jsp");
		dispatcher.forward(request, response);
	}

}
