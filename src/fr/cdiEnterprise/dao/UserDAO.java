/**
 * 
 */
package fr.cdiEnterprise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.cdiEnterprise.model.FormerTrainee;
import fr.cdiEnterprise.model.Trainee;
import fr.cdiEnterprise.model.Trainer;
import fr.cdiEnterprise.model.User;
import fr.cdiEnterprise.service.Users;

/**
 * Handle the basic SQL request for the CDI_USER table in database.
 * 
 * @author Claire
 * @version 24-10-2016
 */
public class UserDAO {

	private Connection connect;
	JFrame popUpFrame;

	// Prepared statement for basic SQL request
	private static PreparedStatement searchUser;
	private static PreparedStatement createUser;
	private static PreparedStatement readUsers;
	private static PreparedStatement updateUser;
	private static PreparedStatement deleteUser;
	// Prepared statement for specific SQL request
	private static PreparedStatement readAlias;

	private static int result;
	private static ResultSet requestRes;

	private static User user;
	private static Users users;
	private static int userId;
	private static String userInscriptionDate;
	private static String userStatus;
	private static String userAlias;	
	private static String userMail;
	private static String userAfpa;

	// TODO (Claire) class DTO which gonna instantiate a UserDAO object.

	/**
	 * Asks for the connection to DB.
	 */
	public UserDAO() {
		this.connect = DBConnection.getConnect();
	}

