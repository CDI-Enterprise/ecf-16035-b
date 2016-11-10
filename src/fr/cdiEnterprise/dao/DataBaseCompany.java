package fr.cdiEnterprise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.model.Contact;
import fr.cdiEnterprise.model.Department;
import fr.cdiEnterprise.model.Language;
import fr.cdiEnterprise.model.Region;
import fr.cdiEnterprise.service.Companies;
import fr.cdiEnterprise.service.Departments;
import fr.cdiEnterprise.service.Languages;
import fr.cdiEnterprise.service.Regions;

public class DataBaseCompany {

	/**
	 * Méthode pour insérer un départment dans la base de données Utiliser
	 * uniquement par le développeur de l'application
	 * 
	 * @author Anaïs
	 * @version : 21/10/2016
	 * @param department
	 * @throws SQLException
	 */
	public static void insertDepartmentData(Department department) throws SQLException {
		// Statement stmt = null;
		Connection connexion = null;
		// String reqSql = null;
		int res;

		connexion = DBConnection.getConnect();
		// stmt = connexion.createStatement();
		//
		// reqSql = "insert into departments values(" +
		// department.getDepartmentNumber() + ",'"
		// + department.getDepartmentName() + "')";
		// System.out.println(reqSql);
		// stmt.executeUpdate(reqSql);
		// connexion.commit();
		// stmt.close();

		PreparedStatement insertDepartment = connexion.prepareStatement("insert into departments values (?,?)");

		insertDepartment.setInt(1, department.getDepartmentNumber());
		insertDepartment.setString(2, department.getDepartmentName());

		res = insertDepartment.executeUpdate();
		System.out.println(res);
		connexion.commit();
		// stmt.close();

	}

	/**
	 * Méthode qui permet de fournir la liste des départments de la base de
	 * données
	 *
	 * @author Anaïs
	 * @version: 21/10/2016
	 * @return: departments
	 * @throws SQLException
	 */

	public static Departments getDepartmentListData() throws SQLException {
		Departments departments = new Departments();
		ResultSet rs = null;
		Statement stmt = null;
		Connection connexion = null;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		rs = stmt.executeQuery("select departmentName from departments order by departmentNumber");
		while (rs.next()) {
			String departmentName = rs.getString("departmentName");
			departments.add(new Department(departmentName));
		}
		stmt.close();
		rs.close();

		return departments;
	}

	/**
	 * Méthode qui permet de supprimer un départment de la base de données
	 * Utilisée uniquement par le développeur de l'application
	 *
	 * @author Anaïs
	 * @version: 21/10/2016
	 * @param :
	 *            Department
	 * @throws SQLException
	 */
	public static void deleteDepartmentData(Department department) throws SQLException {

		Statement stmt = null;
		Connection connexion = null;

		String reqSql;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		reqSql = "delete from departments where idTheme = ?";

		PreparedStatement deleteDepartment = connexion.prepareStatement(reqSql);
		deleteDepartment.setInt(1, department.getDepartmentNumber());

		deleteDepartment.executeUpdate();

		connexion.commit();
		stmt.close();
	}

	/**
	 * Méthode permettant de retourner un départment à partir de son numéro
	 * 
	 * @author Anaïs
	 * @version: 21/10/2016
	 * @param departmentNumber
	 * @return Department
	 * @throws SQLException
	 */
	public static Department getDepartmentName(int departmentNumber) throws SQLException {

		Department department = null;
		ResultSet rs = null;
		Statement stmt = null;
		Connection connexion = null;
		String reqSql = null;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		reqSql = "select departmentNumber, departmentName from departments where departmentNumber = ?";
		PreparedStatement getDepartmentName = connexion.prepareStatement(reqSql);
		getDepartmentName.setInt(1, departmentNumber);

		rs = getDepartmentName.executeQuery();
		// System.out.println("modifier");
		while (rs.next()) {
			String name = rs.getString("departmentName");
			department = new Department(name, departmentNumber);
		}

		stmt.close();

		return department;
	}

