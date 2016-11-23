package fr.cdiEnterprise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.cdiEnterprise.model.Company;
import fr.cdiEnterprise.model.Contact;
import fr.cdiEnterprise.model.Department;
import fr.cdiEnterprise.model.Language;
import fr.cdiEnterprise.model.Recherche;
import fr.cdiEnterprise.model.Region;
import fr.cdiEnterprise.service.Companies;
import fr.cdiEnterprise.service.RecherchesFav;
import fr.cdiEnterprise.service.Regions;

public class RequetesRecherche {
	
	//methode de recherche de toutes les regions de la bdd
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
			System.out.println("La requete de demande de la liste des régions n'a pas pu aboutir");
			e.printStackTrace();
		}
		
		return listeRegion;
	}

	//methode de recherche de toutes les entreprises de la bdd
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
	public Companies listCompanies(String nom, String secteur, String ville/*, String regionId*/){
		Connection connect = DBConnection.getConnect();
		String req = "Select companyid, companyname, companyadress, companycodepostal, companycity"
				+ ",companysize, companysector, companyprojects, companyweb from company where ";
		Companies listeEntreprises = new Companies();
		int param = 0;
		
		if (nom != null){
			req = req + "companyname = '"+nom+"' ";
			param++;
		}
		
		if (secteur != null){
			if (param == 0) { req = req + "companysector ='"+secteur+"' ";}
			else {req = req + "and companysector ='"+secteur+"' ";}
			param++;
		}
		
		if (ville != null){
			if (param == 0) { req = req + "companycity ='"+ville+"' ";}
			else {req = req + "and companycity ='"+ville+"' ";}
			param++;
		}
		
//		if (regionId != null){
//			if (param == 0) { req = req + "companycity ='"+ville+"' ";}
//			else {req = req + "and companycity ='"+ville+"' ";}
//			param++;
//		}
		
		System.out.println(req);
		
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
	
	//Methode de recuperation des recherches favorites de l'utilisateur
	public RecherchesFav listeRech(String idUser){
		Connection connect = DBConnection.getConnect();
		String req = "Select id_rech, user_id, nom_rech, comp_rech, sector_rech, region_rech, city_rech from RECH_FAV where USER_ID= ?";
		PreparedStatement prepStmt;
		
		RecherchesFav listeRech = new RecherchesFav();
		
		try {
			prepStmt = connect.prepareStatement(req);
			prepStmt.setString(1, idUser);
			ResultSet res= prepStmt.executeQuery();
			
			while (res.next()){
				Region region = recupRegion(res.getInt("region_rech"));
				Recherche recherche = new Recherche (res.getInt("id_rech"), res.getString("user_id"), res.getString("nom_rech"), res.getString("comp_rech"),
													res.getString("sector_rech"), region, res.getString("city_rech"));
				listeRech.add(recherche);
			}
		
			return listeRech;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	//Methode de recuperation d'une recherche favorite
	public Recherche recupRechFav(String rechOpt, String idUser) {
		Connection connect = DBConnection.getConnect();
		String req = "Select id_rech, user_id, nom_rech, comp_rech, sector_rech, region_rech, city_rech from RECH_FAV where nom_rech= ? and user_id= ?";
		PreparedStatement prepStmt;
		
		
		
		try {
			Recherche recherche = null;
			prepStmt = connect.prepareStatement(req);
			prepStmt.setString(1, rechOpt);
			prepStmt.setString(2, idUser);
			ResultSet res= prepStmt.executeQuery();
			
			while (res.next()){
				
				Region region = recupRegion(res.getInt("region_rech"));
				recherche = new Recherche (res.getInt("id_rech"), res.getString("user_id"), res.getString("nom_rech"), res.getString("comp_rech"),
													res.getString("sector_rech"), region, res.getString("city_rech"));
			}
		
			return recherche;	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	//methode pour enregistrer une recherche dans les favoris
	public void enregistrerRech(Recherche rech) {
		Connection connect = DBConnection.getConnect();
		//rechercher le numero de region qui correspond a la string region
		PreparedStatement prepStmt;
		String req = "insert into rech_fav values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			int idRegion =0;
			if (rech.getRegionRech() != null){
				idRegion = rech.getRegionRech().getCodeRegion();
			}
			
			prepStmt = connect.prepareStatement(req);
			prepStmt.setInt(1, rech.getIdRech());
			prepStmt.setString(2, rech.getIdUser());
			prepStmt.setString(3, rech.getNomRech());
			prepStmt.setString(4, rech.getCompRech());
			prepStmt.setString(5, rech.getSectorRech());
			prepStmt.setInt(6, idRegion);
			prepStmt.setString(7, rech.getCityRech());
			ResultSet res= prepStmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void SupprRechFav(String rechOpt, String idUser) {
		Connection connect = DBConnection.getConnect();
		PreparedStatement prepStmt;
		String req = "delete RECH_FAV where user_id= ? and nom_rech= ?";

		try {
			prepStmt = connect.prepareStatement(req);
			prepStmt.setString(1, idUser);
			prepStmt.setString(2, rechOpt);
			prepStmt.executeUpdate();
			System.out.println("fav deleted");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//methode qui recherche l'indice le plus haut de la table de recherches favorites
	public int indexRechFav() {
		Connection connect = DBConnection.getConnect();
		int index=0;
		
		String req = "select id_rech from rech_fav";
		Statement stmt;
		
		
		try {
			stmt = connect.createStatement();
			ResultSet res = stmt.executeQuery(req);
			while (res.next()){
				if(res.getInt("id_rech")>index){
					index=res.getInt("id_rech");
					System.out.println(index);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return index+1;
	}
	
	//Methode de recherche de departement
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

	public Region regionByName(String nomRegion) {
		Connection connect = DBConnection.getConnect();
		String reqDeptId = "Select regionid from regions where regionname= ?";
		Region region = null;
		
		PreparedStatement prepStmt;
		try {
			prepStmt = connect.prepareStatement(reqDeptId);
			prepStmt.setString(1, nomRegion);
			ResultSet res= prepStmt.executeQuery();
			
			while (res.next()){
				region= new Region(nomRegion, res.getInt("regionid"));
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return region;
	
	}

	


	


}
