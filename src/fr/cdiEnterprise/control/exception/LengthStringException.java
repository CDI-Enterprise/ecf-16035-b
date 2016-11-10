/**
 * 
 */
package fr.cdiEnterprise.control.exception;

/**
 * This exception can be instantiate when the method isLessTwentyChar() is used.
 * 
 * @author Claire
 * @see fr.cdiEnterprise.util.StringControl
 * @version 25-10-2016
 */
public class LengthStringException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LengthStringException() {
		super();
	}
	
	public LengthStringException(String message) {
		super(message);
	}
	
}
