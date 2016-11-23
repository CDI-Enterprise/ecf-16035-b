package fr.cdiEnterprise.model;

import java.time.LocalDateTime;

/**
 * This class is going to represent a private message.
 * during the construction, all the fields have to be provided.
 * 
 * the id number will be unique for all new message created for the sender.
 * body will just be a simple text that we might limit to 1500 caracters.
 * the timeStamp is going to use the new java API 8.
 * boolean will represent in which queue the message will be sent too
 * for draft it need to be put to true.
 * 
 * @author Nicolas Tarral
 * @version 30-09-2016
 *
 */
public class Item {


	private int id;
	private String sender;
	private String receiver;
	private String object;
	private String body;
	private LocalDateTime  timeStamp;
	private boolean draftEmail;
	private boolean deletedEmail;
	

	/**
	 * complete constructor.
	 * 
	 * @param thread this is a unique number identifying a new message
	 * @param sender is the user name that will be sending the message
	 * @param receiver is the user name that will be receiving the message
	 * @param object is the object for the email
	 * @param body the body represent the message, should be limited to 1500 caracters.
	 * @param timeStamp is the time when message has been sent.
	 */
	public Item(String sender, String receiver, String object, String body, LocalDateTime timeStamp) {
		super();
		
		this.sender = sender;
		this.receiver = receiver;
		this.object = object;
		this.body = body;
		this.timeStamp = timeStamp;
	}
	
	/**
	 * complete constructor.
	 * 
	 * @param thread this is a unique number identifying a new message
	 * @param sender is the user name that will be sending the message
	 * @param receiver is the user name that will be receiving the message
	 * @param object is the object for the email
	 * @param body the body represent the message, should be limited to 1500 caracters.
	 * @param timeStamp is the time when message has been sent.
	 */
	public Item(int id, String sender, String receiver, String object, String body, LocalDateTime timeStamp, boolean draft, boolean deleted) {
		super();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.object = object;
		this.body = body;
		this.timeStamp = timeStamp;
		this.draftEmail = draft;
		this.deletedEmail = deleted;
	}
	
	/**
	 * Contructeur de copy
	 * @param itm
	 */
	public Item(Item itm) {
	    this.id = itm.id;
	    this.sender = itm.sender;
	    this.receiver= itm.receiver;
	    this.object = itm.object;
	    this.body = itm.body;
	    this.timeStamp = itm.timeStamp;
	    this.draftEmail = itm.draftEmail;// you can access  
	    this.deletedEmail = itm.deletedEmail;
	}
	


	public Item() {
		// TODO (Nicolas) Auto-generated constructor stub
	}



	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public boolean isDraftEmail() {
		return draftEmail;
	}
	public void setDraftEmail(boolean draftEmail) {
		this.draftEmail = draftEmail;
	}
	
	/**
	 * Will help to get the time the message has been sent out.
	 * @return the time stamp in LocalDateTime
	 */
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Set the time when the message was sent out.
	 * @param timeStamp is LocalDateTime type
	 */
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public boolean isDeletedEmail() {
		return deletedEmail;
	}

	public void setDeletedEmail(boolean deletedEmail) {
		this.deletedEmail = deletedEmail;
	}
	
	@Override
	public String toString() {
		return "mpItem [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", object=" + object + ", timeStamp=" + timeStamp + ", draftEmail=" + draftEmail + "]";
	}
	

	

}
