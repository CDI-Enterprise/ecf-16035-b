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
import fr.cdiEnterprise.model.Company;

/**
 * Servlet implementation class CompanyDelete
 */
@WebServlet(name = "Suppression", urlPatterns = { "/action/Supprimer" })

public class CompanyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String companyName;
	private Company company;
	private int idCompany;

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
		System.out.println("---- In the get method -----");
		response.sendRedirect(request.getContextPath() + "/jsp/accueil.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("----- in the post method ----");
		System.out.println(request.getParameter("companiesSelec"));
		companyName = request.getParameter("companiesSelec");
		try {
			company = DataBaseCompany.getCompaniesId(companyName);
			System.out.println(company);
			idCompany = company.getCompanyId();
			DataBaseCompany.deleteCompanyData(idCompany);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/company/companyDeleteOk.jsp");
		dispatcher.forward(request, response);
	}

}
