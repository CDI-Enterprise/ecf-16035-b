package fr.cdiEnterprise.exceptions;

/**
 * Project: A00805231Lab3
 * File: ApplicationException.java
 * Date: Oct 4, 2015
 * Time: 9:43:27 AM
 */

@SuppressWarnings("serial")
/**
 * @author : A00805231 / Nicolas Tarral
 * @version : 07/10/2015
 *
 */

public class CustomMessagingException extends Exception {

	private int size;
	private int incorrectSize;
	private String message;

	public CustomMessagingException() {
		super();
		
	}

	public CustomMessagingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public CustomMessagingException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CustomMessagingException(String message) {
		super(message);
		this.message = message;

	}

	public CustomMessagingException(Throwable cause) {
		super(cause);
	
	}

	/**
	 * THis is an overloaded constructor used to create message about the number of elements and the max number of element in an array.
	 * 
	 * @param msg1
	 *            is the proper size of the array
	 * @param msg2
	 *            this is the incorrect array size.
	 */
	public CustomMessagingException(int msg1, int msg2) {
		super("correct " + msg1 + " incorrect " + msg2);
		this.size = msg1;
		this.incorrectSize = msg2;
		
	}

	/**
	 * this method is going to return a String with the expected size and incorrect size of the array only if the specific constructor has been called.
	 * the second choice of that method will be to display a sentence if an invalid email address has been provided on one of the array elements.
	 */
	public String getMessage() {
		
		return "l'Erreur suivante c'est produite :"+this.message + " pas de données trouvés";
//		if (this.size > 0 && this.incorrectSize > 0) {
//			return "Expected " + this.size + " elements but got " + this.incorrectSize;
//		} else {
//			return this.message + " is an invalid email address";
//		}

	}

}
