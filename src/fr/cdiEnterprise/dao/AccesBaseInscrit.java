package fr.cdiEnterprise.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cdiEnterprise.model.Inscription;

public class AccesBaseInscrit {
	
	private final static String strNomDriver = "oracle.jdbc.driver.OracleDriver";
	private final static String dbURL = "jdbc:oracle:thin:cdi_enterprise/stagpw@junon:1521:AFPA";
	
	private Connection connexion; 
	private Statement stmt;
		
	
	public AccesBaseInscrit(){
		
				
		try {
			Class.forName(strNomDriver);
			connexion = DriverManager.getConnection(dbURL);
			stmt = connexion.createStatement();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
	}

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
			
			PreparedStatement update = connexion.prepareStatement(sql);
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

	
}	
	
	
	
	
	
	
	
	
	