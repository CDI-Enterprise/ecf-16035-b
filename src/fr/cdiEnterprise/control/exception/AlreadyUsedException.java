/**
 * 
 */
package fr.cdiEnterprise.control.exception;

/**
 * This exception can be instantiate when the method isAlreadyUsed() is used.
 * 
 * @author Claire
 * @see fr.cdiEnterprise.util.StringControl
 * @version 25-10-2016
 */
public class AlreadyUsedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyUsedException(String message) {
		super(message);
	}
	
}
