/**
 * 
 */
package fr.cdiEnterprise.service;

import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import fr.cdiEnterprise.dao.UserDAO;
import fr.cdiEnterprise.model.User;

/**
 * Model table to handle users data.
 * 
 * @author Claire
 * @version 23-10-2016
 */
public class UsersTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Attributes for DB access
	private Users users;
	// Attributes for table
	private String[] columnName; // TODO (Claire) get field name of DB

	
	/**
	 * 
	 * @throws SQLException
	 */
	// FIXME (Claire) refresh Mdl when loading the panel (works only by clicking on "Tous" JButton for now!
	public UsersTableModel() throws SQLException {
		super();
		columnName = new String[] {"ID", "Statut", "Nom d'utilisateur", "Date d'inscription", "AFPA"};
		
		users = UserDAO.getUsersList();
	}

	public UsersTableModel(Users users) {
		super();
		columnName = new String[] {"ID", "Statut", "Nom d'utilisateur", "Date d'inscription", "AFPA"};
		
		this.users = users;
		refresh(users);
	}

	
	@Override
	public int getColumnCount() {
		return columnName.length;
	}
	@Override
	public String getColumnName(int columnIndex) {
		return columnName[columnIndex];
	}

	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0:
			return users.get(rowIndex).getId();
		case 1:
			return users.get(rowIndex).getStatus();
		case 2:
			return users.get(rowIndex).getAlias();
		case 3:
			return users.get(rowIndex).getInscriptionDate();
		case 4:
			return users.get(rowIndex).getAfpa();
		default:
			return null;
		}
	}

	
	/**
	 * This method remove all elements from the UsersTableModel and add the new ones from the given Users array list.
	 * 
	 * @author Claire
	 * @param users
	 * @version 24-10-2016
	 */
	public void refresh(Users users) {	
		this.removeAll();		
		for (User user : users) {
			this.addUser(user);
		}
	}

	/**
	 * This method add a User in the model by calling the add() method of the Users array list.
	 * 
	 * @author Claire
	 * @param user
	 * @version 24-10-2016
	 */
	public void addUser(User user) {
		users.add(user);
		fireTableRowsInserted(users.size() -1, users.size() -1);
	}

	// A TESTER
	public void removeUser(int rowIndex) {
		users.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	/**
	 * This method remove all rows from the model by calling the removeAll() method of the Users array list.
	 * 
	 * @author Claire
	 * @param user
	 * @version 24-10-2016
	 */
	public void removeAll() {
		users.removeAll(users);
		fireTableDataChanged();
	}
}