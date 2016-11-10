package fr.cdiEnterprise.model;

/**
 * Class for company's contact creation.
 * 
 * @author Anaïs
 * @version 23-10-2016
 *
 */

public class Contact {

	private int idContact;
	private String name;
	private String phoneNumber;
	private String email;

	public Contact() {

	}

	public Contact(int idContact, String name, String phoneNumber, String email) {
		this.idContact = idContact;
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
		this.setEmail(email);
	}

	public Contact(String name, String phoneNumber, String email) {
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
		this.setEmail(email);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the idContact
	 */
	public int getIdContact() {
		return idContact;
	}

	/**
	 * @param idContact
	 *            the idContact to set
	 */
	public void setIdContact(int idContact) {
		this.idContact = idContact;
	}

	@Override
	public String toString() {
		return "Contact: " + name + phoneNumber + email;
	}

}
