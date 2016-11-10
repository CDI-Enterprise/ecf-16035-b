package fr.cdiEnterprise.model;

import java.sql.SQLException;

/**
 * Class for FormerTrainee creation.
 * 
 * @author Claire
 * @see fr.cdiEnterprise.model.User, fr.cdiEnterprise.model.Trainee
 * @version 13-10-2016
 */

public class FormerTrainee extends Trainee {

	//Compulsory profile (public/protected) information (approacheCompany from super-class compulsory here)
	private String currentProfession;						// Actual occupation, job-seeker possible
	//	private Company currentCompany;							// Actual company where former trainee works, none possible
	//	private Company formerCompany;							// Former companies where former trainee worked, none possible


	// Constructor test for DB
	public FormerTrainee(int id, String inscriptionDate, String status, String alias, String email, String afpa) {
		super(id, inscriptionDate, status, alias, email, afpa);
	}

	/**
	 * Constructs a FormerTrainee with User informations. For test.
	 * 
	 * @param email
	 * @param alias
	 * @param status
	 * @param afpa
	 * @throws SQLException 
	 */
	public FormerTrainee(String status, String alias, String email, String afpa) throws SQLException {
		super(status, alias, email, afpa);
	}


	//TODO (Claire) create input control

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "FormerTrainee [currentProfession=" + currentProfession + "]";
	}


	/**
	 * @return the currentProfession
	 */
	public String getCurrentProfession() {
		return currentProfession;
	}

	/**
	 * @param currentProfession the currentProfession to set
	 */
	public void setCurrentProfession(String currentProfession) {
		this.currentProfession = currentProfession;
	}

	//	/**
	//	 * @return the currentCompany
	//	 */
	//	public Company getCurrentCompany() {
	//		return currentCompany;
	//	}
	//
	//	/**
	//	 * @param currentCompany the currentCompany to set
	//	 */
	//	public void setCurrentCompany(Company currentCompany) {
	//		this.currentCompany = currentCompany;
	//	}
	//
	//	/**
	//	 * @return the formerCompany
	//	 */
	//	public Company getFormerCompany() {
	//		return formerCompany;
	//	}
	//
	//	/**
	//	 * @param formerCompany the formerCompany to set
	//	 */
	//	public void setFormerCompany(Company formerCompany) {
	//		this.formerCompany = formerCompany;
	//	}

}
