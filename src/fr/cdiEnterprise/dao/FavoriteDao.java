/**
 * 
 */
package fr.cdiEnterprise.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.model.Favorite;
import fr.cdiEnterprise.service.Favorites;
//import fr.cdiEnterprise.view.company.CompaniesSRPanel;
//import fr.cdiEnterprise.view.BookMarkPanel;
//import fr.cdiEnterprise.model.NoteCompany;
//import fr.cdiEnterprise.service.Favorites;
//import fr.cdiEnterprise.view.CompanyCreationPanel;


/**
 *20 oct. 2016
 * @author Ismael
 *ecf-16035-a
 *22:19:29
 */

public class FavoriteDao 
{
	private static Connection conn = DBConnection.getConnect();
	private static Statement stmt;
	private static PreparedStatement prStmt;
	private static PreparedStatement createFavorite;
	//	private static PreparedStatement createNote;
	private JComboBox <String> lstMyFavorites;
	//	private int rsNote;
	private static int rsFav;

	/**
	 * Create idMax for BDD
	 * @param favorite
	 * @return idMax
	 * @throws SQLException 
	 * 
	 */
	public static int getIdMax(String table) throws SQLException
	{
		Statement stmt = conn.createStatement();

		ResultSet rsMax = stmt.executeQuery("select max (" + table + "_id) from " + table);
		int idMax = 0;

		while (rsMax.next())
			idMax = rsMax.getInt(1);
		return idMax;
	}


	/**
	 * Exemple add favorite
	 * @param favorite
	 * 
	 */
	//public static String addFavorite (int id, String companyName, String companyCity, String companySize, String companySector, String companyWebSite,String companyContactMail, String noteUser)
	public static String addFavorite(Company newFav)
	{
		String createFav = null;

		String companyName 	= newFav.getCompanyName();
		String companyCity 	= newFav.getCity();
		String companySize 	= newFav.getCity();
		String companySector = newFav.getSector();
		String companyWebSite = newFav.getWebSite();
		String companyContactMail = newFav.getContact().getEmail();

		FavoriteDao newFavDAO = new FavoriteDao();

		int resultFav = newFavDAO.createFavorite( companyName, companyCity, companySize, companySector, companyWebSite, companyContactMail);

		//Validité
		if (resultFav == 0)
		{
			createFav = "Echec de création";
		}
		else 
		{
			createFav = resultFav + "favoris créer";	
		}
		return createFav;
	}


