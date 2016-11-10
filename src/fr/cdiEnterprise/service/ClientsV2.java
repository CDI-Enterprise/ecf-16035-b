/**
 * 
 */
package fr.cdiEnterprise.service;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.cdiEnterprise.model.Item;
import fr.cdiEnterprise.util.MpClientV2;

/**
 * Class is going to hold all the Users that are registered on the Messaging.
 * This class is going to support the Database feature. Server.
 * 
 * @author nicolas Tarral
 * @version 21-10-2016
 */
public class ClientsV2 extends ArrayList<MpClientV2> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int CONST_ZERO = 0;
	private static final int CONST_ONE = 1;
	private static final int CONST_TWO = 2;
	private static final int COL_SIZE = 3;

	/**
	 * This method is going to provide the name of the box that may be in the
	 * box containers
	 * 
	 * @param box
	 * @return the box.
	 */
	public MpClientV2 getClient(String box) {
		MpClientV2 theClient = null;
		for (MpClientV2 current : this) {
			if (current.getBox().equals(box)) {
				theClient = current;
			}
		}
		return theClient;
	}

	/**
	 * This method is going to provide an Array of all the message for a
	 * particular user.
	 * 
	 * @param box
	 *            this is to identify the User message box.
	 * @param draft
	 *            indicate if we need the draft folder
	 * @return an array of message.
	 * @throws SQLException
	 */


}
