package fr.cdiEnterprise.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.cdiEnterprise.dao.DataBaseCompany;
import fr.cdiEnterprise.service.Companies;

/**
 * Servlet implementation class CompanyUpdateServlet
 */
@WebServlet("/action/ModifSuppr")
public class CompanyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private Companies companies;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("--- in the doPost method ---");
//		try {
//			companies = DataBaseCompany.getCompaniesData();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		request.setAttribute("companies", companies);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/CompanyUpdateDelete.jsp");
		dispatcher.forward(request, response);
//		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("--- in the doPost method ---");
//		try {
//			companies = DataBaseCompany.getCompaniesData();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		request.setAttribute("companies", companies);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/CompanyUpdateDelete.jsp");
		dispatcher.forward(request, response);
	}

}