	protected int createFavorite(String companyName ,String companyCity, String companySize, String companySector,
			String companyWebSite, String companyContactMail) 
	{
		rsFav = 0 ;
		try
		{
			createFavorite = conn.prepareStatement("insert into favorite (?,?,?,?,?,?) select companyName, city, size, sector, webSite, contactMAil from company");
			createFavorite.setString(2, companyName);
			createFavorite.setString(3, companyCity);
			createFavorite.setString(4, companySize);
			createFavorite.setString(5, companySector);
			createFavorite.setString(6, companyWebSite);
			createFavorite.setString(7, companyContactMail);
			rsFav  = createFavorite.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return rsFav;
	}


	/**
	 * Exemple update note favorite
	 * @param note reference to Company
	 * 
	 */

	public void noteFavorite (String noteUser) throws SQLException
	{
		String reqSql = null;
		int rs;

		reqSql = "UPDATE favorite set noteUser = ? where favoriteId = ?";

		prStmt = conn.prepareStatement(reqSql);
		prStmt.setString(1, noteUser);
		//updateFavorite.

		rs = prStmt.executeUpdate();
		System.out.println(rs);

		prStmt.close();

	}


	/**
	 * Exemple delete favorite
	 * @param companyName reference to Company
	 * 
	 */

	public String deleteFavorite (String companyName)
	{
		try 
		{
			stmt.executeUpdate("delete favorite where companyName = " + companyName);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return companyName;
	}

	/**
	 * Exemple load jcomboxfavorite to lstfavorite
	 * @param companyName reference to Company
	 * @throws SQLException 
	 * 
	 */

	public static Favorites getMyFavorite () throws SQLException				//load the companyName
	{
		Favorite favCompany;
		Favorites favList		= new Favorites();
		String companyName;
		String reqSql			= null;
		ResultSet rsMyFavorite	= null;
		reqSql					= "select companyName from favorite order by companyName";

		try
		{
			prStmt = conn.prepareStatement(reqSql);    
			rsMyFavorite = prStmt.executeQuery()	;
			while (rsMyFavorite.next())
			{
				companyName = rsMyFavorite.getString(1);
				//System.out.println("cn" + companyName);
				favCompany = new Favorite();
				favCompany.setCompanyName(companyName);
				System.out.println("fc" + favCompany);
				favList.add(favCompany);
				//.add("companyName");
				System.out.println("CompanyName select : " + favList);
			}
			//	prStmt.close();
			//rsMyFavorite.close();
			System.out.println("f2" + favList);
		}
		catch (SQLException e)
		{
			System.out.println("Favorite : erreur myFavorite()");
			e.printStackTrace();
		}
		prStmt.close();
		return favList;	
	}

	//To  display the result

	public Favorite lstFavorite(String companyName)
	{
		Favorite favorite = new Favorite();

		String reqSql	= null;
		reqSql			= "select companycity , companysize, companysector, companywebSite, noteuser from favorite where = ?";
		ResultSet rs	= null;

		try
		{
			PreparedStatement lstFavorite = conn.prepareStatement(reqSql);
			lstFavorite.setString(1, companyName); 							//load the data by companyName
			rs = lstFavorite.executeQuery(reqSql)	;	
		}
		catch (SQLException e)
		{
			System.out.println("Favorite : erreur lstFavorite()");
			e.printStackTrace();
		}
		try
		{
			while (rs.next())
			{
				String city				= rs.getString("city");
				String size 			= rs.getString("size");
				String sector 			= rs.getString("sector");
				String webSite			= rs.getString("webSite");
				String contactMail 		= rs.getString("contactMail");
				String noteUser			= rs.getString("noteUser");

				//				//Search if note is null
				//				NoteCompany note = null;
				//
				//				//New statement for this 
				//				Statement stmtNote 	= conn.createStatement();
				//				String sqlNote		= "select lblNote from noteUser where idNote = " + idNoteUser ; 
				//				ResultSet rsNoteUser = stmtNote.executeQuery(sqlNote);
				//				while (rsNoteUser.next())
				//				{
				//					String lblNote = rsNoteUser.getString(1);
				//					note = new NoteCompany(lblNote);
				//				}

				//Create object
				favorite = new Favorite(city, size, sector, webSite, contactMail, noteUser);
				stmt.close();
			}
		}catch (SQLException e)
		{
			System.out.println("FavoriteDao : lstFavorite() - erreur sur favori");
			e.printStackTrace();
		}
		return favorite;
	}


	/**
	 * @return the lstMyFavorites
	 */
	public JComboBox<String> getLstMyFavorites() {
		return lstMyFavorites;
	}


	public void noteFavorite(int selectId, String noteUser) throws SQLException 
	{
		String reqSql = null;
		int rs;

		reqSql = "UPDATE favorite set noteUser = ? where favorite_id = ? ";

		prStmt = conn.prepareStatement(reqSql);
		prStmt.setInt(1, selectId);
		prStmt.setString(2, noteUser);
		//updateFavorite.

		rs = prStmt.executeUpdate();
		System.out.println(rs);

		prStmt.close();
	}


	public static int createFavorite(Favorite favoriteCompany) 
	{
		int id = favoriteCompany.getIdFavorite();
		String noteUser = favoriteCompany.getNoteUser();
		String companyName = favoriteCompany.getCompanyName();
		String companyCity = favoriteCompany.getCity();
		String companySize = favoriteCompany.getSize();
		String companySector = favoriteCompany.getSector();
		String companyWebSite = favoriteCompany.getWebSite();
		String companyContactMail = favoriteCompany.getContactMail();
		rsFav = 0 ;

		try
		{
			createFavorite = conn.prepareStatement("insert into favorite values (?,?,?,?,?,?,?,?)");

			createFavorite.setInt(1,id);
			createFavorite.setString(2, companyName);
			createFavorite.setString(3, companyCity);
			createFavorite.setString(4, companySize);
			createFavorite.setString(5, companySector);
			createFavorite.setString(6, companyWebSite);
			createFavorite.setString(7, companyContactMail);
			createFavorite.setString(8, noteUser);
			rsFav  = createFavorite.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return rsFav;	
	}


	public void noteFavorite(Favorite markUser) 
	{
		int selectId = markUser.getIdFavorite();
		String noteUser = markUser.getNoteUser();
		String reqSql = null;
		int rs;

		try
		{
			reqSql = "UPDATE favorite set noteUser = ? where favorite_id = ? ";

			prStmt = conn.prepareStatement(reqSql);
			prStmt.setString(1, noteUser);
			prStmt.setInt(2, selectId);
			//updateFavorite.

			rs = prStmt.executeUpdate();
			System.out.println(rs);

			prStmt.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
	}

}
