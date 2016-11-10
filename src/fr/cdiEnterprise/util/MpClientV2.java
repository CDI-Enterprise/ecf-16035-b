package fr.cdiEnterprise.util;



import java.sql.SQLException;
import java.time.LocalDateTime;




import fr.cdiEnterprise.dao.MessageDao;
import fr.cdiEnterprise.exceptions.CustomMessagingException;
import fr.cdiEnterprise.model.Item;
import fr.cdiEnterprise.service.Items;






/**
 * This class is going to give you the control over sending, receiving , editing and removing messages.
 * only the draft message can be edited.
 * 
 * to put message in the draft folder, use the following method draft(String from, String to, String obj, String bdy).
 * at that point we put the draft variable to true in the message.
 * to edit and send a message you will have to follow those steps :
 * Pop the message from the server(draft folder of your user), by calling popMessage method then, use the sendEmail method.
 * As soon as the message is send out to the final user, the timestamp is setup, the draft boolean will be put on false.
 * 
 * @author Nicolas Tarral
 * @version 20-10-2016
 *
 */
public class MpClientV2 {
	
	
	
	private static final int CONST_ZERO = 0;
	private static final int CONST_ONE = 1;
	private static int ID_NUMBER = CONST_ZERO;
	  

	
	private String box;


	private Items myMessages;
	
	
	/**
	 * Ce constructeur va d'abord charger tous les messages contenu dans la base pour l'utilisateur donné en parametre.
	 * par la suite il va charger seuelement les messages dans son attribu d'items.
	 * @param usr represente la Boite de messagerie de l'utilsateur, aillant ouvert l'application.
	 *  @throws SQLException exception venant de la class DAO
	 */
	public MpClientV2(String usr) throws SQLException   {
		box = usr;
		this.myMessages = new Items();
		myMessages 	= getMaxItems(false);
		getMaxItems(true);

		
		

	}

	/**
	 * Cette method est utilise pour determiner le numeros de message ID Max provenant de la base de donnee.
	 * @throws SQLException exception venant de la class DAO
	 */
	private Items getMaxItems(boolean all) throws SQLException   {
		Items items = null;
		if(ID_NUMBER == CONST_ZERO)
		{
			int max = CONST_ZERO;
			if(all) {
				items = getAllMessages();
				for(Item current : items){
					System.out.println(current.getId());
					if(current.getId() > max) {
						max = current.getId();
						ID_NUMBER = max;
						System.out.println("max " +ID_NUMBER);
					}
				}
			
			}else {
				items = getMessages(false);
			}
			
		}
		
	//	System.out.println("l'email le plus rescent est numero "+ ID_NUMBER);
		return items;
	}
	
	
	
	private Items getAllMessages() throws SQLException   {
		return MessageDao.getAllItems(this.box);
	}

	/**
	 * Cette methode va s'occuper de la verification des informations avant de l'envoyer  a la base de donnée sous forme d'objet.
	 * 
	 * @param from this is to indicate the user who send the email
	 * @param to this is to indicate the user who receive the email
	 * @param obj this is to provide the email's object
	 * @param bdy the body of the message.
	 * @return this will return true if the message was correctly sent out
	 * @throws CustomMessagingException cette exception  est executé si le destinataire ou le sujet sont manquant.
	 * @throws SQLException 
	 */
	public void newEmail(String from, String to, String object, String body ) throws CustomMessagingException, SQLException   {
		// TODO (nicolas) Penser a modifier la signature pour ne prendre que un objet Item
		
		int idNumber = CONST_ZERO;
		if(from != null && to != null && object != null && body != null) {
			if(!to.isEmpty() && !object.isEmpty()) {
				ID_NUMBER = ID_NUMBER + CONST_ONE;
				LocalDateTime timeStamp = LocalDateTime.now();
				idNumber = ID_NUMBER;
				Item itm = new Item(from, to, object, body, timeStamp);
				itm.setId(idNumber);
				MessageDao.insertItem(itm);
			} else {
				throw new CustomMessagingException("le Destinataire ou le sujet sont vide.");
			}
			
		}
		
		
		

			
		
	}
	
