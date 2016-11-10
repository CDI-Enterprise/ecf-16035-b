package fr.cdiEnterprise.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cdiEnterprise.dao.DBConnection;
import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.model.IAdvandcedSearch;


public class MetierAdvancedSearch implements IAdvandcedSearch{
	
	List<Company> listCompanies= new ArrayList<Company>();

	

	// REcherche avec un seul mot clé sélectionné
	@Override
	public List<Company> getCompagnyByMCNom(String mcRaisonSociale) {
		//List<Company> listCompanies= new ArrayList<Company>();
		Connection conn =DBConnection.getConnect();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from COMPANY where UPPER(COMPANYNAME) like  UPPER(?)");			
			ps.setString(1, "%"+mcRaisonSociale+"%");
			//ps.executeQuery();
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Company c =new Company();
				c.setIdEnterprise(rs.getInt("COMPANYID"));
				c.setCompanyName(rs.getString("COMPANYNAME"));
				c.setAdress(rs.getString("COMPANYADRESS"));
				c.setPostalCode(rs.getString("COMPANYCODEPOSTAL"));
				c.setCity(rs.getString("COMPANYCITY"));
				c.setSize(rs.getString("COMPANYSIZE"));
				c.setSector(rs.getString("COMPANYSECTOR"));
				c.setProjets(rs.getString("COMPANYPROJECTS"));
				c.setWebSite(rs.getString("COMPANYWEB"));
		
				listCompanies.add(c);
				System.out.println(c.getCompanyName() + "" + c.getCity() + " " + c.getSector());
				System.out.println(listCompanies.isEmpty());
			}
		
