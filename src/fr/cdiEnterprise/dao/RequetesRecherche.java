package fr.cdiEnterprise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sound.midi.Synthesizer;

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
	public Companies listCompanies(Recherche recherche){
		Connection connect = DBConnection.getConnect();
		Statement stmt;
		Companies listeEntreprises = new Companies();
		
		String req = "Select companyid, companyname, companyadress, companycodepostal, companycity"
				+ ",companysize, companysector, companyprojects, companyweb from company where ";
		int param = 0;
		
		if (recherche.getCompRech() != ""){
			req = req + "companyname = '"+recherche.getCompRech()+"' ";
			param++;
		}
		
		if (recherche.getSectorRech() != ""){
			if (param == 0) { req = req + "companysector ='"+recherche.getSectorRech()+"' ";}
			else {req = req + "and companysector ='"+recherche.getSectorRech()+"' ";}
			param++;
		}
		
		if (recherche.getCityRech() != ""){
			if (param == 0) { req = req + "companycity ='"+recherche.getCityRech()+"' ";}
			else {req = req + "and companycity ='"+recherche.getCityRech()+"' ";}
			param++;
		}
		
//		if (regionId != null){
//			if (param == 0) { req = req + "companycity ='"+ville+"' ";}
//			else {req = req + "and companycity ='"+ville+"' ";}
//			param++;
//		}
		
		System.out.println(req);
		
		try {
			stmt = connect.createStatement();
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
				
				System.out.println(entreprise);
				listeEntreprises.add(entreprise);
			}
						
		} catch (SQLException e) {
			System.out.println("Erreur d'acces à la recherche d'entreprise");
			e.printStackTrace();
		}
		return listeEntreprises;
	}
		
//		PreparedStatement prepStmt;
//		String req = "Select companyid, companyname, companyadress, companycodepostal, companycity, companysize, companysector, companyprojects, companyweb "
//				+ "from company where companyname= ? and companysector= ? and companycity= ?";
//		
//		Companies listeEntreprises = new Companies();
//		String nomCompRech = null;
//		String sectorRech = null;
//		String cityRech = null;
//		
//		
//		if (recherche.getCompRech() != "") {
//			nomCompRech = recherche.getCompRech();
//		}
//		
//		if (recherche.getSectorRech() != "") {
//			sectorRech = recherche.getSectorRech();
//		}
//		
//		if (recherche.getCityRech() != "") {
//			cityRech = recherche.getCityRech();
//		}
//		
//		System.out.println("param entrée en recherche: companyname="+nomCompRech+"companysector="+sectorRech+"companycity="+cityRech);
//		
//		try {
//			prepStmt = connect.prepareStatement(req);
//			prepStmt.setString(1, nomCompRech);
//			prepStmt.setString(2, sectorRech);
//			prepStmt.setString(3, cityRech);
//			ResultSet res = prepStmt.executeQuery(req);
//			
//			while (res.next()){
//				Department departement = recupDept(res.getInt("companyId"));
//				Region region = recupRegion(res.getInt("companyId"));
//				Language langage =null;
//				Contact contact = null;
//			
//				Company entreprise = new Company(res.getInt("companyid"), res.getString("companyname"), res.getString("companyadress"), 
//						res.getString("companycodepostal"), res.getString("companycity"), departement, region, 
//						res.getString("companysize"), res.getString("companysector"), langage, res.getString("companyprojects"), 
//						res.getString("companyweb"), contact);
//				
//				System.out.println(entreprise);
//				listeEntreprises.add(entreprise);
//			}
//						
//		} catch (SQLException e) {
//			System.out.println("Erreur d'acces à la recherche d'entreprise");
//			e.printStackTrace();
//		}
//		return listeEntreprises;
//	}
	
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
		String reqRec = "";
		
		
		
		//Recherche si le nom de recherche est déjà associé au user dans la bbd
		String reqVerif = "Select nom_rech from rech_fav where user_id = ? and nom_rech = ?";
		try {
			PreparedStatement prepStmt;
			prepStmt = connect.prepareStatement(reqVerif);
			prepStmt.setString(1, rech.getIdUser());
			prepStmt.setString(2, rech.getNomRech());
			ResultSet res= prepStmt.executeQuery();
			
			if(res.next()){				//si oui fait un update de la ligne dans la bdd
				reqRec="Update RECH_FAV set comp_rech=?, sector_rech=?, region_rech=?, city_rech=? where nom_rech = ?"; 
				System.out.println("resultat non vide... l'enregistrement sera un update");
				
				try {	
					int idRegion =0;											//rechercher le numero de region qui correspond a la string region
					if (rech.getRegionRech() != null){		
						idRegion = rech.getRegionRech().getCodeRegion();
					}
					PreparedStatement prepStmtUpdate;
					prepStmtUpdate = connect.prepareStatement(reqRec);
					prepStmtUpdate.setString(1, rech.getCompRech());
					prepStmtUpdate.setString(2, rech.getSectorRech());
					prepStmtUpdate.setInt(3, idRegion);
					prepStmtUpdate.setString(4, rech.getCityRech());
					prepStmtUpdate.setString(5, rech.getNomRech());
					prepStmtUpdate.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			} else {					//sinon fais un insert
				reqRec="insert into rech_fav values (?, ?, ?, ?, ?, ?, ?)";
				System.out.println("resultat vide... l'enregistrement sera un insert");
				
				try {	
					int idRegion =0;											//rechercher le numero de region qui correspond a la string region
					if (rech.getRegionRech() != null){		
						idRegion = rech.getRegionRech().getCodeRegion();
					}
					PreparedStatement prepStmtInsert;
					prepStmtInsert = connect.prepareStatement(reqRec);
					prepStmtInsert.setInt(1, rech.getIdRech());
					prepStmtInsert.setString(2, rech.getIdUser());
					prepStmtInsert.setString(3, rech.getNomRech());
					prepStmtInsert.setString(4, rech.getCompRech());
					prepStmtInsert.setString(5, rech.getSectorRech());
					prepStmtInsert.setInt(6, idRegion);
					prepStmtInsert.setString(7, rech.getCityRech());
					prepStmtInsert.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	//Methode de suppression d'une recherche favorite dans la bdd
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
