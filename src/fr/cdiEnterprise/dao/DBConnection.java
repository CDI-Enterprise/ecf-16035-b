/**
 * 
 */
package fr.cdiEnterprise.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create a single object Connection to link this program to the database.
 * 
 * @author Claire, Olivier
 * @version 2016-10-19
 */

//TODO (Groupe) separate driver loading from connection?
public class DBConnection {

	// Defines a JDBC driver
	private final static String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String JUNON_URL = "jdbc:oracle:thin:cdi_enterprise/stagpw@junon:1521:AFPA";
//	private static final String NICOLAS_URL ="jdbc:oracle:thin:stag14/stag14pw@localhost:1521:xe"; // a asuga local a la masion
	
	// Defines a connection string
	//private final static String JUNON_URL = "jdbc:oracle:thin:cdi_enterprise/stagpw@junon:1521:AFPA";

	// New connection
	private static Connection connect;

	/**
	 * Private constructor to prevent the instantiation of multiple connections.
	 */
	private DBConnection() {
	}

	/**
	 * Checks if a connection to DB already exists before creating one.
	 * @return connect
	 */
	static {
		try {
			// Loads JDBC Oracle driver
			Class.forName(ORACLE_DRIVER);
			// Asks for a new connection
			if (connect == null) {
				connect = DriverManager.getConnection(JUNON_URL);
				System.out.println("Connexion établie.");
			}
			else {
				System.out.println("Une connexion existe déjà.");
			}
		}
		// If exception, JDBC driver is not linked to the Java project
		catch(ClassNotFoundException e){
			System.err.println("Oracle : Le driver n'a pas été trouvé."); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnect() {
		
		return connect;
	}
	
	public static void disconnect(){
		
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Page Erreur SQL
			System.err.println("Probleme lors de la fermeture de la connexion SQL");
			e.printStackTrace();
		}
		
	}
	
}