			ps.close();
		} 
	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCompanies; 
	}
	
	@Override
	public List<Company> getCompagnyByMCDomaine(String mcDomaine) {
		Connection conn =DBConnection.getConnect();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from COMPANY where UPPER(COMPANYSECTOR) like UPPER(?)");			
			ps.setString(1, "%"+mcDomaine+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Company c =new Company();
				c.setIdEnterprise(rs.getInt("COMPANYID"));
				c.setCompanyName(rs.getString("COMPANYNAME"));
				c.setAdress(rs.getString("COMPANYADRESS"));
				c.setPostalCode(rs.getString("COMPANYCODEPOSTAL"));
				c.setCity(rs.getString("COMPANYCITY"));
				c.setSize(rs.getString("COMPANYSIZE"));
				c.setSector(rs.getString("COMPANYSECTOR"));
				c.setProjets(rs.getString("COMPANYPROJECTS"));
				c.setWebSite(rs.getString("COMPANYWEB"));
				
		
				listCompanies.add(c);
				System.out.println(c.getCompanyName() + "" + c.getCity() + " " + c.getSector());
				System.out.println(listCompanies.isEmpty());
			}
		
			ps.close();
		} 
	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCompanies; 
	}
	@Override
	public List<Company> getCompagnyByMCVille(String mcVille) {
		Connection conn =DBConnection.getConnect();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from COMPANY where UPPER(COMPANYCITY) like UPPER(?)");			
			ps.setString(1, "%"+mcVille+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Company c =new Company();
				c.setIdEnterprise(rs.getInt("COMPANYID"));
				c.setCompanyName(rs.getString("COMPANYNAME"));
				c.setAdress(rs.getString("COMPANYADRESS"));
				c.setPostalCode(rs.getString("COMPANYCODEPOSTAL"));
				c.setCity(rs.getString("COMPANYCITY"));
				c.setSize(rs.getString("COMPANYSIZE"));
				c.setSector(rs.getString("COMPANYSECTOR"));
				c.setProjets(rs.getString("COMPANYPROJECTS"));
				c.setWebSite(rs.getString("COMPANYWEB"));
		
				listCompanies.add(c);
				System.out.println(c.getCompanyName() + "" + c.getCity() + " " + c.getSector());
				System.out.println(listCompanies.isEmpty());
			}
		
			ps.close();
		} 
	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCompanies; 
	}

	@Override
	public List<Company> getCompagnyByMCRegion(String mcRegion) {
		Connection conn =DBConnection.getConnect();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from COMPANY where villeSoc like ?");			
				ps.setString(1, "%"+mcRegion+"%");
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					Company c =new Company();
					c.setIdEnterprise(rs.getInt("COMPANYID"));
					c.setCompanyName(rs.getString("COMPANYNAME"));
					c.setAdress(rs.getString("COMPANYADRESS"));
					c.setPostalCode(rs.getString("COMPANYCODEPOSTAL"));
					//c.setDepartment(rs.getString(columnLabel));
					c.setCity(rs.getString("COMPANYCITY"));
					c.setSize(rs.getString("COMPANYSIZE"));
					c.setSector(rs.getString("COMPANYSECTOR"));
					c.setProjets(rs.getString("COMPANYPROJECTS"));
					c.setWebSite(rs.getString("COMPANYWEB"));
			
					listCompanies.add(c);
					System.out.println(c.getCompanyName() + "" + c.getCity() + " " + c.getSector());
					System.out.println(listCompanies.isEmpty());
				}
			
				ps.close();
			} 
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listCompanies; 
	}

	@Override
	public List<Company> getCompagnyByMC(String mcRaisonSociale, String mcDomaine) {
		
		Connection conn =DBConnection.getConnect();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from COMPANY where COMPANYNAME like (?) or COMPANYSECTOR like (?)");
			ps.setString(1, "%"+mcRaisonSociale+"%");
			ps.setString(2, "%"+mcDomaine+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Company c =new Company();
				c.setIdEnterprise(rs.getInt("COMPANYID"));
				c.setCompanyName(rs.getString("COMPANYNAME"));
				c.setAdress(rs.getString("COMPANYADRESS"));
				c.setPostalCode(rs.getString("COMPANYCODEPOSTAL"));
				c.setCity(rs.getString("COMPANYCITY"));
				c.setSize(rs.getString("COMPANYSIZE"));
				c.setSector(rs.getString("COMPANYSECTOR"));
				c.setProjets(rs.getString("COMPANYPROJECTS"));
				c.setWebSite(rs.getString("COMPANYWEB"));
		
				listCompanies.add(c);
				
			}
		
			ps.close();
		} 
	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCompanies; 
	}

	@Override
	public List<Company> getCompagnyByMC(String mcRaisonSociale, String mcDomaine, String mcVille) {
		Connection conn =DBConnection.getConnect();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from COMPANY where COMPANYNAME like (?) OR COMPANYSECTOR like (?) OR COMPANYCITY like (?)");
			ps.setString(1, "%"+mcRaisonSociale+"%");
			ps.setString(2, "%"+mcDomaine+"%");
			ps.setString(3, "%"+mcVille+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Company c =new Company();
				c.setIdEnterprise(rs.getInt("COMPANYID"));
				c.setCompanyName(rs.getString("COMPANYNAME"));
				c.setAdress(rs.getString("COMPANYADRESS"));
				c.setPostalCode(rs.getString("COMPANYCODEPOSTAL"));
				c.setCity(rs.getString("COMPANYCITY"));
				c.setSize(rs.getString("COMPANYSIZE"));
				c.setSector(rs.getString("COMPANYSECTOR"));
				c.setProjets(rs.getString("COMPANYPROJECTS"));
				c.setWebSite(rs.getString("COMPANYWEB"));
		
				listCompanies.add(c);
				System.out.println(c.getCompanyName() + "" + c.getCity() + " " + c.getSector());
				System.out.println(listCompanies.isEmpty());
			}
		
			ps.close();
		} 
	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCompanies; 
	}

	@Override
	public List<Company> getCompagnyByMC(String mcRaisonSociale, String mcDomaine, String mcVille,
			String mcRegion	) {
		Connection conn =DBConnection.getConnect();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from COMPANY where nomSociete like ?,"
					+ "and domaineSoc like ? and villeSoc like ? and REGION ?");
			ps.setString(1, "%"+mcRaisonSociale+"%");
			ps.setString(2, "%"+mcDomaine+"%");
			ps.setString(3, "%"+mcDomaine+"%");
			ps.setString(4, "%"+mcRegion+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Company c =new Company();
				c.setIdEnterprise(rs.getInt("COMPANYID"));
				c.setCompanyName(rs.getString("COMPANYNAME"));
				c.setAdress(rs.getString("COMPANYADRESS"));
				c.setPostalCode(rs.getString("COMPANYCODEPOSTAL"));
				c.setCity(rs.getString("COMPANYCITY"));
				c.setSize(rs.getString("COMPANYSIZE"));
				c.setSector(rs.getString("COMPANYSECTOR"));
				c.setProjets(rs.getString("COMPANYPROJECTS"));
				c.setWebSite(rs.getString("COMPANYWEB"));
		
				listCompanies.add(c);
				System.out.println(c.getCompanyName() + "" + c.getCity() + " " + c.getSector());
				System.out.println(listCompanies.isEmpty());
			}
		
			ps.close();
		} 
	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCompanies; 
	}


	
}