	/**
	 * This method will be used to reply to an email , draft have to be false, or send a draft email and draft have to be true.
	 * 
	 * @param from the from String
	 * @param to the receiver in String 
	 * @param body in String
	 * @param draft a boolean at True for Draft
	 * @throws CustomMessagingException this is going to use the custom class exception in case the datas received are incorrect
	 * oject or to.
	 * @throws SQLException 
	 */
	public void sendEmail(String from, String to, String object, String body, boolean draft) throws CustomMessagingException, SQLException {

		LocalDateTime timeStamp = LocalDateTime.now();
		ID_NUMBER = ID_NUMBER + CONST_ONE;

		if(from != null && to != null && object != null && body != null) {
			if(!to.isEmpty() && !object.isEmpty()) {
		
				Item repliedItem = new Item(from, to, object, body,  timeStamp);
		
		//repliedItem.setId(item.getId()); // old implementation
		
				repliedItem.setId(ID_NUMBER);
		if(draft) {

			repliedItem.setDraftEmail(true);
			//System.out.println("message envoye a la base");
			MessageDao.insertItem(repliedItem);

		}else {
			//System.out.println("message envoye a la base");
			
				repliedItem.setObject("re: "+ object);
				repliedItem.setTimeStamp(timeStamp);
				String snd = from;
				String rcv = to;
				repliedItem.setReceiver(snd);
				repliedItem.setSender(rcv);
				
				
				MessageDao.insertItem(repliedItem);
				


			}
			}else {
				throw new CustomMessagingException("le Destinataire ou le sujet sont vide.");

			}
		}

	}
	
	
	
	/**
	 * This method is going to put the edited draft email back to the draft folder
	 * @param item is the updated message to be inserted.
	 * @return return true if properly drafted.
	 * @throws SQLException 
	 */
	public void editDraft(Item toEdit) throws SQLException  {	
		
		if(toEdit !=null) {
			MessageDao.updateMessage(toEdit);
		}
		

	}
	
	
	
	
//	/**
//	 * This method is going to put the new created  email into the draft folder
//	 * @param the new created item to be identified as draft.
//	 * 
//	 */
	public void draft(String from, String to, String obj, String bdy, boolean draft) throws SQLException {
//		
		int idNumber = CONST_ZERO;
		ID_NUMBER = ID_NUMBER + CONST_ONE;		
		idNumber = ID_NUMBER;
		Item itm = new Item(idNumber, from, to, obj, bdy, null, draft);
	

		MessageDao.insertItem(itm);
	
	}

	
	/**
	 * THis method is going to get messages , mail or draft email
	 * will depends on the boolean status
	 * @param draft true will indicate to return the draft email
	 * @return an arrayList of items
	 * 
	 */
	public Items getMessages(boolean draft)  {

		
		myMessages = MessageDao.getAllItems(this.box, draft);
		//System.out.println("numbers of email : " +myMessages.size());
		
		
		return myMessages;
	}
	
	/**
	 * this is going to remove a message or a draft message, and with a particular id.
	 * @param identifier to get the requested email removed
	 * @param draft will indicate if this is a draft email
	 * @throws SQLException reçu de la class DAO et a transmettre a l'apellant.
	 */
	public void removeMessage(int identifier, boolean draft) throws SQLException  {
		
		MessageDao.removeMessage(this.box, identifier, draft);
			
	}
	
	public Items searchMessage(String input) {
		Items items = new Items();
		if(input != null) {
			items = MessageDao.searchMessage(input);
		}
		
		return items;
		
	}
	

	
	
//	/**
//	 * going to pop one message with particular Id, and draft or not
//	 * @param identifier 
//	 * @param draft
//	 * @return an item representing the message.
//	 */
//	public Item popMessage(String identifier, boolean draft) {
//		Item message = server.popMessage(this.box, identifier, draft);
//		if(message != null) {
//			//System.out.println("Message has been popped..." + message.toString());
//		}
//		return message;
//	}
//	
//	
//	public int numberOfMessages(boolean draft) {
//		myMessages = server.getAllItems(this.box, draft);
//		return myMessages.size();
//	}
//	
//	/**
//	 * will display the mailbox or the draft box.
//	 * @param draft
//	 */
//	public void display(boolean draft) {
//		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
//		if(draft) {
//			System.out.printf("---*** Boite Brouillon de %s ***---\n",this.box);
//		}else {
//			System.out.printf("---*** Boite de %s ***---\n",this.box);
//		}
//		
//		System.out.printf("--- %s message(s) dans votre boite ---\n",myMessages.size());
//		System.out.printf("--- Liste des messages ---\n",box);
//		for(Item current : myMessages) {
//			System.out.println("message From : "+current.getSender());
//			System.out.println("message To : "+current.getReceiver());
//			if(current.getTimeStamp() != null) {
//				System.out.println("message sent At : "+current.getTimeStamp().format(formatter));
//			} else {
//				System.out.println("Message does not have a timestamp not send out...");
//			}
//			
//			System.out.println("Subject : "+current.getObject());
//			System.out.println("Message : "+current.getBody());
//			System.out.println("Message ID: "+current.getId());
//		}
//	}
//
	public  String getBox() {
		return box;
	}
	
	public Items getMyMessages() {
		return myMessages;
	}

	public static int getID_NUMBER() {
		return ID_NUMBER;
	}
//	
//	
}
