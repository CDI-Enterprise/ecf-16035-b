/**
 * 
 */
package fr.cdiEnterprise.control.util;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.cdiEnterprise.control.exception.AlreadyUsedException;
import fr.cdiEnterprise.control.exception.LengthStringException;
import fr.cdiEnterprise.control.exception.EmptyFieldException;
import fr.cdiEnterprise.dao.UserDAO;

/**
 * Methods for input control.
 * 
 * @author Claire
 * @version 25-10-2016
 */
public class PanelUserCRUDControl {

	static boolean doThrowException;

	/**
	 * This method checks if the length of the String in parameter is equal to 0.
	 * 
	 * @author Claire
	 * @param attribute
	 * @return doThrowException
	 * @throws EmptyFieldException
	 * @version 23-10-2016
	 */
	public static boolean isEmpty(String attribute) throws EmptyFieldException {

		if (attribute.isEmpty()) {
			doThrowException = true;
			throw new EmptyFieldException("Merci de remplir les champs obligatoires.");
		}
		return doThrowException;
	}

	/**
	 * This method checks if the length of the String in parameter is bigger than 20.
	 * 
	 * @author Claire
	 * @param attribute
	 * @param label
	 * @return doThrowException
	 * @throws LengthStringException
	 * @version 25-10-2016
	 */
	public static boolean isLessTwentyChar(String attribute, String label) throws LengthStringException {

		if (attribute.length() > 20) {
			doThrowException = true;
			throw new LengthStringException(label + " doit faire moins de vingt caractères.");
		}
		return doThrowException;
	}

	/**
	 * This method checks if an alias already exists in the table USER_CDI.
	 * 
	 * @param attribute
	 * @return doThrowException
	 * @throws AlreadyUsedException
	 * @throws SQLException
	 * @version 25-10-2016
	 */
	public static boolean isAliasAlreadyUsed(String attribute) throws AlreadyUsedException, SQLException {

		ArrayList<String> aliasList;
		aliasList = UserDAO.getAliasList();

		for (String str : aliasList) {
			if (attribute.equals(str)) {
				doThrowException = true;
				throw new AlreadyUsedException("Désolé, le pseudo " + attribute + " est déjà utilisé.");
			}
		}
		return doThrowException;
	}
}