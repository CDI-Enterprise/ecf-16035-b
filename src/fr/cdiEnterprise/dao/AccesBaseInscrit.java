package fr.cdiEnterprise.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesBaseInscrit {
	
	private final static String strNomDriver = "oracle.jdbc.driver.OracleDriver";
	private final static String dbURL = "jdbc:oracle:thin:stag15/stag15pw@junon:1521:AFPA";
	
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
	
	/*
	
	public void inscription(int reference, String statut, String nom, String prenom, String email, String motDePasse, String confirmerPasse){
		
		int reference = 0;
		String statut = "";
		String nom = "";
		String prenom = "";
		String email = "";
		String motDePasse = "";
		String confirmerPasse = "";     
		
		String reqSql = "insert into inscription values(" + reference + "','" + statut + "','" + nom + "','" + prenom + "','" + email + "','" + motDePasse + "','" + confirmerPasse +"')";
	
		try {
			stmt.executeUpdate(reqSql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void desinscription(){
		
		String reqSql = "delete from inscription where reference =" + Inscription.getReference();
		
		try {
			stmt.executeUpdate(reqSql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
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
	
	public 
	*/
}