	/**
	 * Search an user by Id from database and displays it.
	 * 
	 * @author Claire
	 * @param userId
	 * @return user
	 * @throws SQLException
	 * @version 22-10-2016
	 */
	protected User search(int userId) throws SQLException {

		try {
			searchUser = connect.prepareStatement("SELECT user_id, user_inscription_date, "
					+ "user_status, user_alias, user_mail, user_afpa "
					+ "FROM cdi_user "
					+ "WHERE user_id = ?");
			searchUser.setInt(1, userId);
			requestRes = searchUser.executeQuery();

			while(requestRes.next()){
				userInscriptionDate = requestRes.getString(2);
				userStatus = requestRes.getString(3);
				userAlias = requestRes.getString(4);
				userMail = requestRes.getString(5);
				userAfpa = requestRes.getString(6);

				switch (userStatus) {
				case "Stagiaire" :
					user = new Trainee(userId, userInscriptionDate, userStatus, userAlias, userMail, userAfpa);
					break;

				case "Ancien" :
					user = new FormerTrainee(userId, userInscriptionDate, userStatus, userAlias, userMail, userAfpa);
					break;

				case "Formateur" :
					user = new Trainer(userId, userInscriptionDate, userStatus, userAlias, userMail, userAfpa);
					break;

				default:
					System.out.println("Aucun utilisateur à afficher.");
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Requête incorrecte : aucun auteur n'a pu être affiché.");
		}

		finally {
			closeRequest(searchUser);
		}

		return user;
	}

	/**
	 * Search an user by status from database and displays it.
	 * 
	 * @author Claire
	 * @param userStatus
	 * @return user
	 * @version 22-10-2016
	 */
	protected Users search(String userStatus) throws SQLException {

		users = new Users();

		try {
			searchUser = connect.prepareStatement("SELECT user_id, user_inscription_date, "
					+ "user_status, user_alias, user_mail, user_afpa "
					+ "FROM cdi_user "
					+ "WHERE user_status = ?");
			searchUser.setString(1, userStatus);
			requestRes = searchUser.executeQuery();

			while(requestRes.next()){
				userId = requestRes.getInt(1);
				userInscriptionDate = requestRes.getString(2);
				userStatus = requestRes.getString(3);
				userAlias = requestRes.getString(4);
				userMail = requestRes.getString(5);
				userAfpa = requestRes.getString(6);

				switch (userStatus) {
				case "Stagiaire" :
					user = new Trainee(userId, userInscriptionDate, userStatus, userAlias, userMail, userAfpa);
					users.add(user);
					break;

				case "Ancien" :
					user = new FormerTrainee(userId, userInscriptionDate, userStatus, userAlias, userMail, userAfpa);
					users.add(user);
					break;

				case "Formateur" :
					user = new Trainer(userId, userInscriptionDate, userStatus, userAlias, userMail, userAfpa);
					users.add(user);
					break;

				default:
					System.out.println("Aucun utilisateur à afficher.");
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Requête incorrecte : aucun auteur n'a pu être affiché.");
		}

		finally {
			closeRequest(searchUser);
		}

		return users;
	}

	/**
	 * Insert a new user in database.
	 * 
	 * @author Claire
	 * @param id
	 * @param inscriptionDate
	 * @param status
	 * @param alias
	 * @param mail
	 * @param afpa
	 * @return creationDone (test code)
	 * @throws SQLException
	 * @version 22-10-2016
	 */
	protected int create(int id, String inscriptionDate,
			String status, String alias, String mail, String afpa) throws SQLException {

		result = 0;

		try {
			createUser = connect.prepareStatement("INSERT INTO cdi_user VALUES (?, ?, ?, ?, ?, ?)");
			createUser.setInt(1, id);
			createUser.setString(2, inscriptionDate);
			createUser.setString(3, status);
			createUser.setString(4, alias);
			createUser.setString(5, mail);
			createUser.setString(6, afpa);
			result = createUser.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Requête incorrecte : l'utilisateur n'a pas pu être créé.");
		}

		finally {
			closeRequest(createUser);
		}
		return result;
	}

	/**
	 * Read all users from database.
	 * 
	 * @author Claire
	 * @return users
	 * @throws SQLException
	 * @version 22-10-2016
	 */
	protected Users read() throws SQLException {

		users = new Users();

		try {
			readUsers = connect.prepareStatement("SELECT user_id, user_inscription_date, "
					+ "user_status, user_alias, user_mail, user_afpa "
					+ "FROM cdi_user");
			requestRes = readUsers.executeQuery();

			while(requestRes.next()){
				userId = requestRes.getInt(1);
				userInscriptionDate = requestRes.getString(2);
				userStatus = requestRes.getString(3);
				userAlias = requestRes.getString(4);
				userMail = requestRes.getString(5);
				userAfpa = requestRes.getString(6);

				switch (userStatus) {
				case "Stagiaire" :
					user = new Trainee(userId, userInscriptionDate, userStatus, userAlias, userMail, userAfpa);
					users.add(user);
					break;

				case "Ancien" :
					user = new FormerTrainee(userId, userInscriptionDate, userStatus, userAlias, userMail, userAfpa);
					users.add(user);
					break;

				case "Formateur" :
					user = new Trainer(userId, userInscriptionDate, userStatus, userAlias, userMail, userAfpa);
					users.add(user);
					break;

				default:
					System.out.println("Aucun utilisateur à afficher.");
					break;
				}
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Requête incorrecte : la liste des auteurs n'a pu être affichée.");
		}

		finally {
			closeRequest(readUsers);
		}

		return users;
	}

	
	// A TESTER
	protected ArrayList<Integer> readId() throws SQLException {
		
		ArrayList<Integer> idList = new ArrayList<Integer>();
		Integer userId;
		
		try {
			PreparedStatement readId = connect.prepareStatement("SELECT user_id FROM cdi_user");
			requestRes = readId.executeQuery();
			
			while(requestRes.next()) {
				userId = requestRes.getInt(1);
				idList.add(userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Requête incorrecte : la liste d'ID n'a pas pu être affichée.");
		}
		return idList;	
	}
	
	/**
	 * Read users' alias from database.
	 * 
	 * @author Claire
	 * @return aliasList
	 * @throws SQLException
	 * @version 25-10-2016
	 */
	protected ArrayList<String> readAlias() throws SQLException {

		ArrayList<String> aliasList = new ArrayList<String>();

		try {
			readAlias = connect.prepareStatement("SELECT user_alias FROM cdi_user");
			requestRes = readAlias.executeQuery();

			while(requestRes.next()){
				userAlias = requestRes.getString(1);
				aliasList.add(userAlias);
			}	

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Requête incorrecte : la liste des auteurs n'a pu être affichée.");
		}

		finally {
			closeRequest(readAlias);
		}

		return aliasList;
	}

	/**
	 * Updates an user in database.
	 * 
	 * @author Claire
	 * @param id
	 * @param inscriptionDate
	 * @param status
	 * @param alias
	 * @param mail
	 * @param afpa
	 * @return updateDone (test code)
	 * @throws SQLException
	 * @version 22-10-2016
	 */
	protected int update(int id, String status, String mail) throws SQLException {

		result = 0;

		try {
			updateUser = connect.prepareStatement("UPDATE cdi_user "
					+ "SET user_status = ?, user_mail = ? "
					+ "WHERE user_id = ?");
			updateUser.setString(1, status);
			updateUser.setString(2, mail);
			updateUser.setInt(3,id);
			result = updateUser.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Requête incorrecte : l'utilisateur n'a pas pu être modifié.");
		}

		finally {
			closeRequest(updateUser);
		}
		return result;
	}


	/**
	 * Deletes an user in database.
	 * 
	 * @author Claire
	 * @param id
	 * @return deleteDone (test code)
	 * @throws SQLException
	 * @version 22-10-2016
	 */
	protected int delete(int id) throws SQLException {

		result = 0;

		try {
			deleteUser = connect.prepareStatement("DELETE FROM cdi_user WHERE user_id = ?");
			deleteUser.setInt(1,id);
			result = deleteUser.executeUpdate();
		} catch (SQLException e) {
			//			e.printStackTrace();
			System.err.println("Requête incorrecte : l'utilisateur n'a pas pu être supprimé.");
		}

		finally {
			closeRequest(deleteUser);
		}
		return result;
	}

	/**
	 * Closes request and automatically result of request if there's one.
	 * 
	 * @author Claire
	 * @param prepStatmt
	 * @return a string (test code)
	 * @throws SQLException
	 * @version 22-10-2016
	 */
	private void closeRequest(PreparedStatement prepStatmt) throws SQLException {
		try {
			connect.commit();
		} catch (NullPointerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(popUpFrame, "Oups, l'application ne peut pas se lancer. "
					+ "Sa base de données est inaccessible.");
		}

		if (prepStatmt != null) {
			prepStatmt.close();
			// Test code
			boolean request = false;
			request = prepStatmt.isClosed();
			System.out.println("Statement is closed: " + request);
			if (requestRes != null) {
				boolean result = false;
				result = requestRes.isClosed();
				System.out.println("Result of request is closed: " + result);
			}
			// Fin test code
		}	
	}


/*--------------------------------------------------------------------------------------------------------------------------*/	
	// TODO (Claire) In DTO Static or not static, that is the question!
	/**
	 * Public method to initiate a SELECT WHERE SQL request, calling the UserDAO.search(String) protected method.
	 * 
	 * @author Claire
	 * @param userStatut
	 * @return users
	 * @throws SQLException
	 * @version 23-10-2016
	 */
	public static Users getUsersByStatusList(String userStatut) throws SQLException {
		UserDAO userDAO = new UserDAO();
		Users users = userDAO.search(userStatut);
		return users;
	}

	/**
	 * Public method to initiate a CREATE SQL request, calling the UserDAO.create protected method.
	 * 
	 * @author Claire
	 * @param user
	 * @return creationDone
	 * @throws SQLException
	 * @version 24-10-2016
	 */
	public static String createUser(User user) throws SQLException {

		int id = user.getId();
		String inscriptionDate = user.getInscriptionDate();
		String status = user.getStatus();
		String alias = user.getAlias();
		String mail = user.getEmail();
		String afpa = user.getAfpa();

		UserDAO userDAO = new UserDAO();

		// int for test code
		int result = userDAO.create(id, inscriptionDate, status, alias, mail, afpa);

		// Test code
		String creationDone = null;

		if (result == 0) {
			creationDone = "Aucune création effectuée.";
		}
		else {
			creationDone = result + " création(s) effectuée(s).";
		}

		return creationDone;
		// Fin test code
	}

	/**
	 * Public method to initiate a SELECT SQL request, calling the UserDAO.read() protected method.
	 * 
	 * @author Claire
	 * @return users
	 * @throws SQLException
	 * @version 22-10-2016
	 */
	public static Users getUsersList() throws SQLException {

		UserDAO userDAO = new UserDAO();

		Users users = userDAO.read();
		return users;
	}

	/**
	 * Public method to initiate an UPDATE SQL request, calling the UserDAO.update() protected method.
	 * 
	 * @author Claire
	 * @param user
	 * @return updateDone
	 * @throws SQLException 
	 * @version 25-10-2016
	 */
	public static String updateUser(User selectedUser) throws SQLException {

		int id = selectedUser.getId();
		String status = selectedUser.getStatus();
		String mail = selectedUser.getEmail();

		UserDAO userDAO = new UserDAO();

		// int for test code
		int result = userDAO.update(id, status, mail);

		// Test code
		String updateDone = null;

		if (result == 0) {
			updateDone = "Aucune mise à jour effectuée."; 
		}
		else {
			updateDone = result + " mise(s) à jour effectuée(s).";
		}

		return updateDone;
		// Fin test code
	}

	/**
	 * Public method to initiate a DELETE SQL request, calling the UserDAO.delete() protected method.
	 * 
	 * @author Claire
	 * @param id
	 * @return deleteDone
	 * @throws SQLException
	 * @version 24-10-2016
	 */
	public static String deleteUser(int id) throws SQLException {

		UserDAO userDAO = new UserDAO();

		// int for test code
		int result = userDAO.delete(id);

		// Test code
		String deleteDone = null;

		if (result == 0) {
			deleteDone = "Aucune suppression effecutée."; // Test code
		}
		else {
			deleteDone = result + " suppression(s) effectuée(s)."; // Test code
		}

		return deleteDone;
		// Fin test code
	}
	
	// More methods
	// A TESTER
	public static int getBiggerId() throws SQLException {
		
		UserDAO userDAO = new UserDAO();
		
		ArrayList<Integer> idList = new ArrayList<Integer>();
		int id = 0;
		
		idList = userDAO.readId();
		
		for (int nb : idList) {
			if (nb > id) {
				id = nb;
			}
		}
		
		return id;
	}
	
	/**
	 * This method calls the protected method readAlias() from UserDAO to initiate a SELECT SQL request.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> getAliasList() throws SQLException {
		
		UserDAO userDAO = new UserDAO();
		
		ArrayList<String> aliasList = new ArrayList<String>();
		aliasList = userDAO.readAlias();
		return aliasList;
	}
}
