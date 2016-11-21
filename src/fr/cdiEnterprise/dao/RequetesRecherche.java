package fr.cdiEnterprise.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.model.Contact;
import fr.cdiEnterprise.model.Department;
import fr.cdiEnterprise.model.Language;
import fr.cdiEnterprise.model.Region;
import fr.cdiEnterprise.service.Companies;
import fr.cdiEnterprise.service.Regions;

public class RequetesRecherche {
	
	
	public Regions listerRegions(){
		Connection connect = DBConnection.getConnect();
		String req = "Select regionname, regionid from regions";
		Regions listeRegion = new Regions();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet res = stmt.executeQuery(req);
			while (res.next()){
				Region region = new Region(res.getString("regionname"), res.getInt("regionid"));
				listeRegion.add(region);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listeRegion;
	}

	public Companies listAllCompanies(){
		Connection connect = DBConnection.getConnect();
		String req = "Select companyid, companyname, companyadress, companycodepostal, companycity"
				+ ",companysize, companysector, companyprojects, companyweb from company";
		Companies listeEntreprises = new Companies();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet res = stmt.executeQuery(req);
			
			while (res.next()){
				Department departement = recupDept(res.getInt("companyId"));
				Region region = recupRegion(res.getInt("companyId"));
				Language langage =null;
				Contact contact = null;
			
				Company entreprise = new Company(res.getInt("companyid"), res.getString("companyname"), res.getString("companyadress"), 
						res.getString("companycodepostal"), res.getString("companycity"), departement, region, 
						res.getString("companysize"), res.getString("companysector"), langage, res.getString("companyprojects"), 
						res.getString("companyweb"), contact);
				listeEntreprises.add(entreprise);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeEntreprises;
	}

	//Methode de recherche d'une liste d'entreprises selon les critères
	public Companies listCompanies(String nom, String secteur, String ville, String regionId){
		Connection connect = DBConnection.getConnect();
		String req = "Select companyid, companyname, companyadress, companycodepostal, companycity"
				+ ",companysize, companysector, companyprojects, companyweb from company";
		Companies listeEntreprises = new Companies();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet res = stmt.executeQuery(req);
			
			while (res.next()){
				Department departement = recupDept(res.getInt("companyId"));
				Region region = recupRegion(res.getInt("companyId"));
				Language langage =null;
				Contact contact = null;
			
				Company entreprise = new Company(res.getInt("companyid"), res.getString("companyname"), res.getString("companyadress"), 
						res.getString("companycodepostal"), res.getString("companycity"), departement, region, 
						res.getString("companysize"), res.getString("companysector"), langage, res.getString("companyprojects"), 
						res.getString("companyweb"), contact);
				listeEntreprises.add(entreprise);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeEntreprises;
	}
	
	public Department recupDept(int companyId) {
		int number = 0;
		String name = "";
		
		Connection connect = DBConnection.getConnect();
		String reqDeptId = "Select departmentnumber from companydepartment where companyid = '"+companyId+"'";
		Statement stmt;
		
		try {
			stmt = connect.createStatement();
			ResultSet res = stmt.executeQuery(reqDeptId);
		
			while (res.next()){
				number = res.getInt("departmentnumber");
				String reqDept = "Select departmentname from departments where departmentnumber = '"+number+"'";
				Statement stmt2 = connect.createStatement();
				ResultSet res2 = stmt2.executeQuery(reqDept);
				while (res2.next()){
					name = res2.getString("departmentname");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Department dept = new Department(name, number);
		return dept;
	}
	
	public Region recupRegion(int companyId) {
		int number = 0;
		String name = "";
		
		Connection connect = DBConnection.getConnect();
		String reqDeptId = "Select regionid from companyregion where companyid= '"+companyId+"'";
		
		Statement stmt;
		try {
			stmt = connect.createStatement();
			ResultSet res = stmt.executeQuery(reqDeptId);
		
			while (res.next()){
				number = res.getInt("regionid");
				String reqDept = "Select regionname from regions where regionid= '"+number+"'";
				Statement stmt2 = connect.createStatement();
				ResultSet res2 = stmt2.executeQuery(reqDept);
				while (res2.next()){
					name = res2.getString("regionname");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Region region = new Region(name, number);
		return region;
	}
}