	/**
	 * Méthode permettant de retourner un départment à partir de son nom
	 * 
	 * @author Anaïs
	 * @version: 21/10/2016
	 * @param departmentName
	 * @return Department
	 * @throws SQLException
	 */
	public static Department getDepartmentId(String departmentName) throws SQLException {

		Department department = null;
		Statement stmt = null;
		Connection connexion = null;
		String reqSql = null;
		ResultSet res;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		reqSql = "select departmentNumber, departmentName from departments where departmentName = ?";
		PreparedStatement getDepartmentId = connexion.prepareStatement(reqSql);
		// System.out.println(reqSql);
		getDepartmentId.setString(1, departmentName);

		res = getDepartmentId.executeQuery();
		// System.out.println("modifier");
		while (res.next()) {
			int number = res.getInt("departmentNumber");
			department = new Department(departmentName, number);
		}
		stmt.close();

		return department;
	}

	/**
	 * Méthode permettant d'insérer une nouvelle région dans la base de données
	 * Utilisée uniquement par le développeur de l'application
	 * 
	 * @author Anaïs
	 * @version: 21/10/2016
	 * @param region
	 * @throws SQLException
	 */
	public static void insertRegionData(Region region) throws SQLException {
		Statement stmt = null;
		Connection connexion = null;
		String reqSql = null;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		reqSql = "insert into regions values('" + region.getRegionName() + "'," + region.getCodeRegion() + ")";
		System.out.println(reqSql);
		stmt.executeUpdate(reqSql);

		connexion.commit();
		stmt.close();

	}

	/**
	 * Méthode permettant de renvoyer la liste des régions de la base de données
	 * 
	 * @author Anaïs
	 * @version: 21/10/2016
	 * @return Regions
	 * @throws SQLException
	 */
	public static Regions getRegionsListData() throws SQLException {

		Regions regions = new Regions();
		Connection connexion = null;
		ResultSet rs = null;
		Statement stmt = null;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();
		rs = stmt.executeQuery("select regionName from regions order by regionId");
		while (rs.next()) {
			String regionName = rs.getString("regionName");
			regions.add(new Region(regionName));
			// System.out.println("Resultat select : " + nomEditeur);
		}

		stmt.close();
		rs.close();
		return regions;
	}

	/**
	 * 
	 * Méthode permettant de retourner une région en fonction de son numéro
	 * 
	 * @author : Anaïs
	 * @version: 21/10/2016
	 * @param: int
	 * @return: Region
	 * 
	 */
	public static Region getRegionName(int regionId) throws SQLException {

		Region region = null;
		ResultSet rs = null;
		Statement stmt = null;
		Connection connexion = null;
		String reqSql = null;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		reqSql = "select regionId, regionName from regions where regionId = ?";
		PreparedStatement getRegionName = connexion.prepareStatement(reqSql);
		getRegionName.setInt(1, regionId);

		rs = getRegionName.executeQuery();
		// System.out.println("modifier");
		while (rs.next()) {
			String name = rs.getString("regionName");
			region = new Region(name, regionId);
		}

		stmt.close();

		return region;
	}

	/**
	 * 
	 * Méthode permettant de fournir le numéro de région correspondant à la
	 * région portée en paramètre
	 * 
	 * @author : Anaïs
	 * @version: 21/10/2016
	 * @return Region
	 * @param: String
	 * 
	 */

	public static Region getRegionId(String regionName) throws SQLException {

		Region region = null;
		Statement stmt = null;
		Connection connexion = null;
		String reqSql = null;
		ResultSet res;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		reqSql = "select regionId, regionName from regions where regionName = ?";
		PreparedStatement getRegionId = connexion.prepareStatement(reqSql);
		// System.out.println(reqSql);
		getRegionId.setString(1, regionName);

		res = getRegionId.executeQuery();
		// System.out.println("modifier");
		while (res.next()) {
			int number = res.getInt("regionId");
			region = new Region(regionName, number);
		}
		stmt.close();

		return region;
	}

