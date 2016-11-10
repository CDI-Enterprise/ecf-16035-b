package fr.cdiEnterprise.model;

import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import fr.cdiEnterprise.control.exception.EmptyFieldException;
import fr.cdiEnterprise.dao.UserDAO;

/**
 * Abstract base class for all User creation: Trainee, FormerTrainee, Trainer.
 * 
 * @author Claire
 * @version 13-10-2016
 * 
 */

public abstract class User {

	//Auto-generated
	// TODO (Claire) get last biggest id in DB to set totalId
	private static int totalId = 0;							// Auto-generated total user's number since the beginning
	private int id;											// Id number for user
	private String inscriptionDate;							// Date of registration for user

	//Compulsory first log-in information
	private String status;									// Three possible choices: Trainee, FormerTrainee, Trainer
	private String alias;									// User's nickname for log-in
	private String email;									// User's email (can be use for log-in)
//	private String password;								// User's password (minimum 8 characters)
	private String afpa;									// Name of the AFPA the user go/went to or work for

	
	/**
	 * Constructs a user with compulsory first log-in informations.
	 * 
	 * @param email
	 * @param alias
	 * @param password
	 * @param status
	 * @param afpa
	 * @throws SQLException 
	 */
	public User(String status, String alias, String email, String afpa) throws SQLException {
		setId();
		setInscriptionDate();
		setStatus(status);
		setAlias(alias);
		setEmail(email);
		setAfpa(afpa);
	}
	
	// Constructor test for DB
	public User(int id, String inscriptionDate, String status, String alias, String email, String afpa) {
		this.id = id;
		this.inscriptionDate = inscriptionDate;
		setStatus(status);
		setAlias(alias);
		setEmail(email);
		setAfpa(afpa);
	}
	
	/**
	 * Basic user description.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Id : " + id + ", inscription : " + inscriptionDate + ", statuts : " + status + ", alias : " + alias
				+ ", email : " + email + ", afpa : " + afpa;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 *Increments totalId of Users and  set the id
	 * @throws SQLException 
	 */
	private void setId() throws SQLException {
		totalId = UserDAO.getBiggerId();
		totalId++;
		this.id = totalId;
	}
	
	/**
	 * @return the inscriptionDate
	 */
	public String getInscriptionDate() {
		return inscriptionDate;
	}

	/**
	 * Generates a date and time from the "Europe/Paris" zone when a User is created.
	 * 
	 * @author Claire
	 * @return inscriptionDate
	 * @since 16-10-2016
	 */
	private void setInscriptionDate() {
		
		DateTimeFormatter formatter;
		ZonedDateTime zdt;
		// TODO (Claire) format time = format time in DB
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		zdt = Instant.now().atZone(ZoneId.of("Europe/Paris"));
		this.inscriptionDate = zdt.format(formatter);
		
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	// TODO (Claire) throws exception up?
	/**
	 * This setter checks the integrity of the string Alias before setting it.
	 * 
	 * @param alias the alias to set
	 * @throws EmptyFieldException 
	 * @version 27-10-2016
	 */
	public void setAlias(String alias) /* throws NoAliasException */  {
//		if (alias.isEmpty()) {
//			throw new NoAliasException("");
//		}
//		else {
			this.alias = alias;
//		}
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the afpa
	 */
	public String getAfpa() {
		return afpa;
	}

	/**
	 * @param afpa the afpa to set
	 */
	public void setAfpa(String afpa) {
		this.afpa = afpa;
	}

	/**
	 * @return the totalId
	 */
	public static int getTotalId() {
		return totalId;
	}
}