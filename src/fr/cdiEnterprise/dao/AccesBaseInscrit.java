package fr.cdiEnterprise.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cdiEnterprise.model.Inscription;
import fr.cdiEnterprise.service.Inscriptions;

/*
 * Mise en place d' une couche dao pour la gestion
 * des inscriptions, désinscription, modification
 * et affichage
 */

public class AccesBaseInscrit {
	
	private final static String strNomDriver = "oracle.jdbc.driver.OracleDriver";
	private final static String dbURL = "jdbc:oracle:thin:stag15/stag15pw@localhost:1521:xe";
	
	private Connection connexion; 
	Statement stmt;
	
	public AccesBaseInscrit(){
					
		try {
			Class.forName(strNomDriver);
			connexion = DriverManager.getConnection(dbURL);
			connexion.createStatement();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
	}
<<<<<<< HEAD
	
	/**
	 * 
	 * @param exemple
	 * @throws SQLException
	 */
	public void inscription(Inscription exemple) throws SQLException{
		
		// recupération des paramètres
=======

	public void inscription(Inscription exemple){
		
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		int reference = exemple.getReference();
		String statut = exemple.getStatut();
		String nom = exemple.getNom();
		String prenom = exemple.getPrenom();
		String email = exemple.getEmail();
		String motDePasse = exemple.getMotDePasse();
		String confirmation = exemple.getConfirmation();     
		
		// Essai d'insertion dans la table Inscription 
		try {
			
			String sql = "insert into inscription values(?,?,?,?,?,?,?)";
			PreparedStatement update = connexion.prepareStatement(sql);
						
			update.setInt(1, reference);
			update.setString(2, statut);
			update.setString(3, nom);
			update.setString(4, prenom);
			update.setString(5, email);
			update.setString(6, motDePasse);
			update.setString(7, confirmation);
			update.executeQuery();
			connexion.commit();               // Validation pour visibilté extérieure
							
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
<<<<<<< HEAD
	/**
	 * 
	 * @param exemple
	 * @throws SQLException
	 * Méthode pour la suppression d' un inscrit de la base de données
	 */
	
=======
<<<<<<< HEAD
	/*
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
	public void desinscription(Inscription exemple) throws SQLException{
<<<<<<< HEAD
		
		// Récupération des parametres à vérifier dans la base de données
		int reference = exemple.getReference();
		String statut = exemple.getStatut();
=======

	*/

=======
	
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
	public void desinscription(Inscription exemple){

<<<<<<< HEAD
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		String nom = exemple.getNom();
		String prenom = exemple.getPrenom();
		String email = exemple.getEmail();
		String motDePasse = exemple.getMotDePasse();
		String confirmation = exemple.getConfirmation();     
		
<<<<<<< HEAD
		// Essai de suppression de l'utilisateur		
=======
		String reqSup = "delete from inscription where reference =" + nom ;
		
		Statement stmt;
		stmt = connexion.createStatement();
		

=======
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		try {
<<<<<<< HEAD

			String query = "DELETE FROM inscription WHERE reference="+reference+" AND statut='"+statut+"' AND nom ='"+nom+"'AND prenom='"+prenom+"' AND email='"+email+"' AND motDePasse='"+motDePasse+"' AND confirmation='"+confirmation+"'";       
			System.out.println("requete SQL :"+query);  // vérification de la requete dans la console

			Statement stmt = connexion.createStatement();
			stmt.executeQuery(query);
			connexion.commit();                // Validation pour visibilté extérieure

=======
<<<<<<< HEAD
			stmt.executeUpdate(reqSup);

=======
		
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		String statut = exemple.getStatut();
		String prenom = exemple.getPrenom();
		String email = exemple.getEmail();
		String motDePasse = exemple.getMotDePasse();
		String confirmation = exemple.getConfirmation();  
		
				
			String reqSql = "delete from inscription where( statut= ?, nom=?, prenom=?, email=?, motDePasse=?, confirmation=?)"; 
			PreparedStatement update = connexion.prepareStatement(reqSql);
			//update.setInt(1, 0);
			update.setString(2, statut);
			update.setString(3, nom);
			update.setString(4, prenom);
			update.setString(5, email);
			update.setString(6, motDePasse);
			update.setString(7, confirmation);

			
			update.executeUpdate(reqSql);
								
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
<<<<<<< HEAD

=======
		
<<<<<<< HEAD
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
	}
<<<<<<< HEAD

		/**
		 * 
		 * @return
		 * Méthode pour lister les inscrits, elle retourne la liste des inscrits
		 */
		public Inscriptions afficherInscrits(){
			
			Inscriptions inscriptions = new Inscriptions();
			        
			try {
=======

  	
	/*

	public  Object listeInscrit(Arraylist<Inscription>inscription){
		
		String reqSql = "select * from inscription";
		
		try {
			ResultSet rs = stmt.executeQuery(reqSql);
			while (rs.next()){
				listInscrit = rs.getListeInscrit();
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
				
				
				String query = "SELECT reference, statut, nom, prenom, email, motDePasse, confirmation FROM inscription";
				//String query = "SELECT * FROM inscription";
				System.out.println("requete SQL :"+query);
				
				Statement stmt = connexion.createStatement();
				ResultSet result = stmt.executeQuery(query);
				while(result.next()) {
					
					Inscription ins = new Inscription();
					
					ins.setReference(result.getInt("reference"));
					ins.setStatut(result.getString("statut"));
					ins.setNom(result.getString("nom"));
					ins.setPrenom(result.getString("prenom"));
					ins.setEmail(result.getString("email"));
					ins.setMotDePasse(result.getString("motDePasse"));
					ins.setConfirmation(result.getString("confirmation"));
					inscriptions.add(ins);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
<<<<<<< HEAD
			return inscriptions;
=======
		} catch (Exception e) {
			e.printStackTrace();
=======
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		}

<<<<<<< HEAD

=======
	}

	

  	
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
	
	
	
	
	
	
	
	
	