package fr.cdiEnterprise.exceptions;

public class CompanyCreationException extends NullPointerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyCreationException() {
	}

	public CompanyCreationException(String message) {
		super(message);
	}

}