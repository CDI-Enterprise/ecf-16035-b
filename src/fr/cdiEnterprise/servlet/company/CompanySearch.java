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

/**
 * Servlet implementation class CompanySearch
 */
@WebServlet(name = "Rechercher", urlPatterns = { "/action/Rechercher" })

public class CompanySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Company company;
	private String companyName;

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
		System.out.println("--------doget------");
		response.sendRedirect(request.getContextPath() + "/jsp/accueil.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("----Dans method post -----");
		companyName = request.getParameter("companyName");
		companyName = companyName.toUpperCase().trim();
		MethodsForListeners.nullField(companyName);
		System.out.println(companyName);
		try {
			company = DataBaseCompany.getCompaniesId(companyName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("company", company);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/company/companySearch.jsp");
		dispatcher.forward(request, response);
	}

}
