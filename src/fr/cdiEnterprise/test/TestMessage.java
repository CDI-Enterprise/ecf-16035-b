package fr.cdiEnterprise.test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import fr.cdiEnterprise.dao.Server;
import fr.cdiEnterprise.model.Item;
import fr.cdiEnterprise.service.Items;
import fr.cdiEnterprise.util.MpClient;










/***
 * This is the test class.
 * @author Nicolas Tarral
 *
 * @version 02-10-2016
 *
 *
 */
public class TestMessage {
	
	private static boolean isPresent(String usr, ArrayList<String> clients) {
		for(String current : clients) {
			if(current.equalsIgnoreCase(usr)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		
		Server newServer = new Server();
		ArrayList<String> clients = new ArrayList<String>();
		
		//TODO (Nicolas) Poster message vers un utilisateur.
		
		// Création de nouveaux messages et sauvegarde dans.
		// creation des clients utilisateurs
		
		MpClient nicolas = new MpClient(newServer, "nicolas");
		MpClient claire = new MpClient(newServer, "claire");
		MpClient olivier = new MpClient(newServer, "olivier");
		MpClient anais = new MpClient(newServer, "anais");
		MpClient ismael = new MpClient(newServer, "ismael");
		
		clients.add("nicolas");
		clients.add("claire");
		clients.add("olivier");
		clients.add("anais");
		clients.add("ismael");
		
		
		//Poster message 
		// note : avant d'envoyer un message veririfer si le destinataire est existe.
		// TODO (Nicolas) envoyer message derreur.
		//System.out.println("envoie d'emails");
		
		
		if(isPresent("anais", clients)) {
			nicolas.newEmail("nicolas", "anais", "test1", "message body from anais.");
		}else {
			//System.out.println("user does not exist  anais");
		}
		
		if(isPresent("claire", clients)) {
			nicolas.newEmail("nicolas", "claire", "test1", "message body from claire.");
		}else {
			//System.out.println("user does not exist: claire");
		}
		if(isPresent("ismael", clients)) {
			nicolas.newEmail("nicolas", "ismael", "test1", "message body from ismael.");
		}else {
			//System.out.println("user does not exist: anais");
		}
		if(isPresent("olivier", clients)) {
			nicolas.newEmail("nicolas", "olivier", "test1", "message body from nicolas.");	
		}else {
			//System.out.println("user does not exist: olivier");
		}
		
		// clqire consulte ses messages et les affichent.
		
		claire.getMessages(false);
		claire.display(false);
		
		if(isPresent("bernard", clients)) {
			nicolas.newEmail("nicolas", "bernard", "test", "message body from niocolas.");	
		} else {
			//System.out.println("there is no user bernard");
		}
		
		
		
		
		// create email and put in nicolas's draft folder.
		
		
		
		//System.out.println("\n**** Nicolas create an email and put it on its draft folder ****\n");
		
		if(isPresent("olivier", clients)) {
			if(nicolas.draft("nicolas", "olivier", "test1", "message body for olivier.")) 
			{
				//System.out.println("Message saved as a draft ");
				
			}
			// affiche les message du brouillon
			nicolas.getMessages(true);
			nicolas.display(true);
			
			Items  nickitms = nicolas.getMessages(true);
			//System.out.println("size of draft is : " + nickitms.size());
			////System.out.println(nickitms.toString());
			//simule la selection du message dans une liste et renvoie de l'identifiant de l'email selectionné
			//exemple ici 5.
			
			
			Item myItm = getMessage("nicolas", "5", nickitms);
			//System.out.println("checking draft message " +myItm.toString());
			
			
			
			String identity = null;
			
			// edition du message brouillon dans la boite de nicolas
			
			//System.out.println("\n**** edition du message brouillon ****\n");
			
			// on pop le message delah boite draft, et ensuite on l"edite.
			// le message sera remis.
			
		
			
			// now we get the message from the draft queue and send it to the final receipient
			
			//System.out.println("\n**** sending drafted message to the final user ****\n");
			
			Item draftMessage = nicolas.popMessage(identity, true);
			////System.out.println("get message from draft queue..." + draftMessage.getBody());
			nicolas.sendEmail(draftMessage, true);
			
			////System.out.println("Sending message...");
			
			
		}
		// checking olivier's Mailbox
		
		//System.out.println("\n**** checking olivier Mailbox ****\n");
		
		ArrayList<Item> olivierItems = olivier.getMessages(false);
		//System.out.println(olivierItems.size());
		olivier.display(false);
		
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO (Nicolas) Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("\n**** olivier reply to nicolas ****\n");
		
		// reply email to nicolas
		Item oneItem = olivierItems.get(0);
		oneItem.setObject("test[reply]");
		oneItem.setBody("Message reçu...\n\n" + oneItem.getBody());
		//System.out.println("Reply to that email " + oneItem.toString());
		if(olivier.sendEmail(oneItem, false)) {
			//System.out.println("email sent out...");
		}
		
		//System.out.println("\n**** checking nicolas's Mailbox ****\n");
		
		nicolas.getMessages(false);
		nicolas.display(false);
		
		
		
		
		
		

		
		
		
		

	}
	
	private static Item getMessage(String usr,String id , ArrayList<Item> allItems) {
		
		for(int i = 0; i <allItems.size(); i++ ) {
			if(allItems.get(i).getId() == 0) {
				return allItems.get(i);
				
			}
		}
		return null;
		
		
		
	}

}
