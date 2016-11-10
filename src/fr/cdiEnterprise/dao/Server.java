package fr.cdiEnterprise.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.util.Set;

import fr.cdiEnterprise.model.Item;
import fr.cdiEnterprise.service.Items;

/**
 * This is the class server, which is actually a message container. message are
 * stored in a hashmap and keys are identified by receiver.
 * a second map will be used to store the sender's draft email.
 * 
 * @author Nicolas Tarral
 * @version : 2016-01-10
 *
 */
public  class Server {

	 
	static HashMap<String, Items> items      = new HashMap<String, Items>();
	static HashMap<String, Items> itemsDraft = new HashMap<String, Items>();

	public Server() {
		items = new HashMap<String, Items>();
 		itemsDraft = new HashMap<String, Items>();
         
	}

	/**
	 * this method is going to post a message to a receiver 
	 * @param item message to be delivered
	 * @return boolean which tell if the message has been properly delivered.
	 */
	public static boolean post(Item item) {
		
		Items mess = getAllItems(item.getReceiver(),false);
		System.out.println("Nombre de message deja present :" + mess.size());

		if (item != null) {

			mess.add(item);
			items.put(item.getReceiver(), mess);
			System.out.println("Message posted in "+ item.getReceiver() + "address " + item);
			return true;

		}

		return false;
	}

	/**
	 * this method is going to post a message into the sender draft folder.
	 * in that case the timeStamp is not set up.
	 * @param the item that was created by the sender and need to be stored in the draft folder.
	 * @return indicate if operation successfully happened
	 */
	public static boolean postDraft(Item item) {

		
		Items mess = getAllItems(item.getReceiver(),true);
		System.out.println("Nombre de message deja present :" + mess.size());
		//ArrayList<Item> mess = new ArrayList<Item>();

		if (item != null) {
			mess.add(item);
			//System.out.println(item.getSender());
			itemsDraft.put(item.getSender(), mess);
			return true;
		}

		return false;
	}
	
	
	
	/**
	 * This method is going to get an array list with draft or message
	 * @param rcv is for identifying the mailbox
	 * @param draft will tell if the message to look for is a draft or a message
	 * @return an Arraylist of message.
	 */
	public static Items getAllItems(String rcv, boolean draft) {

		Items allMyItems = new Items();
		
		if(draft) {
			Set<String> fromDraft = itemsDraft.keySet();
			Iterator<String> fromDraftIterator = fromDraft.iterator();
			while (fromDraftIterator.hasNext()) {

				String key1 = fromDraftIterator.next();
				// System.out.println("key: " + key + " value: " + items.get(key));

				if (key1.equalsIgnoreCase(rcv)) {

					allMyItems = itemsDraft.get(key1);
					for (int i = 0; i < allMyItems.size(); i++) {
						if (!allMyItems.get(i).isDraftEmail()) {
					
							System.out.println("non draft :" + allMyItems.get(i).toString());
							allMyItems.remove(i);
						} else {
							System.out.println("draft :" + allMyItems.get(i).toString());
						}
					}
				}
			}
		} else {
				System.out.println("itzem from server" +allMyItems.size());
				Set<String> fromSet = items.keySet();
				Iterator<String> fromSetIterator = fromSet.iterator();
				while (fromSetIterator.hasNext()) {

					String key = fromSetIterator.next();

					if (key.equalsIgnoreCase(rcv)) {

						allMyItems = items.get(key);
						for (int i = 0; i < allMyItems.size(); i++) {
							if (allMyItems.get(i).isDraftEmail()) {
								allMyItems.remove(i);
							}
						}
					}
				}
			}
		
		System.out.println("NUMBER OIF ITEMS " + allMyItems.size());
		return allMyItems;
	}
	
	
	
	
	
	/**
	 * This method is going to remove one message at a time, the mailbox will need to be provided , as well as the email id to remove and also 
	 * to remove draft or not
	 * @param usr is the mailbox of the user.
	 * @param identifier is the message ID to find the proper message to remove
	 * @param draft tell if the message to be removed is draft.
	 * @return a boolean telling that the message has been removed from the list.
	 */
//	public static boolean removeMessage(String usr, String identifier, boolean draft) {
//		boolean removed = false;
//
//			if(draft) {
//				ArrayList<Item> allUsrItems = itemsDraft.get(usr);
//				for(int i = 0; i < allUsrItems.size();i++) {
//				Item current = null;
//				if(allUsrItems.get(i).getId().equals(identifier)) {
//					
//					allUsrItems.remove(i);
//					return removed;
//
//				}
//				}
//			}else {
//				ArrayList<Item> allUsrItems = items.get(usr);
//				for(int i = 0; i < allUsrItems.size();i++) {
//				Item current = null;
//				if(allUsrItems.get(i).getId().equals(identifier)) {
//					
//					allUsrItems.remove(i);
//					return removed;
//
//				}
//				}
//			}
//		
//		return removed;
//		
//	}
	
	
	
	/**
	 * This method is going to remove one message at a time, the mailbox will need to be provided , as well as the email id to remove and also 
	 * to remove draft or not
	 * @param usr is the mailbox of the user.
	 * @param identifier is the message ID to find the proper message to remove
	 * @param draft tell if the message to be removed is draft.
	 * @return a boolean telling that the message has been removed from the list.
	 */
	public static Item popMessage(String usr, String identifier, boolean draft) {
		
		Item current = null;
		
		//System.out.println(allUsrItems.size());
		
		
		
			if(draft) {
				ArrayList<Item> allUsrItems = itemsDraft.get(usr);
				for(int i = 0; i < allUsrItems.size();i++) {
				if(allUsrItems.get(i).getId()==0) {
					current = allUsrItems.get(i);
					allUsrItems.remove(i);
					return current;

				}
				}
			}else {
				ArrayList<Item> allUsrItems = items.get(usr);
				for(int i = 0; i < allUsrItems.size();i++) {
				if(allUsrItems.get(i).getId()==0) {
					current = allUsrItems.get(i);
					allUsrItems.remove(i);
					return current;

				}
				}
			}
		
		return current;
		
	}
	
	
	
	

}
