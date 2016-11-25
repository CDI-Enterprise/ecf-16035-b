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
import fr.cdiEnterprise.service.Departments;
import fr.cdiEnterprise.service.Languages;
import fr.cdiEnterprise.service.Regions;


/**
 * Servlet implementation class CompanyServlet
 */
@WebServlet(name = "Creation", urlPatterns = { "/action/Creation/*" })

public class CompanyCreationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
		
	private Departments departments;
	private Regions regions;
	private Languages languages;
	
	public void init() {
		DBConnection.getConnect();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--- in the doGet method ---");
		try {
			departments = DataBaseCompany.getDepartmentListData();
			regions = DataBaseCompany.getRegionsListData();
			languages = DataBaseCompany.getLanguagesListData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("departments", departments);
		request.setAttribute("regions", regions);
		request.setAttribute("languages", languages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/company/companyCreation.jsp");
		dispatcher.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("----- in the post method-----");
	}
}