	/**
	 * Méthode permettant d'insérer un nouveau langage informatique dans la base
	 * de données
	 * 
	 * @author Anaïs
	 * @version 21/10/2016
	 * @param language
	 * @throws SQLException
	 */

	public static void insertLanguageData(String languageName) throws SQLException {
		Connection connexion = null;
		Statement stmt = null;
		int res;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		PreparedStatement insertLanguage = connexion.prepareStatement("insert into language values (?,?)");
		insertLanguage.setInt(1, DataBaseCompany.getIdMax("language"));
		insertLanguage.setString(2, languageName);

		res = insertLanguage.executeUpdate();
		System.out.println(res);

		connexion.commit();
		stmt.close();
	}

	/**
	 * Méthode permettant de retourner la liste des langages informatiques de la
	 * base de données
	 * 
	 * @author Anaïs
	 * @version 21/10/2016
	 * @return Languages
	 * @throws SQLException
	 */
	public static Languages getLanguagesListData() throws SQLException {

		Languages languages = new Languages();
		Connection connexion = null;
		ResultSet rs = null;
		Statement stmt = null;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();
		rs = stmt.executeQuery("select languageName from language order by languageId");
		while (rs.next()) {
			String languageName = rs.getString("languageName");
			languages.add(new Language(languageName));
			// System.out.println("Resultat select : " + nomEditeur);
		}

		stmt.close();
		rs.close();
		return languages;
	}

	/**
	 * Méthode permettant de retourner le langage à partir de son nom
	 * 
	 * @author Anaïs
	 * @version 21/10/2016
	 * @param languageSelect
	 * @return Language
	 * @throws SQLException
	 */
	public static Language getLanguageId(String languageSelect) throws SQLException {
		Language language = null;
		Statement stmt = null;
		Connection connexion = null;
		String reqSql = null;
		ResultSet res;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		reqSql = "select languageId, languageName from language where languageName = ?";
		PreparedStatement getLanguageId = connexion.prepareStatement(reqSql);
		// System.out.println(reqSql);
		getLanguageId.setString(1, languageSelect);

		res = getLanguageId.executeQuery();
		// System.out.println("modifier");
		while (res.next()) {
			int number = res.getInt("languageId");
			language = new Language(languageSelect, number);
		}
		stmt.close();

		return language;

	}

	/**
	 * Méthode permettant de retourner la liste des entreprises de la base de
	 * données
	 * 
	 * @author Anaïs
	 * @version 25/10/2016
	 * @return Companies
	 * @throws SQLException
	 */

	public static Companies getCompaniesData() throws SQLException {

		Companies companies = new Companies();
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		rs = stmt.executeQuery("select company.companyId, companyName,companyAdress, companyCODEPOSTAL, companyCity, "
				+ "companySize , companySector , companyProjects , companyWeb, departmentname, regionName, languagename "
				+ "from company, language, departments, regions, companydepartment, companyregion, companylanguage "
				+ "where company.companyId = companyregion.companyId "
				+ "and company.companyId = companydepartment.companyId "
				+ "and company.companyId = companyLanguage.companyId "
				+ "and departments.departmentNumber = companydepartment.departmentNumber "
				+ "and regions.regionId = companyregion.regionId "
				+ "and language.languageId = companylanguage.LANGUAGEID");

		while (rs.next()) {
			int companyId = rs.getInt("companyId");
			String companyName = rs.getString("companyName");
			String companyAdress = rs.getString("companyAdress");
			String postalcode = rs.getString("companyCodepostal");
			String city = rs.getString("companyCity");
			String departmentN = rs.getString("departmentName");
			String regionN = rs.getString("regionName");

			Department department = DataBaseCompany.getDepartmentId(departmentN);
			Region region = DataBaseCompany.getRegionId(regionN);

			companies.add(new Company(companyId, companyName, companyAdress, postalcode, city, department, region));

		}
		stmt.close();
		return companies;
	}

