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
 * Servlet implementation class CompanyUpdate
 */
@WebServlet(name = "Modifier", urlPatterns = { "/action/Modifier" })

public class CompanyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private String companyName;
	private Company company;
    
	/**
	 * Permet d'initialiser la base de données
	 * 
	 */
	public void init() {
		DBConnection.getConnect();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("----- in the post method ----");
			System.out.println(request.getParameter("companiesSelec"));
			companyName = request.getParameter("companiesSelec");
			try {
				company = DataBaseCompany.getCompaniesId(companyName);
				System.out.println(company);
				request.setAttribute("company", company);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("company", company);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/company/companyUpdate.jsp");
			dispatcher.forward(request, response);
		}


}
