/**
 * 
 */
package fr.cdiEnterprise.service;

import java.util.ArrayList;

import fr.cdiEnterprise.model.Item;
import fr.cdiEnterprise.util.MpClient;
import fr.cdiEnterprise.util.MpClientV2;

/**
 * Class is going to hold all the Users that are registered on the Messaging
 * Server.
 * 
 * @author nicolas Tarral
 * @version 13-10-2016
 */
public class Clients extends ArrayList<MpClient> {

	
	/**
	 * This method is going to provide the name of the box that may be in the box containers
	 * 
	 * @param box
	 * @return the box.
	 */
	public MpClient getClient(String box) {
		MpClient theClient = null;
		for (MpClient current : this) {
			if (current.getBox().equals(box)) {
				theClient = current;
			}
		}
		return theClient;
	}

	

	
	
	/**
	 * This method is going to provide an Array of all the message for a particular user.
	 * @param box this is to identify the User message box.
	 * @param draft indicate if we need the draft folder
	 * @return an array of message.
	 */
	public String[][] getMsgTableFormat(String box, boolean draft) {
		int index = 0;
		String[][] liste = null;
		MpClient cli = getClient(box);
		ArrayList<Item> itms = cli.getMessages(draft);
		
		if(itms.isEmpty()) {
			liste = new String[itms.size()][3];
		} else  {
			liste = new String[itms.size()+1][3];
			
			for(Item current : itms) {
				
				liste[index][0] = current.getSender();
				liste[index][1] = current.getObject();
				liste[index][2] = current.getTimeStamp().toString();
			
				

				index++;
			}
		}
			
		
	
		
		return liste;
	}

}
