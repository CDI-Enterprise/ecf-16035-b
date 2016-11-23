package fr.cdiEnterprise.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cdiEnterprise.model.Inscription;

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
	/*
	public void inscription(Inscription exemple){
		
		int reference = exemple.getReference();
		String statut = exemple.getStatut();
		String nom = exemple.getNom();
		String prenom = exemple.getPrenom();
		String email = exemple.getEmail();
		String motDePasse = exemple.getMotDePasse();
		String confirmation = exemple.getConfirmation();     
		
		
		//String reqSql = "insert into inscription values(" + 03 + ",'" + statut + "','" + nom + "','" + prenom + "','" + email + "','" + motDePasse + "','" + confirmation +"')";
		String sql = "insert into inscription values(?,?,?,?,?,?,?)";
				
		try {
			
			PreparedStatement update;
			update = connexion.prepareStatement(sql);
			
			update.setInt(1, reference);
			update.setString(2, statut);
			update.setString(3, nom);
			update.setString(4, prenom);
			update.setString(5, email);
			update.setString(6, motDePasse);
			update.setString(7, confirmation);
			
		update.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
<<<<<<< HEAD
	
	/*
	public void desinscription(Inscription exemple) throws SQLException{
=======
	*/

	public void desinscription(Inscription exemple){
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		
<<<<<<< HEAD
		String nom = exemple.getNom();
		
		String reqSup = "delete from inscription where reference =" + nom ;
		
		Statement stmt;
		stmt = connexion.createStatement();
		
		
=======
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
		
<<<<<<< HEAD
		try {
			stmt.executeUpdate(reqSup);
=======
		String statut = exemple.getStatut();
		String nom = exemple.getNom();
		String prenom = exemple.getPrenom();
		String email = exemple.getEmail();
		String motDePasse = exemple.getMotDePasse();
		String confirmation = exemple.getConfirmation();  
		
		
			try {
				
			String reqSql = "delete from inscription where( statut= ?, nom=?, prenom=?, email=?, motDePasse=?, confirmation=?)"; 
			PreparedStatement update = connexion.prepareStatement(reqSql);
			//update.setInt(1, 0);
			update.setString(2, statut);
			update.setString(3, nom);
			update.setString(4, prenom);
			update.setString(5, email);
			update.setString(6, motDePasse);
			update.setString(7, confirmation);
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
			
			update.executeUpdate(reqSql);
								
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
<<<<<<< HEAD
	
	
=======
  	
	/*
>>>>>>> branch 'master' of https://github.com/CDI-Enterprise/ecf-16035-b
	public  Object listeInscrit(Arraylist<Inscription>inscription){
		
		String reqSql = "select * from inscription";
		
		try {
			ResultSet rs = stmt.executeQuery(reqSql);
			while (rs.next()){
				listInscrit = rs.getListeInscrit();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeInscrit(null);
		
	}
	
	*/
	
}