	/**
	 * Méthode permettant de créer une nouvelle entreprise dans la base de
	 * données
	 * 
	 * @author Anaïs
	 * @version 24/10/2016
	 * @param company
	 * @throws SQLException
	 */
	public static void insertCompanyData(Company company, Contact contact) throws SQLException {

		Connection connexion = null;
		Statement stmt = null;
		// int companyId = DataBaseCompany.getIdMax("company");
		String reqSqla;
		String reqSqlb;
		String reqSqlc;
		String reqSqld;
		String reqSqle;
		String reqSqlf;
		int rsa;
		int rsb;
		int rsc;
		int rsd;
		int rse;
		int rsf;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		reqSqla = "insert into company values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertCompany = connexion.prepareStatement(reqSqla);

		insertCompany.setInt(1, company.getCompanyId());
		insertCompany.setString(2, company.getCompanyName());
		insertCompany.setString(3, company.getAdress());
		insertCompany.setString(4, company.getPostalCode());
		insertCompany.setString(5, company.getCity());
		insertCompany.setString(6, company.getSize());
		insertCompany.setString(7, company.getSector());
		insertCompany.setString(8, company.getProjets());
		insertCompany.setString(9, company.getWebSite());

		reqSqlb = "insert into companyregion values (?,?)";
		PreparedStatement insertCompanyRegion = connexion.prepareStatement(reqSqlb);
		insertCompanyRegion.setInt(1, company.getCompanyId());
		insertCompanyRegion.setInt(2, company.getRegion().getCodeRegion());

		reqSqlc = "insert into companydepartment values (?,?)";
		PreparedStatement insertCompanyDepartment = connexion.prepareStatement(reqSqlc);
		insertCompanyDepartment.setInt(1, company.getCompanyId());
		insertCompanyDepartment.setInt(2, company.getDepartment().getDepartmentNumber());

		reqSqld = "insert into companylanguage values (?,?)";
		PreparedStatement insertCompanyLanguage = connexion.prepareStatement(reqSqld);
		insertCompanyLanguage.setInt(1, company.getCompanyId());
		insertCompanyLanguage.setInt(2, company.getLanguage().getId());

		reqSqle = "insert into contact values (?,?,?,?)";
		PreparedStatement insertContact = connexion.prepareStatement(reqSqle);
		insertContact.setInt(1, contact.getIdContact());
		insertContact.setString(2, contact.getName());
		insertContact.setString(3, contact.getPhoneNumber());
		insertContact.setString(4, contact.getEmail());

		reqSqlf = "insert into companyContact values (?,?)";
		PreparedStatement insertCompanyContact = connexion.prepareStatement(reqSqlf);
		insertCompanyContact.setInt(1, company.getCompanyId());
		insertCompanyContact.setInt(2, contact.getIdContact());

		rsa = insertCompany.executeUpdate();
		rsb = insertCompanyRegion.executeUpdate();
		rsc = insertCompanyDepartment.executeUpdate();
		rsd = insertCompanyLanguage.executeUpdate();
		rse = insertContact.executeUpdate();
		rsf = insertContact.executeUpdate();

		stmt.close();

		System.out.println("rsa" + rsa + "rsb" + rsb + "rsc" + rsc + "rsd" + rsd + "rse" + rse + "rsf" + rsf);

		connexion.commit();
		stmt.close();

	}

	/**
	 * Méthode permettant de supprimer une entreprise de la base de données
	 * 
	 * @author Anaïs
	 * @version 25/10/2016
	 * @param company
	 * @throws SQLException
	 */

