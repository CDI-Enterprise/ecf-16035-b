package fr.cdiEnterprise.model;

import java.sql.SQLException;

/**
 * Class for Trainee creation.
 * 
 * @author Claire
 * @see fr.cdiEnterprise.model.User
 * @version 13-10-2016 
 */

public class Trainee extends User {

	//Compulsory first log-in information
	private String trainer;									// AFPA trainer's name

	//Compulsory profile (public/protected) information
	private String surname;									// Trainee's surname, protected profile only
	private String givenName;								// Trainee's given name, protected profile only
	private String sessionCode;								// AFPA code for training session
	private String programmingLanguage;						// Known programming language, one compulsory

	//Optional profile information
	private String graphicAPI;								// Known graphic API
//	private Company approachedCompany;						// Name of company approach by trainee
	private String webSite;									// Website's trainee
	private String linkedIn;								// LinkedIn address profile

	
	/**
	 * Constructs a user with compulsory first log-in informations, except trainer. Test.
	 * 
	 * @param status 
	 * @param alias 
	 * @param email 
	 * @param afpa
	 * @throws SQLException 
	 */
	public Trainee(String status, String alias, String email, String afpa) throws SQLException {
		super(status, alias, email, afpa);
	}
	
	// Constructor test for DB
	public Trainee(int id, String inscriptionDate, String status, String alias, String email, String afpa) {
		super(id, inscriptionDate, status, alias, email, afpa);
	}


	//TODO (Claire) create input control
	/**
	 * Basic trainee description
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return super.toString();
	}
	
	
	/**
	 * @return the trainer
	 */
	public String getTrainer() {
		return trainer;
	}

	/**
	 * @param trainer the trainer to set
	 */
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the sessionCode
	 */
	public String getSessionCode() {
		return sessionCode;
	}

	/**
	 * @param sessionCode the sessionCode to set
	 */
	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

	/**
	 * @return the programmingLanguage
	 */
	public String getProgrammingLanguage() {
		return programmingLanguage;
	}

	/**
	 * @param programmingLanguage the programmingLanguage to set
	 */
	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	/**
	 * @return the graphicAPI
	 */
	public String getGraphicAPI() {
		return graphicAPI;
	}

	/**
	 * @param graphicAPI the graphicAPI to set
	 */
	public void setGraphicAPI(String graphicAPI) {
		this.graphicAPI = graphicAPI;
	}

//	/**
//	 * @return the approachedCompany
//	 */
//	public Company getApproachedCompany() {
//		return approachedCompany;
//	}
//
//	/**
//	 * @param approachedCompany the approachedCompany to set
//	 */
//	public void setApproachedCompany(Company approachedCompany) {
//		this.approachedCompany = approachedCompany;
//	}

	/**
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}

	/**
	 * @param webSite the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	/**
	 * @return the linkedIn
	 */
	public String getLinkedIn() {
		return linkedIn;
	}

	/**
	 * @param linkedIn the linkedIn to set
	 */
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
}