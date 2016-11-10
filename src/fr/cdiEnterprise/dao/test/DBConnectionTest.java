package fr.cdiEnterprise.dao.test;

import fr.cdiEnterprise.dao.DBConnection;

/**
 * Class to test connection to database.
 * @author Claire
 * @see fr.cdiEnterprise.dao.DBConnection.java
 * @version 22-10-2016
 *
 */
public class DBConnectionTest {

	public static void main(String[] args){

		// If connection is done, you should have "Connexion établie" in your console.
		// If you try two connections, you should have the same so just one message.
		DBConnection.getConnect();
		
	}
		
}