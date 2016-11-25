package fr.cdiEnterprise.servlet.company;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.cdiEnterprise.dao.DBConnection;
import fr.cdiEnterprise.dao.DataBaseCompany;
import fr.cdiEnterprise.service.Companies;

/**
 * Servlet implementation class CompanySearchList
 */
@WebServlet(name = "Affichage", urlPatterns = { "/action/Affichage" })

public class CompanyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Companies companies;

	/**
	 * Permet d'initialiser la base de données
	 * 
	 */
	public void init() {
		DBConnection.getConnect();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("--- in the get method----");
		response.sendRedirect(request.getContextPath() + "/jsp/accueil.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("----Dans method post -----");
		try {
			companies = DataBaseCompany.getCompaniesData();
			System.out.println(companies);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("companies", companies);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/company/companyList.jsp");
		dispatcher.forward(request, response);
	}

}