	public static void deleteCompanyData(int selecId) throws SQLException {
		Connection connexion = null;
		Statement stmt = null;
		String reqSqla;
		String reqSqlb;
		String reqSqlc;
		String reqSqld;
		int rsa;
		int rsb;
		int rsc;
		int rsd;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		reqSqla = "delete from company where companyId = ?";
		PreparedStatement deleteCompany = connexion.prepareStatement(reqSqla);
		deleteCompany.setInt(1, selecId);

		reqSqlb = "delete from companyregion where companyId = ?";
		PreparedStatement deleteCompanyRegion = connexion.prepareStatement(reqSqlb);
		deleteCompanyRegion.setInt(1, selecId);

		reqSqlc = "delete from companydepartment where companyId = ?";
		PreparedStatement deleteCompanyDepartment = connexion.prepareStatement(reqSqlc);
		deleteCompanyDepartment.setInt(1, selecId);

		reqSqld = "delete from companylanguage where companyId = ?";
		PreparedStatement deleteCompanyLanguage = connexion.prepareStatement(reqSqld);
		deleteCompanyLanguage.setInt(1, selecId);

		rsa = deleteCompany.executeUpdate();
		rsb = deleteCompanyRegion.executeUpdate();
		rsc = deleteCompanyDepartment.executeUpdate();
		rsd = deleteCompanyLanguage.executeUpdate();

		System.out.println("rsa" + rsa + "rsb" + rsb + "rsc" + rsc + "rsd" + rsd);

		connexion.commit();
		stmt.close();

	}

	/**
	 * Méthode permettant de modifier une entreprise contenue dans la base de
	 * données (pour le moment uniquement l'adresse)
	 * 
	 * @author Anaïs
	 * @version 25/10/2016
	 * @param company
	 * @param newAdress
	 * @throws SQLException
	 */
	public static void updateCompanyData(int selecId, String newAdress) throws SQLException {
		Connection connexion = null;
		Statement stmt = null;
		String reqSql = null;
		int rs;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		reqSql = "UPDATE company set companyAdress = ? where companyId = ?";

		PreparedStatement updateCompany = connexion.prepareStatement(reqSql);
		updateCompany.setString(1, newAdress);
		updateCompany.setInt(2, selecId);
		// System.out.println(rqSql);

		rs = updateCompany.executeUpdate();
		System.out.println(rs);

		stmt.close();
	}

	public static Company getCompaniesId(String companyNameSel) throws SQLException {
		Company company = null;
		Statement stmt = null;
		Connection connexion = null;
		String reqSql = null;
		ResultSet rs;

		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		reqSql = "select company.companyId, companyName,companyAdress, companyCODEPOSTAL, companyCity, "
				+ "companySize , companySector , companyProjects , companyWeb, departmentname, regionName, languagename "
				+ "from company, language, departments, regions, companydepartment, companyregion, companylanguage "
				+ "where companyName = ?" + "and company.companyId = companyregion.companyId "
				+ "and company.companyId = companydepartment.companyId "
				+ "and company.companyId = companyLanguage.companyId "
				+ "and departments.departmentNumber = companydepartment.departmentNumber "
				+ "and regions.regionId = companyregion.regionId "
				+ "and language.languageId = companylanguage.LANGUAGEID";

		PreparedStatement getCompaniesId = connexion.prepareStatement(reqSql);
		// System.out.println(reqSql);
		getCompaniesId.setString(1, companyNameSel);

		rs = getCompaniesId.executeQuery();
		// System.out.println("modifier");
		while (rs.next()) {
			int companyId = rs.getInt("companyId");
			String companyName = rs.getString("companyName");
			String companyAdress = rs.getString("companyAdress");
			String postalcode = rs.getString("companyCodepostal");
			String city = rs.getString("companyCity");
			String departmentN = rs.getString("departmentName");
			String regionN = rs.getString("regionName");

			Department department = DataBaseCompany.getDepartmentId(departmentN);
			Region region = DataBaseCompany.getRegionId(regionN);

			company = new Company(companyId, companyName, companyAdress, postalcode, city, department, region);
		}
		stmt.close();

		return company;
	}

	/**
	 * Méthode permettant de fournir l'id max d'une table de la base de données
	 * fournie en paramètre
	 * 
	 * @author Anaïs
	 * @version 25/10/2016
	 * @param table
	 * @return int
	 * @throws SQLException
	 */
	public static int getIdMax(String table) throws SQLException {
		Connection connexion;
		Statement stmt;
		int idMax = 0;
		connexion = DBConnection.getConnect();
		stmt = connexion.createStatement();

		ResultSet rsMax = stmt.executeQuery("select max (" + table + "id) from " + table);

		while (rsMax.next())
			idMax = rsMax.getInt(1);

		return idMax;
	}

}
