/**
 * 
 */
package fr.cdiEnterprise.control.exception;

/**
 * This exception can be instantiate when the method isZeroLengthString() is used.
 * 
 * @author Claire
 * @see fr.cdiEnterprise.util.StringControl
 * @version 23-10-2016
 */
public class EmptyFieldException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyFieldException(String message) {
		super(message);
	}

}
