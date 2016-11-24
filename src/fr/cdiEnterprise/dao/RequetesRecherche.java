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
import fr.cdiEnterprise.model.Recherche;
import fr.cdiEnterprise.model.Region;
import fr.cdiEnterprise.service.Companies;
import fr.cdiEnterprise.service.RecherchesFav;
import fr.cdiEnterprise.service.Regions;


/**
 * <p><b>Package : fr.cdiEnterprise.dao</b></p>
 * 
 * <p>Cette classe regroupe les méthodes de construction de requêtes à la base de donnée
 *utilisées pour génerer et mettre à jour la page web générée par le fichier rech_jsp</p>
 *
 * @author Francois Georgel
 * @version: 1.0	24/11/2016
 *
 */
public class RequetesRecherche {
	
	
	/**
	 * Methode qui retourne la liste entière des regions contenues dans la table regions de la bbd
	 * 
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @return 	listeRegion, Objet Regions		
	 * @throws 	SQLException
	 */
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

	
	/**
	 * Methode qui retourne la liste entière des entreprises contenues dans la table company de la bbd
	 * 
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @return 	listeEntreprises, Objet Companies	
	 * @throws 	SQLException 
	 */
	public Companies listAllCompanies(){ //TODO paramétrer la requete!
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
			System.out.println("La requete de demande de la liste des entreprises n'a pas pu aboutir");
			e.printStackTrace();
		}
		return listeEntreprises;
	}

	
	/**
	 * Methode qui retourne une liste des entreprises correspondant à la recherche passée en paramètre
	 * 
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @param 	recherche, Objet Recherche	
	 * @return 	listeEntreprises, Objet Companies
	 * @throws 	SQLException 
	 */
	public Companies listCompanies(Recherche recherche){	//TODO parametrer cette requete!
		Connection connect = DBConnection.getConnect();
		Statement stmt;
		Companies listeEntreprises = new Companies();
		
		
		//La recherche peut contenir de 1 à 4 critères
		//la requete va donc être construite dynamiquement en fonction des critères non vides
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
		
		//le 4ème critère (la region) est pour le moment non prise en compte, elle le sera dans une prochaine iteration
		//TODO
//		if (regionId != null){
//			if (param == 0) { req = req + "companycity ='"+ville+"' ";}
//			else {req = req + "and companycity ='"+ville+"' ";}
//			param++;
//		}
		
//		System.out.println(req);
		
		if (param != 0) {
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
		} else {
			listeEntreprises = null;
		}
		return listeEntreprises;
	}
		

	/**
	 * Methode qui retourne la liste des recherches favorites de l'utilisateur passé en paramètre
	 * 
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @param 	idUser, String			
	 * @return 	listeRech, Objet RecherchesFav	
	 * @throws 	SQLException 
	 */
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
			System.out.println("La requete pour recuperer la liste des favoris de l'utilisateur n'a pas pu aboutir");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Methode qui retourne un objet Recherche correspondant à l'id de l'utilisateur passé en paramètre et le nom de la recherche favorite
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @param 	rechOpt, String	
	 * @param 	idUser, String
	 * @return 	recherche, Objet Recherche	
	 * @throws 	SQLException 
	 */
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
			System.out.println("La requete pour recuperer la recherche favorite selectionnée n'a pas pu aboutir");
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	/**
	 * Methode qui enregistre la Recherche passée en paramètre dans la table des recherches favorites
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @param 	rech, Objet Recherche
	 * @throws 	SQLException 
	 */
	public void enregistrerRech(Recherche rech) {
		Connection connect = DBConnection.getConnect();
		String reqRec = "";

																						
		String reqVerif = "Select nom_rech from rech_fav where user_id = ? and nom_rech = ?"; 	//On recherche si le nom de recherche est déjà associé au user dans la bbd...
		try {
			PreparedStatement prepStmt;
			prepStmt = connect.prepareStatement(reqVerif);
			prepStmt.setString(1, rech.getIdUser());
			prepStmt.setString(2, rech.getNomRech());
			ResultSet res= prepStmt.executeQuery();
			
			if(res.next()){																		//...si oui, fait un update de la ligne dans la bdd
				reqRec="Update RECH_FAV set comp_rech=?, sector_rech=?, region_rech=?, city_rech=? where nom_rech = ?"; 
				System.out.println("resultat non vide... l'enregistrement sera un update");
				
				try {	
					int idRegion =0;											
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
					System.out.println("L'update de recherche favorite n'a pas pu aboutir");
					e.printStackTrace();
				}
				
				
			} else {																			//... sinon, fait un insert
				reqRec="insert into rech_fav values (?, ?, ?, ?, ?, ?, ?)";
				System.out.println("resultat vide... l'enregistrement sera un insert");
				
				try {	
					int idRegion =0;											
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
					System.out.println("L'insert de recherche favorite n'a pas pu aboutir");
					e.printStackTrace();
				}
			
			}
			
		} catch (SQLException e1) {
			System.out.println("La recherche de correspondance idUser/nom de recherche favorite n'a pas pu aboutir");
			e1.printStackTrace();
		}
	}
	

	/**
	 * Methode qui supprime la recherche favorite, correspondant à l'id de l'utilisateur passé en paramètre et le nom de la recherche favorite, de la BDD
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @param 	rechOpt, String	
	 * @param 	idUser, String
	 * @throws 	SQLException 
	 */
	public void SupprRechFav(String rechOpt, String idUser) {
		Connection connect = DBConnection.getConnect();
		PreparedStatement prepStmt;
		String req = "delete RECH_FAV where user_id= ? and nom_rech= ?";

		try {
			prepStmt = connect.prepareStatement(req);
			prepStmt.setString(1, idUser);
			prepStmt.setString(2, rechOpt);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("La requête de suppression de la rcherche favorite n'a pas pu aboutir");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Methode qui retourne un entier correspondant à l'id_rech le plus haut de la table des recherches favorites dans la bdd auquel on ajoute 1 
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @return index, int
	 * @throws 	SQLException 
	 */
	public int indexRechFav() {				//TODO parametrer la requete
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
				}
			}
		} catch (SQLException e) {
			System.out.println("La recherche de l'indice max dans la table rech_fav n'a pas pu aboutir");
			e.printStackTrace();
		}
		return index+1;
	}
	
	/**
	 * Methode qui retourne un departement correspondant à l'index d'entreprise entré en paramètre
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @param	companyId, int
	 * @return 	dept, Objet Department	
	 * @throws 	SQLException 
	 */
	public Department recupDept(int companyId) {		//TODO param requete
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
			System.out.println("La recherche de departement n'a pas pu aboutir");
			e.printStackTrace();
		}
		Department dept = new Department(name, number);
		return dept;
	}
	
	/**
	 * Methode qui retourne un objet Region correspondant à l'index d'entreprise entré en paramètre
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @param	companyId, int
	 * @return 	region, Objet Region
	 * @throws 	SQLException 
	 */
	public Region recupRegion(int companyId) {	//TODO param requete
		int number = 0;
		String name = "";
		Region region = null;
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
			region = new Region(name, number);
		} catch (SQLException e) {
			System.out.println("La recherche de region par id d'entreprise n'a pas pu aboutir");
			e.printStackTrace();
		}
		return region;
	}

	/**
	 * Methode qui retourne un objet Region correspondant à un nom reçu en paramètre
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @param	nomRegion, String
	 * @return 	region, Objet Region
	 * @throws 	SQLException 
	 */
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
			System.out.println("La recherche de region par nom n'a pas pu aboutir");
			e.printStackTrace();
		}
		return region;
	}

}
