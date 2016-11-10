package fr.cdiEnterprise.dao.test;

import java.sql.SQLException;

import fr.cdiEnterprise.dao.FavoriteDao;
import fr.cdiEnterprise.model.Favorite;
import fr.cdiEnterprise.service.Favorites;
import fr.cdiEnterprise.view.company.CompaniesSRPanel;

public class testFav {
//
//	private int idFavorite;
//	private String companyName;
//	private String companyCity;
//	private String companySize;
//	private String companySector;
//	private String companyWebSite;
//	private String contactMail;	
//	private String noteCompany;
//	private FavoriteDao favoriteDao;
//	private CompaniesSRPanel panCompaniesSR;

	public static void main(String[] args) throws SQLException 
{
		
	Favorites favorites = new Favorites();
	
	favorites = FavoriteDao.getMyFavorite();
	System.out.println(favorites);
	
//	if (e.getSource() == panCompaniesSR.getBtnFavoris())	
//	{
//		FavoriteDao favoriteDao = new FavoriteDao();
//		try
//		{
//
//			idFavorite		= FavoriteDao.getIdMax("favorite") +1;
//			companyName		= panCompaniesSR.getTxtCompanyCity().getText();
//			companyCity		= panCompaniesSR.getTxtCompanyCity().getText();
//			companySize		= panCompaniesSR.getLblSize().getText();	
//			companySector	= panCompaniesSR.getTxtSector().getText();
//			companyWebSite	= panCompaniesSR.getTxtWebSite().getText();
//			contactMail		= panCompaniesSR.getTxtContactMail().getText();
//
//			//Create a favorite's object
//			favoriteCompany = new Favorite(idFavorite, companyName, companyCity, companySize, companySector, companyWebSite, contactMail, noteCompany);
//			System.out.println(favoriteCompany);
//
//			//Send the add
//			favoriteDao.addFavorite(favoriteCompany);
//			System.out.println("hey" + favoriteDao);
//		}
//		catch (SQLException e1)
//		{
//			e1.printStackTrace();
//		}		
//	}
	
		// TODO Auto-generated method stub

	}

}
