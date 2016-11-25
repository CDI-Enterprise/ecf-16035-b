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
 * <p>Cette classe regroupe les m�thodes de construction de requ�tes � la base de donn�e
 *utilis�es pour g�nerer et mettre � jour la page web g�n�r�e par le fichier rech_jsp</p>
 *
 * @author Francois Georgel
 * @version: 1.0	24/11/2016
 *
 */
public class RequetesRecherche {
	
	
	/**
	 * Methode qui retourne la liste enti�re des regions contenues dans la table regions de la bbd
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
			res.close();
		} catch (SQLException e) {
			System.out.println("La requete de demande de la liste des r�gions n'a pas pu aboutir");
			e.printStackTrace();
		}
		
		return listeRegion;
	}

	
	/**
	 * Methode qui retourne la liste enti�re des entreprises contenues dans la table company de la bbd
	 * 
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @return 	listeEntreprises, Objet Companies	
	 * @throws 	SQLException 
	 */
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
				Language langage = recupLanguage(res.getInt("companyId"));
				Contact contact = null;
			
				Company entreprise = new Company(res.getInt("companyid"), res.getString("companyname"), res.getString("companyadress"), 
						res.getString("companycodepostal"), res.getString("companycity"), departement, region, 
						res.getString("companysize"), res.getString("companysector"), langage, res.getString("companyprojects"), 
						res.getString("companyweb"), contact);
				listeEntreprises.add(entreprise);
			}
			res.close();
		} catch (SQLException e) {
			System.out.println("La requete de demande de la liste des entreprises n'a pas pu aboutir");
			e.printStackTrace();
		}
		return listeEntreprises;
	}

	
	/**
	 * Methode qui retourne une liste des entreprises correspondant � la recherche pass�e en param�tre
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
		
		//La recherche peut contenir de 1 � 3 crit�res
		//la requete va donc �tre construite dynamiquement en fonction des crit�res non vides
		String req = "Select companyid, companyname, companyadress, companycodepostal, companycity"
				+ ",companysize, companysector, companyprojects, companyweb from company where ";
		int param = 0;
		int posComp = 0;
		int posSector = 0;
		int posCity = 0;
		
		if (recherche.getCompRech() != ""){				//si le nom d'entreprise est renseign� (non null)
			req = req + "companyname = ?";				//la requete est concat�n�e
			posComp = 1;								//la position du nom d'entreprise dans la requete sera en 1
			param++;
		}
		
		if (recherche.getSectorRech() != ""){			//si le secteur de la recherche est renseign� (non null)
			if (param == 0) { 							//si c'est le premier param�tre qu'on renseigne
				req = req + "companysector = ? ";		//la requ�te est concat�n�e sans le "and"
									
			
			} else {									//si il y avait d�j� un param�tre renseign�
				req = req + " and companysector = ?";	
				posSector = 2;							//la position du secteur dans la requete sera en 2
			}
			param++;
			posSector = param;							//la position du secteur dans la requete sera �gale au nombre de param�tre de la requete
		}
		
		if (recherche.getCityRech() != ""){				//si la ville de la recherche est renseign�e (non null)
			if (param == 0) { 							//si c'est le premier param�tre qu'on renseigne
				req = req + "companycity = ?";			//la requ�te est concat�n�e sans le "and"
			} else {
				req = req + " and companycity = ?";
			}
			param++;
			posCity = param;
		}
		
		System.out.println(req);
		
		
		if (param != 0) {
			try {
					PreparedStatement prepStmtUpdate;
					prepStmtUpdate = connect.prepareStatement(req);
					
					if (posComp != 0)	{prepStmtUpdate.setString(posComp, recherche.getCompRech());}
					if (posSector != 0)	{prepStmtUpdate.setString(posSector, recherche.getSectorRech());}
					if (posCity != 0)	{prepStmtUpdate.setString(posCity, recherche.getCityRech());}
					ResultSet res= prepStmtUpdate.executeQuery();
								
				while (res.next()){
					Department departement = recupDept(res.getInt("companyId"));
					Region region = recupRegion(res.getInt("companyId"));
					Language langage = recupLanguage(res.getInt("companyId"));
					Contact contact = null;
				
					Company entreprise = new Company(res.getInt("companyid"), res.getString("companyname"), res.getString("companyadress"), 
							res.getString("companycodepostal"), res.getString("companycity"), departement, region, 
							res.getString("companysize"), res.getString("companysector"), langage, res.getString("companyprojects"), 
							res.getString("companyweb"), contact);
				
					listeEntreprises.add(entreprise);
				}
				res.close();			
			} catch (SQLException e) {
				System.out.println("Erreur d'acces � la recherche d'entreprise");
				e.printStackTrace();
			}
		} else {
			listeEntreprises = null;
		}
		return listeEntreprises;
	}
		
/**
 * Methode qui retourne un langage correspondant � l'index d'entreprise entr� en param�tre
 * @author Francois Georgel
 * @version: 1.0	24/11/2016
 * @param	companyId, int
 * @return 	lang, Objet Language	
 * @throws 	SQLException 
 */
	private Language recupLanguage(int companyId) {
		int number = 0;
		String name = "";
		Connection connect = DBConnection.getConnect();
		String reqLangId = "Select languageid from companylanguage where companyid = ? ";
		PreparedStatement prepStmt;
		
		try {
			prepStmt = connect.prepareStatement(reqLangId);
			prepStmt.setInt(1, companyId);
			ResultSet res = prepStmt.executeQuery();
		
			while (res.next()){
				number = res.getInt("languageid");
			}
			res.close();
			
			String reqLang = "Select languagename from language where languageid = ? ";
			PreparedStatement prepStmt2 = connect.prepareStatement(reqLang);
			prepStmt2.setInt(1, number);
			ResultSet res2 = prepStmt2.executeQuery();
		
			while (res2.next()){
				name = res2.getString("languagename");
			}
			res2.close();
			
		} catch (SQLException e) {
			System.out.println("La recherche de langage n'a pas pu aboutir");
			e.printStackTrace();
		}
		
		Language lang = new Language(name, number);
		return lang;
	}


	/**
	 * Methode qui retourne la liste des recherches favorites de l'utilisateur pass� en param�tre
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
			res.close();
			return listeRech;
			
		} catch (SQLException e) {
			System.out.println("La requete pour recuperer la liste des favoris de l'utilisateur n'a pas pu aboutir");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Methode qui retourne un objet Recherche correspondant � l'id de l'utilisateur pass� en param�tre et le nom de la recherche favorite
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
			res.close();
			return recherche;	
			
		} catch (SQLException e) {
			System.out.println("La requete pour recuperer la recherche favorite selectionn�e n'a pas pu aboutir");
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	/**
	 * Methode qui enregistre la Recherche pass�e en param�tre dans la table des recherches favorites
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @param 	rech, Objet Recherche
	 * @throws 	SQLException 
	 */
	public void enregistrerRech(Recherche rech) {
		Connection connect = DBConnection.getConnect();
		String reqRec = "";

																						
		String reqVerif = "Select nom_rech from rech_fav where user_id = ? and nom_rech = ?"; 	//On recherche si le nom de recherche est d�j� associ� au user dans la bbd...
		try {
			PreparedStatement prepStmt;
			prepStmt = connect.prepareStatement(reqVerif);
			prepStmt.setString(1, rech.getIdUser());
			prepStmt.setString(2, rech.getNomRech());
			ResultSet res= prepStmt.executeQuery();
			
			if(res.next()){																		//...si oui, fait un update de la ligne dans la bdd
				reqRec="Update RECH_FAV set comp_rech=?, sector_rech=?, region_rech=?, city_rech=? where nom_rech = ?"; 
				
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
	 * Methode qui supprime la recherche favorite, correspondant � l'id de l'utilisateur pass� en param�tre et le nom de la recherche favorite, de la BDD
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
			System.out.println("La requ�te de suppression de la recherche favorite n'a pas pu aboutir");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Methode qui retourne un entier correspondant � l'id_rech le plus haut de la table des recherches favorites dans la bdd auquel on ajoute 1 
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @return index, int
	 * @throws 	SQLException 
	 */
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
				}
			}
			res.close();
		} catch (SQLException e) {
			System.out.println("La recherche de l'indice max dans la table rech_fav n'a pas pu aboutir");
			e.printStackTrace();
		}
		return index+1;
	}
	
	/**
	 * Methode qui retourne un departement correspondant � l'index d'entreprise entr� en param�tre
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @param	companyId, int
	 * @return 	dept, Objet Department	
	 * @throws 	SQLException 
	 */
	public Department recupDept(int companyId) {		
		int number = 0;
		String name = "";
		Connection connect = DBConnection.getConnect();
		String reqDeptId = "Select departmentnumber from companydepartment where companyid = ? ";
		PreparedStatement prepStmt;
		
		try {
			prepStmt = connect.prepareStatement(reqDeptId);
			prepStmt.setInt(1, companyId);
			ResultSet res = prepStmt.executeQuery();
			
			while (res.next()){
				number = res.getInt("departmentnumber");
				name=recupDeptName(number);
			}
			res.close();
		} catch (SQLException e) {
			System.out.println("La recherche de num�ro de departement n'a pas pu aboutir");
			e.printStackTrace();
		}	
		Department dept = new Department(name, number);
		return dept;
	}
	
	/**
	 * Methode qui retourne un nom de departement correspondant � l'index entr� en param�tre
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @param	deptId, int
	 * @return 	nameDpt, String	
	 * @throws 	SQLException 
	 */	
	public String recupDeptName(int deptId) {
		Connection connect = DBConnection.getConnect();
		String reqDept = "Select departmentname from departments where departmentnumber = ? ";
		PreparedStatement prepStmt;
		String nameDpt="";
		
		try {
			prepStmt = connect.prepareStatement(reqDept);
			prepStmt.setInt(1, deptId);
			ResultSet res2 = prepStmt.executeQuery();
						
			while (res2.next()){
				nameDpt = res2.getString("departmentname");
			}
			res2.close();
			
		} catch (SQLException e) {
			System.out.println("La recherche de nom de departement n'a pas pu aboutir");
			e.printStackTrace();
		}
		return nameDpt;
	}
		
	
	/**
	 * Methode qui retourne un objet Region correspondant � l'index d'entreprise entr� en param�tre
	 * @author Francois Georgel
	 * @version: 1.0	24/11/2016
	 * @param	companyId, int
	 * @return 	region, Objet Region
	 * @throws 	SQLException 
	 */
	public Region recupRegion(int companyId) {	
		int number = 0;
		String name = "";
		Region region = null;
		Connection connect = DBConnection.getConnect();
		String reqRegId = "Select regionid from companyregion where companyid= ?";
		
		PreparedStatement prepStmt;
		try {
			prepStmt = connect.prepareStatement(reqRegId);
			prepStmt.setInt(1, companyId);
			ResultSet res = prepStmt.executeQuery();
		
			while (res.next()){
				number = res.getInt("regionid");
				String reqReg = "Select regionname from regions where regionid= ?";
				PreparedStatement prepStmt2 = connect.prepareStatement(reqReg);
				prepStmt2.setInt(1, number);
				ResultSet res2 = prepStmt2.executeQuery();
				
				while (res2.next()){
					name = res2.getString("regionname");
				}
				res2.close();
			}
			
			res.close();
			region = new Region(name, number);
			
		} catch (SQLException e) {
			System.out.println("La recherche de region par id d'entreprise n'a pas pu aboutir");
			e.printStackTrace();
		}
		return region;
	}

	/**
	 * Methode qui retourne un objet Region correspondant � un nom re�u en param�tre
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
			res.close();
		} catch (SQLException e) {
			System.out.println("La recherche de region par nom n'a pas pu aboutir");
			e.printStackTrace();
		}
		return region;
	}

}
