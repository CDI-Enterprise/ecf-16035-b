package fr.cdiEnterprise.dao.test;

import java.sql.SQLException;
import java.time.LocalDateTime;

import fr.cdiEnterprise.dao.DBConnection;
import fr.cdiEnterprise.dao.MessageDao;
import fr.cdiEnterprise.exceptions.CustomMessagingException;
import fr.cdiEnterprise.model.Item;
import fr.cdiEnterprise.service.Items;
import fr.cdiEnterprise.util.MpClientV2;
import fr.cdiEnterprise.util.ReadProperties;

/**
 * test creation base et insert d'une donnee.
 * @author nicolas Tarral
 *
 */
public class TestDB {

	public static void main(String[] args) throws SQLException  {

		// commande a utiliser pour effacer toutes les lignes de la table
		// dans SQL developer.
		//delete from mailbox;
		
		DBConnection.getConnect();
		Items items = new Items();
		MpClientV2 client1 = null;
		MpClientV2 client2 = null;
		int tailleBoite1 = 0;
		int tailleBoite2 = 0;
		try {
			client1 = new MpClientV2("oracle");
			client2 = new MpClientV2("omy");
			tailleBoite1 = client1.getID_NUMBER();
			tailleBoite2 = client2.getID_NUMBER();
			
		} catch (SQLException e) {
			System.out.println("une erreur sql c'est produite");
			e.printStackTrace();
		}

		
		System.out.println("--- TESTEUR DE FONCTIONNALITE MESSAGERIE ---");
		System.out.println("1a - Creation d’un email et envoie.");
		System.out.println("1b - Creation d’un email et placé dans le dossier brouillon.");
		System.out.println("1c - Verification des données");
		
		System.out.println("2a - Creation d’un email et envoie.");
		System.out.println("2b - Reponse d’un utilisateur a un meassage.");
		System.out.println("2c - Verification des données");

		System.out.println("3a - Creation d’un email et envoie dans brouillon.");
		System.out.println("3b - Modification d’un brouillon.");
		System.out.println("3c - Verification des données");

		System.out.println("4a - suppression d'un message.");
		System.out.println("4b - suppression d’un brouillon.");
		System.out.println("4c - Verification des données");
		
		// TEST D'ENVOI DE MESSAGE
		
		//used to be tested with messageDAO  
		
		// Fonction pour envoyé un message
		
		System.out.println("1 er Message a envoyé de omy vers oracle, avec Sujet test2");
		
		try {
			client1.newEmail("omy", "oracle", "test2", "This is a test.");
		} catch (CustomMessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Fonction pour envoyé le message dans son brouillon.
		
		System.out.println("2 eme Message a envoyé de claire vers oracle, avec Sujet test3 stocké en brouillon");
		try {
			client1.sendEmail("claire", "oracle", "test3", "This is a test3.", true);
		} catch (CustomMessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		System.out.println("--- TEST 1 ---");

		items =  client1.getMessages(false,false);
		for(Item current :items) {
			
			System.out.println("email : "+current);
		}
		
		System.out.println("dans la boite brouillon");
		
		items =  client1.getMessages(true,false);
		for(Item current :items) {
			System.out.println("email : "+current);
		}
		
//		
		System.out.println("---- FIN DU TEST 1 ----");
		
		
		
		
		// TEST 2
		
		
		try {
			client1.newEmail("oracle", "omy", "test 2 envoie", "This is a test.");
		} catch (CustomMessagingException e) {
			System.out.println("une erreur c'est produite.");
			e.printStackTrace();
		}
		
		Items itms = client2.searchMessage("test 2 envoie");
		Item itm = itms.get(0);
		
		try {
			client2.sendEmail("omy", "oracle","test 2 reply" , "This is a reply.", false);
		} catch (CustomMessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Items itms2 = client2.getMessages(false,false);
		
		
		
	}

}
